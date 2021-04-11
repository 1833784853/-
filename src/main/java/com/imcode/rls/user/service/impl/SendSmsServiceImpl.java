package com.imcode.rls.user.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.imcode.rls.user.service.SendSmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendSmsServiceImpl implements SendSmsService {

//    @Value("${access.key}")
//    private String accessKey;
//
//    @Value("${access.secret}")
//    private String accessSecret;

    public boolean sendSms(String phome, String template, Map<String, Object> code) {

//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
//
//        // 手机号、验证码、模板
//        request.putQueryParameter("PhoneNumbers", phome); // 手机号
//        request.putQueryParameter("SignName", "阿里云通信"); // 签名
//        request.putQueryParameter("TemplateParam", template); // 签名
//        request.putQueryParameter("TemplateParam", JSONUtils.toJSONString(code)); // 验证码
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            return response.getHttpResponse().isSuccess();
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

        return false;
    }
}
