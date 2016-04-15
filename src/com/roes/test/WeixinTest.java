package com.roes.test;

import com.rose.po.AccessToken;
import com.rose.po.UserInfo;
import com.rose.util.QRTicket;
import com.rose.util.WeixinUtil;

import net.sf.json.JSONObject;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据："+token.getAccessToken());
			System.out.println("有效时间："+token.getExpiresIn());
			//UserInfo userInfo=WeixinUtil.getUserInfo(token.getAccessToken(),"onw-Fv63xbl6nCDAV0XP8baL3aak");
			//System.out.println(userInfo);
			
			
			
			//文件上传  哈哈 没有权限。。。。
			//String path = "e:/imooc.jpg"; 
			//String mediaId=WeixinUtil.upload(path, token.getToken(), "image");
			//String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			//System.out.println(mediaId);
			
			//String result = WeixinUtil.translate("my name is laobi");
			//String result = WeixinUtil.translateFull("");
			//System.out.println(result);
			String qrPost=JSONObject.fromObject(WeixinUtil.createqrPost()).toString();
			
			System.out.println(qrPost);
			QRTicket qrticket=WeixinUtil.getTicket(token.getAccessToken(), qrPost);
			System.out.println(qrticket);
			
//			String menu=JSONObject.fromObject(WeixinUtil.initMenu()).toString();
//			int result=WeixinUtil.createMenu(token.getAccessToken(), menu);
//			if(result==0){
//				System.out.println("创建菜单成功");
//			}else{
//				System.out.println("错误"+result);
//			}
			//int result=WeixinUtil.getUserInfo(token.getAccessToken(), path);
			
//			JSONObject jsonObject=WeixinUtil.queryMenu(token.getAccessToken());
//			System.out.println(jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
