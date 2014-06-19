package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description:
 * 
 * @version 2014-5-23 下午17:42:32 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class EdeskCompanyDaoImpl{
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
    //添加公司
	public void addCompany(EdesktopCompany edesktopCompany) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into company(mark_id,fullname,shortname,clouduser_id,cloud_user) values('");
			sql.append(edesktopCompany.getMarkId()+"','");
			sql.append(edesktopCompany.getFullName()+"','");
			sql.append(edesktopCompany.getShortName()+"','");
			sql.append(edesktopCompany.getCloudUserId()+"','");
			sql.append(edesktopCompany.getCloudUser());
			sql.append("')");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		
	}
	//根据公司具体名称查询
	public EdesktopCompany selectByFullName(String fullName) {
		EdesktopCompany ec = new EdesktopCompany();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from company where fullname='"+fullName+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ec.setId(rs.getInt("id"));
				ec.setMarkId(rs.getString("mark_id"));
				ec.setFullName(rs.getString("fullname"));
				ec.setShortName(rs.getString("shortname"));
				ec.setCloudUserId(rs.getString("clouduser_id"));
				ec.setCloudUser(rs.getString("cloud_user"));
			}
			return ec;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	//修改
	public void updateEdeskCompany(EdesktopCompany ec) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("update company set ");
			sql.append("mark_id='");
			sql.append(ec.getMarkId());
			sql.append("',fullname='");
			sql.append(ec.getFullName());
			sql.append("',shortname='");
			sql.append(ec.getShortName());
			sql.append("',clouduser_id='");
			sql.append(ec.getCloudUserId());
			sql.append("',cloud_user='");
			sql.append(ec.getCloudUser());
			sql.append("' where id=");
			sql.append(ec.getId());
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}
	public EdesktopCompany selectCompany() {
		EdesktopCompany company = new EdesktopCompany();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from company";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				company.setId(rs.getInt("id"));
				company.setMarkId(rs.getString("mark_id"));
				company.setCloudUser(rs.getString("cloud_user"));
				company.setCloudUserId(rs.getString("clouduser_id"));
				company.setFullName(rs.getString("fullname"));
				company.setShortName(rs.getString("shortname"));
			}
			return company;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	
}
