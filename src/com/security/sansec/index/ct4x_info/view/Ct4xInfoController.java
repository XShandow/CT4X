package com.security.sansec.index.ct4x_info.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.security.conf.ct4x_model.Ct4xConfig;
import com.security.conf.util.JaxbConvertUtil;
import com.security.sansec.index.ct4x_info.model.CaTableCell;
import com.security.sansec.index.ct4x_info.util.CaConvertUtil;
import com.security.sansec.main.CT4X;
import com.security.sansec.util.Constants;
import com.security.sansec.util.DialogUtil;
import com.security.sansec.util.FileChooserUtil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Ct4xInfoController {
	private CT4X ct4x;

	private Ct4xConfig ct4xConfig = Ct4xConfig.getInstance();

	AnchorPane ct4xInfoView;

	private ToggleGroup hsmUsedGroup;
	RadioButton hsmUsedYesRBtn;
	RadioButton hsmUsedNoRBtn;
	String hsmUsedFlagString;

	private ToggleGroup hsmTypeGroup;
	RadioButton hsmTypePkiRBtn;
	RadioButton hsmTypeRBtn;
	String hsmTypeFlagString;

	private ToggleGroup providerUsedGroup;
	RadioButton providerUsedYesRBtn;
	RadioButton providerUsedNoRBtn;
	String providerUsedFlagString;

	@FXML
	private TextField swxaIniPathTextField;

	TableView<CaTableCell> table;

	public void setMainApp(CT4X ct4x) {
		this.ct4x = ct4x;
		ct4xInfoView = (AnchorPane) ct4x.getCT4XPane().getCenter();

		/* 密码机使用单选按钮 */
		hsmUsedGroup = new ToggleGroup();
		hsmUsedYesRBtn = (RadioButton) ct4xInfoView.getChildren().get(3);
		hsmUsedYesRBtn.setToggleGroup(hsmUsedGroup);
		hsmUsedNoRBtn = (RadioButton) ct4xInfoView.getChildren().get(4);
		hsmUsedNoRBtn.setToggleGroup(hsmUsedGroup);

		hsmUsedGroup.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
					if (hsmUsedGroup.getSelectedToggle() != null) {
						if (hsmUsedYesRBtn.isSelected()) {
							hsmUsedFlagString = "1"; // use hsm
						} else {
							hsmUsedFlagString = "0"; // not use hsm
						}
					}
				});

		/* 密码机类型单选按钮 */
		hsmTypeGroup = new ToggleGroup();
		hsmTypePkiRBtn = (RadioButton) ct4xInfoView.getChildren().get(6);
		hsmTypePkiRBtn.setToggleGroup(hsmTypeGroup);
		hsmTypeRBtn = (RadioButton) ct4xInfoView.getChildren().get(7);
		hsmTypeRBtn.setToggleGroup(hsmTypeGroup);

		hsmTypeGroup.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
					if (hsmTypeGroup.getSelectedToggle() != null) {
						if (hsmTypePkiRBtn.isSelected()) {
							hsmTypeFlagString = "0"; // PKI HSM
						} else {
							hsmTypeFlagString = "1"; // 金融HSM
						}
					}
				});

		/* Provider使用单选按钮 */
		providerUsedGroup = new ToggleGroup();
		providerUsedYesRBtn = (RadioButton) ct4xInfoView.getChildren().get(14);
		providerUsedYesRBtn.setToggleGroup(providerUsedGroup);
		providerUsedNoRBtn = (RadioButton) ct4xInfoView.getChildren().get(15);
		providerUsedNoRBtn.setToggleGroup(providerUsedGroup);

		providerUsedGroup.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
					if (providerUsedGroup.getSelectedToggle() != null) {
						if (providerUsedYesRBtn.isSelected()) {
							providerUsedFlagString = "1"; // use provider
						} else {
							providerUsedFlagString = "0"; // not use
						}
					}
				});

		setHsmUsedRBtn();
		setHsmTypeRBtn();
		setProviderUserRBtn();
		swxaIniPathTextField.setText(ct4xConfig.getHsm().getSwsds());
		setCaTableValue();
	}

	/**
	 * @Title: setHsmUsedRBtn
	 * @Description: 根据配置文件设置密码机是否使用
	 */
	public void setHsmUsedRBtn() {
		switch (ct4xConfig.getHsm().getUsed()) {
			case "1":
				hsmUsedYesRBtn.setSelected(true);
				break;

			default:
				hsmUsedNoRBtn.setSelected(true);
				break;
		}
	}

	/**
	 * @Title: setHsmTypeRBtn
	 * @Description: 根据配置文件设置密码机类型
	 */
	public void setHsmTypeRBtn() {
		switch (ct4xConfig.getHsm().getType()) {
			case "0":
				hsmTypePkiRBtn.setSelected(true);
				break;
			case "1":
				hsmTypeRBtn.setSelected(true);
				break;
			default:
				break;
		}
	}

	/**
	 * @Title: setProviderUserRBtn
	 * @Description: 根据配置文件设置Provider是否使用
	 */
	public void setProviderUserRBtn() {
		switch (ct4xConfig.getProvider().getUsed()) {
			case "1":
				providerUsedYesRBtn.setSelected(true);
				break;

			default:
				providerUsedNoRBtn.setSelected(true);
				break;
		}
	}

	/**
	 * @Title: setCaTableValue
	 * @Description: 根据配置文件设置CA列表
	 */
	public void setCaTableValue() {
		table = (TableView) ct4xInfoView.getChildren().get(22);
		TableColumn<?, ?> idCol = table.getColumns().get(0);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortable(false);

		idCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<?, ?> nameCol = table.getColumns().get(1);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setSortable(false);

		nameCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<?, ?> typeCol = table.getColumns().get(2);
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		typeCol.setSortable(false);
		typeCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<?, ?> indexCol = table.getColumns().get(3);
		indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
		indexCol.setSortable(false);
		indexCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<?, ?> valueCol = table.getColumns().get(4);
		valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
		valueCol.setSortable(false);

		TableColumn<?, ?> rootCertCol = table.getColumns().get(5);
		rootCertCol.setCellValueFactory(new PropertyValueFactory<>("rootcert"));
		rootCertCol.setSortable(false);

		List<CaTableCell> caList = CaConvertUtil.caSet2CaTableCell(ct4xConfig.getCaset());

		table.setItems(FXCollections.observableArrayList(caList));
	}

	/**
	 * swxa.ini 浏览按钮
	 */
	@FXML
	private void handleIniBrowse() {
		File file = FileChooserUtil.openFileChooser(ct4x, "swsds.ini files (*.ini)", "*.ini");
		if (file != null) {
			swxaIniPathTextField.setText(file.getAbsolutePath());
		}

	}

	/**
	 * 保存信息按钮
	 */
	@FXML
	private void handleSave() {
		// HSM
		ct4xConfig.getHsm().setUsed(hsmUsedFlagString);
		ct4xConfig.getHsm().setType(hsmTypeFlagString);
		ct4xConfig.getHsm().setSwsds(swxaIniPathTextField.getText().trim());

		// provider
		ct4xConfig.getProvider().setUsed(providerUsedFlagString);

		String xmlStr = null;
		try {
			xmlStr = JaxbConvertUtil.beanToXml(ct4xConfig, Ct4xConfig.class);
		} catch (JAXBException e) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(), "错误", "配置信息转XML失败", e.getMessage());
			return;
		}

		try {
			JaxbConvertUtil.writeFile(xmlStr);
		} catch (IOException e) {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(), "错误", "保存XML文件失败", e.getMessage());
			return;
		}
		DialogUtil.infoDialig(ct4x.getPrimaryStage(), "成功", "保存XML文件成功.");
	}

	/**
	 * 删除选中行按钮
	 */
	@FXML
	private void handleDeleteCA() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		String xmlStr;
		if (selectedIndex >= 0) {
			table.getItems().remove(selectedIndex);
			ct4xConfig.getCaset().remove(selectedIndex);

			try {
				xmlStr = JaxbConvertUtil.beanToXml(ct4xConfig, Ct4xConfig.class);
			} catch (JAXBException e) {
				DialogUtil.errorDialig(ct4x.getPrimaryStage(), "错误", "CA信息转XML失败", e.getMessage());
				return;
			}

			try {
				JaxbConvertUtil.writeFile(xmlStr);
			} catch (IOException e) {
				DialogUtil.errorDialig(ct4x.getPrimaryStage(), "错误", "保存XML文件失败", e.getMessage());
				return;
			}
			DialogUtil.infoDialig(ct4x.getPrimaryStage(), "成功", "保存XML文件成功.");
		} else {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(), "错误", "未选中错误", "请先选中表格中的某行数据.");
		}
	}

	/**
	 * 添加CA信息
	 *
	 * @throws IOException
	 */
	@FXML
	private void handleAddCA() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("./CaInfo.fxml"));

		Stage stage = new Stage(StageStyle.DECORATED);
		Pane pane=(Pane) loader.load();
		Scene caInfoScene =new Scene(pane);
		stage.setScene(caInfoScene);

		CaInfoController controller = loader.<CaInfoController>getController();

		controller.setMainApp(stage,pane);

		stage.show();

	}

	/**
	 * 查看选中CA信息
	 *
	 * @throws IOException
	 */
	@FXML
	private void handleViewSelectCA() throws IOException {

		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		String xmlStr;
		if (selectedIndex >= 0) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("./CaInfo.fxml"));

			Stage stage = new Stage(StageStyle.DECORATED);
			Pane pane=(Pane) loader.load();
			Scene caInfoScene =new Scene(pane);
			stage.setScene(caInfoScene);

			CaInfoController controller = loader.<CaInfoController>getController();

			CaTableCell caTableCell = table.getItems().get(selectedIndex);

			controller.setMainApp(stage,pane);
			controller.initData(caTableCell);

			stage.show();
		} else {
			DialogUtil.errorDialig(ct4x.getPrimaryStage(), "错误", "未选中错误", "请先选中表格中的某行数据.");
		}
	}

}
