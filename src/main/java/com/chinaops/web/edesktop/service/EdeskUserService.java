package com.chinaops.web.edesktop.service;

import java.util.List;

import com.chinaops.web.edesktop.dao.EdeskDepartmentDaoImpl;
import com.chinaops.web.edesktop.dao.EdeskUserDaoImpl;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.Page;

public class EdeskUserService {

	private EdeskUserDaoImpl edeskUserDaoImpl;

	public EdeskUserDaoImpl getEdeskUserDaoImpl() {
		return edeskUserDaoImpl;
	}

	public void setEdeskUserDaoImpl(EdeskUserDaoImpl edeskUserDaoImpl) {
		this.edeskUserDaoImpl = edeskUserDaoImpl;
	}

	// 按组查询（刘雅娟）
	public List<EdesktopUser> findByGroupId(int id) {
		return edeskUserDaoImpl.findByGroupId(id);
	}

	// 查询没有和组关联的用户（刘雅娟）
	public List<EdesktopUser> findAllUnAss() {
		return edeskUserDaoImpl.findAllUnAss();
	}

	public List<EdesktopUser> findByDeptId(int id) {
		return edeskUserDaoImpl.findByDeptId(id);
	}

	public Page getEuserByPage(int pageNo, int pageSize, String loginId) {
		return edeskUserDaoImpl.getEuserByPage(pageNo, pageSize, loginId);
	}

	public void addEuser(EdesktopUser eUser) {
		edeskUserDaoImpl.addEuser(eUser);
	}

	public EdesktopUser findById(int id) {
		return edeskUserDaoImpl.findById(id);
	}

	public void updateEuserById(EdesktopUser eUser) {
		edeskUserDaoImpl.updateEuserById(eUser);
	}

	public void deleteById(int id) {
		edeskUserDaoImpl.deleteById(id);
	}

	public List<EdesktopUser> selectEusers() {
		return edeskUserDaoImpl.selectEusers();
	}

	public EdesktopUser findByLoginId(String loginId) {
		return edeskUserDaoImpl.findByLoginId(loginId);
	}

	// 查询没有分配桌面的用户（刘雅娟）
	public List<EdesktopUser> findAllUnAssDesktop() {
		return edeskUserDaoImpl.findAllUnAssDesktop();
	}

	// 增加桌面数量（增加）刘雅娟
	public void addEuserDesktopCount(String euserIds) {
		edeskUserDaoImpl.addEuserDesktopCount(euserIds);
	}

	// 减少桌面数量（增加）刘雅娟
	public void reduceEuserDesktopCount(String euserId) {
		edeskUserDaoImpl.reduceEuserDesktopCount(euserId);
	}

	// 根据Id查询所有和桌面关联的用户（刘雅娟）
	public List<EdesktopUser> findByIds(String ids) {
		return edeskUserDaoImpl.findByIds(ids);
	}

	// 更改编辑时桌面数量（刘雅娟）
	public void updateDesktopCount(EdesktopUser edesktopUser) {
		edeskUserDaoImpl.updateDesktopCount(edesktopUser);
	}
}
