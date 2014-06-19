package com.chinaops.web.edesktop.entity;

/**
 * Description: 桌面管理
 */
public class Desktop {
	private int id;
	private String instance_id;
	private String instance_name;
	private String port;
	private String ip;
	private String instance_type;
	private String distribute_status;
	private String date_created;
	private String date_amended;
	private String auto_hand;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstance_id() {
		return instance_id;
	}
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	public String getInstance_name() {
		return instance_name;
	}
	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getInstance_type() {
		return instance_type;
	}
	public void setInstance_type(String instance_type) {
		this.instance_type = instance_type;
	}
	public String getDistribute_status() {
		return distribute_status;
	}
	public void setDistribute_status(String distribute_status) {
		this.distribute_status = distribute_status;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_amended() {
		return date_amended;
	}
	public void setDate_amended(String date_amended) {
		this.date_amended = date_amended;
	}
	public String getAuto_hand() {
		return auto_hand;
	}
	public void setAuto_hand(String auto_hand) {
		this.auto_hand = auto_hand;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    
	
}
