package com.security.conf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.security.conf.ct4x_model.Ca;
import com.security.conf.ct4x_model.Ct4xConfig;
import com.security.conf.ct4x_model.Hsm;
import com.security.conf.ct4x_model.KeyPair;
import com.security.conf.ct4x_model.Provider;
import com.security.conf.util.JaxbConvertUtil;

public class JaxbTest {

	@Test
	public void bean2Xml() throws JAXBException {
		Hsm hsmConf = new Hsm("1", "0", "");
		Provider providerConf = new Provider("1");
		KeyPair keyPairConf1 = new KeyPair("10", "aaaaasssssdddd");
		KeyPair keyPairConf2 = new KeyPair("10", "aaaaasssssdddd");
		Ca caConf1 = new Ca("testca1","1", keyPairConf1, "cccccccccccc");
		Ca caConf2 = new Ca("testca2","2", keyPairConf2, "pppppppppppp");

		List<Ca> caset = new ArrayList<>();
		caset.add(caConf1);
		caset.add(caConf2);

		Ct4xConfig ct4xConf = Ct4xConfig.getInstance();
		ct4xConf.setCaset(caset);
		ct4xConf.setHsm(hsmConf);
		ct4xConf.setProvider(providerConf);

		String str = JaxbConvertUtil.beanToXml(ct4xConf, Ct4xConfig.class);

		System.out.println(str);
	}

	@Test
	public void xml2Bean() throws  IOException, JAXBException {
		String xmlPath =  "C:\\Users\\xujianchao\\Desktop\\ct4x.xml";
		Object object = JaxbConvertUtil.xmlToBean(xmlPath,Ct4xConfig.class);
		Ct4xConfig ct4x = (Ct4xConfig)object;

		String hsmtype=ct4x.getHsm().getType();
		System.err.println(hsmtype);

	}

}
