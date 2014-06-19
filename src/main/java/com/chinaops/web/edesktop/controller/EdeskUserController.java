package com.chinaops.web.edesktop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.service.EdeskCompanyService;
import com.chinaops.web.edesktop.service.EdeskDepartmentService;
import com.chinaops.web.edesktop.service.EdeskUserService;

/**
 * Description: 
 * @version 2014-6-03 上午11:39:45 马宁涛 （ningtao.ma@china-ops.com） 创建
 */
@Controller
public class EdeskUserController {
    
	private static final Log  log=LogFactory.getLog(EdeskUserController.class);
	private EdeskUserService edeskUserService;
	private EdeskCompanyService edeskCompanyService;
	private EdeskDepartmentService edeskDepartmentService;
	private File upload ;
	
	@Autowired
	public void setEdeskUserService(EdeskUserService edeskUserService) {
		this.edeskUserService = edeskUserService;
	}
	@Autowired
	public void setEdeskCompanyService(EdeskCompanyService edeskCompanyService) {
		this.edeskCompanyService = edeskCompanyService;
	}
	@Autowired
	public void setEdeskDepartmentService(EdeskDepartmentService edeskDepartmentService) {
		this.edeskDepartmentService = edeskDepartmentService;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	@RequestMapping("/euser.htm")  
	public String showEuser(@RequestParam String username, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "userManager/euser";
	}
	
	//查询
	@RequestMapping(value = "/selectEusers.do", method = RequestMethod.POST)
	public @ResponseBody List<EdesktopUser> selectEusers() {
		List<EdesktopUser> lists = edeskUserService.selectEusers();
		return lists;
 	}

	//查询
	@RequestMapping(value = "/euser_selectByDeptId.do", method = RequestMethod.POST)
	public @ResponseBody List<EdesktopUser> euser_selectByDeptId(@RequestParam int id) {
		List<EdesktopUser> lists = edeskUserService.findByDeptId(id);
		return lists;
 	}
	
	//员工列表
	@RequestMapping(value = "/euser_list.do", method = RequestMethod.POST)
	public @ResponseBody Page euser_list(@RequestParam String StringPageNum,@RequestParam String StringPageSize,@RequestParam String loginId ) {
	    int pageNo = Integer.parseInt(StringPageNum);
		int pageSize = Integer.parseInt(StringPageSize);
		if (pageNo <= 1) {
		   pageNo = 1;
		}
		Page pages = edeskUserService.getEuserByPage(pageNo, pageSize, loginId);
		//System.out.println(p.getList());
		return pages;
	  }
	
	//添加
    @RequestMapping(value = "/eUser_add.do", method = RequestMethod.POST)
    public @ResponseBody String euser_add(@RequestParam String userName, @RequestParam String loginId, @RequestParam int departmentId, @RequestParam String status, @RequestParam int desktopCount, @RequestParam String password, @RequestParam String confirmPassword, @RequestParam String email, @RequestParam String phoneNumber, @RequestParam String domainId ){
	      String result="";
	      EdesktopUser eUser = new EdesktopUser();
	      
	      EdesktopCompany eCompany = edeskCompanyService.selectCompany();
	      eUser.setCompanyId(eCompany.getId());
	      
	      //eUser.setGroupId(groupId);
	      eUser.setDomainId(domainId);
	      
	      if(status.equals("空闲")){
	    	  eUser.setStatus("0");
	      }else{
	    	  eUser.setStatus("1");
	      }
	      eUser.setDepartmentId(departmentId);
	      eUser.setConfirmPassword(confirmPassword);
	      eUser.setDesktopCount(desktopCount);
	      eUser.setEmail(email);
	      eUser.setLoginId(loginId);
	      eUser.setPassword(password);
	      eUser.setPhoneNumber(phoneNumber);
	      eUser.setUserName(userName);
		  try {
			  edeskUserService.addEuser(eUser);
			  result="1";
		  } catch (Exception e) {
				log.error(e.getMessage());
		  }
	      return result;
    }
    
    //查询
  	@RequestMapping(value = "/euser_selectById.do", method = RequestMethod.POST)
  	public @ResponseBody EdesktopUser euser_selectById(@RequestParam int id) {
  		EdesktopUser eUser = edeskUserService.findById(id);
  		return eUser;
   	}
  	
  	
    //修改
  	@RequestMapping(value = "/euser_modify.do", method = RequestMethod.POST)
	public @ResponseBody String euser_modify(@RequestParam int id, @RequestParam String userName, @RequestParam String deptName, @RequestParam String email, @RequestParam String phoneNumber) {
		String result = "";
		EdesktopUser eUser = new EdesktopUser();
		eUser.setId(id);
		eUser.setUserName(userName);
		eUser.setEmail(email);
		eUser.setPhoneNumber(phoneNumber);
		EdesktopDepartment eDepartment = edeskDepartmentService.findByDeptName(deptName);
		eUser.setDepartmentId(eDepartment.getId());
		try {
			edeskUserService.updateEuserById(eUser);
			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
 	}
  	
    //删除部门
    @RequestMapping(value = "/euser_delete.do", method = RequestMethod.POST)
	public @ResponseBody String euser_delete(@RequestParam int id) {
       try {
    	   edeskUserService.deleteById(id);
		   return "1";
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
	   return null;
	} 
    
    //Excel
//    @RequestMapping(value = "/euser_excle.do", method = RequestMethod.POST)
//    public @ResponseBody String importXls(@RequestParam String downloaduri , @RequestParam String haomiao , HttpServletRequest request){
//    	String result="";
//    	String year = downloaduri.substring(0, 4);
//		String month = downloaduri.substring(4, 6);
//		String day = downloaduri.substring(6, 8);
//		String nameOfFile = downloaduri.substring(8);
//		String path=request.getSession().getServletContext().getRealPath("/");
//		String uri = path + "download/excel/" + year + "/" + month + "/" + day + "/" + haomiao + "/" + nameOfFile;
//		System.out.println(uri);
//		try {
//			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(new File(uri)));
//			hssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
//		    Sheet sheet = hssfWorkbook.getSheetAt(0);
//		    int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
//		    Row row1 = sheet.getRow(0);
//		    Cell cell1 = row1.getCell(0);
//		    Cell cell2 = row1.getCell(1);
//		    Cell cell3 = row1.getCell(2);
//		    if(colCount == 3 && "用户名".equals(cell1.getStringCellValue()) && "显示名".equals(cell2.getStringCellValue()) && "密码".equals(cell3.getStringCellValue())){
//		    	for (Row row : sheet) {
//		    		if(row.getRowNum()==0){
//		    			continue;
//		    		}
//		    		String loginId = row.getCell(0).getStringCellValue(); 
//		    		EdesktopUser eUser2 = edeskUserService.findByLoginId(loginId);
//		    		if(eUser2.getLoginId() != null){
//		    			result = "2";
//		    			break;
//		    		}else{
//		    			EdesktopUser eUser = new EdesktopUser();
//			    		EdesktopCompany eCompany = edeskCompanyService.selectCompany();
//			    		eUser.setCompanyId(eCompany.getId());
//			    		//eUser.setGroupId(groupId);
//			    		eUser.setDomainId("1");
//			    		eUser.setStatus("0");
//			    		eUser.setDesktopCount(0);
//		    			eUser.setLoginId(loginId);
//			    		String userName = row.getCell(1).getStringCellValue(); 
//			    		eUser.setUserName(userName);
//			    		String password = row.getCell(2).getStringCellValue();
//			    		eUser.setPassword(password);
//			    		eUser.setConfirmPassword(password);
//			    		eUser.setDepartmentId(0);
//			    		eUser.setEmail("");
//			    		eUser.setPhoneNumber("");
//			    		try {
//			    			edeskUserService.addEuser(eUser);
//			    			result = "1";
//			    		} catch (Exception e) {
//			    			log.error(e.getMessage());
//			    		}
//		    		}
//		    	}
//		    }else {
//				result = "0"; //excel格式不对
//			}
//		}catch (Exception e) {
//			log.error(e.getMessage());
//		}
//	      return result;
//    }
    
			
}
