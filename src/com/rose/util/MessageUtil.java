package com.rose.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rose.po.Image;
import com.rose.po.ImageMessage;
import com.rose.po.Music;
import com.rose.po.MusicMessage;
import com.rose.po.News;
import com.rose.po.NewsMessage;
import com.rose.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	// 消息的格式转换

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_MUSIC="music";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIWEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";

	/**
	 * xml转为map集合
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream ins = request.getInputStream();

		Document doc = reader.read(ins);

		Element root = doc.getRootElement();

		List<Element> list = root.elements();

		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();

		return map;

	}

	/**
	 * 将文本消息对象转化为xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();

		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	public static String initText(String toUserName,String fromUesrName,String content){
		TextMessage text=new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUesrName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎关注 \n\n");
		sb.append("现在还在测试中\n");
		return sb.toString();
	}
	/**
	 * 自动回复1
	 * @return
	 */
	public static String auto1(){
		StringBuffer sb=new StringBuffer();
		sb.append("这里是自动回复\n\n");
		sb.append("现在还在测试中\n");
		return sb.toString();
	}
	/**
	 * 自动回复2
	 * @return
	 */
	public static String auto2(){
		StringBuffer sb=new StringBuffer();
		sb.append("这里是自动回复\n");
		sb.append("自动回复2\n");
		sb.append("现在还在测试中\n");
		return sb.toString();
	}
	/**
	 * 将图文消息对象转化为xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item",new News().getClass());
		return xstream.toXML(newsMessage);
	}
	/**
	 * 生成图文消息
	 * @param toUserName
	 * @param fromUesrName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUesrName){
		String message=null;
		List<News> newsList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage(); 
		
		News news=new News();
		news.setTitle("第一次 好紧张");
		news.setDescription("这里是描述文本吧");
		news.setPicUrl("http://huashengke1994.6655.la/weixin/image/imooc.jpg");
		news.setUrl("www.baidu.com");
		newsList.add(news);
		
		newsMessage.setToUserName(fromUesrName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticleCount(newsList.size());
		newsMessage.setArticles(newsList);
		
		message=newsMessageToXml(newsMessage);
		
		return message;
		
	}
	/**
	 * 图片消息转为xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/**
	 * 组装图片消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initImageMessage(String toUserName,String fromUserName){
		String message = null;
		Image image = new Image();
		image.setMediaId("sN_PnpgXeKpc2LvLVpkgajqZUP3mZzSz_lY-tnEIpHO1q53FzYbex3E_RgjdR21G");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		message = imageMessageToXml(imageMessage);
		return message;
	}
	/**
	 * 音乐消息转为xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/**
	 * 组装音乐消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("vJNtTUH_5hbZ0nJsugp1_qtjYrovIVC3WQAvTopFIAXarA4w-tjDkQBUWcD7tqpf");
		music.setTitle("see you again");
		music.setDescription("速7片尾曲");
		music.setMusicUrl("http://huashengke1994.6655.la/weixin/resource/See You Again.mp3");
		music.setHQMusicUrl("http://huashengke1994.6655.la/weixin/resource/See You Again.mp3");
		
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = musicMessageToXml(musicMessage);
		return message;
	}
	
}
