package com.inred.media.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inred.media.model.FileType;
import com.inred.media.model.PathResultBean;

/**
 * 
 */
public class PathUtil {
	private static final Logger LOG = LoggerFactory.getLogger(PathUtil.class);
	public static final String DOT = ".";
	public static final String SLASH_ONE = "/";
	public static final String SLASH_TWO = "\\";

	/**
	 * 获取没有扩展名的文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getWithoutExtension(String fileName) {
		String ext = StringUtils.substring(fileName, 0,
				StringUtils.lastIndexOf(fileName, DOT));
		return StringUtils.trimToEmpty(ext);
	}

	/**
	 * 获取扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(fileName, DOT))
			return StringUtils.EMPTY;
		String ext = StringUtils.substring(fileName,
				StringUtils.lastIndexOf(fileName, DOT));
		return StringUtils.trimToEmpty(ext);
	}

	/**
	 * 判断是否同为扩展名
	 * 
	 * @param fileName
	 * @param ext
	 * @return
	 */
	public static boolean isExtension(String fileName, String ext) {
		return StringUtils.equalsIgnoreCase(getExtension(fileName), ext);
	}

	/**
	 * 判断是否存在扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean hasExtension(String fileName) {
		return !isExtension(fileName, StringUtils.EMPTY);
	}

	/**
	 * 得到正确的扩展名
	 * 
	 * @param ext
	 * @return
	 */
	public static String trimExtension(String ext) {
		return getExtension(DOT + ext);
	}

	/**
	 * 向path中填充扩展名(如果没有或不同的话)
	 * 
	 * @param fileName
	 * @param ext
	 * @return
	 */
	public static String fillExtension(String fileName, String ext) {
		fileName = replacePath(fileName + DOT);
		ext = trimExtension(ext);
		if (!hasExtension(fileName)) {
			return fileName + getExtension(ext);
		}
		if (!isExtension(fileName, ext)) {
			return getWithoutExtension(fileName) + getExtension(ext);
		}
		return fileName;
	}

	/**
	 * 判断是否是文件PATH
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFile(String fileName) {
		return hasExtension(fileName);
	}

	/**
	 * 判断是否是文件夹PATH
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFolder(String fileName) {
		return !hasExtension(fileName);
	}

	public static String replacePath(String path) {
		return StringUtils.replace(StringUtils.trimToEmpty(path), SLASH_ONE,
				SLASH_TWO);
	}

	/**
	 * 链接PATH前处理
	 * 
	 * @param path
	 * @return
	 */
	public static String trimLeftPath(String path) {
		if (isFile(path))
			return path;
		path = replacePath(path);
		String top = StringUtils.left(path, 1);
		if (StringUtils.equalsIgnoreCase(SLASH_TWO, top))
			return StringUtils.substring(path, 1);
		return path;
	}

	/**
	 * 链接PATH后处理
	 * 
	 * @param path
	 * @return
	 */
	public static String trimRightPath(String path) {
		if (isFile(path))
			return path;
		path = replacePath(path);
		String bottom = StringUtils.right(path, 1);
		if (StringUtils.equalsIgnoreCase(SLASH_TWO, bottom))
			return StringUtils.substring(path, 0, path.length() - 2);
		return path + SLASH_TWO;
	}

	/**
	 * 链接PATH前后处理，得到准确的链接PATH
	 * 
	 * @param path
	 * @return
	 */
	public static String trimPath(String path) {
		path = StringUtils.replace(StringUtils.trimToEmpty(path), SLASH_ONE,
				SLASH_TWO);
		path = trimLeftPath(path);
		path = trimRightPath(path);
		return path;
	}

	/**
	 * 通过数组完整链接PATH
	 * 
	 * @param paths
	 * @return
	 */
	public static String bulidFullPath(String... paths) {
		StringBuffer sb = new StringBuffer();
		for (String path : paths) {
			sb.append(trimPath(path));
		}
		return sb.toString();
	}

	/**
	 * 文件名取文件的类型
	 * 
	 * @param filename
	 * @return 当前文件的类型
	 */
	public static FileType getFileType(String filename) {

		String fileName = getExtension(filename).toLowerCase();
		if (ConfigFileUtil.getMusicType().contains(fileName)) {
			return FileType.MUSIC;
		} else if (ConfigFileUtil.getDocumentType().contains(fileName)) {
			return FileType.DOCUMENT;
		} else if (ConfigFileUtil.getPictureType().contains(fileName)) {
			return FileType.PICTURE;
		} else if (ConfigFileUtil.getVideoType().contains(fileName)) {
			return FileType.VIDEO;
		} else if (ConfigFileUtil.getCompressionType().contains(fileName)) {
			return FileType.COMPRESSION;
		}
		return FileType.OTHER;
	}

	/**
	 * 创建文件夹的路径
	 * 
	 * @param userId
	 *            用户的Id
	 * @param schoolId
	 *            用户学校的id
	 * @param parentPath
	 *            父路径path，如果为空就在根目录下面建立
	 * @param folderName
	 *            新建文件的名称
	 */
	public static PathResultBean getFolderPath(String userId, String schoolId,
			String parentPath, String folderName) {
		PathResultBean bean = new PathResultBean();
		String localPath = null;
		try {
			localPath = new String(ConfigFileUtil.getPersonalPath().getBytes(
					"iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("文件夹名字编码有问题");
			LOG.error(e.getMessage(), e);
		}

		// 先找到父级的路径
		if (!StringUtils.isEmpty(parentPath)) {
			StringBuffer originalParentPath = new StringBuffer(localPath);
			StringBuffer relativelyParentPath = new StringBuffer();
			relativelyParentPath.append(parentPath).append(File.separator)
					.append(folderName);
			originalParentPath.append(parentPath).append(File.separator)
					.append(folderName);
			LOG.info("父的relativelyPath为:{}", relativelyParentPath);
			LOG.info("父的originalPath为:{}", originalParentPath);
			bean.setOriginalPath(originalParentPath.toString());
			bean.setRelativelyPath(relativelyParentPath.toString());
		} else {
			// 这个是本地文件的创建的绝对路径
			StringBuffer originalPath = new StringBuffer(localPath)
					.append(File.separator);
			originalPath.append(schoolId).append(File.separator).append(userId);
			// 相对路径，存在数据库里面的路径
			StringBuffer relativelyPath = new StringBuffer(File.separator)
					.append(schoolId).append(File.separator).append(userId);

			originalPath.append(File.separator).append(folderName);
			relativelyPath.append(File.separator).append(folderName);
			LOG.info("relativelyPath为:{}", relativelyPath);
			LOG.info("originalPath为:{}", originalPath);
			bean.setOriginalPath(originalPath.toString());
			bean.setRelativelyPath(relativelyPath.toString());
		}
		return bean;
	}

	/**
	 * 创建文件夹的路径
	 * 
	 * @param userId
	 *            用户的Id
	 * @param schoolId
	 *            用户学校的id
	 * @param parentPath
	 *            父路径path，如果为空就在根目录下面建立
	 * @param folderName
	 *            新建文件的名称
	 */
	public static PathResultBean getFilePath(String userId, String schoolId,
			String parentPath) {
		PathResultBean bean = new PathResultBean();
		String localPath = null;
		try {
			localPath = new String(ConfigFileUtil.getPersonalPath().getBytes(
					"iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("文件夹名字编码有问题");
			LOG.error(e.getMessage(), e);
		}
		// 这个是本地文件的创建的绝对路径
		StringBuffer originalPath = new StringBuffer(localPath)
				.append(File.separator);
		originalPath.append(schoolId).append(File.separator).append(userId);
		// 相对路径，存在数据库里面的路径
		StringBuffer relativelyPath = new StringBuffer(File.separator)
				.append(schoolId).append(File.separator).append(userId);
		// 先找到父级的路径
		if (!StringUtils.isEmpty(parentPath)) {
			StringBuffer originalParentPath = new StringBuffer(localPath);
			StringBuffer relativelyParentPath = new StringBuffer();
			relativelyParentPath.append(parentPath).append(File.separator);
			originalParentPath.append(parentPath).append(File.separator);
			relativelyPath.append(File.separator).append(parentPath);

			LOG.info("父的relativelyPath为:{}", relativelyParentPath);
			LOG.info("父的originalPath为:{}", originalParentPath);
			bean.setOriginalPath(originalParentPath.toString());
			bean.setRelativelyPath(relativelyParentPath.toString());
		} else {
			bean.setRelativelyPath(relativelyPath.toString());
			LOG.info("relativelyPath为:{}", relativelyPath);
			LOG.info("originalPath为:{}", originalPath);
			bean.setOriginalPath(originalPath.toString());
			bean.setRelativelyPath(relativelyPath.toString());
		}

		return bean;
	}

	/**
	 * 创建文件夹的路径
	 * 
	 * @param userId
	 *            用户的Id
	 * @param schoolId
	 *            用户学校的id
	 * @param parentPath
	 *            父路径path，如果为空就在根目录下面建立
	 * @param folderName
	 *            新建文件的名称
	 */
	public static PathResultBean getentErprisePath(String schoolId) {
		PathResultBean bean = new PathResultBean();
		String localPath = ConfigFileUtil.getEnterprisePath();
		// 这个是本地文件的创建的绝对路径
		StringBuffer originalPath = new StringBuffer(localPath)
				.append(File.separator);
		originalPath.append(schoolId).append(File.separator);
		// 相对路径，存在数据库里面的路径
		StringBuffer relativelyPath = new StringBuffer(File.separator)
				.append(schoolId).append(File.separator);

		LOG.info("relativelyPath为:{}", relativelyPath);
		LOG.info("originalPath为:{}", originalPath);
		bean.setOriginalPath(originalPath.toString());
		bean.setRelativelyPath(relativelyPath.toString());
		return bean;
	}

	public static void main(String[] args) throws IOException {
		PathResultBean bean = getentErprisePath("111");
		System.out.println(bean.getRelativelyPath());
		System.out.println(bean.getOriginalPath());
		// String s=bean.getOriginalPath().replace("E:\\个人网盘", "");
		// FileOperationUtil.createLocalFolder(bean.getOriginalPath());
	}

}
