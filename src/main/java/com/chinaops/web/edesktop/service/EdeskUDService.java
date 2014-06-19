package com.chinaops.web.edesktop.service;

import java.util.List;

import com.chinaops.web.edesktop.dao.EdeskUDDaoImpl;
import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.EdesktopUD;

public class EdeskUDService {

	private EdeskUDDaoImpl edeskUDDaoImpl;

	public EdeskUDDaoImpl getEdeskUDDaoImpl() {
		return edeskUDDaoImpl;
	}

	public void setEdeskUDDaoImpl(EdeskUDDaoImpl edeskUDDaoImpl) {
		this.edeskUDDaoImpl = edeskUDDaoImpl;
	}

	public List<EdesktopUD> findByEuserId(String eUserId) {
		return edeskUDDaoImpl.findByEuserId(eUserId);
	}

	// 桌面和用户关联(刘雅娟)
	public void insertDesktopEuser(EdesktopUD edesktopUD) {
		this.edeskUDDaoImpl.insertDesktopEuser(edesktopUD);
	}

	// 显示和桌面关联的用户（刘雅娟）
	public List<EdesktopUD> findByEdesktopId(String edesktopId) {
		return edeskUDDaoImpl.findByEdesktopId(edesktopId);
	}

	// 显示和桌面关联的用户（刘雅娟）
	public void deleteByEdesktopId(String edesktopId) {
		 this.edeskUDDaoImpl.deleteByEdesktopId(edesktopId);
	}
}
