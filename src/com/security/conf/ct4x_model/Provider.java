package com.security.conf.ct4x_model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * All rights Reserved, Designed By SANSEC
 * @Title:  Provider.java
 * @Package com.security.conf.model
 * @Description:    xml文件中provider标签
 * @author: xujianchao
 * @date:   2017年11月8日 上午10:16:47
 * @version V1.0
 * @Copyright: 2017  Inc. All rights reserved.
 */
public class Provider {
	private String used;

	public Provider() {
		super();
	}

	public Provider(String used) {
		super();
		this.used = used;
	}

	@XmlAttribute(name = "used")
	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

}
