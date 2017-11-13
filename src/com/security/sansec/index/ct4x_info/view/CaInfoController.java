package com.security.sansec.index.ct4x_info.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.security.conf.ct4x_model.Ca;
import com.security.conf.ct4x_model.Ct4xConfig;
import com.security.conf.ct4x_model.KeyPair;
import com.security.conf.util.JaxbConvertUtil;
import com.security.sansec.index.ct4x_info.model.CaTableCell;
import com.security.sansec.main.CT4X;
import com.security.sansec.util.DialogUtil;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CaInfoController {
	Stage stage;
	AnchorPane caInfoView;

	private ToggleGroup typeGroup;
	RadioButton typeRsaRBtn;
	RadioButton typeSm2RBtn;
	String typeFlagString;

	private Ct4xConfig ct4xConfig = Ct4xConfig.getInstance();


	@FXML
	private Label caIDLabel;
	@FXML
	private TextField caNameTextField;
	@FXML
	private TextField keyIndexTextField;
	@FXML
	private TextArea keyValueTextArea;
	@FXML
	private TextArea rootCertTextArea;

	public void setMainApp(Stage stage,Pane pane) {
		caInfoView = (AnchorPane) pane;
		this.stage= stage;

		/* CA算法单选按钮 */
		typeGroup = new ToggleGroup();
		typeRsaRBtn = (RadioButton) caInfoView.getChildren().get(7);
		typeRsaRBtn.setToggleGroup(typeGroup);
		typeSm2RBtn = (RadioButton) caInfoView.getChildren().get(8);
		typeSm2RBtn.setToggleGroup(typeGroup);

		typeGroup.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
					if (typeGroup.getSelectedToggle() != null) {
						if (typeRsaRBtn.isSelected()) {
							typeFlagString = "0"; // RSA
						} else {
							typeFlagString = "1"; // SM2
						}
					}
				});
	}

	void initialize() {

	}

	void initData(CaTableCell selectCa) {
		caIDLabel.setText(selectCa.id);
		caNameTextField.setText(selectCa.getName());

		switch (selectCa.type) {
			case "RSA":
				typeRsaRBtn.setSelected(true);
				break;
			case "SM2":
				typeSm2RBtn.setSelected(true);
				break;
			default:
				break;
		}

		keyIndexTextField.setText(selectCa.getIndex());
		keyValueTextArea.setText(selectCa.getValue());

		rootCertTextArea.setText(selectCa.getRootcert());
	}


	/**
	 * 查看证书按钮
	 */
	@FXML
	private void handleViewCert() {
		String rootCertString =rootCertTextArea.getText();
		if (rootCertString.equals(null)||rootCertString=="") {
			DialogUtil.errorDialig(stage, "错误", "根证书数据空", "请填写证书数据之后查看证书信息.");
			return;
		}

		File rootCertFile = null;
		FileWriter out ;
		try {
			rootCertFile = File.createTempFile("Ct4xRootCert", ".cert");//创建临时文件
			out = new FileWriter(rootCertFile);
			out.write(rootCertString);

			out.flush();
			out.close();
		} catch (Exception e) {
			DialogUtil.errorDialig(stage, "错误", "创建根证书临时文件错误", "请关闭软件重试.");
		}

		try {
			String cmd = "rundll32.exe cryptext.dll,CryptExtOpenCER "+ rootCertFile.getAbsolutePath();
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			DialogUtil.errorDialig(stage, "错误", "查看证书错误", "调用证书查看软件错误.");
		}

		rootCertFile.deleteOnExit();//程序退出时删除临时文件
	}


	/**
	 * 保存信息按钮
	 */
	@FXML
	private void handleSave() {

		String caid = caIDLabel.getText();
		KeyPair keypair;
		Ca ca;

		//Ca信息修改
		if (caid==""||caid.equals(null)) {
			keypair = new KeyPair(keyIndexTextField.getText(), keyValueTextArea.getText());
			ca = new Ca(caNameTextField.getText(), typeFlagString, keypair, rootCertTextArea.getText());
			ct4xConfig.getCaset().add(ca);
		}else{
			keypair = new KeyPair(keyIndexTextField.getText(), keyValueTextArea.getText());
			ca = new Ca(caNameTextField.getText(), typeFlagString, keypair, rootCertTextArea.getText());
			ct4xConfig.getCaset().set(Integer.parseInt(caid), ca);
		}

		String xmlStr = null;
		try {
			xmlStr = JaxbConvertUtil.beanToXml(ct4xConfig, Ct4xConfig.class);
		} catch (JAXBException e) {
			DialogUtil.errorDialig(stage, "错误", "CA信息转XML失败", e.getMessage());
			return;
		}

		try {
			JaxbConvertUtil.writeFile(xmlStr);
		} catch (IOException e) {
			DialogUtil.errorDialig(stage, "错误", "保存XML文件失败", e.getMessage());
			return;
		}
		DialogUtil.infoDialig(stage, "成功", "保存XML文件成功.");
	}

	/**
	 * 取消按钮
	 */
	@FXML
	private void handleCancel() {
		stage.close();
	}



}
