package com.chinaops.web.edesktop.service;

import java.util.List;

import com.chinaops.web.edesktop.dao.EdeskDepartmentDaoImpl;
import com.chinaops.web.edesktop.dao.EdeskStrategyDaoImpl;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.GroupStrategy;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;

public class EdeskStrategyService {
	
	private EdeskStrategyDaoImpl edeskStrategyDaoImpl;

	public EdeskStrategyDaoImpl getEdeskStrategyDaoImpl() {
		return edeskStrategyDaoImpl;
	}

	public void setEdeskStrategyDaoImpl(EdeskStrategyDaoImpl edeskStrategyDaoImpl) {
		this.edeskStrategyDaoImpl = edeskStrategyDaoImpl;
	}
  //分页查询策略
	public Page getStrategyByPage(int pageNo, int pageSize) {
		return edeskStrategyDaoImpl.getStrategyByPage(pageNo,pageSize);
	}
 //添加策略
   public void addStrategy(Strategies strategies) {
	   edeskStrategyDaoImpl.addStrategy(strategies);
	}
  //查询所有策略
   public List<Strategies> selectAllStrategys() {
	   return  edeskStrategyDaoImpl.selectAllStrategys();
	}
   //查询和某个组关联的所有策略id
   public List<GroupStrategy> selectAllAssStrategy(String groupId) {
	   return  edeskStrategyDaoImpl.selectAllAssStrategy(groupId);
	}
   //根据策略id查找策略
   public List<Strategies> selectStrategysByIds(String StrategyIds) {
	   return  edeskStrategyDaoImpl.selectStrategysByIds(StrategyIds);
	}
   //检查策略名称是否唯一
   public Boolean checkStrategyNameUnique(String  strategieName) {
	  return edeskStrategyDaoImpl.checkStrategyNameUnique(strategieName);
	}
}
