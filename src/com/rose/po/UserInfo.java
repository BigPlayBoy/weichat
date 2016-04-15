package com.rose.po;

public class UserInfo {
	int subscribe;
	String openid;
	String nickname;
	int sex;
	String city;
	String province;
	String langague;
	String headimgurl;
	long subscribe_time;
	String unionid;
	String remark;
	int groupid;
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLangague() {
		return langague;
	}
	public void setLangague(String langague) {
		this.langague = langague;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	@Override
	public String toString() {
		return "UserInfo [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", city=" + city + ", province=" + province + ", langague=" + langague + ", headimgurl=" + headimgurl
				+ ", subscribe_time=" + subscribe_time + ", unionid=" + unionid + ", remark=" + remark + ", groupid="
				+ groupid + "]";
	}
	
}
