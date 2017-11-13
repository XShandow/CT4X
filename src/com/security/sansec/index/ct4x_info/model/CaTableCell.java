package com.security.sansec.index.ct4x_info.model;

/**
 *
 * All rights Reserved, Designed By SANSEC
 *
 * @Title: CaTableCell.java
 * @Package com.security.conf.ct4x_model
 * @Description: 前端页面中CA表格中一行的实例
 * @author: xujianchao
 * @date: 2017年11月9日 上午10:36:57
 * @version V1.0
 * @Copyright: 2017 Inc. All rights reserved.
 */
public class CaTableCell {

	public String id;
	public String name;
	public String type;
	public String index;
	public String value;
	public String rootcert;

	public CaTableCell() {
		super();
	}

	public CaTableCell(String id,String name, String type, String index, String value, String rootcert) {
		super();
		this.name = name;
		this.type = type;
		this.index = index;
		this.value = value;
		this.rootcert = rootcert;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRootcert() {
		return rootcert;
	}

	public void setRootcert(String rootcert) {
		this.rootcert = rootcert;
	}

}
