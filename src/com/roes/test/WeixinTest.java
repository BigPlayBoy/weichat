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
			System.out.println("Ʊ�ݣ�"+token.getAccessToken());
			System.out.println("��Чʱ�䣺"+token.getExpiresIn());
			//UserInfo userInfo=WeixinUtil.getUserInfo(token.getAccessToken(),"onw-Fv63xbl6nCDAV0XP8baL3aak");
			//System.out.println(userInfo);
			
			
			
			//�ļ��ϴ�  ���� û��Ȩ�ޡ�������
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
//				System.out.println("�����˵��ɹ�");
//			}else{
//				System.out.println("����"+result);
//			}
			//int result=WeixinUtil.getUserInfo(token.getAccessToken(), path);
			
//			JSONObject jsonObject=WeixinUtil.queryMenu(token.getAccessToken());
//			System.out.println(jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
