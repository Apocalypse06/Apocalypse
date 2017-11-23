package com.Apocalypse.member.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class GetImageFromServer
 */
@WebServlet("/picture/GetImageFromServer")
public class GetImageFromServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetImageFromServer() {
        super();
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		OutputStream os = null;
		InputStream is = null;
		String path = null;
		try {
			// 讀取瀏覽器傳送來的書籍代號(BookID) 查詢字串 ?
			String id = request.getParameter("id");
			// 分辨讀取哪個表格的圖片欄位
			String type = request.getParameter("type"); 
			// 分辨要以什麼方式輸出圖片
			String base64 = request.getParameter("useBase64"); 
			Context context = new InitialContext();
			// 透過JNDI取得DataSource物件
			DataSource ds = (DataSource) context
					.lookup("java:comp/env/jdbc/MemberDB");
			conn = ds.getConnection();
			PreparedStatement pstmt = null;
			//System.out.println("GetImageFromDB, Type==>" + type);
			//System.out.println("GetImageFromDB, ID==>" + id);
			if (type.equalsIgnoreCase("BOOK")) {  // 讀取eBook表格
				path = "/images/book/";
				pstmt = conn.prepareStatement(
						"SELECT surface_Plot_Name from Book where book_id = ? "
				);	
			} else if (type.equalsIgnoreCase("MEMBER")) {  // 讀取eMember表格
				path = "/images/member/";
				pstmt = conn.prepareStatement(
						"SELECT picture_Name from member where member_id = ? "
				);
			}
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String fileName = rs.getString(1);
				//System.out.println(1 + fileName);
				if (fileName == null) {
					fileName = "default.png";
					is = getServletContext().getResourceAsStream(
							"/images/default.png");
				} else {
					System.out.println(2 + path+fileName);
					is = getServletContext().getResourceAsStream(
							path+fileName);
				}
				String mimeType = getServletContext().getMimeType(fileName);
				if(base64 != null && base64.trim().equalsIgnoreCase("yes")) {
					response.setContentType("text/plain");
					os = response.getOutputStream();	
					int len = 0;
					byte[] bytes = new byte[819200];
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					while((len = is.read(bytes)) != -1) {
						baos.write(bytes, 0, len);
					}
					bytes = baos.toByteArray();
					String prefix = "data:" + mimeType + ";base64,";
					//System.out.println(prefix);
					// 由於java.io.OutputStream類別無法寫出字串，因此將字串轉為位元組陣列寫出
					os.write(prefix.getBytes("UTF-8"));
					Base64.Encoder be = Base64.getEncoder();
					os.write(be.encode(bytes));
					//System.out.println(prefix.getBytes("UTF-8"));
					//prefix = prefix + be.encodeToString(bytes);
					//System.out.println(prefix);
					//os.write(prefix.getBytes("UTF-8"));
				} else {	
					// 設定輸出資料的型態
					response.setContentType(mimeType);
					// 取得能寫出非文字資料的OutputStream物件
					os = response.getOutputStream();		
					int count = 0;
					byte[] bytes = new byte[819200];
					while ((count = is.read(bytes)) != -1) {
						os.write(bytes, 0, count);
					}
				}  
			} 
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close(); // 一定要註解此行來執行本程式五次
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				os.close();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
