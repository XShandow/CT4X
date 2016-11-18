package com.security.sansec.pfx.view;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.security.sansec.app.CT4X;
import com.security.sansec.dialg.DialogUtil;
import com.security.sansec.pfx.util.PFX2JKSUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class PFX2JKSController {

	private CT4X ct4x;


	public  void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
	}

	@FXML
	private TextField pfxPathField;
	@FXML
	private PasswordField pfxPSWField;
	@FXML
	private Button pfxBrowseBtn;

	@FXML
	private TextField jksPathField;
	@FXML
	private PasswordField jksPSWField;
	@FXML
	private Button jksBrowseBtn;

	/**
	 *PFX浏览按钮
	 */
	@FXML
	private void handlePFXBrowse(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PFX files (*.pfx)", "*.pfx");

		fileChooser.getExtensionFilters().add(extensionFilter);

		File file = fileChooser.showOpenDialog(ct4x.getPrimaryStage());
		if (file!=null) {
			pfxPathField.setText(file.getAbsolutePath());
		}

	}

	/**
	 *JKS浏览按钮
	 */
	@FXML
	private void handleJKSBrowse(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JKS files (*.jks)", "*.jks");

		fileChooser.getExtensionFilters().add(extensionFilter);

		File file = fileChooser.showOpenDialog(ct4x.getPrimaryStage());
		if (file!=null) {
			jksPathField.setText(file.getAbsolutePath());
		}
	}

	/**
	 *PFX转换JKS按钮
	 */
	@FXML
	private void handleTransform() {
		String pfxPathString = pfxPathField.getText().trim();
		String pfxPswString = pfxPSWField.getText().trim();
		String jksPathString = jksPathField.getText().trim();
		String jksPswString = jksPSWField.getText().trim();

		if (StringUtils.isBlank(pfxPathString)||StringUtils.isBlank(pfxPswString)||StringUtils.isBlank(jksPathString)||StringUtils.isBlank(jksPswString)) {
			DialogUtil.errorDialig("参数错误", "空参数");
		}else {
			try {
				PFX2JKSUtil.transform(pfxPathString, pfxPswString, jksPathString, jksPswString);
			} catch (Exception e) {
				DialogUtil.errorDialig("转换错误", e.getMessage());
			}
			DialogUtil.infoDialig("成功", "PFX转JKS成功！");
		}


	}
}
