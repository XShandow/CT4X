package com.security.sansec.license.view;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.security.sansec.main.CT4X;
import com.security.sansec.util.DialogUtil;
import com.security.sansec.util.FileChooserUtil;
import com.security.util.soft_sm4.Sm4Util;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ReadLicenseController {

	private CT4X ct4x;

	@FXML
	private TextField licensePathField;
	@FXML
	private TextField licensePswTextField;
	@FXML
	private TextArea licenseArea;


	public  void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
	}

	/**
	 *License 浏览按钮
	 */
	@FXML
	private void handleLicenseBrowse(){
		File  file = FileChooserUtil.openFileChooser(ct4x, "License files (*.dat)", "*.dat");
		if (file!=null) {
			licensePathField.setText(file.getAbsolutePath());
		}
	}

	/**
	 *License 显示按钮
	 */
	@FXML
	private void handleLicenseRead(){
		String licensePathString = licensePathField.getText().trim();
		String licensePswString = licensePswTextField.getText().trim();

		String licenseData =null;
		try {
			licenseData = FileUtils.readFileToString(new File(licensePathString));
		} catch (IOException e) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","读取文件错误", e.getMessage());
		}

		byte[] clearText = null;

		try {
			clearText =Sm4Util.decryptData_ECB(Base64.decodeBase64(licenseData), licensePswString.getBytes());
		}catch(Exception e){
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","解密授权错误", e.getMessage());
		}

		licenseArea.setText(new String(clearText));

	}

}
