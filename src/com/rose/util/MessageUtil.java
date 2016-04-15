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
	// ��Ϣ�ĸ�ʽת��

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
	 * xmlתΪmap����
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
	 * ���ı���Ϣ����ת��Ϊxml
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
	 * ���˵�
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("��ӭ��ע \n\n");
		sb.append("���ڻ��ڲ�����\n");
		return sb.toString();
	}
	/**
	 * �Զ��ظ�1
	 * @return
	 */
	public static String auto1(){
		StringBuffer sb=new StringBuffer();
		sb.append("�������Զ��ظ�\n\n");
		sb.append("���ڻ��ڲ�����\n");
		return sb.toString();
	}
	/**
	 * �Զ��ظ�2
	 * @return
	 */
	public static String auto2(){
		StringBuffer sb=new StringBuffer();
		sb.append("�������Զ��ظ�\n");
		sb.append("�Զ��ظ�2\n");
		sb.append("���ڻ��ڲ�����\n");
		return sb.toString();
	}
	/**
	 * ��ͼ����Ϣ����ת��Ϊxml
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
	 * ����ͼ����Ϣ
	 * @param toUserName
	 * @param fromUesrName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUesrName){
		String message=null;
		List<News> newsList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage(); 
		
		News news=new News();
		news.setTitle("��һ�� �ý���");
		news.setDescription("�����������ı���");
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
	 * ͼƬ��ϢתΪxml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/**
	 * ��װͼƬ��Ϣ
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
	 * ������ϢתΪxml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/**
	 * ��װ������Ϣ
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("vJNtTUH_5hbZ0nJsugp1_qtjYrovIVC3WQAvTopFIAXarA4w-tjDkQBUWcD7tqpf");
		music.setTitle("see you again");
		music.setDescription("��7Ƭβ��");
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
