package com.rose.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.rose.menu.Button;
import com.rose.menu.ClickButton;
import com.rose.menu.Menu;
import com.rose.menu.ViewButton;

import com.rose.po.AccessToken;
import com.rose.po.UserInfo;

/**
 * ΢�Ź�����
 * 
 * @author Stephen
 *
 */
public class WeixinUtil {
	// һ�޿Ƽ�Ȩ�޲��� ����ʹ�ò��Ժ�

	// private static final String APPID = "wx3b158b1ac3eebb57";
	// private static final String APPSECRET =
	// "16156d0b3646bdad27f283b2054738cb";
	private static final String APPID = "wx7a9212f0fc7b1b0c";
	private static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	// ����û���Ϣ
	private static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// �����ʱ��ά������
	private static final String GET_QR_SCENE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

	// ������ö�ά��
	private static final String GET_QR_LIMIT_SCENE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

	/**
	 * get����
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * POST����
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 * 
	 */
	public static JSONObject doPostStr(String url, String outStr) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = client.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * ��ȡaccessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() throws ParseException, IOException {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setAccessToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}

	public static QRPost createqrPost() {
//		System.out.println("һ�仰���������1");
		QRPost qrPost=new QRPost();
//		System.out.println("һ�仰���������2");
		qrPost.setExpire_seconds("604800");
		qrPost.setAction_name("QR_SCENE");
		Action_Info action_info=new Action_Info();
//		System.out.println("һ�仰���������3");
		//action_info.scene.setScene_id(120);
		Scene scene=new Scene();
		scene.setScene_id(120);
		action_info.setScene(scene);
//		System.out.println("��仰�������");

		qrPost.setAction_info(action_info);
		System.out.println("qrPost="+qrPost);
//		System.out.println("һ�仰���������4");
		return qrPost;
	}

	/**
	 * ��ȡticket
	 */
	public static QRTicket getTicket(String token,String qrPost) throws ClientProtocolException, IOException {
		// ����һ��post����
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		System.out.println("���ڻ�û��ִ��post");
		JSONObject jsonObject = doPostStr(url, qrPost);
		System.out.println("����ִ����post");
		System.out.println("jsonObject����ϢΪ"+jsonObject);
		QRTicket qrTicket=new QRTicket();
		qrTicket.setExpire_seconds(jsonObject.getString("expire_seconds"));
		qrTicket.setTicket(jsonObject.getString("ticket"));
		qrTicket.setUrl(jsonObject.getString("url"));
		System.out.println("��ȡ��ϸ����");
		try {
			if (jsonObject != null) {
				result = jsonObject.getInt("errcode");
				System.out.println("result="+result);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("���ص�����ȷ��  �Ͳ���Ҫ���result��");
		}
		return qrTicket;
		// //�����ʱ��ά�������
		// String url=GET_QR_SCENE.replace("TOKEN", token);
		// //������ö�ά�������
		// //String url=GET_QR_LIMIT_SCENE.replace("TOKEN", token);
		// JSONObject jsonObject = doPostStr(url, token);

		// qrTicket.setExpire_seconds(jsonObject.getInt("expire_seconds"));
		// qrTicket.setTicket(jsonObject.getString("ticket"));
		// qrTicket.setUrl(jsonObject.getString("url"));

		//QRTicket qrTicket = new QRTicket();
		//return qrTicket;

	}

	/**
	 * ��װ�˵�
	 * 
	 * @return
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("��Ҫ����");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button21 = new ViewButton();
		button21.setName("��ҪԤ��");
		button21.setType("view");
		button21.setUrl("http://form.mikecrm.com/f.php?t=4Ai7v8");

		ClickButton button31 = new ClickButton();
		button31.setName("ɨ���¼�");
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("����λ��");
		button32.setType("location_select");
		button32.setKey("32");

		Button button = new Button();
		button.setName("��������");
		button.setSub_button(new Button[] { button31, button32 });

		menu.setButton(new Button[] { button11, button21, button });
		return menu;
	}

	public static int createMenu(String token, String menu) throws ParseException, IOException {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	public static JSONObject queryMenu(String token) throws ParseException, IOException {
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	public static int deleteMenu(String token) throws ParseException, IOException {
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		int result = 0;
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @param token
	 * @param openid
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static UserInfo getUserInfo(String token, String openid) throws ClientProtocolException, IOException {
		String url = GET_USERINFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject jsonObject = doGetStr(url);
		UserInfo userInfo = new UserInfo();
		// ��ȡ�û�����Ϣ �����е��鷳 �򵥵Ĳ���
		userInfo.setSubscribe(jsonObject.getInt("subscribe"));
		userInfo.setCity(jsonObject.getString("city"));
		userInfo.setOpenid(jsonObject.getString("openid"));
		userInfo.setNickname(jsonObject.getString("nickname"));
		// userInfo.set
		return userInfo;
	}

	/**
	 * �ļ��ϴ�
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken, String type)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("�ļ�������");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

		URL urlObj = new URL(url);
		// ����
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// ��������ͷ��Ϣ
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// ���ñ߽�
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// ��������
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// �����ͷ
		out.write(head);

		// �ļ����Ĳ���
		// ���ļ������ļ��ķ�ʽ ���뵽url��
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// ��β����
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ����������ݷָ���

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// ����BufferedReader����������ȡURL����Ӧ
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if (!"image".equals(type)) {
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}

}
