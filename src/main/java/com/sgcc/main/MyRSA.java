package com.sgcc.main;

import java.io.Serializable;

public class MyRSA implements Serializable{

	
	private static final long serialVersionUID = 967933209929964378L;
   
	private String strPwd;

	
	public MyRSA(String strPwd) {
		super();
		this.strPwd = strPwd;
	}

	public String getStrPwd() {
		return strPwd;
	}

	public void setStrPwd(String strPwd) {
		this.strPwd = strPwd;
	}
	
}
