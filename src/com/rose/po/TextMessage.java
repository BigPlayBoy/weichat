package com.rose.po;

public class TextMessage extends BaseMessage{
//�����ı����������
	
	private String Content;
	private String MsgId;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
}
