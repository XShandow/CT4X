package com.security.sansec.index.ct4x_info.util;

import java.util.ArrayList;
import java.util.List;

import com.security.conf.ct4x_model.Ca;
import com.security.conf.ct4x_model.Ct4xConfig;
import com.security.sansec.index.ct4x_info.model.CaTableCell;

/**
 *
 * All rights Reserved, Designed By SANSEC
 *
 * @Title: CaConvertUtil.java
 * @Package com.security.sansec.index.ct4x_info.util
 * @Description: CaSet工具类
 * @author: xujianchao
 * @date: 2017年11月9日 上午10:52:49
 * @version V1.0
 * @Copyright: 2017 Inc. All rights reserved.
 */
public class CaConvertUtil {

	/**
	 * @Title: caSet3CaTableCell @Description:
	 * 配置文件中CaSet转列表中显示的CA对象 @param: @param caSet @param: @return @return:
	 * List<CaTableCell> @throws
	 */
	public static List<CaTableCell> caSet2CaTableCell(List<Ca> caSet) {
		List<CaTableCell> caTableCells = new ArrayList<>();
		for (int i = 0; i < caSet.size(); i++) {
			CaTableCell caTableCell = new CaTableCell();
			Ca ca = caSet.get(i);
			caTableCell.setId(String.valueOf(i));
			caTableCell.setName(ca.getName());
			caTableCell.setType("0".equals(ca.getType()) ? "RSA" : "SM2");
			String usedFlag = Ct4xConfig.getInstance().getHsm().getUsed();
			if (usedFlag.equals("1")) {
				caTableCell.setIndex(ca.getKeypair().getIndex());
			} else {
				caTableCell.setValue(ca.getKeypair().getValue());
			}

			caTableCell.setRootcert(ca.getRootcert());
			caTableCells.add(caTableCell);
		}

		return caTableCells;
	}

}
