package com.source.plan.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.beans.Notice;
import com.source.plan.beans.PageInfo;
import com.source.plan.entity.User;
import com.source.plan.jdbc.connectSQL;


@Controller
@RequestMapping(value="/user")
public class UserController{

	@RequestMapping(value="/UserInfoCenter")
	public String intoUserInfoCenter(){
		return "user/UserInfoCenter";
	}
	
	@RequestMapping(value="/intoAmendPass")
	public String intoAmendPass(){
		return "user/AmendPass";
	}
	
	@RequestMapping(value="/UserMessageCenter")
	public String UserMessageCenter(HttpServletRequest req, HttpServletResponse resp){
		User user = (User)req.getSession().getAttribute("sessionUser");
		if(user != null)
		{
			System.out.println(user.getUserId());
			Connection con = connectSQL.getConnection();
			List<Notice> notices = new ArrayList<Notice>();
			PageInfo pageInfo = new PageInfo();
			PreparedStatement pstmt = null;

			try{
				pstmt = con.prepareStatement("select * from notice where NoticeReceiveID=?");
				pstmt.setString(1, user.getUserId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					Notice notice = new Notice();
					notice.setNoticeID(rs.getString(1));
					notice.setNoticeContent(rs.getString(2));
					notice.setNoticeProblemID(rs.getString(5));
					notice.setNoticeDate(rs.getString(7));
					notice.setNoticeIsCheck(rs.getInt(8));
					notices.add(notice);
				}
				pageInfo.setNotices(notices);
				req.setAttribute("pageInfo", pageInfo);
				return "user/UserMessageCenter";
			}catch (Exception e) {
				return "error";
			}finally{
				connectSQL.closeConnection(con);
				connectSQL.closePreparedStatement(pstmt);
			}
		}
		return "user/UserMessageCenter";
	}
	
	@RequestMapping(value="/AmendInfo")
	public String amendInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		String path =  request.getSession().getServletContext().getRealPath("/") + "user/userimg/";
		FileItemFactory factory = new DiskFileItemFactory(); // Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory); 
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		String[] values = new String[5];
		String QuestionGenre = "";
		// Parse the request
		/* FileItem */ 
		int i = 0;
		try {
			List items = upload.parseRequest(request);
			// Create a factory for disk-based file items 
			// Process the uploaded items 
			Iterator iter = items.iterator();
			String fileName="";
			while (iter.hasNext()) { 
				FileItem item = (FileItem) iter.next(); 	
				if (item.isFormField()) { 
					String name = item.getFieldName(); 
					values[i++] =  new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
				} else { 
					String fieldName = item.getFieldName();
					String Fname = item.getName();
				 	if(Fname!="")
				 	{	
				 		String[] fileNames = Fname.split("\\.");
				 		fileName = user.getUserId() + "."+fileNames[1];
				 		File file = new File(path+fileName);
				 		item.write(file);
				 	}else
				 	{
				 		fileName = user.getUserImage();
				 	}
				}
			}
				values[0] = values[0]==null?user.getUserName():values[0];
				values[1] = values[1]==null?user.getUserSex():values[1];
				values[2] = values[2]==null?user.getUserBirth():values[2];
				values[3] = values[3]==null?user.getUserJob():values[3];
				values[4] = values[4]==null?user.getUserInterest():values[4];
				pstmt = con.prepareStatement("UPDATE user set UserName = ? , UserSex = ? , UserBirth = ? , UserJob = ? ,  UserInterest = ? , UserImage = ? where UserID = ?");
				pstmt.setString(1, values[0]);
				pstmt.setString(2, values[1]);
				pstmt.setString(3, values[2]);
				pstmt.setString(4, values[3]);
				pstmt.setString(5, values[4]);
				pstmt.setString(6, fileName);
				pstmt.setString(7, user.getUserId());
				
				int result = pstmt.executeUpdate();
				if(result > 0 )
				{
					user.setUserName(values[0]);
					user.setUserSex(values[1]);
					user.setUserBirth(values[2]);
					user.setUserJob(values[3]);
					user.setUserInterest(values[4]);
					user.setUserImage(fileName);
					request.getSession().setAttribute("sessionUser",user);
					request.setAttribute("result", "success");
					return "user/AmendInfo";
				}
				
				
		} catch (FileUploadException e) {
			return "error.jsp";
		} catch (Exception e) {
			return "error.jsp";
		} finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
			
		}
		return "user/AmendInfo";
	}
	
	@RequestMapping(value="/AmendPass")
	public String amendPass(HttpServletRequest request, HttpServletResponse response){
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
			Connection con = connectSQL.getConnection();
			PreparedStatement pstmt = null;
			try{
				User sessionUser = (User)request.getSession().getAttribute("sessionUser");
				pstmt = con.prepareStatement("select * from user where UserID=? and Userpass=?");
				pstmt.setString(1, sessionUser.getUserId());
				pstmt.setString(2, oldPassword);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					pstmt = con.prepareStatement("UPDATE user set Userpass = ? where userID = ?");
					pstmt.setString(1, newPassword);
					pstmt.setString(2, sessionUser.getUserId());
					int result = pstmt.executeUpdate();
					if(result > 0 )
					{
					request.getSession().removeAttribute("sessionUser");
					request.setAttribute("result", "success");
					return "user/AmendPass";
					}
				}
				else {
					request.setAttribute("result", "fail");
					return "user/AmendPass";
				}
			}catch(Exception e)
			{
				return "error.jsp";
			}finally{
				connectSQL.closeConnection(con);
				connectSQL.closePreparedStatement(pstmt);
			}
			return "user/AmendPass";
	}
}
