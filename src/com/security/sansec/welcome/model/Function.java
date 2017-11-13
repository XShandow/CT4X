package com.security.sansec.welcome.model;

import javafx.beans.property.SimpleStringProperty;

public class Function {
	private final SimpleStringProperty method;
	private final SimpleStringProperty info;


	public Function(String method, String info) {
		this.method =  new SimpleStringProperty(method);
		this.info =  new SimpleStringProperty(info);
	}


	public String getMethod() {
		return method.get();
	}

	public void setMethod(String method) {
		this.method.set(method);
	}

	public SimpleStringProperty getMethodSimpleString() {
		return method;
	}


	public String getInfo() {
		return info.get();
	}

	public SimpleStringProperty getInfoSimpleString() {
		return info;
	}

	public void setInfo(String info) {
		this.method.set(info);
	}


}
