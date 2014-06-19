package com.chinaops.web.edesktop.entity;

/**
 * Description: 
 * @version 2014-5-30 下午16:43:25 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class EdesktopDepartment {
	private int id;
    private String deptName;
    private int companyId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
    
    
}
