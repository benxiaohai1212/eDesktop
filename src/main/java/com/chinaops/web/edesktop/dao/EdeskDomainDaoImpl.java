package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chinaops.web.edesktop.entity.Domain;
import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description:域环境设置
 */
public class EdeskDomainDaoImpl{
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
    //添加域服务器
	public void addDomain(Domain domain) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into domain(domain_name,domain_ip) values('");
			sql.append(domain.getDomainName()+"','");
			sql.append(domain.getDomainIp());
			sql.append("')");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		
	}
	//修改域服务器
	public void updateDomain(Domain domain) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("update domain set ");
			sql.append("domain_name='");
			sql.append(domain.getDomainName());
			sql.append("',domain_ip='");
			sql.append(domain.getDomainIp()+"'");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}
	   //查询有没有域信息
		public Domain selectDomain() {
			Domain domain = new Domain();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from domain";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					domain.setId(rs.getInt("id"));
					domain.setDomainName(rs.getString("domain_name"));
					domain.setDomainIp(rs.getString("domain_ip"));
				}
				return domain;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}
		
}
