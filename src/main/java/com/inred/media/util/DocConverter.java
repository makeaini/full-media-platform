package com.inred.media.util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * doc docx格式转换
 */
public class DocConverter {
	private static final int environment = 2;// 环境 1：windows 2:linux
	private String fileString;// (只涉及pdf2swf路径问题)
	private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置
	private String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;
	
	
	public static void main(String[] args) throws Exception {
//		DocConverter dc=new DocConverter();
//		dc.doc2pdf("/Users/mac/Music/出勤－中赤.xlsx");
		
	//	dc.pdf2swf();
	}
	
	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	public boolean doc2pdf(String fileString) throws Exception {
		
		this.fileString = fileString;
		fileName = fileString.substring(0, fileString.lastIndexOf("."));//路径
		
		docFile = new File(fileString);
		pdfFile = new File(fileName + ".pdf");
		
		
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				///Applications/OpenOffice.app/Contents
				//./soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"
				OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, pdfFile);
					// close the connection
					connection.disconnect();
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");
					return true;
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，openoffice服务未启动！****");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
		return false;
	}
	
	
	/**
	 * 转换成 swf
	 */
	@SuppressWarnings("unused")
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (environment == 1) {// windows环境处理
					try {
						Process p = r.exec("D:/SWFTools/pdf2swf.exe "+ pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}

					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2) {// linux环境处理
					try {
						Process p = r.exec("/Users/mac/Downloads/swftools/swftools-0.9.2/src/pdf2swf.c " + pdfFile.getPath()
								+ " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				System.out.println("****pdf不存在,无法转换****");
			}
		} else {
			System.out.println("****swf已经存在不需要转换****");
		}
	}

	static String loadStream(InputStream in) throws IOException {

		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();

		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}

		return buffer.toString();
	}


	/**
	 * 返回文件路径
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}

	}
	/**
	 * 设置输出路径
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"),
					fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}

}
