package com.chinaops.web.edesktop.service;

import java.util.List;

import com.chinaops.web.edesktop.dao.EdeskDepartmentDaoImpl;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.Page;

public class EdeskDepartmentService {
	
	private EdeskDepartmentDaoImpl edeskDepartmentDaoImpl;
	public EdeskDepartmentDaoImpl getEdeskDepartmentDaoImpl() {
		return edeskDepartmentDaoImpl;
	}

	public void setEdeskDepartmentDaoImpl(EdeskDepartmentDaoImpl edeskDepartmentDaoImpl) {
		this.edeskDepartmentDaoImpl = edeskDepartmentDaoImpl;
	}

	public Page getDeptByPage(int pageNo, int pageSize) {
		return edeskDepartmentDaoImpl.getDeptByPage(pageNo,pageSize);
	}

	public void addDept(EdesktopDepartment eDepartment) {
		edeskDepartmentDaoImpl.addDept(eDepartment);
	}

	public EdesktopDepartment findById(int id) {
		return edeskDepartmentDaoImpl.findById(id);
	}

	public void updateDeptNameById(EdesktopDepartment eDepartment) {
		edeskDepartmentDaoImpl.updateDeptNameById(eDepartment);
	}

	public void dept_delete(int id) {
		edeskDepartmentDaoImpl.dept_delete(id);
	}

	public List<EdesktopDepartment> selectDepts() {
		return edeskDepartmentDaoImpl.selectDepts();
	}

	public EdesktopDepartment findByDeptName(String deptName) {
		return edeskDepartmentDaoImpl.findByDeptName(deptName);
	}
}
