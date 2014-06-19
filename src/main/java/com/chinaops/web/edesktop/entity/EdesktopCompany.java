package com.chinaops.web.edesktop.entity;

/**
 * Description: 
 * @version 2014-5-29 上午10:48:04 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
public class EdesktopCompany {
	private int id;
    private String fullName;
    private String shortName;
    private String cloudUserId;
    private String cloudUser;
    private String markId;
    
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCloudUserId() {
		return cloudUserId;
	}
	public void setCloudUserId(String cloudUserId) {
		this.cloudUserId = cloudUserId;
	}
	public String getCloudUser() {
		return cloudUser;
	}
	public void setCloudUser(String cloudUser) {
		this.cloudUser = cloudUser;
	}
    
    
}
