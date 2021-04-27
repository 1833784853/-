package com.imcode.rls.roomapply.controller;

import com.imcode.common.model.R;
import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.roomapply.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplyController {
    @Autowired
    ApplyService applyServiceimpl;
    /*
    管理者查询全部看房申请列表
     */
    @GetMapping("/Admin/getApplylist")
    public R getApplyListA() {
        R json = R.ok();
        List<Apply> applyList = applyServiceimpl.selectAll();
        json.put("msg", "请求成功");
        json.put("data", applyList);
        return json;
    }

    /*
    管理者查询全部看房申请列表
     */
    @GetMapping("/Admin/getlistAll")
    public R getlistAll(final String currentPage, final String pageSize) {
        R json = R.ok();
        List<Apply> applyList = applyServiceimpl.selectApplylist(new HashMap<String,Object>(){{
            put("currentPage",Integer.parseInt(currentPage)*Integer.parseInt(pageSize));
            put("pageSize",Integer.parseInt(pageSize));
        }});
        json.put("msg", "请求成功");
        json.put("data", applyList).put("totalPage", applyServiceimpl.selectAll().size());
        return json;
    }

    /*
    管理员根据看房申请状态查询
    0：表示该房没有被申请  1：同意申请看房   2：拒绝申请看房     3：表示该房被同意申请   4：表示该放被拒绝申请
    5：表示该房可以被申请退房 6：正在申请退房  7：表示该房被同意申请退房  8：表示该房被拒绝申请退房
     */
    @GetMapping("/Admin/getApplylistID")
    public R getApplyListIDA(int applyStatus) {
        R json = R.ok();
        List<Apply> applyList = applyServiceimpl.selectid(applyStatus);
        json.put("msg", "请求成功");
        json.put("data", applyList);
        return json;
    }

    /*
    租客根据自己的id查询
     */
    @GetMapping("/Tenant/getApplylistUserid")
    public R getApplylistUseridT(final String userID, final String currentPage, final String pageSize) {
        R json = R.ok();
        List<Apply> applyList = applyServiceimpl.selectidTenant(new HashMap<String,Object>(){{
            put("userID",userID);
            put("currentPage",Integer.parseInt(currentPage)*Integer.parseInt(pageSize));
            put("pageSize",Integer.parseInt(pageSize));
        }});
        json.put("msg", "请求成功");
        json.put("data", applyList).put("totalPage", applyServiceimpl.countUserApplySee(userID));
        return json;
    }
    /*
    管理员修改看房申请状态
     0：表示该房没有被申请  1：同意申请看房   2：拒绝申请看房     3：表示该房被同意申请   4：表示该放被拒绝申请
    5：表示该房可以被申请退房 6：正在申请退房  7：表示该房被同意申请退房  8：表示该房被拒绝申请退房
     */
    @PostMapping("/Admin/editApply")
    public R editApply(@RequestBody Map<String,Object> applyStatus){
        R json;
        boolean result=applyServiceimpl.update(applyStatus);
        if(result){
            json=R.ok();//更新成功
            json.put("msg","更新成功");
        }else {
            json=R.error("更新失败");//更新失败
        }
        return json;
    }

    /*
    管理员批量同意
     */
    @PostMapping("/Admin/editAgreebatch")
    public R editAgreebatchA(@RequestBody Map<String,Object> applyID){
        List<Integer> applyStatus1=(List<Integer>)applyID.get("applyID");
        R json;
        boolean result=applyServiceimpl.updateAgreeList(applyStatus1);
        if(result){
            json=R.ok();//更新成功
            json.put("msg","更新成功");
        }else {
            json=R.error("更新失败");//更新失败
        }
        return json;
    }

    /*
    管理员批量拒绝
     */
    @PostMapping("/Admin/editRefusebatch")
    public R editRefusebatchA(@RequestBody Map<String,Object> applyID){
        List<Integer> applyStatus1=(List<Integer>)applyID.get("applyID");
        R json;
        boolean result=applyServiceimpl.updateRefuseList(applyStatus1);
        if(result){
            json=R.ok();//更新成功
            json.put("msg","更新成功");
        }else {
            json=R.error("更新失败");//更新失败
        }
        return json;
    }


    /*
    管理员根据applyID删除
     */
    @PostMapping("/Admin/deleteApply")
    public R deleteApplyA(@RequestBody Map<String,Integer> applyID){
        R json;
        boolean flag=applyServiceimpl.delete(applyID.get("applyID"));
        if(flag){
            json=R.ok().put("msg","删除成功！");
        }else {
            json=R.error("删除失败！");
        }
        return json;
    }
    /*
    租客申请看房
     */

    @PostMapping("/Tenant/roomapply")
    public R addDaily(@RequestBody Map<String,String> roomapply){
        if(roomapply!=null){
            String userID=roomapply.get("userID");
            String roomNO=roomapply.get("roomNO");
            return applyServiceimpl.add(roomapply);
        }else {
            return R.error("添加失败");
        }
    }
    /*
    租客删除已退租信息
     */

    @PostMapping("/Tenant/deleteApply")
    public R deleteApply(@RequestBody Map<String,Integer> applyID){
        R json;
        boolean flag=applyServiceimpl.delete(applyID.get("applyID"));
        if(flag){
            json=R.ok().put("msg","删除成功！");
        }else {
            json=R.error("删除失败！");
        }
        return json;
    }
    /*
    租客根据用户id和房屋编码查询状态为0：表示该房没有被申请
     */
    @GetMapping("/Tenant/getApplyByIdNo")
    public R getApplyByIdNoT(String userID,String roomNO) {
        R json = R.ok();
        List<Apply> applyList =applyServiceimpl.selectByIdNo(userID,roomNO);
        json.put("msg", applyList.size()>0?"已申请":"未申请");
        return json;
    }
    /*
    根据applyID批量删除
     */
    @PostMapping("/Tenant/deletebatch")
    public R deletebatch(@RequestBody Map<String,Object> applyID){
        List<Integer> applyids=(List<Integer>)applyID.get("applyID");
        R json;
        boolean flag=applyServiceimpl.deleteList(applyids);
        if(flag){
           json=R.ok().put("msg","删除成功！");
        }else {
            json=R.error("删除失败！");
        }
        return json;
    }

}
