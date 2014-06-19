package com.chinaops.web.edesktop.service;

import com.chinaops.web.edesktop.dao.EdeskDepartmentDaoImpl;
import com.chinaops.web.edesktop.dao.EdeskGroupDaoImpl;
import com.chinaops.web.edesktop.dao.EdeskStrategyDaoImpl;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.GroupStrategy;
import com.chinaops.web.edesktop.entity.Groups;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;

public class EdeskGroupService {
	
	private EdeskGroupDaoImpl edeskGroupDaoImpl;



	public EdeskGroupDaoImpl getEdeskGroupDaoImpl() {
		return edeskGroupDaoImpl;
	}


	public void setEdeskGroupDaoImpl(EdeskGroupDaoImpl edeskGroupDaoImpl) {
		this.edeskGroupDaoImpl = edeskGroupDaoImpl;
	}


	public Page getGroupByPage(int pageNo, int pageSize) {
		return edeskGroupDaoImpl.getGroupByPage(pageNo,pageSize);
	}

   public void addGroup(Groups group) {
	   edeskGroupDaoImpl.addGroup(group);
	}
   //删除和某个组关联的所有策略
	public void deleteAllStrategyAboutGroup(String groupId) {
	    this.edeskGroupDaoImpl.deleteAllStrategyAboutGroup(groupId);
	}
   //添加组和策略的关系
   public void addGroupAndStrategy(GroupStrategy groupStrategy) {
	   edeskGroupDaoImpl.addGroupAndStrategy(groupStrategy);
	}
   //删除和组关联的所有用户
  	public void updateAllAssEuserByGroupId(String groupId) {
  	    this.edeskGroupDaoImpl.updateAllAssEuserByGroupId(groupId);
  	}
    //添加和组关联的用户
  	public void updateUnAssToAssByGroupId(EdesktopUser edesktopUser) {
  	    this.edeskGroupDaoImpl.updateUnAssToAssByGroupId(edesktopUser);
  	}

	public Groups findById(int id) {
		return edeskGroupDaoImpl.findById(id);
	}
	 //检查策略名称是否唯一
	  public Boolean checkGroupNameUnique(String  groupName) {
		  return edeskGroupDaoImpl.checkGroupNameUnique(groupName);
		}
}
