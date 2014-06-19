package com.chinaops.web.edesktop.entity;

/**
 * Description: 策略管理
 */
public class Strategies {
	private int id;
	private int groupId;
	private String strategiesName;
	private String create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getStrategiesName() {
		return strategiesName;
	}
	public void setStrategiesName(String strategiesName) {
		this.strategiesName = strategiesName;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}
