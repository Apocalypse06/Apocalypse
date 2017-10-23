package com.Apocalypse.core.util;

import com.Apocalypse.init.GlobalService;

public class WTConst {

	public static final String host = "127.0.0.1";
	public static final String USERID = "root";
	public static final String PASSWORD = "password";
	public static final String SYSTEM_NAME = "天啟書城";
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/projectDB";
	public static final int IMAGE_FILENAME_LENGTH = 20;
	public static final String DB_URL = "jdbc:sqlserver://" + GlobalService.host + ":1433;databaseName=IREAD";
	public static final String DB_URLMySQL = "jdbc:mysql://" + GlobalService.host
			+ "/IREAD?useUnicode=yes&characterEncoding=utf8";
	public static final String SERVER_PATH = "D:/_JSP/tomcat8/webapps/";
	public static final String IMAGES_PATH = SERVER_PATH + "Apocalypse/images/";
	public static final String HEAD_SHOT_PATH = IMAGES_PATH + "member/";
	public static final String SURFACE_PLOT_PATH = IMAGES_PATH + "book/";
	public static final String CHAPTER_PATH = SERVER_PATH + "/Apocalypse/chapter/";
	public static final int RANKING_DISPLAY_LIMIT = 5;
	public static String WHERE = " where ";
	public static String AND = " and ";
	public static final String MOBILE_AREA_TBL_LIST = "mobileAreaTblList";
	public static final String MOBILE_AREA_DETAIL_TBL_LIST = "mobileAreaDetailTblList";
	public static final String AREA_TAG_CATEGORY_MAP = "areaTagCategoryMap";
	public static final String MOBILE_KEY_VALUE_MAP = "mobileKeyValueList";
	public static final String FORM_INFO_HELPER_MAP = "formInfoHelperMap";
	public static final String FORM_INFO_CLASS_MAP = "formInfoClassMap";
	public static final String LBL_TYPE_NORMAL = "Normal";
	public static final String LBL_TYPE_PARENT = "Parent";
	public static final String LBL_TYPE_CHILD = "Child";
	public static final String wmbWord = "";
	public static final String PUSH_KEY = "pushKey";
	public static final String SYSTEM_NOTIFY = "systemNotify";
	public static final String WORK_LIST = "workList";

}
