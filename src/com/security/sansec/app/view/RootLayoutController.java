package com.security.sansec.app.view;


import com.security.sansec.app.CT4X;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController {

	private CT4X ct4x;

	/**
	 *  Is called by the main application to give a reference back to itself.
	 * @param mainApp
	 */
	public void setMainApp(CT4X ct4x){
		this.ct4x = ct4x;
	}


    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert dlg = new Alert(AlertType.INFORMATION);
    	dlg.setTitle("Example");
        dlg.getDialogPane().setContentText("V1");
        dlg.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
