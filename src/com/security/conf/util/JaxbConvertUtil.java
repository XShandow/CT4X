package com.security.conf.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.security.sansec.util.Constants;
import com.security.sansec.util.DialogUtil;

/**
 *
 * All rights Reserved, Designed By SANSEC
 *
 * @Title: JaxbConvertUtil.java
 * @Package com.security.conf.util
 * @Description: JXBA工具类：实体转换成xml | xml转换成实体对象
 * @author: xujianchao
 * @date: 2017年11月8日 上午10:17:35
 * @version V1.0
 * @Copyright: 2017 Inc. All rights reserved.
 */
public class JaxbConvertUtil {

	/**
	 * java对象转换为xml文件
	 *
	 * @param obj
	 *            xml文件路径
	 * @param load
	 *            java对象.Class
	 * @return xml文件的String
	 * @throws JAXBException
	 */
	public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(load);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // 编码格式,默认为utf-8
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // 是否省略xml头信息
		StringWriter writer = new StringWriter();
		marshaller.marshal(obj, writer);
		return writer.toString();
	}

	/**
	 *
	 * @param xmlPath
	 *            xml文件路径
	 * @param load
	 *            java对象.Class
	 * @return java对象
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static Object xmlToBean(String xmlPath, Class<?> load) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(load);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object object = unmarshaller.unmarshal(new File(xmlPath));
		return object;
	}

	/**
	 * @throws IOException
	 *
	 * @Title: writeFile @Description: 将xml存储到文件 @param: @param xmlStr @return:
	 * void @throws
	 */
	public static void writeFile(String xmlStr) throws IOException {
		Writer writer = null;

		writer = new FileWriter(Constants.ct4x_XmlPath);
		writer.write(xmlStr);
		writer.flush();
	}

}
