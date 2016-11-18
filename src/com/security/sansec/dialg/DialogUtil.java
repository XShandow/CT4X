package com.security.sansec.dialg;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class DialogUtil {

	public static void infoDialig(String title,String message){
		Alert dlg = new Alert(AlertType.INFORMATION);
    	String optionalMasthead = title;
    	dlg.getDialogPane().setHeaderText(optionalMasthead);
    	dlg.getDialogPane().setContentText(message);
    	Dialog<?> dialog = dlg;
    	dialog.initStyle(StageStyle.UNDECORATED);
        dlg.show();
	}

	public static void errorDialig(String title,String message){
		Alert dlg = new Alert(AlertType.ERROR);
    	String optionalMasthead = title;
    	dlg.getDialogPane().setHeaderText(optionalMasthead);
    	dlg.getDialogPane().setContentText(message);
    	Dialog<?> dialog = dlg;
    	dialog.initStyle(StageStyle.UNDECORATED);
        dlg.show();
	}
}
