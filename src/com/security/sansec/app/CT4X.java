package com.security.sansec.app;

import com.security.sansec.app.view.Root_CT4XController;
import com.security.sansec.app.view.WelcomeController;
import com.security.sansec.pfx.view.PFX2JKSController;
import com.security.sansec.app.view.Root_CT4XController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CT4X extends Application {

	private Stage primaryStage;
	private BorderPane root_ct4x;


	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public BorderPane getCT4XPane() {
		return root_ct4x;
	}
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CT4X");
		this.primaryStage.setResizable(false);

		this.primaryStage.getIcons().add(new Image("file:resources/images/appIcon.png"));

		initRootCT4X();
	}

	/**
	 * 初始化主窗口
	 */
	public void initRootCT4X() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("view/Root_CT4X.fxml"));
			root_ct4x = loader.load();

			Scene scene = new Scene(root_ct4x);
			primaryStage.setScene(scene);
			primaryStage.show();

			Root_CT4XController controller = loader.getController();
			controller.setMainApp(this);

			WelcomeController welcomeController = new WelcomeController(this);
			welcomeController.handleWelcome();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
