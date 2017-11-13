package com.security.conf.ct4x_model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * All rights Reserved, Designed By SANSEC
 * @Title:  Ca.java
 * @Package com.security.conf.model
 * @Description:    XML中CA标签
 * @author: xujianchao
 * @date:   2017年11月8日 上午10:07:17
 * @version V1.0
 * @Copyright: 2017  Inc. All rights reserved.
 */
public class Ca {

	private String name;
	private String type;
	private KeyPair keypair;
	private String rootcert;

	public Ca() {
		super();
	}

	public Ca(String name, String type, KeyPair keypair, String rootcert) {
		super();
		this.name = name;
		this.type = type;
		this.keypair = keypair;
		this.rootcert = rootcert;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "keypair")
	public KeyPair getKeypair() {
		return keypair;
	}

	public void setKeypair(KeyPair keypair) {
		this.keypair = keypair;
	}

	@XmlElement(name = "rootcert")
	public String getRootcert() {
		return rootcert;
	}

	public void setRootcert(String rootcert) {
		this.rootcert = rootcert;
	}

}
