package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description:
 * 
 * @version 2014-5-23 下午17:42:32 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class EdeskDepartmentDaoImpl{
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	//分页获取部门
	public Page getDeptByPage(int pageNo, int pageSize) {
		Page page = new Page();
		int total = 0;
		List<EdesktopDepartment> lists = new ArrayList<EdesktopDepartment>();
		int currentPage=(pageNo-1)*pageSize;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			
			String countSql = "";
			String listSql = "";
			listSql = "select * from dept order by id desc limit " + (currentPage)+ "," + pageSize + ";";
			countSql = "select count(*) from dept";
			
			rs = stmt.executeQuery(countSql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			
			rs=stmt.executeQuery(listSql);
			while (rs.next()) {
				EdesktopDepartment dept = new EdesktopDepartment();
				dept.setId(rs.getInt("id"));
				dept.setDeptName(rs.getString("deptname"));
				dept.setCompanyId(rs.getInt("company_id"));
				lists.add(dept);
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

	public void addDept(EdesktopDepartment eDepartment) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into dept(deptname,company_id) values('");
			sql.append(eDepartment.getDeptName()+"','");
			sql.append(eDepartment.getCompanyId());
			sql.append("')");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public EdesktopDepartment findById(int id) {
		EdesktopDepartment eDepartment = new EdesktopDepartment();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from dept where id='"+id+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				eDepartment.setId(rs.getInt("id"));
				eDepartment.setDeptName(rs.getString("deptname"));
				eDepartment.setCompanyId(rs.getInt("company_id"));
			}
			return eDepartment;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public void updateDeptNameById(EdesktopDepartment eDepartment) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("update dept set ");
			sql.append("deptname='");
			sql.append(eDepartment.getDeptName());
			sql.append("' where id=");
			sql.append(eDepartment.getId());
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public void dept_delete(int id) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from dept where id='"+ id+"'";
			stmt.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public List<EdesktopDepartment> selectDepts() {
		List<EdesktopDepartment> lists = new ArrayList<EdesktopDepartment>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from dept";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EdesktopDepartment eDepartment = new EdesktopDepartment();
				eDepartment.setId(rs.getInt("id"));
				eDepartment.setDeptName(rs.getString("deptname"));
				eDepartment.setCompanyId(rs.getInt("company_id"));
				lists.add(eDepartment);
			}
			return lists;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}

	public EdesktopDepartment findByDeptName(String deptName) {
		EdesktopDepartment eDepartment = new EdesktopDepartment();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from dept where deptname='"+deptName+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				eDepartment.setId(rs.getInt("id"));
				eDepartment.setDeptName(rs.getString("deptname"));
				eDepartment.setCompanyId(rs.getInt("company_id"));
			}
			return eDepartment;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	
}
