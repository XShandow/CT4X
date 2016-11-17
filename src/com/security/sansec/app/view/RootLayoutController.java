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
	 *关于按钮点击事件
	 */
    @FXML
    private void handleAbout() {
    	Alert dlg = new Alert(AlertType.INFORMATION);
    	String optionalMasthead = "关于";
    	dlg.getDialogPane().setHeaderText(optionalMasthead);
    	dlg.getDialogPane().setContentText("Crypto Tool for Xshandow V[?]");
    	Dialog<?> dialog = dlg;
    	dialog.initStyle(StageStyle.UNDECORATED);
        dlg.show();
    }

    /**
     *退出按钮点击事件
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
