package com.security.conf.ct4x_model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * All rights Reserved, Designed By SANSEC
 * @Title:  Hsm.java
 * @Package com.security.conf.model
 * @Description:    XML中hsm标签
 * @author: xujianchao
 * @date:   2017年11月8日 上午10:15:54
 * @version V1.0
 * @Copyright: 2017  Inc. All rights reserved.
 */
public class Hsm {
	private String used;
	private String type;
	private String swsds;

	public Hsm() {
		super();
	}

	public Hsm(String used, String type, String swsds) {
		super();
		this.used = used;
		this.type = type;
		this.swsds = swsds;
	}

	@XmlAttribute(name = "used")
	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "swsds")
	public String getSwsds() {
		return swsds;
	}

	public void setSwsds(String swsds) {
		this.swsds = swsds;
	}

}
