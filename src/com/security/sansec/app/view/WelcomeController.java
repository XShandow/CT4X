package com.security.sansec.app.view;

import com.security.sansec.app.CT4X;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class WelcomeController {

	private CT4X ct4x;

	public WelcomeController(CT4X ct4x) {
		this.ct4x = ct4x;
	}

	@FXML
	public void handleWelcome() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("./view/Welcome.fxml"));
			AnchorPane welcomeView =loader.load();

			/*控制新的Pane显示*/
			 ct4x.getCT4XPane().setCenter(welcomeView);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
