package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.User;
import com.chinaops.web.edesktop.utils.JDBCUtils;

public class LoginUserDaoImpl{
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public List<User> getUserByUsername(String username){
		List<User> lists = new ArrayList<User>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			/*StringBuilder sql = new StringBuilder();
			sql.append("select * from users where username=");
			sql.append("'");
			sql.append(username);
			sql.append("'");*/
			String sql = "select * from users where username='"+ username+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				lists.add(user);
			}
			return lists;//return this.find("from User where username=?",new Object[] {username});
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		
	}
	
	
}
