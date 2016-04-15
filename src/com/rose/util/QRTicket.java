package com.rose.util;

public class QRTicket {
	String ticket;
	String expire_seconds;
	String url;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "QRTicket [ticket=" + ticket + ", expire_seconds=" + expire_seconds + ", url=" + url + "]";
	}
	
}
