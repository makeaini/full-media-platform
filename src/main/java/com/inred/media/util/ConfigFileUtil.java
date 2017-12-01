package com.inred.media.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ConfigFileUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConfigFileUtil.class);
	private static Properties properties;
	static{
		readProperty("file.properties");
	}

	/**
	 * 读取Properties文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return properties 文件类
	 */
	public static Properties readProperty(String filePath) {
		properties=new Properties();
		Resource file = null;
		InputStream propertiesInputStream = null;
		try {
			file = new ClassPathResource(filePath);
			propertiesInputStream = file.getInputStream();
			properties.load(propertiesInputStream);
		} catch (Exception e) {
			LOG.error("读取Properties文件失败！", e);
		} finally {
			try {
				propertiesInputStream.close();
			} catch (IOException e) {
				LOG.error("关闭流失败！", e);
			}
		}
		return properties;
	}
	public static String getMusicType(){
		return properties.getProperty("music");
	}
	public static String getDocumentType(){
		return properties.getProperty("document");
	}
	public static String getVideoType(){
		return properties.getProperty("video");
	}
	public static String getCompressionType(){
		return properties.getProperty("compression");
	}
	public static String getPictureType(){
		return properties.getProperty("picture");
	}
	/**
	 * 个人网盘路径
	 * @return
	 */
	public static String getPersonalPath(){
		return properties.getProperty("personalPath");
	}
	/**
	 * 企业网盘路径
	 * @return
	 */
	public static String getEnterprisePath(){
		String enterprisePath=null;
		try {
			enterprisePath = new String(properties.getProperty("enterprisePath").getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(),e);
		}
		return enterprisePath;
	}
	
	/**
	 * @return
	 */
	public static String getTempCompressorPath(){
		String temcompessorPath=null;
		try {
			temcompessorPath = new String(properties.getProperty("tempCompressorPath").getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(),e);
		}
		return temcompessorPath;
	}
	
}
