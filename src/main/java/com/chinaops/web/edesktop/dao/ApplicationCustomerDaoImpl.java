package com.chinaops.web.edesktop.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chinaops.web.edesktop.entity.Customer;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description: 
 * @version 2014-3-19 下午17:42:32 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class ApplicationCustomerDaoImpl{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	public Customer findByUser_name(String user_name) {
		Customer customer = new Customer();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from customer where user_name='"+ user_name+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setUser_name(rs.getString("user_name"));
			}
			return customer;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public void deleteCustomerById(int id) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from customer where id='"+ id+"'";
			stmt.execute(sql);//this.executeUpdate("delete from Customer where id=?", id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		
	}

	public void add(Customer customer) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into customer(user_name) values('"+customer.getUser_name()+"')";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		
	}

	public Customer getById(int user_id) {
		Customer customer = new Customer();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from customer where id='"+ user_id+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setUser_name(rs.getString("user_name"));
			}
			return customer;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}


	
	
}
