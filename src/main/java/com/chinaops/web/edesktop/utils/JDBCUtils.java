package com.chinaops.web.edesktop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCUtils {
	private static Connection conn;

	public static Connection getConnection() {

		try {
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			String driver = bundle.getString("driverD");
			String url = bundle.getString("urlU");
			String root = bundle.getString("rootR");
			String password = bundle.getString("passwordP");
			// 注册驱动
			Class.forName(driver);
			// 获得链接
			conn = DriverManager.getConnection(url, root, password);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		return conn;
	}

	public static void closeResource(Connection conn, Statement stmt,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		}
	}

}