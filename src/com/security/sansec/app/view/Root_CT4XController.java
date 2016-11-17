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
	 *PFX->JKS��ť
	 *PFX2JKS.fxml �滻ԭ����Pane
	 */
	@FXML
	private void handlePFX2JKS() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../pfx/view/PFX2JKS.fxml"));
			AnchorPane pfx2jksView =loader.load();

			/*�����µ�Pane��ʾ*/
			SplitPane aPane = (SplitPane) ct4x.root_ct4x.getChildren().get(0);
			AnchorPane anchorPane = (AnchorPane) aPane.getItems().get(1);
			anchorPane.getChildren().remove(0);			//ɾ��ԭ��Pane
			anchorPane.getChildren().add(pfx2jksView);	//���PFX2JKS Pane

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
