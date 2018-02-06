package com.yy.bjtours.modules.sys.utils;

import java.util.ResourceBundle;

public class PropertiesUtils {
//	private static AppLogger logger = new AppLogger(PropertiesUtils.class);
	public static String getVaule(String bundle, String key){
		try {
			ResourceBundle rb = ResourceBundle.getBundle(bundle);
			return rb.getString(key);
		} catch (Exception e) {
//			logger.error("PropertiesUtil getValue error", e);
			 return null;
		}
	}

}
