package com.chinaops.web.edesktop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.utils.JDBCUtils;

/**
 * Description: 
 * @version 2014-6-03 上午11:42:33 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class EdeskUserDaoImpl{
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	//查询
		public List<EdesktopUser> findByDeptId(int id) {
			List<EdesktopUser> lists = new ArrayList<EdesktopUser>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from euser where id='"+id+"'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					EdesktopUser eUser = new EdesktopUser();
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
					lists.add(eUser);
				}
				return lists;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		public Page getEuserByPage(int pageNo, int pageSize, String loginId) {
			Page page = new Page();
			int total = 0;
			List<EdesktopUser> lists = new ArrayList<EdesktopUser>();
			int currentPage=(pageNo-1)*pageSize;
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				
				String countSql = "";
				String listSql = "";
				
				if(loginId != null && loginId != ""){
					listSql = "select * from euser where login_id='"+loginId+"' order by id desc limit " + (currentPage) + "," + pageSize + ";";
	            	countSql = "select count(*) from euser where login_id='"+loginId+"'";
				}else{
					listSql = "select * from euser order by id desc limit " + (currentPage)+ "," + pageSize + ";";
					countSql = "select count(*) from euser";
				}
				
				rs = stmt.executeQuery(countSql);
				while (rs.next()) {
					total = rs.getInt(1);
				}
				
				rs=stmt.executeQuery(listSql);
				while (rs.next()) {
					EdesktopUser eUser = new EdesktopUser();
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
					lists.add(eUser);
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

		public void addEuser(EdesktopUser eUser) {
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				StringBuilder sql = new StringBuilder();
				sql.append("insert into euser(company_id,department_id,status,confirm_password,desktop_count,email,login_id,password,phone_number,username,domain_id) values('");
				sql.append(eUser.getCompanyId()+"','");
				sql.append(eUser.getDepartmentId()+"','");
				sql.append(eUser.getStatus()+"','");
				sql.append(eUser.getConfirmPassword()+"','");
				sql.append(eUser.getDesktopCount()+"','");
				sql.append(eUser.getEmail()+"','");
				sql.append(eUser.getLoginId()+"','");
				sql.append(eUser.getPassword()+"','");
				sql.append(eUser.getPhoneNumber()+"','");
				sql.append(eUser.getUserName()+"','");
				sql.append(eUser.getDomainId());
				sql.append("')");
				stmt.execute(sql.toString());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		public EdesktopUser findById(int id) {
			EdesktopUser eUser = new EdesktopUser();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from euser where id='"+id+"'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
				}
				return eUser;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		public void updateEuserById(EdesktopUser eUser) {
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				StringBuilder sql = new StringBuilder();
				sql.append("update euser set ");
				sql.append("username='");
				sql.append(eUser.getUserName());
				sql.append("',department_id='");
				sql.append(eUser.getDepartmentId());
				sql.append("',email='");
				sql.append(eUser.getEmail());
				sql.append("',phone_number='");
				sql.append(eUser.getPhoneNumber());
				sql.append("' where id=");
				sql.append(eUser.getId());
				stmt.execute(sql.toString());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		public void deleteById(int id) {
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "delete from euser where id='"+ id+"'";
				stmt.execute(sql);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		public List<EdesktopUser> selectEusers() {
			List<EdesktopUser> lists = new ArrayList<EdesktopUser>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from euser";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					EdesktopUser eUser = new EdesktopUser();
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
					lists.add(eUser);
				}
				return lists;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

		public EdesktopUser findByLoginId(String loginId) {
			EdesktopUser eUser = new EdesktopUser();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from euser where login_id='"+loginId+"'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
				}
				return eUser;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}
	   //按组名已查询（刘雅娟）
		public List<EdesktopUser> findByGroupId(int groupId) {
			List<EdesktopUser> lists = new ArrayList<EdesktopUser>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from euser where group_id='"+groupId+"'and desktop_count !=0  and desktop_count is not  NULL";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					EdesktopUser eUser = new EdesktopUser();
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
					lists.add(eUser);
				}
				return lists;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}
		 //查询所有未连接用户（刘雅娟）
		public List<EdesktopUser> findAllUnAss() {
			List<EdesktopUser> lists = new ArrayList<EdesktopUser>();
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				String sql = "select * from euser where group_id= 0 and desktop_count !=0  and desktop_count is not  NULL";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					EdesktopUser eUser = new EdesktopUser();
					eUser.setId(rs.getInt("id"));
					eUser.setCompanyId(rs.getInt("company_id"));
					eUser.setDepartmentId(rs.getInt("department_id"));
					eUser.setDesktopCount(rs.getInt("desktop_count"));
					eUser.setDomainId(rs.getString("domain_id"));
					eUser.setGroupId(rs.getInt("group_id"));
					eUser.setLoginId(rs.getString("login_id"));
					eUser.setStatus(rs.getString("status"));
					eUser.setUserName(rs.getString("username"));
					eUser.setPassword(rs.getString("password"));
					eUser.setConfirmPassword(rs.getString("confirm_password"));
					eUser.setEmail(rs.getString("email"));
					eUser.setPhoneNumber(rs.getString("phone_number"));
					lists.add(eUser);
				}
				return lists;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}
		}

//查询所有没有分配桌面的用户（刘雅娟）
	public List<EdesktopUser> findAllUnAssDesktop() {
		List<EdesktopUser> lists = new ArrayList<EdesktopUser>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from euser where  desktop_count =0  or desktop_count is NULL";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EdesktopUser eUser = new EdesktopUser();
				eUser.setId(rs.getInt("id"));
				eUser.setCompanyId(rs.getInt("company_id"));
				eUser.setDepartmentId(rs.getInt("department_id"));
				eUser.setDesktopCount(rs.getInt("desktop_count"));
				eUser.setDomainId(rs.getString("domain_id"));
				eUser.setGroupId(rs.getInt("group_id"));
				eUser.setLoginId(rs.getString("login_id"));
				eUser.setStatus(rs.getString("status"));
				eUser.setUserName(rs.getString("username"));
				eUser.setPassword(rs.getString("password"));
				eUser.setConfirmPassword(rs.getString("confirm_password"));
				eUser.setEmail(rs.getString("email"));
				eUser.setPhoneNumber(rs.getString("phone_number"));
				lists.add(eUser);
			}
			return lists;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
	//增加桌面数量（刘雅娟）
	public void addEuserDesktopCount(String euserIds) {
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("update euser set desktop_count=desktop_count+1  where id in ("+euserIds+")");
			stmt.execute(sql.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}

	}
	//更改桌面数量（刘雅娟）
		public void reduceEuserDesktopCount(String euserId) {
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				StringBuilder sql = new StringBuilder();
				sql.append("update euser set desktop_count=desktop_count-1  where id = "+euserId);
				stmt.execute(sql.toString());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}

		}
	//更改编辑时桌面数量（刘雅娟）
		public void updateDesktopCount(EdesktopUser edesktopUser) {
			try {
				conn = JDBCUtils.getConnection();
				stmt = conn.createStatement();
				StringBuilder sql = new StringBuilder();
				sql.append("update euser set desktop_count="+edesktopUser.getDesktopCount() +"where id ="+edesktopUser.getId());
				stmt.execute(sql.toString());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				JDBCUtils.closeResource(conn, stmt, rs);
			}

		}
	public List<EdesktopUser> findByIds(String ids) {
		List<EdesktopUser> eUserList=new ArrayList<EdesktopUser>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from euser where id in ("+ids+")";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EdesktopUser eUser = new EdesktopUser();
				eUser.setId(rs.getInt("id"));
				eUser.setCompanyId(rs.getInt("company_id"));
				eUser.setDepartmentId(rs.getInt("department_id"));
				eUser.setDesktopCount(rs.getInt("desktop_count"));
				eUser.setDomainId(rs.getString("domain_id"));
				eUser.setGroupId(rs.getInt("group_id"));
				eUser.setLoginId(rs.getString("login_id"));
				eUser.setStatus(rs.getString("status"));
				eUser.setUserName(rs.getString("username"));
				eUser.setPassword(rs.getString("password"));
				eUser.setConfirmPassword(rs.getString("confirm_password"));
				eUser.setEmail(rs.getString("email"));
				eUser.setPhoneNumber(rs.getString("phone_number"));
				eUserList.add(eUser);
			}
			return eUserList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			JDBCUtils.closeResource(conn, stmt, rs);
		}
	}
}
