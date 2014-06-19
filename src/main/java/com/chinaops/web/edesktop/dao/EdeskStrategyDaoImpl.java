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
import com.chinaops.web.edesktop.entity.GroupStrategy;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;
import com.chinaops.web.edesktop.utils.JDBCUtils;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_fr_CA;

/**
 * Description:
 * 策略管理
 */
public class EdeskStrategyDaoImpl{
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	//分页获取策略
	public Page getStrategyByPage(int pageNo, int pageSize) {
		Page page = new Page();
		int total = 0;
		List<Strategies> lists = new ArrayList<Strategies>();
		int currentPage=(pageNo-1)*pageSize;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			
			String countSql = "";
			String listSql = "";
			listSql = "select * from strategies order by id desc limit " + (currentPage)+ "," + pageSize + ";";
			countSql = "select count(*) from strategies";
			
			rs = stmt.executeQuery(countSql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			
			rs=stmt.executeQuery(listSql);
			while (rs.next()) {
				Strategies strategies = new Strategies();
				strategies.setId(rs.getInt("id"));
				strategies.setGroupId(rs.getInt("group_id"));
				strategies.setStrategiesName(rs.getString("strategies_name"));
				strategies.setCreate_time(rs.getString("create_time"));
				lists.add(strategies);
			}

			// 总记录数
			page.setTotalNumber(total);
			// 当前页
			page.setCurrentPage(pageNo);
			// 分页的数据
			page.setList(lists);
			// 总页数
			page.setTotalPage(page.getTotalNumber() % pageSize == 0 ? page
					.getTotalNumber() / pageSize : page.getTotalNumber()
					/ pageSize + 1);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
		return page;
	}
   //添加策略
	public void addStrategy(Strategies strategies) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into strategies(group_id,strategies_name,create_time) values('");
			sql.append("0 ','");
			sql.append(strategies.getStrategiesName()+"','");
			sql.append(strategies.getCreate_time());
			sql.append("');");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public List<Strategies> selectAllStrategys() {
		List<Strategies> lists = new ArrayList<Strategies>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from strategies";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Strategies strategies = new Strategies();
				strategies.setId(rs.getInt("id"));
				strategies.setGroupId(rs.getInt("group_id"));
				strategies.setStrategiesName(rs.getString("strategies_name"));
				strategies.setCreate_time(rs.getString("create_time"));
				lists.add(strategies);
			}
			return lists;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	//查询和某个组关联的所有策略id
	public List<GroupStrategy> selectAllAssStrategy(String groupId) {
		List<GroupStrategy> lists = new ArrayList<GroupStrategy>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = 	"select * from group_strategy where group_id='"+groupId+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GroupStrategy groupStrategy = new GroupStrategy();
				groupStrategy.setId(rs.getInt("id"));
				groupStrategy.setGroupId(rs.getString("group_id"));
				groupStrategy.setStrategiesId(rs.getString("strategies_id"));
				lists.add(groupStrategy);
			}
			return lists;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	//查询和某个组关联的所有策略id
		public List<Strategies> selectStrategysByIds(String StrategyIds) {
			List<Strategies> lists = new ArrayList<Strategies>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = 	"select * from strategies where id  in ("+StrategyIds+")";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Strategies strategies = new Strategies();
					strategies.setId(rs.getInt("id"));
					strategies.setGroupId(rs.getInt("group_id"));
					strategies.setStrategiesName(rs.getString("strategies_name"));
					strategies.setCreate_time(rs.getString("create_time"));
					lists.add(strategies);
				}
				return lists;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}
		
		  //检查策略名是否重复
		public Boolean checkStrategyNameUnique(String strategieName) {
			int total = -1;
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = 	"select count(*) from strategies where strategies_name = '"+strategieName+"'";
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
