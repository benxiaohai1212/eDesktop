package com.chinaops.web.edesktop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaops.web.edesktop.entity.Domain;
import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.service.EdeskDomainService;


/**
 * Description:
 * 域环境设置
 */
@Controller
public class EdeskDomianController {
	private static final Log  log=LogFactory.getLog(EdeskDomianController.class);
	private EdeskDomainService edeskDomainService;
	
	@Autowired
	public void setEdeskDomainService(EdeskDomainService edeskDomainService) {
		this.edeskDomainService = edeskDomainService;
	}

	@RequestMapping("/domain.htm")
	public String companyPageShow(@RequestParam String username, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "domain/domain";
	}
	
	@RequestMapping("/selectDomain.do")  
	public @ResponseBody Domain selectDomain(){
		Domain domain = edeskDomainService.selectDomain();
		return domain;
	}
	//添加
    @RequestMapping(value = "/addDomainInfo.do", method = RequestMethod.POST)
    public @ResponseBody String addDomainInfo(@RequestParam String domainName,@RequestParam String domainIp){
	      String result="";
	      Domain domain = new Domain();
	      domain.setDomainName(domainName);
	      domain.setDomainIp(domainIp);
		  try {
			  edeskDomainService.addDomain(domain);
			  result="1";
		  } catch (Exception e) {
				log.error(e.getMessage());
		  }
	      return result;
    }
   //修改
    @RequestMapping(value = "/editDomainInfo.do", method = RequestMethod.POST)
	public @ResponseBody String modifyByFullName(@RequestParam String domainName,@RequestParam String domainIp) {
    	String result = "";
		Domain domain = new Domain();
		domain.setDomainName(domainName);
	    domain.setDomainIp(domainIp);
		try {
			edeskDomainService.updateDomain(domain);
			result = "1";
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
 	}
	
}
