package com.security.sansec.app;

import com.security.sansec.app.view.Root_CT4XController;
import com.security.sansec.app.view.RootLayoutController;

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
	private BorderPane rootLayout;
	public AnchorPane root_ct4x;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CT4X");

		this.primaryStage.getIcons().add(new Image("file:resources/images/appIcon.png"));

		initRootLayout();
		showCT4XView();
	}

	/**
	 *显示菜单栏 RootLayout.fxml
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *显示功能窗口 Root_CT4X.fxml
	 */
	public void showCT4XView() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("view/Root_CT4X.fxml"));
			root_ct4x =loader.load();

			SplitPane aPane = (SplitPane) root_ct4x.getChildren().get(0);
			AnchorPane anchorPane = (AnchorPane) aPane.getItems().get(1);
			anchorPane.getChildren().add(getWelcomePage());

			rootLayout.setCenter(root_ct4x);

			Root_CT4XController controller = loader.getController();
			controller.setMainApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VBox getWelcomePage(){
		VBox vBox = new VBox();
        ImageView imgView = new ImageView();
        imgView.setStyle("-fx-image: url('../../resources/images/XS.png');");
        StackPane pane = new StackPane();
        pane.setPrefHeight(207);
        pane.setStyle("-fx-background-image: url('org/controlsfx/samples/bar.png');"
                + "-fx-background-repeat: repeat-x;");
        pane.getChildren().add(imgView);
        Label label = new Label();
        label.setWrapText(true);
        StringBuilder desc = new StringBuilder();
        desc.append("CT4X:Crypto Tool for Xshandow\n");
        desc.append("\n");
        desc.append("PKCS12:\n");
        desc.append("\tPFX->JKS:将PFX文件转换成JKS文件");
        label.setText(desc.toString());
        label.setStyle("-fx-font-size: 1.5em; -fx-padding: 20 0 0 5;");
        vBox.getChildren().addAll(pane, label);

        return vBox;
	}



	public static void main(String[] args) {
		launch(args);
	}
}
