package com.Apocalypse.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ResetDatabase {
	
	public static final String UTF8_BOM = "\uFEFF";  // 定義 UTF-8的BOM字元 
	
	public static void main(String[] args) {
		Connection con;
	    PreparedStatement pstmt;
	    Statement stmt;
	    String dropString;   
	    String createString;
	    String sql   = "";
	    String chapterPath = GlobalService.CHAPTER_PATH;
	    String photoPath = GlobalService.HEAD_SHOT_PATH;
	    String surfacePlotPath = GlobalService.SURFACE_PLOT_PATH;
	    try {
	        // 連上後端的資料庫
	        con =  DriverManager.getConnection(
	        		  GlobalService.DB_URLMySQL, 
	        		  GlobalService.USERID, 
	        		  GlobalService.PASSWORD);
	        // 建立Statement物件，以便傳送SQL命令到後端的資料庫
	        stmt = con.createStatement();
	          
	    }  catch(SQLException e) {
	    	System.err.println("存取資料庫有關的例外：" + e.getMessage() );
	        e.printStackTrace() ;
	        return ;
	    }
	    
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
	    
	    // 定義刪除chapter表格的SQL命令
	    dropString = "DROP Table volume " ;
	    executeUpdate(dropString, "刪除volume表格", stmt);
	    
	    // 定義刪除chapter表格的SQL命令
	    dropString = "DROP Table history_volume " ;
	    executeUpdate(dropString, "刪除history_volume表格", stmt);
	    
	    // 定義刪除book表格的SQL命令
	    dropString = "DROP Table book " ;
	    executeUpdate(dropString, "刪除book表格", stmt);
	    
	    // 定義刪除history_book表格的SQL命令
	    dropString = "DROP Table history_book " ;
	    executeUpdate(dropString, "刪除history_book表格", stmt);
	    
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
	    
	    
	   
	    
	    // 定義建立permission表格的SQL命令
	    createString = "CREATE TABLE permission ( " + 
	    		"  permission  TINYINT AUTO_INCREMENT ,     /*權限          */" + 
	    		"  status_name VARCHAR(32) NOT NULL,        /*權限名稱      */" + 
	    		"  PRIMARY KEY (permission) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立permission表格", stmt);
	    
	    createString = "INSERT INTO permission (status_name) VALUES ('社群互動(回復,投票)') ";
	    executeUpdate(createString, "新增權限 '社群互動'", stmt);
	    createString = "INSERT INTO permission (status_name) VALUES ('作品管理') ";
	    executeUpdate(createString, "新增權限 '作品管理'", stmt);
	    createString = "INSERT INTO permission (status_name) VALUES ('作品審查') ";
	    executeUpdate(createString, "新增權限 '作品審查'", stmt);
	    createString = "INSERT INTO permission (status_name) VALUES ('後台管理'); ";
	    executeUpdate(createString, "新增權限 '後台管理'", stmt);
	    
	    // 定義建立role表格的SQL命令
	    createString = "CREATE TABLE role ( " + 
	    		"  role_id TINYINT AUTO_INCREMENT ,      /*主鍵          */" + 
	    		"  role_Name VARCHAR(32) NOT NULL,       /*角色名稱  */" + 
	    		"  PRIMARY KEY (role_id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "建立role表格", stmt);
	    
	    createString = "INSERT INTO role (role_Name) VALUES ('temp_member') ";
	    executeUpdate(createString, "新增角色 '未完成驗證會員'", stmt);
	    createString = "INSERT INTO role (role_Name) VALUES ('member') ";
	    executeUpdate(createString, "新增角色 '一般會員'", stmt);
	    createString = "INSERT INTO role (role_Name) VALUES ('author'); ";
	    executeUpdate(createString, "新增角色 '作者'", stmt);
	    createString = "INSERT INTO role (role_Name) VALUES ('manager') ";
	    executeUpdate(createString, "新增角色 '管理者'", stmt);
	    
	    // 定義建立role_permission表格的SQL命令
	    createString = "CREATE TABLE role_permission ( " + 
	    		"  role_id TINYINT NOT NULL,      /*角色ID        */" + 
	    		"  permission TINYINT NOT NULL,   /*權限的擁有          */" + 
	    		"  FOREIGN KEY (role_id) REFERENCES role (role_id), " + 
	    		"  FOREIGN KEY (permission) REFERENCES permission (permission)," + 
	    		"  CONSTRAINT pk_roleId_levels PRIMARY KEY (role_id, permission)   /*以角色ID及對照的權限組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci; ";
	    executeUpdate(createString, "建立role表格", stmt);
	    
	    createString = "INSERT INTO role_permission (role_id, permission) VALUES (2,1) ";
	    executeUpdate(createString, "新增角色權限 '一般會員 - 社群互動'", stmt);
	    createString = "INSERT INTO role_permission (role_id, permission) VALUES (3,1) ";
	    executeUpdate(createString, "新增角色權限 '作者 - 社群互動'", stmt);
	    createString = "INSERT INTO role_permission (role_id, permission) VALUES (3,2) ";
	    executeUpdate(createString, "新增角色權限 '作者 - 作品管理'", stmt);
	    createString = "INSERT INTO role_permission (role_id, permission) VALUES (4,1) ";
	    executeUpdate(createString, "新增角色權限 '管理者 - 社群互動'", stmt);
	    createString = "INSERT INTO role_permission (role_id, permission) VALUES (4,3) ";
	    executeUpdate(createString, "新增角色權限 '管理者 - 作品審查'", stmt);
	    createString = "INSERT INTO role_permission (role_id, permission) VALUES (4,4) ";
	    executeUpdate(createString, "新增角色權限 '管理者 - 後台管理'", stmt);
	    
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
	    
	    // member_Id, account, pswd, nick_Name, gender, birthday, points, 
	    // email, reg_date
	    sql = "INSERT INTO member( "
	    		+ "member_Id, account, pswd, nick_Name, gender, birthday, points, "
	    		+ "email, reg_date) " 
	    		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	    try {
			pstmt = con.prepareStatement(sql);
			insertMembers(pstmt, 5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
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
	    
	    createString = "INSERT INTO author(author_Id,pen_Name,member_Id,bank_account) "
	    		+      "VALUES(1,\"金庸\",'1','1212-2121-2454');";
	    executeUpdate(createString, "新增author資料 金庸 ", stmt);
	    createString = "INSERT INTO author(author_Id,pen_Name,member_Id,bank_account) "
	    		+      "VALUES(2,\"古龍\",'2','1212-3434-5656');";
	    executeUpdate(createString, "新增author資料 古龍 ", stmt);
	    
	    
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
	    executeUpdate(createString, "新增Book Table ", stmt);
	    
	    sql = "INSERT INTO BOOK (title, author_Id"
	    		+   ", intro, classify, clicks, publish_Date) "
	    		+  "VALUES (?, ?, ?, ?, ?, ?)";
	    try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "最強反派係統");
			pstmt.setInt(2, 1);		
			pstmt.setString(3, "　　什麼是反派？\r\n" + 
					"　　是拳傾天下，縱橫一世，還是萬人皆敵，攪動風雲？\r\n" + 
					"　　重生一世，大反派係統加身，蘇信可以獲得前世武俠世界當中所有的反派人物功法和武技。\r\n" + 
					"　　前世慘遭橫死，這一世自己要做，就要做那最狠、最強的大反派！\r\n" + 
					"　　人皆言我惡，那我便凶殘到底！\r\n" + 
					"　　人皆言我邪，那我便魔焰滔天！\r\n" + 
					"　　“做人要講信用。說殺你全家，就殺你全家。我叫蘇信，我言而有信。");
			pstmt.setString(4, "武俠_武俠幻想");
			pstmt.setInt(5, 0);
			pstmt.setDate(6, new Date(System.currentTimeMillis()));
			pstmt.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
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
	    executeUpdate(createString, "新增history_book Table ", stmt);
	    
	    createString = "CREATE TABLE volume (  " + 
	    		"  book_Id      INT NOT NULL ,         	           /*這卷屬於哪一本書    */" + 
	    		"  volume_Id    INT NOT NULL , 	                   /*卷ID                */" + 
	    		"  volume_Title VARCHAR(32) NOT NULL,               /*卷Title             */" + 
	    		"  FOREIGN KEY (book_Id) REFERENCES book (book_Id)," + 
	    		"  CONSTRAINT pk_volume_bookId_volumeId PRIMARY KEY (book_Id, volume_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增volume Table", stmt);
	    
	    sql = "INSERT INTO volume (book_Id, volume_Id, volume_Title) "
	    		+  "VALUES (1, 1, '這是一個屬於大反派的時代')";
	    executeUpdate(sql, "新增Book_1 的第一卷", stmt);
	    
	    createString = "CREATE TABLE history_volume (  " + 
	    		"  book_Id      INT NOT NULL ,         	           /*這卷屬於哪一本書    */" + 
	    		"  volume_Id    INT NOT NULL , 	                   /*卷ID             */" + 
	    		"  volume_Title VARCHAR(32) NOT NULL,              /*卷Title          */" + 
	    		"  FOREIGN KEY (book_Id) REFERENCES history_book (book_Id)," + 
	    		"  CONSTRAINT pk_historyVolume_bookId_volumeId PRIMARY KEY (book_Id, volume_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增history_volume Table", stmt);
	    
	    createString = "CREATE TABLE chapter ( " + 
	    		"  book_Id         INT NOT NULL,                    /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID            */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,            /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                   /*章節內容                  */" + 
	    		"  content_Name    VARCHAR(50) NOT NULL UNIQUE,     /*章節檔案名稱              */" + 
	    		"  publish_time    TIMESTAMP NOT NULL DEFAULT NOW(),/*發布日期                  */ " + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),/*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 15 ,        /*章節價錢 (點數)           */ " + 
	    		"  CONSTRAINT fk_chapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_chapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */ " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增chapter Table", stmt);
	    
	    sql = "INSERT INTO chapter (book_Id, volume_Id, chapter_id, chapter_title"
	    		+   ", chapter_content, content_Name) "
	    		+  "VALUES (?, ?, ?, ?, ?, ?)";
	    try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 1);
			pstmt.setString(4, "大反派系統");
			File aFile = new File(chapterPath,"default_1.txt");
      	  	long size = aFile.length();
      	  	InputStream is = new FileInputStream(aFile);
      	  	InputStreamReader isr = new InputStreamReader(is);
			pstmt.setClob(5, isr, size);
			// 寫檔案至server
			FileReader bFile = new FileReader(chapterPath + "default_1.txt");
      	  	String s = null;
			try(
				BufferedReader br = new BufferedReader(bFile);
				PrintWriter pw = new PrintWriter(new FileWriter(new File(chapterPath,"1_1.txt")));
			) {
      	  		while((s = br.readLine()) != null) {
      	  			pw.println(s);
      	  		}
      	  		pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pstmt.setString(6, "1_1.txt");
			pstmt.execute();
			pstmt.clearParameters();
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 2);
			pstmt.setString(4, "能殺人的，就是劍！");
			aFile = new File(chapterPath,"default_2.txt");
      	  	size = aFile.length();
      	  	is = new FileInputStream(aFile);
      	  	isr = new InputStreamReader(is);
			pstmt.setClob(5, isr, size);
			pstmt.setString(6, "1_2.txt");
			pstmt.execute();
			pstmt.clearParameters();
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 3);
			pstmt.setString(4, "天欲使其滅亡，必使其瘋狂");
			aFile = new File(chapterPath,"default_3.txt");
      	  	size = aFile.length();
      	  	is = new FileInputStream(aFile);
      	  	isr = new InputStreamReader(is);
			pstmt.setClob(5, isr, size);
			pstmt.setString(6, "1_3.txt");
			pstmt.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    createString = "CREATE TABLE history_chapter ( " + 
	    		"  book_Id         INT NOT NULL,                         /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID                */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,                 /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                        /*章節內容                  */" + 
	    		"  content_Name    VARCHAR(50) NOT NULL,                 /*章節檔案名稱              */" + 
	    		"  publish_time    TIMESTAMP NOT NULL DEFAULT NOW(),     /*發布日期                  */ " + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),     /*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 15 ,             /*章節價錢 (點數)           */" + 
	    		"  CONSTRAINT fk_historyChapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES history_volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_historyChapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增history_chapter Table", stmt);
	    
	    createString = "CREATE TABLE check_chapter ( " + 
	    		"  book_Id         INT NOT NULL,                         /*這章節屬於哪一本書        */" + 
	    		"  volume_Id       INT NOT NULL,         	        /*這章節屬於哪一卷          */ " + 
	    		"  chapter_id      INT NOT NULL , 	                /*章節內容ID                */ " + 
	    		"  chapter_title   VARCHAR(32) NOT NULL,                 /*章節名稱                  */ " + 
	    		"  chapter_content TEXT NOT NULL,                        /*章節內容                  */" + 
	    		"  content_Name    VARCHAR(50) NOT NULL,                 /*章節檔案名稱              */" + 
	    		"  publish_time    TIMESTAMP NOT NULL DEFAULT NOW(),     /*發布日期                  */ " + 
	    		"  last_modified   TIMESTAMP NOT NULL DEFAULT NOW(),     /*最後修改時間              */ " + 
	    		"  price           INT NOT NULL DEFAULT 15 ,             /*章節價錢 (點數)           */ " + 
	    		"  CONSTRAINT fk_checkChapter_bookId_volumeId FOREIGN KEY (book_Id, volume_Id) REFERENCES volume (book_Id, volume_Id),  /*以卷ID及書本ID組成複合外來鍵*/" + 
	    		"  CONSTRAINT pk_checkChapter_bookId_volumeId_chapterId PRIMARY KEY (book_Id, volume_Id, chapter_id)   /*以bookID及volumeID及chapterID組成複合主鍵  */" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增check_chapter Table", stmt);
	    
	    createString = "CREATE TABLE bookList ( " + 
	    		"  member_Id VARCHAR(36) NOT NULL,/*哪一個會員           */" + 
	    		"  book_Id   INT NOT NULL,        /*對應之書本ID         */" + 
	    		"  FOREIGN KEY (book_Id)   REFERENCES book  (book_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member(member_Id)," + 
	    		"  CONSTRAINT pk_bookList_memberId_bookId PRIMARY KEY (member_Id, book_id)   /*以會員ID及書本ID組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增bookList Table", stmt);
	    
	    createString = "CREATE TABLE comments ( " + 
	    		"  book_Id    INT NOT NULL,         	    /*這個評論屬於哪一個書本    */ " + 
	    		"  comment_Id INT NOT NULL, 		    /*comment_ID                */ " + 
	    		"  member_id  VARCHAR(36) NOT NULL,          /*哪一個會員評論的站出來    */ " + 
	    		"  COMMENT VARCHAR(600) NOT NULL,            /*評論內容                  */  " + 
	    		"  FOREIGN KEY (book_Id)   REFERENCES book   (book_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id),  " + 
	    		"  CONSTRAINT pk_comments_bookId_commentId PRIMARY KEY (book_Id, comment_Id)   /*以comment_Id及會員ID組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增comments Table", stmt);
	    
	    createString = "CREATE TABLE sub_comments (  " + 
	    		"  sub_comment_id INT NOT NULL AUTO_INCREMENT , /*sub_comment_id          */" + 
	    		"  comment_Id     INT NOT NULL, 		       /*回覆哪一個comment_ID    */ " + 
	    		"  book_Id        INT NOT NULL,         	       /*這個評論屬於哪一個書本  */ " + 
	    		"  member_id      varchar(36) NOT NULL,         /*哪一個會員評論的站出來  */" + 
	    		"  comments       VARCHAR(300) NOT NULL ,       /*評論內容                */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id),  " + 
	    		"  CONSTRAINT fk_subComments_bookId_commentId FOREIGN KEY (book_Id, comment_Id) REFERENCES comments (book_Id, comment_Id),   /*以comment_Id及BookID組成複合外來鍵*/ " + 
	    		"  PRIMARY KEY (sub_comment_id) " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增sub_comments Table", stmt);
	    
	    createString = "CREATE TABLE stars (  " + 
	    		"  book_Id   INT NOT NULL,         	       /*這個評論屬於哪一個書本    */ " + 
	    		"  member_id VARCHAR(36) NOT NULL,              /*哪一個會員評論的站出來    */ " + 
	    		"  starPoint TINYINT NOT NULL DEFAULT 0,        /*星星分數                  */ " + 
	    		"  FOREIGN KEY (book_Id)   REFERENCES book   (book_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id), " + 
	    		"  CONSTRAINT pk_stars_memberId_bookId PRIMARY KEY (member_id, book_Id)   /*以會員ID及BookID組成複合主鍵*/" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增stars Table", stmt);
	    
	    createString = "CREATE TABLE chapter_deadline ( " + 
	    		"  deadline_Id INT NOT NULL, 		      /*deadline_ID         */" + 
	    		"  book_Id     INT NOT NULL,         	      /*哪一個書本          */" + 
	    		"  volume_Id   INT NOT NULL,                   /*哪一卷              */" + 
	    		"  chapter_id  INT NOT NULL,                   /*哪一個章節          */" + 
	    		"  member_id   VARCHAR(36) NOT NULL,           /*哪一個會員          */" + 
	    		"  deadline    TIMESTAMP NOT NULL ,            /*可以看到什麼時候    */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)," + 
	    		"  CONSTRAINT fk_chapter_deadline_bookId_volumeId_chapterId FOREIGN KEY (book_Id, volume_Id, chapter_id) REFERENCES chapter (book_Id, volume_Id, chapter_id)," + 
	    		"  CONSTRAINT pk_chapter_deadline_memberId_bookId_volume_Id_chapter_id PRIMARY KEY (member_id, book_Id, volume_Id, chapter_id)   /*以會員ID對上一個章節組成複合主鍵  */ " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增chapter_deadline Table ", stmt);
	    
	    createString = "CREATE TABLE focus_author ( " + 
	    		"  member_id varchar(36) NOT NULL ,            /*哪一個會員          */" + 
	    		"  author_Id INT NOT NULL,         	      /*喜歡的作者          */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)," + 
	    		"  FOREIGN KEY (author_Id) REFERENCES author (author_Id)," + 
	    		"  CONSTRAINT pk_focus_author_memberId_authorId PRIMARY KEY (member_id, author_Id)   /*以member_id及作者的ID組成複合主鍵  */ " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增focus_author Table", stmt);
	    
	    createString = "CREATE TABLE last_record ( " + 
	    		"  member_id  VARCHAR(36) NOT NULL,            /*哪一個會員          */" + 
	    		"  book_Id    INT NOT NULL,         	      /*哪一個書本          */" + 
	    		"  volume_Id  INT NOT NULL,                    /*哪一個卷            */" + 
	    		"  chapter_id INT NOT NULL,                    /*最後看章節          */" + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)," + 
	    		"  CONSTRAINT fk_last_record_bookId_volumeId_chapterId FOREIGN KEY (book_Id, volume_Id, chapter_id) REFERENCES chapter (book_Id, volume_Id, chapter_id)," + 
	    		"  CONSTRAINT pk_last_record_memberId_bookId PRIMARY KEY (member_id, book_Id)   /*以member_id及書的ID組成複合主鍵  */  " + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增last_record Table", stmt);
	    
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
	    executeUpdate(createString, "新增activity Table ", stmt);
	    
	    createString = "CREATE TABLE trade_record ( " + 
	    		"  record_Id INT NOT NULL AUTO_INCREMENT,       /*record_Id           */" + 
	    		"  member_Id VARCHAR(36) NOT NULL,              /*會員ID              */" + 
	    		"  cash    INT NOT NULL DEFAULT 0,              /*儲值金錢            */" + 
	    		"  points   INT NOT NULL DEFAULT 0,             /*點數變動            */" + 
	    		"  trade_time TIMESTAMP NOT NULL DEFAULT NOW() ,/*交易時間            */" + 
	    		"  PRIMARY KEY (record_Id)," + 
	    		"  FOREIGN KEY (member_Id) REFERENCES member (member_Id)" + 
	    		") ENGINE=INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;";
	    executeUpdate(createString, "新增trade_record Table ", stmt);
	    try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// member_id, account, pswd, nick_Name, gender, birthday, points, 
    // email, reg_date
	static void insertMembers(PreparedStatement pstmt, int n) {
		try {
			for(int i=0;i<n;i++) {
				pstmt.setString(1, String.valueOf(i));
				pstmt.setString(2, "email"+i+"@yahoo.com.tw");
				pstmt.setString(3, "123");
				pstmt.setString(4, "nickName_" + i);
				pstmt.setString(5, (i%2 == 0) ? "F" : "M");
				pstmt.setDate(6, new Date(System.currentTimeMillis()));
				pstmt.setInt(7, i*1000);
				pstmt.setString(8, "email"+i+"@yahoo.com.tw");
				pstmt.setDate(9, new Date(System.currentTimeMillis()));
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}
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
	    } catch(SQLException e) {
	        System.err.println(action + "時發生例外: " + e.getMessage());
	    }
	}
	
}
