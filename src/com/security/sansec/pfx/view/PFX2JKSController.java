package com.security.sansec.pfx.view;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.security.sansec.main.CT4X;
import com.security.sansec.pfx.util.PFX2JKSUtil;
import com.security.sansec.util.DialogUtil;
import com.security.sansec.util.FileChooserUtil;

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
		File  file = FileChooserUtil.openFileChooser(ct4x, "PFX files (*.pfx)", "*.pfx");
		if (file!=null) {
			pfxPathField.setText(file.getAbsolutePath());
		}
	}

	/**
	 *JKS浏览按钮
	 */
	@FXML
	private void handleJKSBrowse(){
		File  file = FileChooserUtil.openFileChooser(ct4x, "JKS files (*.jks)", "*.jks");
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
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","参数错误","请填写PFX路径|PFX密码|JKS路径|JKS密码.");
		}else {
			try {
				PFX2JKSUtil.transform(pfxPathString, pfxPswString, jksPathString, jksPswString);
			} catch (Exception e) {
				DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","转换错误",e.getMessage());
			}
			DialogUtil.infoDialig(ct4x.getPrimaryStage(),"成功", "PFX转JKS成功.");
		}


	}
}
