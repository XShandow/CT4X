
package com.security.sansec.jks.view;

import java.io.File;

import com.security.sansec.jks.util.JKSListUtil;
import com.security.sansec.main.CT4X;
import com.security.sansec.util.DialogUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class JKSListController {
	private CT4X ct4x;

	@FXML
	private TextField jksPathField;
	@FXML
	private ChoiceBox<?> jksTypeChoiceBox;
	@FXML
	private TextField jksPswTextField;
	@FXML
	private TextField swxaIniPathTextField;
	@FXML
	private TextArea jksListArea;


	public  void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
		AnchorPane jksListView = (AnchorPane)ct4x.getCT4XPane().getCenter();

		/*选择框*/
		ObservableList<?> jksTypeList =FXCollections.observableArrayList("JKS", "SWKS");
		ChoiceBox jksTypeCB = (ChoiceBox)jksListView.getChildren().get(3);
		jksTypeCB.setItems(jksTypeList);
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
	 *swxa.ini 浏览按钮
	 */
	@FXML
	private void handleIniBrowse(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("swxaINI files (*.ini)", "*.ini");

		fileChooser.getExtensionFilters().add(extensionFilter);

		File file = fileChooser.showOpenDialog(ct4x.getPrimaryStage());
		if (file!=null) {
			swxaIniPathTextField.setText(file.getAbsolutePath());
		}
	}

	/**
	 *JKS条目“显示” 按钮
	 */
	@FXML
	private void handleViewJKSList() {
		/*输入框*/
		String keyStorePsw = jksPswTextField.getText().trim();
		String keyStoreFilePath = jksPathField.getText().trim();
		String swxaIniPath = swxaIniPathTextField.getText().trim();
		/*选择框*/
		String keyStoreType = jksTypeChoiceBox.getSelectionModel().getSelectedItem().toString();


		String list = null;
		try {
			list = JKSListUtil.getKeyStoreList(keyStoreType, keyStorePsw, keyStoreFilePath, swxaIniPath);
		} catch (Exception e) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(),"错误","获取JKS条目错误", e.getMessage());
		}
		jksListArea.setText(list);

	}
}
