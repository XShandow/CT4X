package com.security.sansec.welcome.view;

import com.security.sansec.main.CT4X;
import com.security.sansec.welcome.model.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class WelcomeController {

	private CT4X ct4x;

	public WelcomeController(CT4X ct4x) {
		this.ct4x = ct4x;
	}

	private  ObservableList<Function> data =
			FXCollections.observableArrayList(
					new Function("PFX转JKS","将PFX文件转化成JKS文件"),
					new Function("自签名证书","使用内部密钥或随机密钥生成自签名证书"),
					new Function("条目List","显示KeyStore内部的条目列表，支持JKS和SWKS")
			);

	@FXML
	public void handleWelcome() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../welcome/view/Welcome.fxml"));
			AnchorPane welcomeView =loader.load();

			TableView<Function> table =(TableView)welcomeView.getChildren().get(1);


			TableColumn<?, ?> methodCol = table.getColumns().get(0);
			methodCol.setCellValueFactory(
					new PropertyValueFactory<>("method"));
			methodCol.setSortable(false);

			TableColumn<?, ?> infoCol = table.getColumns().get(1);
			infoCol.setCellValueFactory(
					new PropertyValueFactory<>("info"));
			infoCol.setSortable(false);

			table.setItems(data);

			/*控制新的Pane显示*/
			ct4x.getCT4XPane().setCenter(welcomeView);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
