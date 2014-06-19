package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinaops.web.edesktop.entity.Customer;
import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.service.ApplicationCustomerService;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description:
 * 
 * 桌面管理
 */
public class EdesktopDaoImpl {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public Page selectByConditions(int pageNo, int pageSize, String fuzzySearchValue) {
		Page page = new Page();
		int total = 0;
		List<Desktop> lists = new ArrayList<Desktop>();
		int currentPage = (pageNo - 1) * pageSize;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String countSql = "";
			String listSql = "";
			if (fuzzySearchValue.equals("") || fuzzySearchValue == null) {
				listSql = "select * from desktop " + " order by id desc limit " + (currentPage) + "," + pageSize + ";";
				countSql = "select count(*) from desktop ";
			} else {
				listSql = "select * from desktop ";
				listSql += " where instance_name like '%" + fuzzySearchValue + "%'";
				listSql += " or distribute_status like '%" + fuzzySearchValue + "%'";
				listSql += " order by id desc limit " + (currentPage) + "," + pageSize + ";";
				countSql = "select count(*) from desktop ";
				countSql += " where instance_name like '%" + fuzzySearchValue + "%'";
				countSql += " or distribute_status like '%" + fuzzySearchValue + "%'";
			}

			rs = stmt.executeQuery(countSql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			rs = stmt.executeQuery(listSql);
			while (rs.next()) {
				Desktop desktop = new Desktop();
				desktop.setId(rs.getInt("id"));
				desktop.setInstance_id(rs.getString("instance_id"));
				desktop.setInstance_name(rs.getString("instance_name"));
				desktop.setPort(rs.getString("port"));
				desktop.setIp(rs.getString("ip"));
				desktop.setInstance_type(rs.getString("instance_type"));
				desktop.setDistribute_status(rs.getString("distribute_status"));
				desktop.setDate_created(rs.getString("date_created"));
				desktop.setDate_amended(rs.getString("date_amended"));
				desktop.setAuto_hand(rs.getString("auto_hand"));
				desktop.setStatus(rs.getString("status"));
				lists.add(desktop);
			}

			// 总记录数
			page.setTotalNumber(total);
			// 当前页
			page.setCurrentPage(pageNo);
			// 分页的数据
			page.setList(lists);
			// 总页数
			page.setTotalPage(page.getTotalNumber() % pageSize == 0 ? page.getTotalNumber() / pageSize : page.getTotalNumber() / pageSize + 1);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return page;
	}
	public Page selectByStatusInfo(int pageNo, int pageSize, String fuzzySearchValue) {
		Page page = new Page();
		int total = 0;
		List<Desktop> lists = new ArrayList<Desktop>();
		int currentPage = (pageNo - 1) * pageSize;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String countSql = "";
			String listSql = "";
			if (fuzzySearchValue.equals("") || fuzzySearchValue == null) {
				listSql = "select * from desktop " + " order by id desc limit " + (currentPage) + "," + pageSize + ";";
				countSql = "select count(*) from desktop ";
			} else {
				listSql = "select * from desktop ";
				listSql += " where distribute_status ='" + fuzzySearchValue + "'";
				listSql += " order by id desc limit " + (currentPage) + "," + pageSize + ";";
				countSql = "select count(*) from desktop ";
				countSql += " where distribute_status ='" + fuzzySearchValue + "'";
			}

			rs = stmt.executeQuery(countSql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			rs = stmt.executeQuery(listSql);
			while (rs.next()) {
				Desktop desktop = new Desktop();
				desktop.setId(rs.getInt("id"));
				desktop.setInstance_id(rs.getString("instance_id"));
				desktop.setInstance_name(rs.getString("instance_name"));
				desktop.setPort(rs.getString("port"));
				desktop.setIp(rs.getString("ip"));
				desktop.setInstance_type(rs.getString("instance_type"));
				desktop.setDistribute_status(rs.getString("distribute_status"));
				desktop.setDate_created(rs.getString("date_created"));
				desktop.setDate_amended(rs.getString("date_amended"));
				desktop.setAuto_hand(rs.getString("auto_hand"));
				desktop.setStatus(rs.getString("status"));
				lists.add(desktop);
			}

			// 总记录数
			page.setTotalNumber(total);
			// 当前页
			page.setCurrentPage(pageNo);
			// 分页的数据
			page.setList(lists);
			// 总页数
			page.setTotalPage(page.getTotalNumber() % pageSize == 0 ? page.getTotalNumber() / pageSize : page.getTotalNumber() / pageSize + 1);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return page;
	}
	public Page selectAllEdesktop(int pageNo, int pageSize) {
		Page page = new Page();
		int total = 0;
		List<Desktop> lists = new ArrayList<Desktop>();
		int currentPage = (pageNo - 1) * pageSize;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String countSql = "";
			String listSql = "";
			listSql = "select * from desktop_temporary " + " order by id desc limit " + (currentPage) + "," + pageSize + ";";
			countSql = "select count(*) from desktop_temporary ";
			rs = stmt.executeQuery(countSql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			rs = stmt.executeQuery(listSql);
			while (rs.next()) {
				Desktop desktop = new Desktop();
				desktop.setId(rs.getInt("id"));
				desktop.setInstance_id(rs.getString("instance_id"));
				desktop.setInstance_name(rs.getString("instance_name"));
				desktop.setIp(rs.getString("ip"));
				desktop.setInstance_type(rs.getString("instance_type"));
				desktop.setDate_created(rs.getString("date_created"));
				desktop.setDate_amended(rs.getString("date_amended"));
				lists.add(desktop);
			}

			// 总记录数
			page.setTotalNumber(total);
			// 当前页
			page.setCurrentPage(pageNo);
			// 分页的数据
			page.setList(lists);
			// 总页数
			page.setTotalPage(page.getTotalNumber() % pageSize == 0 ? page.getTotalNumber() / pageSize : page.getTotalNumber() / pageSize + 1);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return page;
	}

	public List<Desktop> selectAllEdesktopFromDesktopTemporary() {
		List<Desktop> lists = new ArrayList<Desktop>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String listSql = "";
			listSql = "select * from desktop_temporary ";
			rs = stmt.executeQuery(listSql);
			while (rs.next()) {
				Desktop desktop = new Desktop();
				desktop.setId(rs.getInt("id"));
				desktop.setInstance_id(rs.getString("instance_id"));
				desktop.setInstance_name(rs.getString("instance_name"));
				desktop.setIp(rs.getString("ip"));
				desktop.setInstance_type(rs.getString("instance_type"));
				desktop.setDate_created(rs.getString("date_created"));
				desktop.setDate_amended(rs.getString("date_amended"));
				lists.add(desktop);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return lists;
	}

	// 添加选择的云桌面
	public void addDesktop(Desktop desktop) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into desktop(instance_id,instance_name,port,ip,instance_type,distribute_status,date_created,date_amended,auto_hand,status) values('");
			sql.append(desktop.getInstance_id() + "','");
			sql.append(desktop.getInstance_name() + "','");
			sql.append(desktop.getPort() + "','");
			sql.append(desktop.getIp() + "','");
			sql.append(desktop.getInstance_type() + "','");
			sql.append(desktop.getDistribute_status() + "','");
			sql.append(desktop.getDate_created() + "','");
			sql.append(desktop.getDate_amended() + "','");
			sql.append(desktop.getAuto_hand() + "','");
			sql.append(desktop.getStatus());
			sql.append("')");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}

	// 添加选择的云桌面
	public void updateDesktop(Desktop desktop) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("update desktop set instance_id='");
			sql.append(desktop.getInstance_id() + "',instance_name='");
			sql.append(desktop.getInstance_name() + "',port='");
			sql.append(desktop.getPort() + "',ip='");
			sql.append(desktop.getIp() + "',instance_type='");
			sql.append(desktop.getInstance_type() + "',date_created='");
			sql.append(desktop.getDate_created() + "',date_amended='");
			sql.append(desktop.getDate_amended() + "',auto_hand='1");
			sql.append("' where instance_id= '"+desktop.getInstance_id()+"'");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}

	// 添加云主机到临时表里面
	public void addAllDesktop(Desktop desktop) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into desktop_temporary(id,instance_id,instance_name,ip,instance_type,date_created,date_amended) values('");
			sql.append(desktop.getId() + "','");
			sql.append(desktop.getInstance_id() + "','");
			sql.append(desktop.getInstance_name() + "','");
			sql.append(desktop.getIp() + "','");
			sql.append(desktop.getInstance_type() + "','");
			sql.append(desktop.getDate_created() + "','");
			sql.append(desktop.getDate_amended());
			sql.append("')");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}

	public void deleteAllDesktop() {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from desktop_temporary ";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}

	// 根据id查询临时表
	public List<Desktop> findByDesktopIds(String selIps) {
		List<Desktop> lists = new ArrayList<Desktop>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from desktop_temporary where id in (" + selIps + ")";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Desktop desktop = new Desktop();
				desktop.setId(rs.getInt("id"));
				desktop.setInstance_id(rs.getString("instance_id"));
				desktop.setInstance_name(rs.getString("instance_name"));
				desktop.setIp(rs.getString("ip"));
				desktop.setInstance_type(rs.getString("instance_type"));
				desktop.setDate_created(rs.getString("date_created"));
				desktop.setDate_amended(rs.getString("date_amended"));
				lists.add(desktop);
			}
			return lists;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	// 查询所有的桌面
	public List<Desktop> selectAllEdesktopFromDesktop() {
		List<Desktop> lists = new ArrayList<Desktop>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from desktop";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Desktop desktop = new Desktop();
				desktop.setId(rs.getInt("id"));
				desktop.setInstance_id(rs.getString("instance_id"));
				desktop.setInstance_name(rs.getString("instance_name"));
				desktop.setPort(rs.getString("port"));
				desktop.setIp(rs.getString("ip"));
				desktop.setInstance_type(rs.getString("instance_type"));
				desktop.setDistribute_status(rs.getString("distribute_status"));
				desktop.setDate_created(rs.getString("date_created"));
				desktop.setDate_amended(rs.getString("date_amended"));
				desktop.setAuto_hand(rs.getString("auto_hand"));
				desktop.setStatus(rs.getString("status"));
				lists.add(desktop);
			}
			return lists;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public void deleteDesktopById(int id) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from desktop where id= '"+id+" '";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}
	// 更新不正常的状态
	public void updateDesktopStatus(Desktop desktop) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("update desktop set status=0  where id='"+desktop.getId()+"')");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}
	
	// 更新不正常的状态
		public void updateDesktopDistributeStatus(String edesktopsId) {
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				StringBuilder sql = new StringBuilder();
				sql.append("update desktop set distribute_status='分配' where id in ("+edesktopsId+")");
				stmt.execute(sql.toString());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}

		}
		// 根据id查询临时表
		public List<Desktop> findByIdsFromDesktop(String edesktopsIds) {
			List<Desktop> lists = new ArrayList<Desktop>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from desktop where id in (" + edesktopsIds + ")";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Desktop desktop = new Desktop();
					desktop.setId(rs.getInt("id"));
					desktop.setInstance_id(rs.getString("instance_id"));
					desktop.setInstance_name(rs.getString("instance_name"));
					desktop.setPort(rs.getString("port"));
					desktop.setIp(rs.getString("ip"));
					desktop.setInstance_type(rs.getString("instance_type"));
					desktop.setDistribute_status(rs.getString("distribute_status"));
					desktop.setDate_created(rs.getString("date_created"));
					desktop.setDate_amended(rs.getString("date_amended"));
					desktop.setAuto_hand(rs.getString("auto_hand"));
					desktop.setStatus(rs.getString("status"));
					lists.add(desktop);
				}
				return lists;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		// 更新分配状态（编辑）
			public void updateDistributeStatus(Desktop desktop) {
				try {
					conn = JDBCUtils.getConnection();
					stmt = conn.createStatement();
					StringBuilder sql = new StringBuilder();
					sql.append("update desktop set distribute_status='"+desktop.getDistribute_status()+"'where id ="+desktop.getId());
					stmt.execute(sql.toString());
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				} finally {
					JDBCUtils.closeResource(conn, stmt, rs);
				}

			}

}
