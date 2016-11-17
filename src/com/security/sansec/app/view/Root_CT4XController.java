package com.security.sansec.app.view;

import com.security.sansec.app.CT4X;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;


public class Root_CT4XController {

	private CT4X ct4x;

	public Root_CT4XController() {
	}

	public void setMainApp(CT4X ct4x) {
		this.ct4x = ct4x;

	}

	/**
	 *PFX->JKS按钮
	 *PFX2JKS.fxml 替换原来的Pane
	 */
	@FXML
	private void handlePFX2JKS() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../pfx/view/PFX2JKS.fxml"));
			AnchorPane pfx2jksView =loader.load();

			/*控制新的Pane显示*/
			SplitPane aPane = (SplitPane) ct4x.root_ct4x.getChildren().get(0);
			AnchorPane anchorPane = (AnchorPane) aPane.getItems().get(1);
			anchorPane.getChildren().remove(0);			//删除原有Pane
			anchorPane.getChildren().add(pfx2jksView);	//添加PFX2JKS Pane

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
