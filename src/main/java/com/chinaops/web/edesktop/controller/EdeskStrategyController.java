package com.chinaops.web.edesktop.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.NewBeanInstanceStrategy;

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
import com.chinaops.web.edesktop.entity.EdesktopDepartment;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;
import com.chinaops.web.edesktop.service.EdeskCompanyService;
import com.chinaops.web.edesktop.service.EdeskDomainService;
import com.chinaops.web.edesktop.service.EdeskStrategyService;


/**
 * Description:
 * 策略管理
 */
@Controller
public class EdeskStrategyController {
	private static final Log  log=LogFactory.getLog(EdeskStrategyController.class);
	private EdeskStrategyService edeskStrategyService;
	
	@Autowired
	public void setEdeskStrategyService(EdeskStrategyService edeskStrategyService) {
		this.edeskStrategyService = edeskStrategyService;
	}
	@RequestMapping("/strategy.htm")
	public String strategyPageShow(@RequestParam String username, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "strategyManager/strategy";
	}
	  //以分页形式显示策略列表
		@RequestMapping(value = "/strategyList.do", method = RequestMethod.POST)
		public @ResponseBody Page strategyList(@RequestParam String strategyPageNum,@RequestParam String strategyPageSize) {
		    int pageNo = Integer.parseInt(strategyPageNum);
			int pageSize = Integer.parseInt(strategyPageSize);
			if (pageNo <= 1) {
			   pageNo = 1;
			}
			Page pages = edeskStrategyService.getStrategyByPage(pageNo, pageSize);
			return pages;
		  }
		//添加策略
	    @RequestMapping(value = "/addStrategy.do", method = RequestMethod.POST)
	    public @ResponseBody String addStrategy(@RequestParam String strategyName){
		      String result="";
		      Strategies strategies = new Strategies();
		      strategies.setStrategiesName(strategyName);
		      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		      String create_time  =df.format(new java.util.Date());
		      strategies.setCreate_time(create_time);
			  try {
				  edeskStrategyService.addStrategy(strategies);
				  result="1";
			  } catch (Exception e) {
					log.error(e.getMessage());
			  }
		      return result;
	    }
	    
		/**
		 * 验证策略名称是否存在：
		 * 
		 * @param strategyName
		 * @return
		 */
	    @RequestMapping(value = "/checkStrategyNameUnique.do", method = RequestMethod.POST)
		  public @ResponseBody String  checkStrategyNameUnique(@RequestParam String strategyName) {
			String result="-1";
			try {
				if (edeskStrategyService.checkStrategyNameUnique(strategyName)) { // 不存在
					result="0";
				} else {// 已存在
					result="1";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
}
