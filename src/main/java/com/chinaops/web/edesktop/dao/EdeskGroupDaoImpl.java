package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import net.sf.json.util.NewBeanInstanceStrategy;

import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.GroupStrategy;
import com.chinaops.web.edesktop.entity.Groups;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;
import com.chinaops.web.edesktop.utils.JDBCUtils;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_fr_CA;

/**
 * Description: 组管理
 */
public class EdeskGroupDaoImpl {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	// 分页获取组
	public Page getGroupByPage(int pageNo, int pageSize) {
		Page page = new Page();
		int total = 0;
		List<Groups> lists = new ArrayList<Groups>();
		int currentPage = (pageNo - 1) * pageSize;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();

			String countSql = "";
			String listSql = "";
			listSql = "select * from groups order by id desc limit " + (currentPage) + "," + pageSize + ";";
			countSql = "select count(*) from groups";

			rs = stmt.executeQuery(countSql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			rs = stmt.executeQuery(listSql);
			while (rs.next()) {
				Groups groups = new Groups();
				groups.setId(rs.getInt("id"));
				groups.setGroupName(rs.getString("group_name"));
				groups.setCreateTime(rs.getString("create_time"));
				lists.add(groups);
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

	// 添加组
	public void addGroup(Groups group) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into groups(group_name,create_time) values('");
			sql.append(group.getGroupName() + "','");
			sql.append(group.getCreateTime());
			sql.append("');");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	// 添加组和策略的关系
	public void addGroupAndStrategy(GroupStrategy groupStrategy) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into group_strategy(group_id,strategies_id) values('");
			sql.append(groupStrategy.getGroupId() + "','");
			sql.append(groupStrategy.getStrategiesId());
			sql.append("');");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	// 删除和某个组关联的所有策略
	public void deleteAllStrategyAboutGroup(String groupId) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from group_strategy where group_id='" + groupId + "'";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	// 删除和某个组关联的所有用户
	public void updateAllAssEuserByGroupId(String groupId) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "update euser set group_id=0  where group_id='" + groupId + "'";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	// 添加组和用户的关系
	public void updateUnAssToAssByGroupId(EdesktopUser edesktopUser) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "update euser set group_id= '"+edesktopUser.getGroupId()+" 'where id='" + edesktopUser.getId() + "'";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	public Groups findById(int id) {
		Groups eGroup = new Groups();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from groups where id='"+id+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				eGroup.setId(rs.getInt("id"));
				eGroup.setGroupName(rs.getString("group_name"));
				eGroup.setCreateTime(rs.getString("create_time"));
			}
			return eGroup;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	  //检查组名是否重复
	public Boolean checkGroupNameUnique(String  groupName) {
		int total = -1;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = 	"select count(*) from groups where group_name = '"+groupName+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			if (total == 0){
				return true;
				}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return false;
	}
}
