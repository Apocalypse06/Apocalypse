package com.Apocalypse.bookSystem.initial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.Apocalypse.bookSystem.util.GlobalService;

public class ResetDatabase {
	
//	public static final String UTF8_BOM = "\uFEFF";  // 定義 UTF-8的BOM字元 
	public static int success_count = 0;
	public static int error_count = 0;
	
	
	public static void main(String[] args) {
		Connection con;
	    PreparedStatement pstmt;
	    Statement stmt;
	    String dropString;   
	    String createString;
	    String sql   = "";
	    String chapterPath = GlobalService.CHAPTER_PATH;
//	    String photoPath = GlobalService.HEAD_SHOT_PATH;
//	    String surfacePlotPath = GlobalService.SURFACE_PLOT_PATH;		
				
	    try {
	        	// 連上後端的資料庫
	        	con =  DriverManager.getConnection(
	        		   GlobalService.DB_URLMySQL, 
	        		   GlobalService.USERID, 
	        		   GlobalService.PASSWORD);
	        	// 建立Statement物件，以便傳送SQL命令到後端的資料庫
	        	stmt = con.createStatement();	          
	    	} catch(SQLException e) {
	    		System.err.println("存取資料庫有關的例外：" + e.getMessage() );
	    		e.printStackTrace() ;
	        return;
	    }
		
	    // 刪除舊有表格開始
	    // 定義刪除stars表格的SQL命令
	    dropString = "DROP Table stars " ;
	    executeUpdate(dropString, "刪除stars表格", stmt);	    
	    // 定義刪除trade_record表格的SQL命令
	    dropString = "DROP Table trade_record " ;
	    executeUpdate(dropString, "刪除trade_record表格", stmt);	    
	    // 定義刪除activity表格的SQL命令
	    dropString = "DROP Table activity " ;
	    executeUpdate(dropString, "刪除activity表格", stmt);	    
	    // 定義刪除last_record表格的SQL命令
	    dropString = "DROP Table last_record " ;
	    executeUpdate(dropString, "刪除last_record表格", stmt);	    
	    // 定義刪除focus_author表格的SQL命令
	    dropString = "DROP Table focus_author " ;
	    executeUpdate(dropString, "刪除focus_author表格", stmt);	    
	    // 定義刪除chapter_deadline表格的SQL命令
	    dropString = "DROP Table chapter_deadline " ;
	    executeUpdate(dropString, "刪除chapter_deadline表格", stmt);	    
	    // 定義刪除sub_comments表格的SQL命令
	    dropString = "DROP Table sub_comments " ;
	    executeUpdate(dropString, "刪除sub_comments表格", stmt);	    
	    // 定義刪除COMMENT表格的SQL命令
	    dropString = "DROP Table comments " ;
	    executeUpdate(dropString, "刪除comments表格", stmt);	    
	    // 定義刪除bookList表格的SQL命令
	    dropString = "DROP Table bookList " ;
	    executeUpdate(dropString, "刪除bookList表格", stmt);	    
	    // 定義刪除check_chapter表格的SQL命令
	    dropString = "DROP Table check_chapter " ;
	    executeUpdate(dropString, "刪除check_chapter表格", stmt);	    
	    // 定義刪除chapter表格的SQL命令
	    dropString = "DROP Table chapter " ;
	    executeUpdate(dropString, "刪除chapter表格", stmt);	    
	    // 定義刪除chapter表格的SQL命令
	    dropString = "DROP Table history_chapter " ;
	    executeUpdate(dropString, "刪除history_chapter表格", stmt);
	    // 定義刪除edit_chapter表格的SQL命令
	    dropString = "DROP Table edit_chapter " ;
	    executeUpdate(dropString, "刪除edit_chapter表格", stmt);
	    
	    // 定義刪除volume表格的SQL命令
	    dropString = "DROP Table volume " ;
	    executeUpdate(dropString, "刪除volume表格", stmt);	    
	    // 定義刪除history_volume表格的SQL命令
	    dropString = "DROP Table history_volume " ;
	    executeUpdate(dropString, "刪除history_volume表格", stmt);	
	    // 定義刪除check_volume表格的SQL命令
	    dropString = "DROP Table check_volume " ;
	    executeUpdate(dropString, "刪除check_volume表格", stmt);
	    // 定義刪除check_volume表格的SQL命令
	    dropString = "DROP Table edit_volume " ;
	    executeUpdate(dropString, "刪除edit_volume表格", stmt);

	    
	    // 定義刪除history_book表格的SQL命令
	    dropString = "DROP Table history_book " ;
	    executeUpdate(dropString, "刪除history_book表格", stmt);	 
	    // 定義刪除check_book表格的SQL命令
	    dropString = "DROP Table check_book " ;
	    executeUpdate(dropString, "刪除check_book表格", stmt);
	    // 定義刪除check_book表格的SQL命令
	    dropString = "DROP Table edit_book " ;
	    executeUpdate(dropString, "刪除edit_book表格", stmt);
	    // 定義刪除book表格的SQL命令
	    dropString = "DROP Table book " ;
	    executeUpdate(dropString, "刪除book表格", stmt);
	    
	    
	    // 定義刪除author表格的SQL命令
	    dropString = "DROP Table author " ;
	    executeUpdate(dropString, "刪除author表格", stmt);	    
	    // 定義刪除member表格的SQL命令
	    dropString = "DROP Table member " ;
	    executeUpdate(dropString, "刪除member表格", stmt);	    
	    // 定義刪除member表格的SQL命令
	    dropString = "DROP Table admin " ;
	    executeUpdate(dropString, "刪除admin表格", stmt);	    
	    // 定義刪除role_permission表格的SQL命令
	    dropString = "DROP Table role_permission " ;
	    executeUpdate(dropString, "刪除role_permission表格", stmt);	    
	    // 定義刪除role表格的SQL命令
	    dropString = "DROP Table role " ;
	    executeUpdate(dropString, "刪除role表格", stmt);	    
	    // 定義刪除permission表格的SQL命令
	    dropString = "DROP Table permission " ;
	    executeUpdate(dropString, "刪除permission表格", stmt);	
	    System.out.println("共刪除"+(success_count+error_count)+"個表格");
	    System.out.println(success_count+"個表格刪除成功，"+error_count+"個表格刪除失敗");
	    success_count=0;
	    error_count =0;
	    System.out.println("--------------------------------------------------------------");
	    // 刪除舊有表格結束
		
	    // 建立新表格開始
	    // 定義建立permission表格的SQL命令
	    createString = "CREATE TABLE permission ( " + 
	    		"  permission  TINYINT AUTO_INCREMENT ,     /*權限          */" + 
	    		"  status_name VARCHAR(32) NOT NULL,        /*權限名稱      */" + 
	    		"  PRIMARY KEY (permission) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立permission表格", stmt);	    
	    // 定義建立role表格的SQL命令
	    createString = "CREATE TABLE role ( " + 
	    		"  role_id TINYINT AUTO_INCREMENT ,      /*主鍵          */" + 
	    		"  role_Name VARCHAR(32) NOT NULL,       /*角色名稱  */" + 
	    		"  PRIMARY KEY (role_id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立role表格", stmt);
	    // 定義建立role_permission表格的SQL命令
	    createString = "CREATE TABLE role_permission ( " + 
	    		"  role_id TINYINT NOT NULL,      /*角色ID        */" + 
	    		"  permission TINYINT NOT NULL,   /*權限的擁有          */" + 
	    		"  FOREIGN KEY (role_id) REFERENCES role (role_id), " + 
	    		"  FOREIGN KEY (permission) REFERENCES permission (permission)," + 
	    		"  CONSTRAINT pk_roleId_levels PRIMARY KEY (role_id, permission)   /*以角色ID及對照的權限組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci; ";
	    executeUpdate(createString, "建立role表格", stmt); 
	    // 定義建立admin表格的SQL命令
	    createString = "CREATE TABLE admin ( " + 
	    		"  admin_Id        INT AUTO_INCREMENT NOT NULL,  " + 
	    		"  account   VARCHAR(32) NOT NULL UNIQUE,   /*帳號 這裡是Email*/" + 
	    		"  pswd  VARCHAR(64) NOT NULL, 		   		/*密碼            */" + 
	    		"  reg_date DATE ,                          /*註冊日期        */" + 
	    		"  last_Login TIMESTAMP,                    /*最後登入時間    */" + 
	    		"  lastLogin_Ip VARCHAR(15) NULL ,          /*最後登入ip      */" + 
	    		"  role_id TINYINT DEFAULT 4 NOT NULL ,  /*角色ID        */" + 
	    		"  PRIMARY KEY (admin_Id),           " + 
	    		"  FOREIGN KEY (role_id) REFERENCES role (role_id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci; ";
	    executeUpdate(createString, "建立admin表格", stmt);
	    // 定義建立member表格的SQL命令
	    createString = "CREATE TABLE member ( " + 
	    		"  member_Id    VARCHAR(36) NOT NULL UNIQUE,  " + 
	    		"  account      VARCHAR(64) NOT NULL UNIQUE,   /*帳號 這裡是Email*/" + 
	    		"  pswd         VARCHAR(64) NOT NULL, 	       /*密碼            */" + 
	    		"  email        VARCHAR(64) NOT NULL UNIQUE,   /*email           */" + 
	    		"  nick_Name    VARCHAR(32) NOT NULL,          /*暱稱            */" + 
	    		"  gender       CHAR(1) NOT NULL,              /*性別            */" + 
	    		"  birthday     DATE NOT NULL,                 /*生日            */" + 
	    		"  cellPhone    VARCHAR(32) ,                  /*手機            */" + 
	    		"  reg_date     DATE NOT NULL,                 /*註冊日期        */" + 
	    		"  lastLogin    TIMESTAMP,                     /*最後登入時間    */" + 
	    		"  lastLogin_Ip VARCHAR(64) ,                  /*最後登入ip      */" + 
	    		"  picture      MEDIUMBLOB NULL,               /*大頭貼          */" + 
	    		"  picture_Name VARCHAR(50) UNIQUE,            /*大頭貼檔名      */" + 
	    		"  points       INT(8) DEFAULT 0 NOT NULL ,    /*點數            */" + 
	    		"  creditCardNo VARCHAR(64),	               /*信用卡帳號      */" + 
	    		"  tickets      INT NOT NULL DEFAULT 0,        /*月票            */" + 
	    		"  role_id  TINYINT DEFAULT 1 NOT NULL ,   /*角色等級        */" + 
	    		"  PRIMARY KEY (member_Id),           " + 
	    		"  FOREIGN KEY (role_id) REFERENCES role (role_id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立member表格", stmt);
	    // 定義建立author表格的SQL命令
	    createString = "CREATE TABLE author (" + 
	    		"  author_Id INT AUTO_INCREMENT NOT NULL, " + 
	    		"  pen_Name  VARCHAR(32) NOT NULL UNIQUE,         /*筆名           */" + 
	    		"  member_Id VARCHAR(36) NOT NULL UNIQUE,  /*對應之會員ID   */" + 
	    		"  bank_account VARCHAR(64) NOT NULL UNIQUE,      /*銀行帳號       */" + 
	    		"  PRIMARY KEY (author_Id),           " + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立author表格", stmt);
	    // 定義建立book表格的SQL命令	    
	    createString = "CREATE TABLE book ( " + 
	    		"  book_Id INT AUTO_INCREMENT NOT NULL, " + 
	    		"  title  VARCHAR(32) NOT NULL,             /*書名            */" + 
	    		"  author_Id INT NOT NULL,                  /*對應作者ID      */" + 
	    		"  book_state        VARCHAR(32) NOT NULL DEFAULT '新書上傳'," + 
	    		"  publish_Date DATE NOT NULL ,             /*發布日期        */" + 
	    		"  surface_Plot MEDIUMBLOB NULL,            /*封面圖          */" + 
	    		"  surface_Plot_Name VARCHAR(50) UNIQUE,    /*封面圖名稱      */" + 
	    		"  intro TEXT ,                             /*簡介            */" + 
	    		"  classify VARCHAR(32) NOT NULL,           /*分類            */" + 
	    		"  clicks INT NOT NULL DEFAULT 0 ,          /*點擊數          */" + 
	    		"  tickets INT NOT NULL DEFAULT 0,          /*獲得月票數目    */" + 
	    		"  PRIMARY KEY (book_Id),           " + 
	    		"  FOREIGN KEY (author_Id) REFERENCES author (author_Id)," + 
	    		"  INDEX (clicks),                          /*以點擊數建立索引*/" + 
	    		"  INDEX (tickets)                          /*以月票數建立索引*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增book Table ", stmt);
	    
	    // 定義建立check_book表格的SQL命令	    
	    createString = "CREATE TABLE check_book ( " + 
	    		"  book_Id INT AUTO_INCREMENT NOT NULL, " + 
	    		"  title  VARCHAR(32) NOT NULL,             /*書名            */" + 
	    		"  author_Id INT NOT NULL,                  /*對應作者ID      */" + 
	    		"  book_state        VARCHAR(32) NOT NULL DEFAULT '待審查'," + 
	    		"  publish_Date DATE NOT NULL ,             /*發布日期        */" + 
	    		"  surface_Plot MEDIUMBLOB NULL,            /*封面圖          */" + 
	    		"  surface_Plot_Name VARCHAR(50) UNIQUE,    /*封面圖名稱      */" + 
	    		"  intro TEXT ,                             /*簡介            */" + 
	    		"  classify VARCHAR(32) NOT NULL,           /*分類            */" + 
	    		"  clicks INT NOT NULL DEFAULT 0 ,          /*點擊數          */" + 
	    		"  tickets INT NOT NULL DEFAULT 0,          /*獲得月票數目    */" + 
	    		"  PRIMARY KEY (book_Id),           " + 
	    		"  FOREIGN KEY (author_Id) REFERENCES author (author_Id)," + 
	    		"  INDEX (clicks),                          /*以點擊數建立索引*/" + 
	    		"  INDEX (tickets)                          /*以月票數建立索引*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增 check_book Table ", stmt);
	    
	    // 定義建立edit_book表格的SQL命令	    
	    createString = "CREATE TABLE edit_book ( " +
	    		"  edit_Id INT AUTO_INCREMENT NOT NULL, " +
	    		"  book_Id   INT NOT NULL, " + 
	    		"  title  VARCHAR(32) NOT NULL,             /*書名            */" + 
	    		"  author_Id INT NOT NULL,                  /*對應作者ID      */" + 
	    		"  surface_Plot MEDIUMBLOB NULL,            /*封面圖          */" + 
	    		"  surface_Plot_Name VARCHAR(50) UNIQUE,    /*封面圖名稱      */" + 
	    		"  intro TEXT ,                             /*簡介            */" + 
	    		"  classify VARCHAR(32) NOT NULL,           /*分類            */" +  
	    		"  PRIMARY KEY (edit_Id),           " + 
	    		"  FOREIGN KEY (book_Id) REFERENCES book (book_Id), " +
	    		"  FOREIGN KEY (author_Id) REFERENCES author (author_Id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增 edit_book Table ", stmt);
	    
	    
	    
	        
	    // 定義建立history_book表格的SQL命令	
	    createString = "CREATE TABLE history_book ( " + 
	    		"  book_Id INT AUTO_INCREMENT NOT NULL, " + 
	    		"  title  VARCHAR(32) NOT NULL,             /*書名            */" + 
	    		"  author_Id INT NOT NULL,                  /*對應作者ID      */" + 
	    		"  book_state VARCHAR(32) NOT NULL,         /*書本狀態 ex.連載*/" + 
	    		"  publish_Date DATE NOT NULL ,             /*發布日期        */" + 
	    		"  surface_Plot MEDIUMBLOB NULL,            /*封面圖          */" + 
	    		"  surface_Plot_Name VARCHAR(50) ,          /*封面圖名稱      */" + 
	    		"  intro TEXT ,                             /*簡介            */" + 
	    		"  classify VARCHAR(32) NOT NULL,           /*分類            */" + 
	    		"  clicks INT NOT NULL DEFAULT 0 ,          /*點擊數          */" + 
	    		"  tickets INT NOT NULL DEFAULT 0,          /*獲得月票數目    */" + 
	    		"  PRIMARY KEY (book_Id),           " + 
	    		"  FOREIGN KEY (author_Id) REFERENCES author (author_Id)," + 
	    		"  INDEX (clicks),                          /*以點擊數建立索引*/" + 
	    		"  INDEX (tickets)                          /*以月票數建立索引*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立history_book表格", stmt);
	    // 定義建立volume表格的SQL命令	
	    createString = "CREATE TABLE volume (  " + 
	    		"  book_Id      INT NOT NULL ,         	           /*這卷屬於哪一本書    */" + 
	    		"  volume_Id    INT NOT NULL , 	                   /*卷ID                */" + 
	    		"  volume_Title VARCHAR(32) NOT NULL,               /*卷Title             */" + 
	    		"  FOREIGN KEY (book_Id) REFERENCES book (book_Id)," + 
	    		"  CONSTRAINT pk_volume_bookId_volumeId PRIMARY KEY (book_Id, volume_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立volume表格", stmt);
	    // 定義建立check_volume表格的SQL命令	
	    createString = "CREATE TABLE check_volume (  " + 
	    		"  book_Id      INT NOT NULL ,         	           /*這卷屬於哪一本書    */" + 
	    		"  volume_Id    INT NOT NULL , 	                   /*卷ID                */" + 
	    		"  volume_Title VARCHAR(32) NOT NULL,               /*卷Title             */" + 
	    		"  FOREIGN KEY (book_Id) REFERENCES book (book_Id)," + 
	    		"  CONSTRAINT pk_checkVolume_bookId_volumeId PRIMARY KEY (book_Id, volume_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立check_volume表格", stmt);
	 // 定義建立history_volume表格的SQL命令
	    createString = "CREATE TABLE history_volume (  " + 
	    		"  book_Id      INT NOT NULL ,         	           /*這卷屬於哪一本書    */" + 
	    		"  volume_Id    INT NOT NULL , 	                   /*卷ID             */" + 
	    		"  volume_Title VARCHAR(32) NOT NULL,              /*卷Title          */" + 
	    		"  FOREIGN KEY (book_Id) REFERENCES history_book (book_Id)," + 
	    		"  CONSTRAINT pk_historyVolume_bookId_volumeId PRIMARY KEY (book_Id, volume_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立history_volume表格", stmt);	
	    // 定義建立edit_volume表格的SQL命令	
	    createString = "CREATE TABLE edit_volume (  " + 
	    		"  book_Id      INT NOT NULL ,         	           /*這卷屬於哪一本書    */" + 
	    		"  volume_Id    INT NOT NULL , 	                   /*卷ID                */" + 
	    		"  volume_Title VARCHAR(32) NOT NULL,               /*卷Title             */" + 
	    		"  FOREIGN KEY (book_Id) REFERENCES book (book_Id)," + 
	    		"  CONSTRAINT pk_editVolume_bookId_volumeId PRIMARY KEY (book_Id, volume_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立edit_volume表格", stmt);
	    

	    // 定義建立chapter表格的SQL命令
	    createString = "CREATE TABLE chapter ( " + 
	    		"  book_Id         INT NOT NULL,                    /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID            */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,            /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                   /*章節內容                  */" + 
	    		"  content_Name    VARCHAR(50) NOT NULL UNIQUE,     /*章節檔案名稱              */" + 
	    		"  publish_time    TIMESTAMP NOT NULL DEFAULT NOW(),/*發布日期                  */ " + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),/*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 5 ,        /*章節價錢 (點數)           */ " + 
	    		"  CONSTRAINT fk_chapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_chapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */ " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立chapter表格", stmt);
	    // 定義建立history_chapter表格的SQL命令
	    createString = "CREATE TABLE history_chapter ( " + 
	    		"  book_Id         INT NOT NULL,                         /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID                */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,                 /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                        /*章節內容                  */" + 
	    		"  content_Name    VARCHAR(50) NOT NULL,                 /*章節檔案名稱              */" + 
	    		"  publish_time    TIMESTAMP NOT NULL DEFAULT NOW(),     /*發布日期                  */ " + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),     /*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 5 ,             /*章節價錢 (點數)           */" + 
	    		"  CONSTRAINT fk_historyChapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES history_volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_historyChapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立history_chapter表格", stmt);
		// 定義建立check_chapter表格的SQL命令	    
	    createString = "CREATE TABLE check_chapter ( " + 
	    		"  book_Id         INT NOT NULL,                         /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID                */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,                 /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                        /*章節內容                  */" + 
	    		"  content_Name    VARCHAR(50) NOT NULL,                 /*章節檔案名稱              */" + 
	    		"  publish_time    TIMESTAMP NOT NULL DEFAULT NOW(),     /*發布日期                  */ " + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),     /*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 5 ,             /*章節價錢 (點數)           */ " + 
	    		"  CONSTRAINT fk_checkChapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES check_volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_checkChapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立check_chapter表格", stmt);
		// 定義建立edit_chapter表格的SQL命令	    
	    createString = "CREATE TABLE edit_chapter ( " + 
	    		"  book_Id         INT NOT NULL,                         /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID                */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,                 /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                        /*章節內容                  */" + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),     /*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 5 ,             /*章節價錢 (點數)           */ " + 
	    		"  CONSTRAINT fk_editChapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_editChapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立edit_chapter表格", stmt);
	    
	    
	    
	    
		// 定義建立bookList表格的SQL命令
	    createString = "CREATE TABLE bookList ( " + 
	    		"  member_Id VARCHAR(36) NOT NULL,/*哪一個會員           */" + 
	    		"  book_Id   INT NOT NULL,        /*對應之書本ID         */" + 
	    		"  FOREIGN KEY (book_Id)   REFERENCES book  (book_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member(member_Id)," + 
	    		"  CONSTRAINT pk_bookList_memberId_bookId PRIMARY KEY (member_Id, book_id)   /*以會員ID及書本ID組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立bookList表格", stmt);	    
	    // 定義建立comments表格的SQL命令
	    createString = "CREATE TABLE comments ( " + 
	    		"  book_Id    INT NOT NULL,         	    /*這個評論屬於哪一個書本    */ " + 
	    		"  comment_Id INT NOT NULL, 		    /*comment_ID                */ " + 
	    		"  member_Id  VARCHAR(36) NOT NULL,          /*哪一個會員評論的站出來    */ " + 
	    		"  comment VARCHAR(600) NOT NULL,            /*評論內容                  */  " + 
	    		"  FOREIGN KEY (book_Id)   REFERENCES book   (book_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id),  " + 
	    		"  CONSTRAINT pk_comments_bookId_commentId PRIMARY KEY (book_Id, comment_Id)   /*以comment_Id及會員ID組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立comments表格", stmt);
	    // 定義建立sub_comments表格的SQL命令
	    createString = "CREATE TABLE sub_comments (  " + 
	    		"  sub_comment_Id INT NOT NULL AUTO_INCREMENT , /*sub_comment_id          */" + 
	    		"  comment_Id     INT NOT NULL, 		       /*回覆哪一個comment_ID    */ " + 
	    		"  book_Id        INT NOT NULL,         	       /*這個評論屬於哪一個書本  */ " + 
	    		"  member_Id      varchar(36) NOT NULL,         /*哪一個會員評論的站出來  */" + 
	    		"  comments       VARCHAR(300) NOT NULL ,       /*評論內容                */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id),  " + 
	    		"  CONSTRAINT fk_subComments_bookId_commentId FOREIGN KEY (book_Id, comment_Id) REFERENCES comments (book_Id, comment_Id),   /*以comment_Id及BookID組成複合外來鍵*/ " + 
	    		"  PRIMARY KEY (sub_comment_id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立sub_comments表格", stmt);
	    // 定義建立stars表格的SQL命令
	    createString = "CREATE TABLE stars (  " + 
	    		"  book_Id   INT NOT NULL,         	       /*這個評論屬於哪一個書本    */ " + 
	    		"  member_Id VARCHAR(36) NOT NULL,              /*哪一個會員評論的站出來    */ " + 
	    		"  starPoint TINYINT NOT NULL DEFAULT 0,        /*星星分數                  */ " + 
	    		"  FOREIGN KEY (book_Id)   REFERENCES book   (book_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id), " + 
	    		"  CONSTRAINT pk_stars_memberId_bookId PRIMARY KEY (member_Id, book_Id)   /*以會員ID及BookID組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立stars表格", stmt);
	    // 定義建立chapter_deadline表格的SQL命令
	    createString = "CREATE TABLE chapter_deadline ( " + 
	    		"  deadline_Id INT NOT NULL, 		      /*deadline_ID         */" + 
	    		"  book_Id     INT NOT NULL,         	      /*哪一個書本          */" + 
	    		"  volume_Id   INT NOT NULL,                   /*哪一卷              */" + 
	    		"  chapter_id  INT NOT NULL,                   /*哪一個章節          */" + 
	    		"  member_Id   VARCHAR(36) NOT NULL,           /*哪一個會員          */" + 
	    		"  deadline    TIMESTAMP NOT NULL ,            /*可以看到什麼時候    */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)," + 
	    		"  CONSTRAINT fk_chapter_deadline_bookId_volumeId_chapterId FOREIGN KEY (book_Id, volume_Id, chapter_id) REFERENCES chapter (book_Id, volume_Id, chapter_id)," + 
	    		"  CONSTRAINT pk_chapter_deadline_memberId_bookId_volume_Id_chapter_id PRIMARY KEY (member_Id, book_Id, volume_Id, chapter_id)   /*以會員ID對上一個章節組成複合主鍵  */ " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立chapter_deadline表格", stmt);
	    // 定義建立focus_author表格的SQL命令
	    createString = "CREATE TABLE focus_author ( " + 
	    		"  member_Id varchar(36) NOT NULL ,            /*哪一個會員          */" + 
	    		"  author_Id INT NOT NULL,         	      /*喜歡的作者          */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)," + 
	    		"  FOREIGN KEY (author_Id) REFERENCES author (author_Id)," + 
	    		"  CONSTRAINT pk_focus_author_memberId_authorId PRIMARY KEY (member_Id, author_Id)   /*以member_Id及作者的ID組成複合主鍵  */ " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立focus_author表格", stmt);
	    // 定義建立last_record表格的SQL命令
	    createString = "CREATE TABLE last_record ( " + 
	    		"  member_Id  VARCHAR(36) NOT NULL,            /*哪一個會員          */" + 
	    		"  book_Id    INT NOT NULL,         	      /*哪一個書本          */" + 
	    		"  volume_Id  INT NOT NULL,                    /*哪一個卷            */" + 
	    		"  chapter_id INT NOT NULL,                    /*最後看章節          */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)," + 
	    		"  CONSTRAINT fk_last_record_bookId_volumeId_chapterId FOREIGN KEY (book_Id, volume_Id, chapter_id) REFERENCES chapter (book_Id, volume_Id, chapter_id)," + 
	    		"  CONSTRAINT pk_last_record_memberId_bookId PRIMARY KEY (member_Id, book_Id)   /*以member_Id及書的ID組成複合主鍵  */  " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立last_record表格", stmt);
	    // 定義建立activity表格的SQL命令
	    createString = "CREATE TABLE activity ( " + 
	    		"  activity_Id    INT NOT NULL AUTO_INCREMENT, /*activityId          */" + 
	    		"  activity_name  VARCHAR(50) NOT NULL,        /*活動名稱            */" + 
	    		"  activity_image_Name VARCHAR(50),            /*宣傳圖片位置        */" + 
	    		"  activity_image MEDIUMBLOB,                  /*宣傳圖片            */" + 
	    		"  activity_intro VARCHAR(500) NOT NULL,       /*活動內容            */" + 
	    		"  start_date DATE NOT NULL ,                  /*開始時間            */" + 
	    		"  end_date DATE NOT NULL ,                    /*結束日期            */" + 
	    		"  PRIMARY KEY (activity_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立activity表格", stmt);
	    // 定義建立trade_record表格的SQL命令
	    createString = "CREATE TABLE trade_record ( " + 
	    		"  record_Id INT NOT NULL AUTO_INCREMENT,       /*record_Id           */" + 
	    		"  member_Id VARCHAR(36) NOT NULL,              /*會員ID              */" + 
	    		"  cash    INT NOT NULL DEFAULT 0,              /*儲值金錢            */" + 
	    		"  points   INT NOT NULL DEFAULT 0,             /*點數變動            */" + 
	    		"  trade_time TIMESTAMP NOT NULL DEFAULT NOW() ,/*交易時間            */" + 
	    		"  PRIMARY KEY (record_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立trade_record表格", stmt);	    
	    //輸出建立新表格成果
	    System.out.println("共建立"+(success_count+error_count)+"個表格");
	    System.out.println(success_count+"個表格建立成功，"+error_count+"個表格建立失敗");
	    success_count=0;
	    error_count =0;
	    System.out.println("--------------------------------------------------------------");
	    // 建立新表格結束
	    
	    
	    try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
	    
    
	    
	
	}
	
	static void executeUpdate(String sql, String action , Statement stmt) {
		try {    
	  	    // 執行actionSQL命令
	        stmt.executeUpdate(sql); 
	        // 印出執行成功的訊息
            System.out.println(action + "成功") ;
            success_count++; 
	    } catch(SQLException e) {
	        System.err.println(action + "時發生例外: " + e.getMessage());
	        error_count++;
	    }
	}   
	
}
