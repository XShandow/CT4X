package com.security.sansec.main.view;

import java.io.File;

import com.security.conf.ct4x_model.Ct4xConfig;
import com.security.conf.util.JaxbConvertUtil;
import com.security.sansec.index.ct4x_info.view.Ct4xInfoController;
import com.security.sansec.jks.view.JKSListController;
import com.security.sansec.license.view.ReadLicenseController;
import com.security.sansec.main.CT4X;
import com.security.sansec.pfx.view.PFX2JKSController;
import com.security.sansec.swcert.view.SelfSignCertController;
import com.security.sansec.util.Constants;
import com.security.sansec.util.DialogUtil;
import com.security.sansec.util.FileChooserUtil;
import com.security.sansec.welcome.view.WelcomeController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Root_CT4XController {

	private CT4X ct4x;

	public static Ct4xConfig ct4xConfig = Ct4xConfig.getInstance();
	public static boolean Ct4xConfigState =false;

	public void setMainApp(CT4X ct4x) {
		this.ct4x = ct4x;
	}

	/**
	 *
	 * @Title: handleAbout
	 * @Description: 关于按钮点击事件
	 */
	@FXML
	private void handleAbout() {
		WelcomeController welcomeController = new WelcomeController(ct4x);
		welcomeController.handleWelcome();
	}

	/**
	 *
	 * @Title: handleExit
	 * @Description: 退出按钮点击事件
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	/**
	 * @Title: handleCt4xXmlFileBrowse
	 * @Description: 打开Ct4x配置文件，将路径赋值给常量
	 */
	@FXML
	private void handleCt4xXmlFileBrowse() {
		File file = FileChooserUtil.openFileChooser(ct4x, "ct4x.xml files (*.xml)", "*.xml");
		if (file != null) {
			Constants.ct4x_XmlPath = file.getAbsolutePath();
			try {
				Ct4xConfig config = (Ct4xConfig) JaxbConvertUtil.xmlToBean(Constants.ct4x_XmlPath, Ct4xConfig.class);
				ct4xConfig .setCaset(config.getCaset());
				ct4xConfig .setHsm(config.getHsm());
				ct4xConfig .setProvider(config.getProvider());
			} catch (Exception e) {
				DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","打开ct4x.xml失败",e.getMessage());
			}
			DialogUtil.infoDialig(ct4x.getPrimaryStage(),"成功", "成功打开ct4x.xml.");
			Ct4xConfigState = true;
		} else {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","打开ct4x.xml失败","请检查ct4x.xml配置文件.");
		}
	}

	/**
	 * @Title: handleViewCt4xInfo
	 * @Description: 查看&显示ct4x.xml文件的信息
	 */
	@FXML
	private void handleViewCt4xInfo() {
		if (Ct4xConfigState == false) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","操作错误","请先打开ct4x.xml文件.");
			return;
		}
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../index/ct4x_info/view/Ct4xInfo.fxml"));
			AnchorPane pfx2jksView = loader.load();

			/* 控制新的Pane显示 */
			ct4x.getCT4XPane().setCenter(pfx2jksView);

			Ct4xInfoController controller = loader.getController();
			controller.setMainApp(this.ct4x);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Title: handlePFX2JKS
	 * @Description: “PFX转JKS”按钮 点击事件, PFX2JKS.fxml 替换原来的Pane
	 */
	@FXML
	private void handlePFX2JKS() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../pfx/view/PFX2JKS.fxml"));
			AnchorPane pfx2jksView = loader.load();

			/* 控制新的Pane显示 */
			ct4x.getCT4XPane().setCenter(pfx2jksView);

			PFX2JKSController controller = loader.getController();
			controller.setMainApp(this.ct4x);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Title: handleSelfSignCert
	 * @Description: “自签名证书”按钮 点击事件, SelfSignCert.fxml 替换原来的Pane
	 */
	@FXML
	private void handleSelfSignCert() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../swcert/view/SelfSignCert.fxml"));
			AnchorPane selfSignCertView = loader.load();

			/* 控制新的Pane显示 */
			ct4x.getCT4XPane().setCenter(selfSignCertView);

			SelfSignCertController controller = loader.getController();
			controller.setMainApp(this.ct4x);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Title: handleJKSList
	 * @Description: “JKS条目”按钮 点击事件, JKSList.fxml 替换原来的Pane
	 */
	@FXML
	private void handleJKSList() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../jks/view/JKSList.fxml"));
			AnchorPane jksListView = loader.load();

			/* 控制新的Pane显示 */
			ct4x.getCT4XPane().setCenter(jksListView);

			JKSListController controller = loader.getController();
			controller.setMainApp(this.ct4x);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @Title: handleLicenseRead
	 * @Description: “解密授权文件”按钮 点击事件, JKSList.fxml 替换原来的Pane
	 */
	@FXML
	private void handleLicenseRead() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../license/view/ReadLicense.fxml"));
			AnchorPane ReadLicenseView = loader.load();

			/* 控制新的Pane显示 */
			ct4x.getCT4XPane().setCenter(ReadLicenseView);

			ReadLicenseController controller = loader.getController();
			controller.setMainApp(this.ct4x);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
