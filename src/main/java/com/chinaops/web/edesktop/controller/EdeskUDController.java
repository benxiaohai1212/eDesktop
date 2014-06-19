package com.chinaops.web.edesktop.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.EdesktopUD;
import com.chinaops.web.edesktop.service.EdeskUDService;

/**
 * Description: 
 * @version 2014-6-9 下午14:41:53 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
@Controller
public class EdeskUDController {
    
	private static final Log  log=LogFactory.getLog(EdeskUDController.class);
	private EdeskUDService edeskUDService;
	
	@Autowired
	public void setEdeskUDService(EdeskUDService edeskUDService) {
		this.edeskUDService = edeskUDService;
	}
	
	//根据Id查询
	@RequestMapping(value = "/ud_selectByEuserId.do", method = RequestMethod.POST)
	public @ResponseBody List<EdesktopUD> ud_selectByEuserId(@RequestParam String eUserId) {
		List<EdesktopUD> eUDs = edeskUDService.findByEuserId(eUserId);
		return eUDs;
 	}
	
}
