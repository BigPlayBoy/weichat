package com.rose.util;

public class QRPost {
	String expire_seconds;
	String action_name;
	Action_Info action_info;
	public String getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public Action_Info getAction_info() {
		return action_info;
	}
	public void setAction_info(Action_Info action_info) {
		this.action_info = action_info;
	}
	@Override
	public String toString() {
		return "QRPost [expire_second=" + expire_seconds + ", action_name=" + action_name + ", action_info="
				+ action_info + "]";
	}
	
}
