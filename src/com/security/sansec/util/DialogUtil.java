package com.security.sansec.util;

import com.security.sansec.main.CT4X;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * All rights Reserved, Designed By SANSEC
 * @Title:  DialogUtil.java
 * @Package com.security.sansec.dialg
 * @Description:    对话框类，前端提示对话框
 * @author: xujianchao
 * @date:   2017年11月8日 上午10:40:14
 * @version V1.0
 * @Copyright: 2017  Inc. All rights reserved.
 */
public class DialogUtil {
	/**
	 *
	 * @Title: infoDialig
	 * @Description: 信息级别的对话框
	 * @param: @param title
	 * @param: @param message
	 * @return: void
	 * @throws
	 */
	public static void infoDialig(Stage owner, String title,String headerText){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(owner);
		alert.setTitle(title);
		alert.setHeaderText(headerText);

		alert.showAndWait();
	}

	/**
	 *
	 * @Title: errorDialig
	 * @Description: 错误级别的对话框
	 * @param: @param title
	 * @param: @param message
	 * @return: void
	 * @throws
	 */
	public static void errorDialig(Stage owner, String title,String headerText,String message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(owner);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(message);

		alert.showAndWait();
	}

	/**
	 *
	 * @Title: warningDialig
	 * @Description: 警告级别的对话框
	 * @param: @param ct4x
	 * @param: @param title
	 * @param: @param headerText
	 * @param: @param message
	 * @return: void
	 * @throws
	 */
	public static void warningDialig(Stage owner, String title,String headerText,String message){
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(owner);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(message);

		alert.showAndWait();
	}

	/**
	 *
	 * @Title: confirmationDialig
	 * @Description: 确认级别对话框
	 * @param: @param ct4x
	 * @param: @param title
	 * @param: @param headerText
	 * @param: @param message
	 * @return: void
	 * @throws
	 */
	public static void confirmationDialig(Stage owner, String title,String headerText,String message){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(owner);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(message);

		alert.showAndWait();
	}
}
