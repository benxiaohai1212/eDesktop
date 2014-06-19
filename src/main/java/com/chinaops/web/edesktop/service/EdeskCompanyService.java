package com.chinaops.web.edesktop.service;

import com.chinaops.web.edesktop.dao.EdeskCompanyDaoImpl;
import com.chinaops.web.edesktop.entity.EdesktopCompany;

public class EdeskCompanyService {
	
	private EdeskCompanyDaoImpl edeskCompanyDaoImpl;

	public EdeskCompanyDaoImpl getEdeskCompanyDaoImpl() {
		return edeskCompanyDaoImpl;
	}
	public void setEdeskCompanyDaoImpl(EdeskCompanyDaoImpl edeskCompanyDaoImpl) {
		this.edeskCompanyDaoImpl = edeskCompanyDaoImpl;
	}
	//添加公司
	public void add(EdesktopCompany edesktopCompany) {
		edeskCompanyDaoImpl.addCompany(edesktopCompany);
	}
	//根据公司具体名称查询
	public EdesktopCompany selectByFullName(String fullName) {
		return edeskCompanyDaoImpl.selectByFullName(fullName);
	}
	//修改
	public void updateEdeskCompany(EdesktopCompany ec) {
		edeskCompanyDaoImpl.updateEdeskCompany(ec);
	}
	public EdesktopCompany selectCompany() {
		return edeskCompanyDaoImpl.selectCompany();
	}
}
