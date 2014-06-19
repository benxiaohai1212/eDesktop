package com.chinaops.web.edesktop.service;


import com.chinaops.web.edesktop.dao.EdeskDomainDaoImpl;
import com.chinaops.web.edesktop.entity.Domain;
import com.chinaops.web.edesktop.entity.EdesktopCompany;


public class EdeskDomainService {
	
	private EdeskDomainDaoImpl edeskDomainDaoImpl;
	

   public EdeskDomainDaoImpl getEdeskDomainDaoImpl() {
		return edeskDomainDaoImpl;
	}


	public void setEdeskDomainDaoImpl(EdeskDomainDaoImpl edeskDomainDaoImpl) {
		this.edeskDomainDaoImpl = edeskDomainDaoImpl;
	}
	//查询存不存在域服务器
	public Domain selectDomain() {
		return edeskDomainDaoImpl.selectDomain();
	}
	//添加域服务器
	public void addDomain(Domain domain) {
		edeskDomainDaoImpl.addDomain(domain);
	}
	//修改域服务器
	public void updateDomain(Domain domain) {
		edeskDomainDaoImpl.updateDomain(domain);
	}
}
