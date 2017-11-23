package com.Apocalypse.member.Email;

import java.util.Arrays;
import java.util.List;




public class JavaMailMain {

	
			
		String from = "md0150250@gmail.com";
		List<String> to; 
		List<String> cc ;
		List<String> bcc;
		String subject;
		String text ;
		List<String> attachment;
	
	 public void sendmail_Changerole_id(String member_Id,String email) {
		
		String subject = "歡迎加入會員";
		String text = 
					  "<h1>謝謝您加入會員</h1>" +
					  "<h2>請按以下的來連結來成為正式會員</h2>" +
				      "<a href='http://localhost:8080/MemberSystem/controler/EmailLogInServlet?member_Id="+
				      member_Id+"&time="+System.currentTimeMillis()+"&role_id=2'>IRead</a><br>" +
					  "<br><br><font color='blue'> 再次感謝, </font><br>工作小組敬上";
		  
		  List<String> to = Arrays.asList(
				   new String[]{email});
		  		  
		JavaMailUtil  util = new JavaMailUtil(from, to, cc, bcc, subject, text ,attachment);
		if (util.send()){
		   System.out.println("發信成功");
		} else {
		   System.out.println("發信失敗");
		}
	}
     
	 public void sendmail_ChangePswd(String member_Id,String email) {
			
			String subject = "更改帳號密碼";
			String text = 
						  "<h1>會員您好</h1>" +
						  "<h2>請按以下的來連結來更改密碼</h2>" +
					      "<a href='http://localhost:8080/MemberSystem/ForgetPswd/ChangePswd.jsp?member_Id="+
					      member_Id+"&time="+System.currentTimeMillis()+"'>IRead</a><br>" +
						  "<br><br><font color='blue'> 再次感謝, </font><br>工作小組敬上";
			  System.out.println(System.currentTimeMillis());
			  List<String> to = Arrays.asList(
					   new String[]{email});
			  		  
			JavaMailUtil  util = new JavaMailUtil(from, to, cc, bcc, subject, text ,attachment);
			if (util.send()){
			   System.out.println("發信成功");
			} else {
			   System.out.println("發信失敗");
			}
	 }
 }
