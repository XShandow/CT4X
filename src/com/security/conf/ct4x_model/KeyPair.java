package com.security.conf.ct4x_model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * All rights Reserved, Designed By SANSEC
 * @Title:  KeyPair.java
 * @Package com.security.conf.model
 * @Description:    xml文件中keypair标签
 * @author: xujianchao
 * @date:   2017年11月8日 上午10:16:20
 * @version V1.0
 * @Copyright: 2017  Inc. All rights reserved.
 */
public class KeyPair {
	private String index;
	private String value;

	public KeyPair() {
		super();
	}

	public KeyPair(String index, String value) {
		super();
		this.index = index;
		this.value = value;
	}

	@XmlElement(name = "index")
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@XmlElement(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
