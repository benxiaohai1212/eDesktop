package com.chinaops.web.edesktop.service;

import java.util.List;

import com.chinaops.web.edesktop.dao.EdesktopDaoImpl;
import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.Page;

/**
 * Description: 桌面管理
 */
public class EdesktopService {

	private EdesktopDaoImpl edesktopDaoImpl;

	public EdesktopDaoImpl getEdesktopDaoImpl() {
		return edesktopDaoImpl;
	}

	public void setEdesktopDaoImpl(EdesktopDaoImpl edesktopDaoImpl) {
		this.edesktopDaoImpl = edesktopDaoImpl;
	}

	// 根据条件查找云桌面（模糊）
	public Page selectByConditions(int pageNo, int pageSize, String fuzzySearchValue) {
		return this.edesktopDaoImpl.selectByConditions(pageNo, pageSize, fuzzySearchValue);
	}
	// 根据状态查找云桌面（云主机）
	public Page selectByStatusInfo(int pageNo, int pageSize, String fuzzySearchValue) {
		return this.edesktopDaoImpl.selectByStatusInfo(pageNo, pageSize, fuzzySearchValue);
	}
	// 添加选择的云主机
	public void addDesktop(Desktop desktop) {
		this.edesktopDaoImpl.addDesktop(desktop);
	}

	// 选择的云主机已经存在更新
	public void updateDesktop(Desktop desktop) {
		this.edesktopDaoImpl.updateDesktop(desktop);
	}

	// 添加所有的云主机到云主机临时表里
	public void addAllDesktop(Desktop desktop) {
		this.edesktopDaoImpl.addAllDesktop(desktop);
	}

	// 删除所有的云主机
	public void deleteAllDesktop() {
		this.edesktopDaoImpl.deleteAllDesktop();
	}

	// 分页查询云主机临时表
	public Page selectAllEdesktop(int pageNo, int pageSize) {
		return this.edesktopDaoImpl.selectAllEdesktop(pageNo, pageSize);
	}

	// 根据id查询云主机临时表
	public List<Desktop> findByDesktopIds(String selIps) {
		return this.edesktopDaoImpl.findByDesktopIds(selIps);
	}

	// 查询所有的云桌面
	public List<Desktop> selectAllEdesktopFromDesktop() {
		return this.edesktopDaoImpl.selectAllEdesktopFromDesktop();
	}

	// 查询所有的云桌面从临时表里
	public List<Desktop> selectAllEdesktopFromDesktopTemporary() {
		return this.edesktopDaoImpl.selectAllEdesktopFromDesktopTemporary();
	}

	// 根据ID删除云主机
	public void deleteDesktopById(int id) {
		this.edesktopDaoImpl.deleteDesktopById(id);
	}
	// 更新不正常的状态
	public void updateDesktopStatus(Desktop desktop) {
		this.edesktopDaoImpl.updateDesktopStatus(desktop);
	}
	// 更改桌面分配的状态（批量）
	public void updateDesktopDistributeStatus(String edesktopsId) {
		this.edesktopDaoImpl.updateDesktopDistributeStatus(edesktopsId);;
	}
	// 根据id查询云主机
	public List<Desktop> findByIdsFromDesktop(String edesktopsIds) {
		return this.edesktopDaoImpl.findByIdsFromDesktop(edesktopsIds);
	}
	// 更改桌面分配的状态（编辑）
	public void updateDistributeStatus(Desktop desktop) {
		this.edesktopDaoImpl.updateDistributeStatus(desktop);;
	}
}
