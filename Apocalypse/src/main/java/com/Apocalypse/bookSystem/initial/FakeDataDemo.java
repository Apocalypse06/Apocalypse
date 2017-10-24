package com.Apocalypse.bookSystem.initial;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.Apocalypse.bookSystem.util.GlobalService;



public class FakeDataDemo {
	
	public static int success_count = 0;
	public static int error_count = 0;
	
	
	
	public static void main(String[] args) throws SQLException, IOException {
		Connection con;
//	    PreparedStatement pstmt;
	    Statement stmt;
	    String insertString;
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
	
	    
	    
	    
	    
	    // 新增測試用資料開始
	    // 新增permission表格資料的SQL命令	    
	    insertString = "INSERT INTO permission (status_name) VALUES ('社群互動(回復,投票)') ";
	    executeUpdate(insertString, "新增權限 '社群互動'", stmt);
	    insertString = "INSERT INTO permission (status_name) VALUES ('作品管理') ";
	    executeUpdate(insertString, "新增權限 '作品管理'", stmt);
	    insertString = "INSERT INTO permission (status_name) VALUES ('作品審查') ";
	    executeUpdate(insertString, "新增權限 '作品審查'", stmt);
	    insertString = "INSERT INTO permission (status_name) VALUES ('後台管理'); ";
	    executeUpdate(insertString, "新增權限 '後台管理'", stmt);
	    // 新增role表格資料的SQL命令	
	    insertString = "INSERT INTO role (role_Name) VALUES ('temp_member') ";
	    executeUpdate(insertString, "新增角色 '未完成驗證會員'", stmt);
	    insertString = "INSERT INTO role (role_Name) VALUES ('member') ";
	    executeUpdate(insertString, "新增角色 '一般會員'", stmt);
	    insertString = "INSERT INTO role (role_Name) VALUES ('author'); ";
	    executeUpdate(insertString, "新增角色 '作者'", stmt);
	    insertString = "INSERT INTO role (role_Name) VALUES ('manager') ";
	    executeUpdate(insertString, "新增角色 '管理者'", stmt);
	    // 新增role_permission表格資料的SQL命令
	    insertString = "INSERT INTO role_permission (role_id, permission) VALUES (2,1) ";
	    executeUpdate(insertString, "新增角色權限 '一般會員 - 社群互動'", stmt);
	    insertString = "INSERT INTO role_permission (role_id, permission) VALUES (3,1) ";
	    executeUpdate(insertString, "新增角色權限 '作者 - 社群互動'", stmt);
	    insertString = "INSERT INTO role_permission (role_id, permission) VALUES (3,2) ";
	    executeUpdate(insertString, "新增角色權限 '作者 - 作品管理'", stmt);
	    insertString = "INSERT INTO role_permission (role_id, permission) VALUES (4,1) ";
	    executeUpdate(insertString, "新增角色權限 '管理者 - 社群互動'", stmt);
	    insertString = "INSERT INTO role_permission (role_id, permission) VALUES (4,3) ";
	    executeUpdate(insertString, "新增角色權限 '管理者 - 作品審查'", stmt);
	    insertString = "INSERT INTO role_permission (role_id, permission) VALUES (4,4) ";
	    executeUpdate(insertString, "新增角色權限 '管理者 - 後台管理'", stmt);
	    //  新增member表格資料的SQL命令(2筆)	    
	    insertString = "INSERT INTO "+
	    "member(member_Id,account,pswd,email,nick_Name,gender,birthday,cellPhone,reg_date,creditCardNo)"+
	    "VALUES('t00','t00@gmail.com','pswd00','st00@gmail.com','測試員0','F','2017-09-18','0930-200100','2017-09-18','000-000-000-000')";
	    executeUpdate(insertString, "新增會員-0", stmt);
	    insertString = "INSERT INTO "+
	    "member(member_Id,account,pswd,email,nick_Name,gender,birthday,cellPhone,reg_date,creditCardNo)"+
	    "VALUES('t01','t01@gmail.com','pswd01','st01@gmail.com','測試員1','M','2017-09-19','0931-211011','2017-09-19','111-111-111-111')";
	    executeUpdate(insertString, "新增會員-1", stmt);
	    insertString = "INSERT INTO "+
	    "member(member_Id,account,pswd,email,nick_Name,gender,birthday,cellPhone,reg_date,creditCardNo)"+
	    "VALUES('t02','t02@gmail.com','pswd02','st02@gmail.com','測試員2','F','2017-09-22','0971-212021','2017-09-30','222-222-222-222')";
	    executeUpdate(insertString, "新增會員-2", stmt);
	    
 
	    //  新增author表格資料的SQL命令(2筆)
	    insertString = "INSERT INTO "+
	    "author(pen_Name,member_Id,bank_account)"+
	    "VALUES('卡膠','t01','111-000-111-111')";
	    executeUpdate(insertString, "新增作家-1", stmt);
	    insertString = "INSERT INTO "+
	    "author(pen_Name,member_Id,bank_account)"+
	    "VALUES('黑格洱','t00','101-000-101-110')";
	    executeUpdate(insertString, "新增作家-2", stmt);
	    

	    //  新增book表格資料的SQL命令(4筆)
	    File file1 = new File("src\\main\\webapp\\fakedata_resources\\BraveNewWorld.jpg");
	    int length1 = (int) file1.length();
	    InputStream fin1 = new FileInputStream(file1);
	    String st1 ="'O wonder!\r\n" + 
	    		   "How many goodly creatures are there here!\r\n" + 
	    		   "How beauteous mankind is! O brave new world,\r\n" + 
	    		   "That has such people in't.'";  
	    PreparedStatement pstmt1 = con.prepareStatement(
	    "INSERT INTO "+
        "book(title,author_Id,book_state,publish_Date,intro,classify,surface_Plot,surface_Plot_Name,clicks,tickets)"+
	    "VALUES('美麗新世界','1','連載中','1945-08-15',?,'科幻',?,'BraveNewWorld',101,50)");
        pstmt1.setString(1,st1);
        pstmt1.setBinaryStream (2, fin1, length1);
        pstmt1.executeUpdate();
        pstmt1.clearParameters();
        pstmt1.close();
        fin1.close();
        
        File file2 = new File("src\\main\\webapp\\fakedata_resources\\five.jpg");
	    int length2 = (int) file2.length();
	    InputStream fin2 = new FileInputStream(file2);
	    String st2 ="The story is told in a nonlinear order, "
	    		+ "and events become clear through flashbacks (or time travel experiences)"
	    		+ " from the unreliable narrator. He describes the stories of Billy Pilgrim,"
	    		+ " who believes he was held in an alien zoo and has experienced time travel.";  
	    PreparedStatement pstmt2 = con.prepareStatement(
	    "INSERT INTO "+
        "book(title,author_Id,book_state,publish_Date,intro,classify,surface_Plot,surface_Plot_Name,clicks,tickets)"+
	    "VALUES('第五號屠宰場','1','已完結','1969-09-21',?,'科幻',?,'five',21300,751)");
        pstmt2.setString(1,st2);
        pstmt2.setBinaryStream (2, fin2, length2);
        pstmt2.executeUpdate();
        pstmt2.clearParameters();
        pstmt2.close();
        fin2.close();
        
        File file3 = new File("src\\main\\webapp\\fakedata_resources\\fivePigs.jpg");
	    int length3 = (int) file3.length();
	    InputStream fin3 = new FileInputStream(file3);
	    String st3 = "是誰毒死了藝術家？"+
	    			 "一段古老的愛恨情仇，一件十六年前的謀殺案，如何憑藉一封單薄的遺書，重新揭開塵封多年的迷障？"+
	    			 "神探白羅受美麗少女卡拉．駱曼楨的請託，調查其故去母親多年前犯下的謀殺案。且看白羅如何走訪多位證人，如何抽絲剝繭、研析人心，以今之殘存片斷，補昔時疏漏之誤。看他如何為死者發聲，替逝者請命。"+
	    			 "藝術家究竟是誰殺的？";  
	    PreparedStatement pstmt3 = con.prepareStatement(
	    "INSERT INTO "+
        "book(title,author_Id,book_state,publish_Date,intro,classify,surface_Plot,surface_Plot_Name,clicks,tickets)"+
	    "VALUES('五隻小豬之歌','2','連載中','1942-11-21',?,'推理',?,'fivePigs',599995,211)");
        pstmt3.setString(1,st3);
        pstmt3.setBinaryStream (2, fin3, length3);
        pstmt3.executeUpdate();
        pstmt3.clearParameters();
        pstmt3.close();
        fin3.close();
        
        File file4 = new File("src\\main\\webapp\\fakedata_resources\\worldWar.jpg");
	    int length4 = (int) file4.length();
	    InputStream fin4 = new FileInputStream(file4);
	    String st4 = "外星人早就潛伏在地球的每個角落，隨時準備伸開魔掌掠奪地球。"+
	    			 "一場毀滅性的外星入侵行動即將如火如荼地展開，挑戰全人類的愛與勇氣；"+
	    			 "遭遇外星高級智慧生物致命突襲的地球，正面臨生死存亡的保衛戰，在這個看似滅絕希望的世界，人類將如何存活下去？";  
	    PreparedStatement pstmt4 = con.prepareStatement(
	    "INSERT INTO "+
        "book(title,author_Id,book_state,publish_Date,intro,classify,surface_Plot,surface_Plot_Name,clicks,tickets)"+
	    "VALUES('世界大戰-火星人入侵','2','連載中','1898-05-17',?,'軍事',?,'worldWar',57889,215)");
        pstmt4.setString(1,st4);
        pstmt4.setBinaryStream (2, fin4, length4);
        pstmt4.executeUpdate();
        pstmt4.clearParameters();
        pstmt4.close();
        fin4.close();
        
        
        

        
        // 新增卷表格資料的SQL命令(1筆)
        insertString = "INSERT INTO "+
        "volume(book_Id,volume_Id,volume_Title)"+
        "VALUES('1','1','烏托邦')";
        executeUpdate(insertString, "新增volume表格資料-1", stmt);
        insertString = "INSERT INTO "+
        "volume(book_Id,volume_Id,volume_Title)"+
        "VALUES('1','2','新生命到來')";
        executeUpdate(insertString, "新增volume表格資料-2", stmt);
        
        
        
        
        // 新增章表格資料的SQL命令(1筆)
        String content = "'這是一棟只有三十四層樓的矮小灰色建築物。在大門上方有這排字，「中倫敦孵化與制約中心」，在一塊盾形牌子上則刻著世界邦的座右銘：社群，同一，穩定。\\r\\n"+
        	             "一樓巨大的房間面朝北方。窗子後方的空間整個夏天都很冷，雖然房間本身有著熱帶的暑氣，一道刺眼而單薄的光炫目地穿過窗戶，飢渴地尋找某些包在布幔後躺著的形體，\\r\\n"+
        	             "一些學院培養出來、狀似雞皮疙瘩的蒼白形狀，但最後只找到一座實驗室裡的玻璃與鎳，還有閃著陰鬱光芒的瓷器。冬天般的寒冷彼此呼應。工人們的連身工作服是白色的，\\r\\n"+
        	             "他們的手上戴著蒼白如死屍顏色的橡膠手...'";
        insertString = "INSERT INTO "+
        "chapter(book_Id,volume_Id,chapter_id,chapter_title,chapter_content,content_Name)"+
        "VALUES('1','1','1','序章',"+content+",'introduction')";
        executeUpdate(insertString, "新增chapter表格資料-1", stmt); 
        
        String content2 = "'主任打開一扇門。他們在一個光禿禿的大房間，非常明亮、陽光充足，畢竟整面南邊牆就是一扇大窗戶。\\r\\n"+
        		"半打護士──穿著長褲跟規定的白色人造麻纖維制服外套，頭髮為了保持無菌而藏在白帽底下──正忙著在地\\r\\n "+
        		"板上擺出一長排裝碗玫瑰，這些大碗緊緊塞滿了盛開的花，數千片成熟綻放且柔軟如絲的花瓣，就跟那些\\r\\n"+
        		"數不清的小天使孩童的臉頰一樣，只不過明亮陽光中的這些無邪小孩並非只有白人跟亞利安人，也有顯眼\\r\\n "+
        		"的中國人、墨西哥人，亦有的嬰孩因為天使太用力吹天國的號角而膚色發白，還有的白如死屍、白如人們\\r\\n "+
        		"死後的大理石白。\\r\\n"+
        		"孵育暨制約中心主任進來時，護士們僵硬地立正站好。'";
		insertString = "INSERT INTO "+
		"chapter(book_Id,volume_Id,chapter_id,chapter_title,chapter_content,content_Name)"+
		"VALUES('1','2','1','陽光下',"+content2+",'about...')";
		executeUpdate(insertString, "新增chapter表格資料-2", stmt);
        
        
               
        //  新增comments表格資料的SQL命令(2筆)
        insertString = "INSERT INTO "+
        "comments(book_Id,comment_Id,member_Id,comment)"+
        "VALUES('1','1','t00','這不是故事，而是即將到來的未來...')";
        executeUpdate(insertString, "新增comments表格資料-1", stmt);
        insertString = "INSERT INTO "+
        "comments(book_Id,comment_Id,member_Id,comment)"+
        "VALUES('1','2','t02','welcome to japari park!')";
        executeUpdate(insertString, "新增comments表格資料-2", stmt);
        
        //  新增sub_comments表格資料的SQL命令(2筆)
        insertString = "INSERT INTO "+
        "sub_comments(comment_Id,book_Id,member_Id,comments)"+
        "VALUES('1','1','t02','純推不下')";
        executeUpdate(insertString, "新增sub_comments表格資料-1", stmt);
        insertString = "INSERT INTO "+
        "sub_comments(comment_Id,book_Id,member_Id,comments)"+
        "VALUES('2','1','t00','action station')";
        executeUpdate(insertString, "新增sub_comments表格資料-2", stmt);
        
        //  新增stars表格資料的SQL命令(2筆)
        insertString = "INSERT INTO "+
        "stars(book_Id,member_id,starPoint)"+
        "VALUES('1','t00','5')";
        executeUpdate(insertString, "新增stars表格資料-1", stmt);
        insertString = "INSERT INTO "+
        "stars(book_Id,member_id,starPoint)"+
        "VALUES('1','t02','4')";
        executeUpdate(insertString, "新增stars表格資料-2", stmt);
        
        
        
        
        
        
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
