package com.chinaops.web.edesktop.controller;

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

import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.service.EdeskCompanyService;
import com.chinaops.web.edesktop.service.EdeskDepartmentService;

/**
 * Description:
 * 
 * @version 2014-5-23 下午18:12:43 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
@Controller
public class EdeskDepartmentController {
    
	private static final Log  log=LogFactory.getLog(EdeskDepartmentController.class);
	private EdeskDepartmentService edeskDepartmentService;
	private EdeskCompanyService edeskCompanyService;
	
	@Autowired
	public void setEdeskCompanyService(EdeskCompanyService edeskCompanyService) {
		this.edeskCompanyService = edeskCompanyService;
	}
	
	@Autowired
	public void setEdeskDepartmentService(EdeskDepartmentService edeskDepartmentService) {
		this.edeskDepartmentService = edeskDepartmentService;
	}
	
	@RequestMapping("/department.htm")  
	public String showDepartment(@RequestParam String username, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "userManager/department";
	}
	//部门列表
	@RequestMapping(value = "/dept_list.do", method = RequestMethod.POST)
	public @ResponseBody Page dept_list(@RequestParam String StringPageNum,@RequestParam String StringPageSize) {
	    int pageNo = Integer.parseInt(StringPageNum);
		int pageSize = Integer.parseInt(StringPageSize);
		if (pageNo <= 1) {
		   pageNo = 1;
		}
		Page pages = edeskDepartmentService.getDeptByPage(pageNo, pageSize);
		//System.out.println(p.getList());
		return pages;
	  }
	
	//添加
    @RequestMapping(value = "/dept_add.do", method = RequestMethod.POST)
    public @ResponseBody String dept_add(@RequestParam String deptName){
	      String result="";
	      EdesktopCompany eCompany = edeskCompanyService.selectCompany();
	      int companyId = Integer.parseInt(eCompany.getMarkId());
	      EdesktopDepartment eDepartment = new EdesktopDepartment();
	      eDepartment.setDeptName(deptName);
	      eDepartment.setCompanyId(companyId);
		  try {
			  edeskDepartmentService.addDept(eDepartment);
			  result="1";
		  } catch (Exception e) {
				log.error(e.getMessage());
		  }
	      return result;
    }
    
    //修改
	@RequestMapping(value = "/dept_modifyPre.do", method = RequestMethod.POST)
	public @ResponseBody EdesktopDepartment modifyPreById(@RequestParam int id) {
		EdesktopDepartment eDepartment = edeskDepartmentService.findById(id);
		return eDepartment;
 	}
	
	@RequestMapping(value = "/dept_modify.do", method = RequestMethod.POST)
	public @ResponseBody String modify(@RequestParam int id, @RequestParam String deptName) {
		String result = "";
		EdesktopDepartment eDepartment = new EdesktopDepartment();
		eDepartment.setId(id);
		eDepartment.setDeptName(deptName);
		try {
			edeskDepartmentService.updateDeptNameById(eDepartment);
			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
 	}
	
	//删除部门
    @RequestMapping(value = "/dept_delete.do", method = RequestMethod.POST)
	public @ResponseBody String dept_delete(@RequestParam int id) {
       try {
    	   edeskDepartmentService.dept_delete(id);
		   return "1";
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
	   return null;
	} 
	
	@RequestMapping("/selectDepts.do")  
	public @ResponseBody List<EdesktopDepartment> selectDepts(){
		List<EdesktopDepartment> lists = edeskDepartmentService.selectDepts();
		return lists;
	}
    
    //查询
  	@RequestMapping(value = "/dept_select.do", method = RequestMethod.POST)
  	public @ResponseBody List<EdesktopDepartment> dept_select() {
  		List<EdesktopDepartment> eDepartments = edeskDepartmentService.selectDepts();
  		return eDepartments;
   	}
  	
   //根据部门名称查询
    @RequestMapping(value = "/dept_selectByDeptName.do", method = RequestMethod.POST)
	public @ResponseBody EdesktopDepartment dept_selectByDeptName(@RequestParam String deptName) {
    	EdesktopDepartment eDepartment = edeskDepartmentService.findByDeptName(deptName);
		return eDepartment;
	}
	
}
