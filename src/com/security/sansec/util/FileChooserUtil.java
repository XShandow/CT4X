package com.security.sansec.util;

import java.io.File;

import com.security.sansec.main.CT4X;

import javafx.stage.FileChooser;

public class FileChooserUtil {

	/**
	 *
	 * @Title: openFileChooser
	 * @Description: 打开文件选择器
	 * @param: @param ct4x
	 * @param: @param message	提示信息 如：swxaINI files (*.ini)
	 * @param: @param suffix	文件后缀	如：*.ini
	 * @return: void
	 * @throws
	 */
	public static File openFileChooser(CT4X ct4x,String message,String suffix){

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(message, suffix);

		fileChooser.getExtensionFilters().add(extensionFilter);

		File file = fileChooser.showOpenDialog(ct4x.getPrimaryStage());
		return file;

	}
}
