package com.imcode.rls.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.common.model.CacheMap;
import com.imcode.common.model.JwtUtils;
import com.imcode.common.model.R;
import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.user.model.WxUser;
import com.imcode.rls.user.service.IUserLoginService;
import com.imcode.rls.user.service.IWxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class LoginController {

    @Autowired
    private IUserLoginService userLoginServiceImpl;


    @Autowired
    private IWxLoginService wxLoginServiceImpl;

    @PostMapping("/login")
    @ResponseBody
    public R login(@RequestBody Map<String, String> map) throws JsonProcessingException {
        R json = R.ok();
        json.put("msg", "登录失败");
        json.put("data", "用户名或手机号或密码不正确");
        String username = map.get("username");
        String password = map.get("password");
        boolean flag = false;
        if (username != null && password != null) {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            Loginregister user = userLoginServiceImpl.getLoginregisterByNameAndPwd(username, password);

            if (user != null) flag = true;
            else if ((user = userLoginServiceImpl.getLoginregisterByPhoneAndPwd(username, password)) != null)
                flag = true;
            if (flag) {
                ObjectMapper jsonParse = new ObjectMapper();
                final String jwt = JwtUtils.createJWT(user.getUserId(), jsonParse.writeValueAsString(user), JwtUtils.TTLMILLIS);// 签发令牌
                json.put("msg", "登录成功");
                json.put("data", new HashMap<String, Object>() {{
                    put("token", jwt);
                }});
            }
        }

        return json;
    }

    /**
     * 校验是否已经登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/login/checked")
    @ResponseBody
    public R loginChecked(HttpServletRequest request) {
        R json = R.ok();
        String token = request.getHeader("token");
        if (JwtUtils.validateJWT(token)) {
            json.put("msg", "已登录");
        } else {
            json.put("msg", "未登录");
        }
        return json;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */

    @RequestMapping("/login/getUser")
    @ResponseBody
    public R getUser(HttpServletRequest request) throws Exception {
        R json = R.ok();
        HashMap<String, Object> map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        String token = request.getHeader("token");
        if (token != null) {
            Loginregister loginregister = objectMapper.readValue((String) JwtUtils.parseJWT(token).get("sub"), Loginregister.class);
            map.put("username", loginregister.getUserName());
            map.put("userType", loginregister.getUserType());
            map.put("userID", loginregister.getUserId());
            json.put("data", map);
        }
        return json;
    }

    /**
     * 发送验证码
     *
     * @param code
     * @return
     */
    @PostMapping("/sendCode")
    @ResponseBody
    public R sendCode(String code) {
        R json = R.ok();
        CacheMap<Object, Object> codeMap = CacheMap.getInstance();
        if (codeMap.get(code) != null) {
            json = R.error("验证码已发送，请一分钟后再尝试重新发送验证码");
        }
        return json;
    }


    /**
     * 检验用户名是否被注册使用
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/checkUserIslive")
    @ResponseBody
    public R checkUserIslive(String username) {
        System.out.println(username);
        R json = R.ok();
        if (username == null || username.equals("")) {
            json.put("msg", "请输入用户名");
        } else if (!username.matches("^[\u4e00-\u9fa5\\w]{3,16}$")) {
            json.put("msg", "用户名只能是中文、字母、数字、下划线组成的3-16个字符！！");
        } else {
            Loginregister user = userLoginServiceImpl.getLoginregisterByName(username);
            if (user != null) {
                json.put("msg", "已注册");
            } else {
                json.put("msg", "未注册");
            }
        }
        return json;
    }

    @GetMapping("/getCode")
    @ResponseBody
    public R getCode(String phone, HttpSession session) {
        R json = R.ok();
        CacheMap<Object, Object> cacheMap = CacheMap.getInstance();
        String template = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        String code = "";
        for (int i = 0; i < 4; i++) {
            int randomNum = (int) Math.floor(Math.random() * template.length());
            code += template.charAt(randomNum);
        }
        cacheMap.put(session.getId(), code);
        final String finalCode = code;
        json.put("data", new HashMap<Object, Object>() {{
            put("code", finalCode);
        }});
        return json;
    }

    /**
     * 注册接口
     *
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public R register(String username, String password, String repassword, String phone, String code, HttpSession session) {
        return userLoginServiceImpl.registerUser(username, password, repassword, phone, code, session);
    }


    @GetMapping("/getWxUserInfo")
    public R getWxUserInfo(@RequestParam("openid") String openid) {
        if (openid == null || openid.equals("")) {
            return R.error("缺少必要参数");
        } else {
            WxUser wxUser = wxLoginServiceImpl.getWxUser(openid);

            if (wxUser == null) return R.error("错误的参数");
            else {
                return R.ok("请求成功").put("data", wxUser);
            }
        }
    }

    /**
     * 绑定微信账号
     *
     * @return
     */
    @PostMapping("/login/bindAccount")
    public R bindAccount(@RequestBody Map<String, String> data) {
        if (data == null) return R.error("缺少必要参数");
        else {
            String username = data.get("username");
            String password = data.get("password");
            String openid = data.get("openid");
            if (openid == null || openid.equals("")) return R.error("缺少必要参数");
            if (username == null || password == null || username.equals("") || password.equals(""))
                return R.error("请输入账号或密码");
            if (wxLoginServiceImpl.getWxUser(openid) == null) {
                return R.error("未找到openid");
            }
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            Loginregister user = userLoginServiceImpl.getLoginregisterByNameAndPwd(username,password);
            boolean flag = false;
            if (user != null) flag = true;
            else if ((user = userLoginServiceImpl.getLoginregisterByPhoneAndPwd(username,password))!=null) flag = true;
            if (flag) {
                 if (userLoginServiceImpl.updateBindWxOpenid(openid, user.getUserId())) {
                    try {
                        user = userLoginServiceImpl.getLoginregisterByName(username);
                        ObjectMapper jsonParse = new ObjectMapper();
                        String jwt = JwtUtils.createJWT(user.getUserId(), jsonParse.writeValueAsString(user), JwtUtils.TTLMILLIS);// 签发令牌
                        return R.ok("绑定成功").put("token", jwt);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return R.error("绑定失败");
                    }
                } else {
                    return R.error("绑定失败");
                }
            }
            return R.error("账号或密码错误");
        }
    }



    @PostMapping("/login/wxRegisterAndBind")
    public R wxRegisterAndBind(@RequestBody Map<String,String> data ,HttpSession session) {
        if (data == null) return R.error("缺少必要参数");
        String phone = data.get("phone");
        String password = data.get("password");
        String repassword = data.get("repassword");
        String code = data.get("code");
        String openid = data.get("openid");
        return userLoginServiceImpl.wxRegisterAndBind(phone, password, repassword, code, openid, session);
    }

    @PostMapping("/registerUserInfo")
    public R registerUserInfo(@RequestBody Map<String,Object> data) {
        return userLoginServiceImpl.registerUserInfo(data);
    }
}
