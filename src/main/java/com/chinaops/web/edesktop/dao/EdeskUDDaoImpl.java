package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.EdesktopUD;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description:
 * 
 * @version 2014-6-9 下午14:50:53 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class EdeskUDDaoImpl {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public List<EdesktopUD> findByEuserId(String eUserId) {
		List<EdesktopUD> eUds = new ArrayList<EdesktopUD>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from euser_desktop where euser_id='" + eUserId + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EdesktopUD eUD = new EdesktopUD();
				eUD.setId(rs.getInt("id"));
				eUD.seteUserId(rs.getString("euser_id"));
				eUD.setDesktopId(rs.getString("desktop_id"));
				eUds.add(eUD);
			}
			return eUds;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	// 桌面和用户关联(刘雅娟)
	public void  insertDesktopEuser(EdesktopUD edesktopUD) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into euser_desktop (euser_id,desktop_id) values(");
			sql.append(edesktopUD.geteUserId()+",");
			sql.append(edesktopUD.getDesktopId()+")");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}
	public List<EdesktopUD> findByEdesktopId(String edesktopId) {
		List<EdesktopUD> eUds = new ArrayList<EdesktopUD>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from euser_desktop where desktop_id='" + edesktopId + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EdesktopUD eUD = new EdesktopUD();
				eUD.setId(rs.getInt("id"));
				eUD.seteUserId(rs.getString("euser_id"));
				eUD.setDesktopId(rs.getString("desktop_id"));
				eUds.add(eUD);
			}
			return eUds;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	// 删除和某个桌面和用户关联的信息
	public void deleteByEdesktopId(String edesktopId) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from euser_desktop where desktop_id='" + edesktopId + "'";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
}
