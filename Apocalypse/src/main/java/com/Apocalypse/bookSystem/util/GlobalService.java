package com.Apocalypse.bookSystem.util;

public class GlobalService {
	public static final String host = "127.0.0.1";
	public static final String USERID = "root";
	public static final String PASSWORD = "a53313158";
	public static final String SYSTEM_NAME = "天啟書城";
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/MemberDB";
//	public static final int IMAGE_FILENAME_LENGTH = 20;
	public static final String DB_URL = "jdbc:sqlserver://" + GlobalService.host + ":1433;databaseName=IREAD" ;
	public static final String DB_URLMySQL = "jdbc:mysql://" + GlobalService.host + "/IREAD?useUnicode=yes&characterEncoding=utf8" ;
	public static final String SERVER_PATH = "D:/_JSP/tomcat8/webapps/";
	public static final String IMAGES_PATH = SERVER_PATH + "Booksystem/images/";
//	public static final String HEAD_SHOT_PATH = IMAGES_PATH + "member/";
//	public static final String SURFACE_PLOT_PATH = IMAGES_PATH + "book/";
	public static final String CHAPTER_PATH = SERVER_PATH + "Booksystem/chapter/";
	public static final int RANKING_DISPLAY_LIMIT = 5 ;

//	public static String getFileType(final Part part) {
//		String fileType = part.getSubmittedFileName();
//		return fileType.substring(fileType.lastIndexOf("."));
//	}
	
}
