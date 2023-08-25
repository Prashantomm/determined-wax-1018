package com.masai.admin;

public enum Admin {
	USERNAME("admin"), PASSWORD("admin");

	private final String value;

	Admin(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
