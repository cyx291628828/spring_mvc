package com.source.plan.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.source.plan.jdbc.connectSQL;


//获得等级对应图片的方法
public class staticVar {
	private static int levelExp[]={0,5,12,21,32,45,60,77,96,117,140,165,192,221,252,285,320,357,396,437,480};

	public int getLevel(int e){
		int length = levelExp.length;
		int level = 0;
		for(int i = 0; i < length; i ++)
		{
			if(e >= levelExp[i])
				level = i;
		}
		return level;
	}


	public void AmendUser(HttpServletRequest request,String userID){
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		try{
			User sessionUser = new User();
			pstmt = con.prepareStatement("select UserID,UserName,UserRegTime,UserSex,UserBirth,UserJob,UserFakeMoney,UserInterest,UserLevel,UserImage from user where UserID=?");
			pstmt.setString(1, userID);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				sessionUser.setUserId(rs.getString(1));
				sessionUser.setUserName(rs.getString(2));
				sessionUser.setUserRegTime(rs.getString(3));
				sessionUser.setUserSex(rs.getString(4));
				sessionUser.setUserBirth(rs.getString(5));
				sessionUser.setUserJob(rs.getString(6));
				sessionUser.setUserFakeMoney(Integer.parseInt(rs.getString(7)));
				sessionUser.setUserInterest(rs.getString(8));
				sessionUser.setUserLevel(Integer.parseInt(rs.getString(9)));
				sessionUser.setUserImage(rs.getString(10));
				try{
					String[] str = rs.getString(10).split("\\.");
					str[1] = "sb";

				}
				catch (Exception e) {
					sessionUser.setUserImage("peopleHead2.png");
				}
				request.getSession().setAttribute("sessionUser", sessionUser);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}
}
