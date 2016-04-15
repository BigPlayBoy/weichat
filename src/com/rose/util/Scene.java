package com.rose.util;

public class Scene {
	long scene_id;
//	String scene_str;

	public long getScene_id() {
		return scene_id;
	}

	public void setScene_id(long scene_id) {
		this.scene_id = scene_id;
	}

	@Override
	public String toString() {
		return "Scene [scene_id=" + scene_id + "]";
	}
	
}
