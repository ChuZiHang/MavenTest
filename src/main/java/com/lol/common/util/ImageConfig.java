package com.lol.common.util;


import java.io.InputStream;
import java.util.Properties;


/**
 * 一个简单的的配置文件。
 */
public class ImageConfig {

	private static Properties properties = null;
	
	private static String CONFIG_FILE = "/imgthumb.properties";
	
	static {
	
		InputStream in = null;
		try {
			in = ImageConfig.class.getResourceAsStream(CONFIG_FILE);
			properties = new Properties();
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
	//img.thumb.server 访问thumbimg的http服务器url前缀
	public static String IMG_THUMB_SERVER = ImageConfig.getProperty("img.thumb.server");
	//img.thumb.store 存储thumbimg的目录前缀
	public static String IMG_THUMB_STORE = ImageConfig.getProperty("img.thumb.store");
	//访问img的http服务器url前缀
	public static String IMG_SERVER = ImageConfig.getProperty("img.server");
	//img.local.url 本地资源url前缀，在此前缀的，直接通过目录访问
	public static String IMG_STORE = ImageConfig.getProperty("img.store");
	//img.temp 上传临时文件目录
	public static String IMG_TEMP = ImageConfig.getProperty("img.temp");
	/**
	 * 获得一个属性名
	 */
	public static String getProperty(String name) {
		String property = properties.getProperty(name);
		if (property == null) {
			return null;
		} else {
			return property.trim();
		}
	}
}
