package com.imcode.rls.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxToken {
//{
//"access_token":"ACCESS_TOKEN",
//"expires_in":7200,
//"refresh_token":"REFRESH_TOKEN",
//"openid":"OPENID",
//"scope":"SCOPE",
//"unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//}

    private String access_token; //接口调用凭证

    private String expires_in; //access_token接口调用凭证超时时间，单位（秒）

    private String refresh_token; //用户刷新access_token

    private String openid; //授权用户唯一标识

    private String scope; //用户授权的作用域，使用逗号（,）分隔

    private String unionid; //当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。

}
