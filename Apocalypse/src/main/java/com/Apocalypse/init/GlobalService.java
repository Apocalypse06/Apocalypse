package com.Apocalypse.init;

import javax.servlet.http.Part;

public class GlobalService {
	//public static final int    RECORDS_PER_PAGE = 3;
	public static final String host = "127.0.0.1";
	//public static final String host = "192.168.11.22";
	public static final String USERID = "root";
	public static final String PASSWORD = "a53313158";
	public static final String SYSTEM_NAME = "天啟書城";
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/projectDB";
	public static final int IMAGE_FILENAME_LENGTH = 20;
	public static final String DB_URL = "jdbc:sqlserver://" + GlobalService.host + ":1433;databaseName=IREAD" ;
	public static final String DB_URLMySQL = "jdbc:mysql://" + GlobalService.host + "/IREAD?useUnicode=yes&characterEncoding=utf8" ;
	public static final String SERVER_PATH = "D:/_JSP/tomcat8/webapps/";
	public static final String IMAGES_PATH = SERVER_PATH + "Apocalypse/images/";
	public static final String HEAD_SHOT_PATH = IMAGES_PATH + "member/";
	public static final String SURFACE_PLOT_PATH = IMAGES_PATH + "book/";
	public static final String CHAPTER_PATH = SERVER_PATH + "/Apocalypse/chapter/";
	public static final int RANKING_DISPLAY_LIMIT = 5 ;
	
	//public static final String KEY = "KittySnoopyMicky";  // 16, 24, 32
	
	public static String getFileType(final Part part) {
		String fileType = part.getSubmittedFileName();
		return fileType.substring(fileType.lastIndexOf("."));
	}
	
//	public static String getFileName(final Part part) {
//		for (String content : part.getHeader("content-disposition").split(";")) {
//			if (content.trim().startsWith("filename")) {
//				return content.substring(content.indexOf('=') + 1).trim()
//						.replace("\"", "");
//			}
//		}
//		return null;
//	}
	
}
