package com.security.sansec.app.view;


import com.security.sansec.app.CT4X;
import com.security.sansec.dialg.DialogUtil;
import com.security.sansec.pfx.view.PFX2JKSController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Root_CT4XController {


	private CT4X ct4x;

	public void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
	}

	/**
	 *���ڰ�ť����¼�
	 */
    @FXML
    private void handleAbout() {
    	WelcomeController welcomeController = new WelcomeController(ct4x);
		welcomeController.handleWelcome();
    }

    /**
     *�˳���ť����¼�
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
	 *PFX->JKS��ť ����¼�
	 *PFX2JKS.fxml �滻ԭ����Pane
	 */
	@FXML
	private void handlePFX2JKS() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CT4X.class.getResource("../pfx/view/PFX2JKS.fxml"));
			AnchorPane pfx2jksView =loader.load();

			/*�����µ�Pane��ʾ*/
			 ct4x.getCT4XPane().setCenter(pfx2jksView);

			 PFX2JKSController controller = loader.getController();
			 controller.setMainApp(this.ct4x);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
