package com.security.sansec.app.view;


import com.security.sansec.app.CT4X;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;

public class RootLayoutController {

	private CT4X ct4x;

	public void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
	}

	/**
	 *���ڰ�ť����¼�
	 */
    @FXML
    private void handleAbout() {
    	Alert dlg = new Alert(AlertType.INFORMATION);
    	String optionalMasthead = "����";
    	dlg.getDialogPane().setHeaderText(optionalMasthead);
    	dlg.getDialogPane().setContentText("Crypto Tool for Xshandow V[?]");
    	Dialog<?> dialog = dlg;
    	dialog.initStyle(StageStyle.UNDECORATED);
        dlg.show();
    }

    /**
     *�˳���ť����¼�
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
