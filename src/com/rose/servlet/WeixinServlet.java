package com.rose.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.rose.po.AccessToken;
import com.rose.util.CheckUtil;
import com.rose.util.MessageUtil;
import com.rose.util.WeixinUtil;

public class WeixinServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(req, resp);
		// 获取参数
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		System.out.println("处理请求");
		PrintWriter out = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// super.doPost(req, resp);
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			// 获取属性
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");

			String message = null;
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				if ("1".equals(content)) {
					// 自动回复
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.auto1());
				} else if ("2".equals(content)) {
					// 自动回复
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.auto2());
				} else if ("3".equals(content)) {
					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
					System.out.println("输入的是3");
				} else if ("4".equals(content)) {
					message = MessageUtil.initImageMessage(toUserName, fromUserName);
					System.out.println("输入的是4");
				} else if ("5".equals(content)) {
					message = MessageUtil.initMusicMessage(toUserName, fromUserName);
					System.out.println("输入的是4");
				}

			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");

				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
//					AccessToken token = WeixinUtil.getAccessToken();
//					System.out.println(WeixinUtil.getUserInfo(token.getAccessToken(), fromUserName));
				}else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if (MessageUtil.MESSAGE_VIEW.equals(eventType)) {
					String url=map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else  if (MessageUtil.MESSAGE_SCANCODE.equals(eventType)) {
					String key=map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, key);
				}

			}else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String Label=map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, Label);
			}
System.out.println("收到的信息为"+map);
			//System.out.println(message);
			out.print(message);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	public static void main(String[] args) throws ServletException, IOException {
		WeixinServlet w = new WeixinServlet();
		w.doGet(null, null);
	}

}
