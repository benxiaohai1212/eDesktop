package com.chinaops.web.edesktop.service;

import com.chinaops.web.edesktop.dao.ApplicationCustomerDaoImpl;
import com.chinaops.web.edesktop.entity.Customer;
/**
 * Description: 
 * @version 2014-3-19 下午17:02:32 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class ApplicationCustomerService {
	
	private ApplicationCustomerDaoImpl applicationCustomerDaoImpl;
	
	public ApplicationCustomerDaoImpl getApplicationCustomerDaoImpl() {
		return applicationCustomerDaoImpl;
	}
	public void setApplicationCustomerDaoImpl(
			ApplicationCustomerDaoImpl applicationCustomerDaoImpl) {
		this.applicationCustomerDaoImpl = applicationCustomerDaoImpl;
	}
	//添加
	public void add(Customer customer){
		this.applicationCustomerDaoImpl.add(customer);
	}
	//根据用户名查找云主机
	public Customer findByUser_name(String user_name) {
		return this.applicationCustomerDaoImpl.findByUser_name(user_name);
	}
	//根据主键查询
	public Customer getById(int user_id) {
		return this.applicationCustomerDaoImpl.getById(user_id);
	}
	public void deleteCustomerById(int id) {
		this.applicationCustomerDaoImpl.deleteCustomerById(id);
	}
}
