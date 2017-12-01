package com.inred.media.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.inred.media.model.FileUploadResultBean;

public class FileOperationUtil {
	private static final Logger LOG = LoggerFactory
			.getLogger(FileOperationUtil.class);

	
	public static File downloadFile(HttpServletResponse response,
			String fileName, String filePath,boolean bof) throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");
		//String filename=new String(fileName.getBytes("iso8859-1"),"utf-8");
		String filename=URLEncoder.encode(fileName, "utf-8");
		response.setHeader("Content-Disposition", "inline;fileName="
				+ filename);
		File file = new File(filePath);
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			inputStream = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
		
			// 这里主要关闭。
			try {
				os.flush();
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				if (os != null) {
					os.close();
					os = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);

			}
		}
		return file;
		// 返回值要注意，要不然就出现下面这句错误！
	}
	
	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param fileName
	 *            文件的名称
	 * @param filePath
	 *            文件的路径
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static File downloadFile(HttpServletResponse response,
			String fileName, String filePath) throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");
		//String filename=new String(fileName.getBytes("iso8859-1"),"utf-8");
		String filename=URLEncoder.encode(fileName, "utf-8");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ filename);
		File file = new File(filePath);
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			inputStream = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
		
			// 这里主要关闭。
			try {
				os.flush();
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				if (os != null) {
					os.close();
					os = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);

			}
		}
		return file;
		// 返回值要注意，要不然就出现下面这句错误！
	}


	/**
	 * 上传文件
	 * 
	 * @param path
	 * @param multipartFiles
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static FileUploadResultBean uploadFile(String path,
			MultipartFile[] multipartFiles) throws IllegalStateException,
			IOException {
		FileUploadResultBean bean = null;
		for (MultipartFile file : multipartFiles) {
			bean = new FileUploadResultBean();
			String fileName = file.getOriginalFilename();
			// 重命名当前的文件名
			String extensionName = PathUtil.getExtension(fileName);
			String targetName = RandomUtil.getUUID();
			String targetFileName = targetName.concat(extensionName);
			LOG.info("原文件的名称为:----->{},新的文件名是---->:{}", fileName,
					targetFileName);
			File targetFile = new File(path, targetFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			bean.setFileType(PathUtil.getFileType(fileName));
			bean.setExtension(extensionName);
			bean.setFileSize(file.getSize());
			bean.setFileSizeStr(FileOperationUtil.speedUtil(file.getSize()));
			bean.setSourceName(fileName);
			bean.setTargetName(targetFileName);
			bean.setGenerateName(targetName);
		}
		return bean;
	}

	/**
	 * 创建本地文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static boolean createLocalFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return file.mkdirs();
		}
		return false;
	}

	/**
	 * 删除本地文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteLocalFolder(String path) {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 删除本地文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteLocalFile(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 得到文件夹下面所有的文件，如果有子目录，继续迭代
	 * @param filePath 目录的位置
	 */
	public static List<String> getFiles(String filePath) {
		List<String> filelist = new ArrayList<String>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				filelist.addAll(getFiles(file.getAbsolutePath()));
			}
			filelist.add(file.getAbsolutePath());
		}
		return filelist;
	}
	
	
    /**
     * 单位计算
     * @param speed
     * @return
     */
    public static String speedUtil(long speed) {  
        //对结果进行格式化（保留小数点后的2位）  
        java.text.DecimalFormat format = new java.text.DecimalFormat("0.00");  
        //对结果进行格式化（不保留小数点后）  
        java.text.DecimalFormat format1 = new java.text.DecimalFormat(" ");  
        String res="";  
        //原始bit  
        double speedIn = speed;  
        //如果是bit那么直接返回bit  
   
            //如果比bit大，那么直接换算成KB   
            speedIn = speedIn /(1024*1);  
            if(speedIn < 1024){  
                String r="KB";  
                res=format1.format(speedIn)+" "+r;  
            }else{  
                //如果比KB大，那么直接换算成MB，当换算成MB的时候，需要保留小数点后两位  
                speedIn = speedIn /1024;  
                if(speedIn < 1024){  
                    String r="MB";  
                    res=format.format(speedIn)+" "+r;  
                }else{  
                    //如果比MB还大，那么换算成GB，同样保留后两位  
                    speedIn = speedIn /1024;  
                    String r="GB";  
                    res=format.format(speedIn)+" "+r;  
                }  
            }  
        return res;  
    }  


}
