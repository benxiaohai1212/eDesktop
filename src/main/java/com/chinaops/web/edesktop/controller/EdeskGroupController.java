package com.chinaops.web.edesktop.controller;

import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.GroupStrategy;
import com.chinaops.web.edesktop.entity.Groups;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;
import com.chinaops.web.edesktop.service.EdeskGroupService;
import com.chinaops.web.edesktop.service.EdeskStrategyService;
import com.chinaops.web.edesktop.service.EdeskUserService;

/**
 * Description: 组管理
 */
@Controller
public class EdeskGroupController {
	private static final Log log = LogFactory.getLog(EdeskGroupController.class);
	private EdeskGroupService edeskGroupService;
	private EdeskStrategyService edeskStrategyService;
	private EdeskUserService edeskUserService;

	@Autowired
	public void setEdeskGroupService(EdeskGroupService edeskGroupService) {
		this.edeskGroupService = edeskGroupService;
	}

	@Autowired
	public void setEdeskStrategyService(EdeskStrategyService edeskStrategyService) {
		this.edeskStrategyService = edeskStrategyService;
	}
	
	@Autowired
	public void setEdeskUserService(EdeskUserService edeskUserService) {
		this.edeskUserService = edeskUserService;
	}

	@RequestMapping("/group.htm")
	public String strategyPageShow(@RequestParam String username, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "strategyManager/group";
	}
	/**
	 *  组列表
	 * @param groupPageNum
	 * @param groupPageSize
	 * @return pages
	 */
	@RequestMapping(value = "/groupList.do", method = RequestMethod.POST)
	public @ResponseBody
	Page groupList(@RequestParam String groupPageNum, @RequestParam String groupPageSize) {
		int pageNo = Integer.parseInt(groupPageNum);
		int pageSize = Integer.parseInt(groupPageSize);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		Page pages = edeskGroupService.getGroupByPage(pageNo, pageSize);
		return pages;
	}
	
	/**
	 *  添加组
	 * @param groupName
	 * @return result
	 */
	@RequestMapping(value = "/addGroup.do", method = RequestMethod.POST)
	public @ResponseBody
	String addGroup(@RequestParam String groupName) {
		String result = "";
		Groups group = new Groups();
		group.setGroupName(groupName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String create_time = df.format(new java.util.Date());
		group.setCreateTime(create_time);
		try {
			edeskGroupService.addGroup(group);
			result = "1";
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 	显示所有的策略列表
	 * @return strategyList
	 */

	@RequestMapping(value = "/showAllStrategy.do", method = RequestMethod.POST)
	public @ResponseBody
	List<Strategies> showAllStrategy() {
		List<Strategies> strategyList = edeskStrategyService.selectAllStrategys();
		return strategyList;
	}

	/**
	 * 显示与某个组关联的策略
	 * 
	 * @param groupId
	 * @return strategyList
	 */
	@RequestMapping(value = "/showAllAssStrategy.do", method = RequestMethod.POST)
	public @ResponseBody
	List<Strategies> showAllAssStrategy(String groupId) {
		//选择出和组关联的策略ID
		List<GroupStrategy> groupStrategyList = edeskStrategyService.selectAllAssStrategy(groupId);
		List<Strategies> strategyList = new ArrayList<Strategies>();
		String strategyIds="";
		StringBuilder  strategyIdsBuilder=new StringBuilder();
		for (GroupStrategy groupStrategy : groupStrategyList) {
			strategyIdsBuilder.append("'").append(groupStrategy.getStrategiesId()).append("'").append(",");
			strategyIds=strategyIdsBuilder.substring(0, strategyIdsBuilder.length()-1).toString();
		}
		if (!"".equals(strategyIds)) {
			strategyList=edeskStrategyService.selectStrategysByIds(strategyIds);
		}
		return strategyList;
	}
	/**
	 * 显示与某个组未关联的策略
	 * 
	 * @param groupId
	 * @return strategyList
	 */
	@RequestMapping(value = "/showAllUnAssStrategy.do", method = RequestMethod.POST)
	public @ResponseBody
	List<Strategies> showAllUnAssStrategy(String groupId) {
		List<Strategies> allStrategyList=showAllStrategy();
		List<Strategies> assStrategyList=showAllAssStrategy(groupId);
		List<Strategies> notAssStrategyList = new ArrayList<Strategies>(); // 保存没有关联的策略
		final Map<String,Strategies> assStrategyListMap = new HashMap<String, Strategies>();
		for (Strategies strategies : assStrategyList) {
			assStrategyListMap.put(String.valueOf(strategies.getId()), strategies);
		}
		for (Strategies strategies2 : allStrategyList) {
			if (!assStrategyListMap.containsKey(String.valueOf(strategies2
					.getId()))) {
				notAssStrategyList.add(strategies2);
			}
		}
		return notAssStrategyList;
	}

	/**
	 * 组与策略关联后数据添加到group_strategy:
	 * 
	 * @param groupId
	 * @param StrateiesId
	 * @return result
	 */
	@RequestMapping(value = "/groupAndStrategy.json", method = RequestMethod.POST)
	public @ResponseBody
	String groupAndStrategy(@RequestParam String groupId, String StrateiesId) {
		String result = "";
		if (StrateiesId != null && !"".equals(StrateiesId)) {
			edeskGroupService.deleteAllStrategyAboutGroup(groupId);
			String[] StrategyId = StrateiesId.split(",");
			for (String strId : StrategyId) {
				GroupStrategy groupStrategy = new GroupStrategy();
				groupStrategy.setGroupId(String.valueOf(groupId));
				groupStrategy.setStrategiesId(strId);
				edeskGroupService.addGroupAndStrategy(groupStrategy);
			}
		}
		return result;

	}
	
	/**
	 * 	根据组id查询已分配组用户
	 * @return strategyList
	 */

	@RequestMapping(value = "/showAllAssEuserByGroupId.do", method = RequestMethod.POST)
	public @ResponseBody
	List<EdesktopUser> showAllAssEuserByGroupId(@RequestParam int groupId) {
		List<EdesktopUser> euserList = edeskUserService.findByGroupId(groupId);
		return euserList;
	}
	/**
	 * 	查询所有未分配组的用户
	 */

	@RequestMapping(value = "/showAllUnAssEuser.do", method = RequestMethod.POST)
	public @ResponseBody
	List<EdesktopUser> findAllUnAss() {
		List<EdesktopUser> euserList = edeskUserService.findAllUnAss();
		return euserList;
	}
	
	/**
	 * 组与用户关联:
	 * 
	 * @param groupId
	 * @param EuserIds
	 * @return result
	 */
	@RequestMapping(value = "/groupAndEuser.json", method = RequestMethod.POST)
	public @ResponseBody
	String groupAndEuser(@RequestParam int groupId, String EuserIds) {
		String result = "";
		if (EuserIds != null && !"".equals(EuserIds))  {
			edeskGroupService.updateAllAssEuserByGroupId(String.valueOf(groupId));
			String[] EusersId = EuserIds.split(",");
			for (String EuserId : EusersId) {
				EdesktopUser edesktopUser = new EdesktopUser();
				edesktopUser.setGroupId(groupId);
				edesktopUser.setId(Integer.valueOf(EuserId));
				edeskGroupService.updateUnAssToAssByGroupId(edesktopUser);
			}
		}
		return result;

	}
	
	//修改
	@RequestMapping(value = "/group_getById.do", method = RequestMethod.POST)
	public @ResponseBody Groups groupGetById(@RequestParam int id) {
		Groups eGroup = edeskGroupService.findById(id);
		return eGroup;
 	}
	
	/**
	 * 验证组名称是否存在：
	 * 
	 * @param strategyName
	 * @return
	 */
    @RequestMapping(value = "/checkGroupNameUnique.do", method = RequestMethod.POST)
	  public @ResponseBody String  checkGroupNameUnique(@RequestParam String groupName) {
		String result="-1";
		try {
			if (edeskGroupService.checkGroupNameUnique(groupName)) { // 不存在
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
