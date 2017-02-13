package com.haoyu.sip.file.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonConfig {
	/*
	 * 配置文件路径
	 */
	private static final String cfgFile = "/common.properties";

	/**
	 * 读出的属性
	 */
	private static Properties properties;

	static {
		properties = new Properties();
		InputStream is = CommonConfig.class.getResourceAsStream(cfgFile);
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("读取common.propertise属性文件失败，请检查该属性文件是否存在！");
		}
	}

	/**
	 * 返回一个属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @return 返回指定属性名的值
	 */
	public static String getProperty(String propertyName) {
		if (properties == null) {
			throw new RuntimeException("系统错误：读取common.properties属性失败！");
		} else {
			return properties.getProperty(propertyName);
		}
	}
}
