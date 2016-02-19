package com.smartrac.objectexplore.models;

import android.util.Base64;
import android.util.Log;

public class SSLoginModel {
	static String basicAuth;
	private String username;
	private String password;
	private String instance;

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateUser(String username, String password) {
		// TODO Auto-generated method stub
		if (username != null && password != null) {
			String credentials = username + ":" + password;
			basicAuth = "Basic "
					+ Base64.encodeToString(credentials.getBytes(),
							Base64.NO_WRAP);
			Log.d("Authorization:", basicAuth);
		}

		return basicAuth;
	}

}
