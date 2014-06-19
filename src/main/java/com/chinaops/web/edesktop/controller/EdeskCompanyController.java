package com.chinaops.web.edesktop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.chinaops.cloud.auth.shared.Company;
import com.chinaops.cloud.framework.CloudUserManager;
import com.chinaops.cloud.framework.CompanyManager;
import com.chinaops.cloud.metadata.shared.CloudUser;
import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.service.EdeskCompanyService;

/**
 * Description:
 * 
 * @version 2014-5-26 上午9:33:27 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
@Controller
public class EdeskCompanyController {
	private static final Log  log=LogFactory.getLog(EdeskCompanyController.class);
	private CompanyManager companyManager;
	private CloudUserManager cloudUserManager;
	private EdeskCompanyService edeskCompanyService;
	
	@Autowired
	public void setCloudUserManager(CloudUserManager cloudUserManager) {
		this.cloudUserManager = cloudUserManager;
	}
	@Autowired
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}
	@Autowired
	public void setEdeskCompanyService(EdeskCompanyService edeskCompanyService) {
		this.edeskCompanyService = edeskCompanyService;
	}
	
	@RequestMapping("/company.htm")  
	public String companyPageShow(@RequestParam String username, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "userManager/company";
	}

	@RequestMapping("/selectCompany.do")  
	public @ResponseBody EdesktopCompany selectCompany(){
		EdesktopCompany company = edeskCompanyService.selectCompany();
		return company;
	}
	
	//得到所有公司
	@RequestMapping(value = "/companyList.do", method = RequestMethod.POST)
	public @ResponseBody List<Company> getLists() throws Exception{
	   List<Company> allCompanies = new ArrayList<Company>();
	   List<CloudUser> allCloudUsers = cloudUserManager.getAllCloudUsers();
	   if(allCloudUsers.size()>0){
		  for (CloudUser cloudUser:allCloudUsers) {
			 int companyId = (cloudUser.getCompany()).getId();
		     if(companyId != 0){
			     Company company = companyManager.getCompanyById(companyId);
			     company.setCloudUser(cloudUser);
			     allCompanies.add(company);
 	         }
		  }
		}
		return allCompanies;
    }
	
	//添加
    @RequestMapping(value = "/company_add.do", method = RequestMethod.POST)
    public @ResponseBody String company_add(@RequestParam String markId,@RequestParam String fullName, @RequestParam String shortName, @RequestParam String cloudUserId, @RequestParam String cloudUser){
	      String result="";
	      EdesktopCompany edesktopCompany = new EdesktopCompany();
	      edesktopCompany.setMarkId(markId);
	      edesktopCompany.setFullName(fullName);
	      edesktopCompany.setShortName(shortName);
	      edesktopCompany.setCloudUserId(cloudUserId);
	      edesktopCompany.setCloudUser(cloudUser);
		  try {
			  edeskCompanyService.add(edesktopCompany);
			  result="1";
		  } catch (Exception e) {
				log.error(e.getMessage());
		  }
	      return result;
    }
	
    //根据公司具体名称查询
    @RequestMapping(value = "/selectByFullName.do", method = RequestMethod.POST)
	public @ResponseBody EdesktopCompany selectByFullName(@RequestParam String fullName) {
  	      EdesktopCompany ec = edeskCompanyService.selectByFullName(fullName);
		  return ec;
	}
	
    //根据公司具体名称修改
    @RequestMapping(value = "/modifyByFullName.do", method = RequestMethod.POST)
	public @ResponseBody String modifyByFullName(@RequestParam int id, @RequestParam String markId, @RequestParam String fullName, @RequestParam String shortName, @RequestParam String cloudUserId, @RequestParam String cloudUser) {
    	String result = "";
		EdesktopCompany ec = new EdesktopCompany();
		ec.setId(id);
		ec.setMarkId(markId);
		ec.setFullName(fullName);
		ec.setShortName(shortName);
		ec.setCloudUserId(cloudUserId);
		ec.setCloudUser(cloudUser);
		try {
			edeskCompanyService.updateEdeskCompany(ec);
			result = "1";
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
 	}
	
}
