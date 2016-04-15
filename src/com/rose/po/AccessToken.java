package com.rose.po;

import com.rose.po.CalendarUtil;

public class AccessToken {
	private String accessToken;// 鎺ュ彛璁块棶鍑瘉
	private int expiresIn;// 鍑瘉鏈夋晥鏈燂紝鍗曚綅锛氱
	private long createTime;//鍒涘缓鏃堕棿锛屽崟浣嶏細绉� 锛岀敤浜庡垽鏂槸鍚﹁繃鏈�
	
	private Integer errcode;//閿欒缂栫爜
	private String errmsg;//閿欒娑堟伅
	
	public AccessToken(){
		this.createTime = CalendarUtil.getTimeInSeconds();
	}
	
	public AccessToken(String accessToken,int expiresIn){
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.createTime = CalendarUtil.getTimeInSeconds();
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
		this.errmsg = ErrCode.errMsg(errcode);
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	/**
	 * 鏄惁瓒呮椂锛屽井淇￠粯璁�7200s瓒呮椂
	 * @return true-瓒呮椂锛沠alse-娌℃湁瓒呮椂
	 */
	public boolean isExpires(){
		long now = CalendarUtil.getTimeInSeconds();
		return now - this.createTime - 10 >= this.expiresIn; //棰勭暀 10s 
	}
	
	/**
	 * 鏄惁瓒呮椂
	 * @return true-瓒呮椂锛沠alse-娌℃湁瓒呮椂
	 */
	public boolean isExpires(Long expireTime){
		long now = CalendarUtil.getTimeInSeconds();
		return now - this.createTime - 10 >= expireTime; //棰勭暀 10s 
	}
	
	
}
