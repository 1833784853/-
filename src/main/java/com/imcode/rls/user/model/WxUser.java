package com.imcode.rls.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信服务器返回的用户信息封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUser {

	/**
	 * 微信扫码登录后台返回的是json数据
	 * {
		"openid":"OPENID",
		"nickname":"NICKNAME",
		"sex":1,
		"province":"PROVINCE",
		"city":"CITY",
		"country":"COUNTRY",
		"headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
		"privilege":[
		"PRIVILEGE1",
		"PRIVILEGE2"
		],
		"unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
		}
	 */

	// 用户的唯一标识
	private String openid;
	// 用户的昵称
	private String nickname;
	// 用户的性别
	private String sex;
	// 用户所在的省份
	private String province;
	// 用户所在的城市
	private String city;
	// 用户所在的国家
	private String country;
	// 用户的头像URL地址
	private String headimgurl;

}
