package com.security.sansec.swcert.view;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.security.sansec.main.CT4X;
import com.security.sansec.swcert.util.SelfSignCertUtil;
import com.security.sansec.util.DialogUtil;
import com.security.sansec.util.FileChooserUtil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class SelfSignCertController {
	private CT4X ct4x;
	private ToggleGroup group ;

	private boolean flag = true;  //true：内部密钥   false：外部密钥

	@FXML
	private TextField subjectInfoTextField;
	@FXML
	private ChoiceBox<?> keyAlgChoiceBox;
	@FXML
	private TextField keyLengthTextField;
	@FXML
	private ChoiceBox<?> signAlgChoiceBox;
	@FXML
	private TextField validYearTextField;
	@FXML
	private TextField keyIndexTextField;
	@FXML
	private TextField swxaIniPathTextField;
	@FXML
	private TextArea certTextArea;


	public  void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
		AnchorPane selfSignCertView = (AnchorPane)ct4x.getCT4XPane().getCenter();
		/*单选按钮*/
		group = new ToggleGroup();
		RadioButton radioBtn1 = (RadioButton)selfSignCertView.getChildren().get(0);
		radioBtn1.setToggleGroup(group);
		RadioButton radioBtn2 = (RadioButton)selfSignCertView.getChildren().get(1);
		radioBtn2.setToggleGroup(group);


		group.selectedToggleProperty().addListener(
				(ObservableValue<? extends Toggle> ov, Toggle old_Toggle,
				 Toggle new_Toggle) -> {
					if (group.getSelectedToggle() != null) {
						if (radioBtn2.isSelected()) {
							keyIndexTextField.setDisable(true);
							keyLengthTextField.setDisable(false);
							flag = false;
						}else {
							keyIndexTextField.setDisable(false);
							keyLengthTextField.setDisable(true);
							flag = true;
						}
					}
				}
		);

		/*选择框*/
		ObservableList<?> algList =FXCollections.observableArrayList("SM2", "RSA");
		ObservableList<?> sm2SignAlgList =FXCollections.observableArrayList("SM3WithSM2");
		ObservableList<?> rsaSignAlgList =FXCollections.observableArrayList("SHA1WithRSA","SHA256WithRSA",
				"SHA512WithRSA","MD2WithRSA","MD5WithRSA");

		ChoiceBox keyAlgCB = (ChoiceBox)selfSignCertView.getChildren().get(8);
		keyAlgCB.setItems(algList);

		ChoiceBox signAlgCB = (ChoiceBox)selfSignCertView.getChildren().get(9);

		keyAlgCB.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number old_val,Number new_val) -> {
					if (new_val.intValue() == 0) {
						signAlgCB.setItems(sm2SignAlgList);
					}else if (new_val.intValue() == 1) {
						signAlgCB.setItems(rsaSignAlgList);
					}
				}
		);

	}

	/**
	 *swxa.ini 浏览按钮
	 */
	@FXML
	private void handleIniBrowse(){
		File  file = FileChooserUtil.openFileChooser(ct4x, "swsds.ini files (*.ini)",  "*.ini");
		if (file!=null) {
			swxaIniPathTextField.setText(file.getAbsolutePath());
		}

	}

	/**
	 *“生成自签名证书” 按钮
	 */
	@FXML
	private void handleGenSelfSignCert() {
		/*输入框*/
		String subjectInfoString = subjectInfoTextField.getText().trim();
		String validYearString = validYearTextField.getText().trim();
		String keyIndexString = keyIndexTextField.getText().trim();
		String keyLengthString = keyLengthTextField.getText().trim();
		String swxaIniPathString = swxaIniPathTextField.getText().trim();

		/*选择框*/
		String keyAlgString = keyAlgChoiceBox.getSelectionModel().getSelectedItem().toString();
		String signAlgString = signAlgChoiceBox.getSelectionModel().getSelectedItem().toString();

		if (StringUtils.isBlank(subjectInfoString)||StringUtils.isBlank(validYearString)||StringUtils.isBlank(keyAlgString)||StringUtils.isBlank(signAlgString)||StringUtils.isBlank(swxaIniPathString)) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","参数错误", "请填写申请者信息|有效期|密钥算法");
		}
		if (flag && StringUtils.isBlank(keyIndexString)) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","参数错误", "请填写密钥索引");
		}
		if (!flag && StringUtils.isBlank(keyLengthString)) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","参数错误", "请填写密钥长度");
		}

		String msg = null;
		if (flag) { //内部密钥
			try {
				msg = SelfSignCertUtil.genSelfSignCertTrue(subjectInfoString, validYearString, keyIndexString, keyAlgString, signAlgString, swxaIniPathString);
			} catch (Exception e) {
				DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","生成证书错误",e.getMessage());
			}
		}else{//随机密钥
			try {
				msg = SelfSignCertUtil.genSelfSignCertFalse(subjectInfoString, validYearString, keyLengthString, keyAlgString, signAlgString, swxaIniPathString);
			} catch (Exception e) {
				DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","生成证书错误",e.getMessage());
			}
		}

		certTextArea.setText(msg);

	}
}
