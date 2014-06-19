package com.chinaops.web.edesktop.entity;

/**
 * Description: 域环境设置
 */
public class Domain {
	private int id;
	private String domainName;
	private String domainIp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomainIp() {
		return domainIp;
	}
	public void setDomainIp(String domainIp) {
		this.domainIp = domainIp;
	}
	

}
