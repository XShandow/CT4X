package com.security.conf.ct4x_model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * All rights Reserved, Designed By SANSEC
 *
 * @Title: Ct4x.java
 * @Package com.security.conf.model
 * @Description: ct4x.xml中根标签CT4X
 * @author: xujianchao
 * @date: 2017年11月8日 上午10:15:11
 * @version V1.0
 * @Copyright: 2017 Inc. All rights reserved.
 */
@XmlRootElement(name = "ct4x")
public class Ct4xConfig {
	private Hsm hsm;
	private Provider provider;
	private List<Ca> caset;

	private Ct4xConfig() {
	}

	// 使用一个内部类来维护单例
	private static class Ct4xConfigFactory {
		private static Ct4xConfig instance = new Ct4xConfig();
	}

	// 获取实例
	public static Ct4xConfig getInstance() {
		return Ct4xConfigFactory.instance;
	}

	// 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	public Object readResolve() {
		return getInstance();
	}

	private Ct4xConfig(Hsm hsm, Provider provider, List<Ca> caset) {
		super();
		this.hsm = hsm;
		this.provider = provider;
		this.caset = caset;
	}

	@XmlElement(name = "hsm")
	public Hsm getHsm() {
		return hsm;
	}

	public void setHsm(Hsm hsm) {
		this.hsm = hsm;
	}

	@XmlElement(name = "provider")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@XmlElementWrapper(name = "caset")
	@XmlElement(name = "ca")
	public List<Ca> getCaset() {
		return caset;
	}

	public void setCaset(List<Ca> caset) {
		this.caset = caset;
	}


}
