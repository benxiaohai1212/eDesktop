/*
 * $Id$
 *
 * All Rights Reserved 2013 China OPS Information Technology Co.,Ltd.
 */
package com.chinaops.web.edesktop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaops.cloud.auth.shared.Company;
import com.chinaops.cloud.framework.CloudUserManager;
import com.chinaops.cloud.framework.CompanyManager;
import com.chinaops.cloud.framework.InstanceManager;
import com.chinaops.cloud.metadata.shared.CloudUser;
import com.chinaops.cloud.metadata.shared.Instance;
import com.chinaops.web.edesktop.entity.Customer;
import com.chinaops.web.edesktop.entity.Desktop;
import com.chinaops.web.edesktop.entity.EdesktopCompany;
import com.chinaops.web.edesktop.entity.EdesktopUD;
import com.chinaops.web.edesktop.entity.EdesktopUser;
import com.chinaops.web.edesktop.entity.GroupStrategy;
import com.chinaops.web.edesktop.entity.Page;
import com.chinaops.web.edesktop.entity.Strategies;
import com.chinaops.web.edesktop.service.EdeskCompanyService;
import com.chinaops.web.edesktop.service.EdeskUDService;
import com.chinaops.web.edesktop.service.EdeskUserService;
import com.chinaops.web.edesktop.service.EdesktopService;

@Controller
public class EdesktopController {

	private static final Log log = LogFactory.getLog(EdesktopController.class);

	private EdesktopService edesktopService;
	private EdeskCompanyService edeskCompanyService;
	private InstanceManager instanceManager;
	private CompanyManager companyManager;
	private EdeskUserService edeskUserService;
	private EdeskUDService edeskUDService;

	@Autowired
	public void setEdeskUDService(EdeskUDService edeskUDService) {
		this.edeskUDService = edeskUDService;
	}

	@Autowired
	public void setEdeskUserService(EdeskUserService edeskUserService) {
		this.edeskUserService = edeskUserService;
	}

	@Autowired
	public void setEdesktopService(EdesktopService edesktopService) {
		this.edesktopService = edesktopService;
	}

	@Autowired
	public void setInstanceManager(InstanceManager instanceManager) {
		this.instanceManager = instanceManager;
	}

	@Autowired
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	@Autowired
	public void setEdeskCompanyService(EdeskCompanyService edeskCompanyService) {
		this.edeskCompanyService = edeskCompanyService;
	}

	@RequestMapping("/edesktop.htm")
	public String edesktopPageShow(@RequestParam String username, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("username", username);
		System.out.println(request.getAttribute("username"));
		return "edesktop/edesktop";
	}

	// 模糊查询
	@RequestMapping(value = "/selectByConditions.do", method = RequestMethod.POST)
	public @ResponseBody
	Page selectByConditions(@RequestParam String stringPageNum, @RequestParam String stringPageSize, @RequestParam String fuzzySearchValue) {
		int pageNo = Integer.parseInt(stringPageNum);
		int pageSize = Integer.parseInt(stringPageSize);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		Page p = edesktopService.selectByConditions(pageNo, pageSize, fuzzySearchValue);
		// System.out.println(p.getList());
		return p;
	}
	// 按分配状态精确查询
	@RequestMapping(value = "/selectByStatusInfo.do", method = RequestMethod.POST)
	public @ResponseBody
	Page selectByStatusInfo(@RequestParam String stringPageNum, @RequestParam String stringPageSize, @RequestParam String fuzzySearchValue) {
		int pageNo = Integer.parseInt(stringPageNum);
		int pageSize = Integer.parseInt(stringPageSize);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		if (fuzzySearchValue.equals("1")) {
			fuzzySearchValue="空闲";
		} else if (fuzzySearchValue.equals("2")){
			fuzzySearchValue="分配";
		}else  {
			fuzzySearchValue="";
		}
		Page p = edesktopService.selectByConditions(pageNo, pageSize, fuzzySearchValue);
		// System.out.println(p.getList());
		return p;
	}

	// 导入云主机
	@RequestMapping(value = "/autoImport.do", method = RequestMethod.POST)
	public @ResponseBody
	String autoImport() {
		String result = "0";
		int i = 0;
		List<Instance> instances = null;
		edesktopService.deleteAllDesktop();
		try {
			EdesktopCompany edesktopCompany = edeskCompanyService.selectCompany();
			if (edesktopCompany == null || edesktopCompany.getId() == 0) {
				result = "2";
				return result;
			}
			int companyId = Integer.valueOf(edesktopCompany.getMarkId());
			if (companyId != 0) {
				Company company = companyManager.getCompanyById(companyId);
				try {
					// 查询云平台那边所有云主机
					try {
						instances = instanceManager.getCompanyInstances(company, new ArrayList<String>());
						if (instances.size() > 0) {
							for (Instance instance : instances) {
								i = i + 1;
								Desktop desktop = new Desktop();
								desktop.setId(i);
								desktop.setInstance_id(instance.getInstanceId());
								desktop.setInstance_name(instance.getInstanceName());
								desktop.setIp(instance.getPublicDNS());
								desktop.setInstance_type(instance.getType());
								desktop.setDate_created(instance.getCreateTime());
								desktop.setDate_amended(instance.getCreateTime());
								edesktopService.addAllDesktop(desktop);
							}
						}
					} catch (Exception e) {
						e.getStackTrace();
						System.out.println(e);
						log.error(e.getMessage(), e);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			result = "1";

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return result;
	}

	// 从临时表里分页查询所有的云桌面
	@RequestMapping(value = "/selectAllEdesktop.do", method = RequestMethod.POST)
	public @ResponseBody
	Page selectAllEdesktop(@RequestParam String stringPageNum, @RequestParam String stringPageSize) {
		int pageNo = Integer.parseInt(stringPageNum);
		int pageSize = Integer.parseInt(stringPageSize);
		if (pageNo <= 1) {
			pageNo = 1;
		}
		Page p = edesktopService.selectAllEdesktop(pageNo, pageSize);
		// System.out.println(p.getList());
		return p;
	}

	// 添加已选择的桌面
	@RequestMapping(value = "/addSelectdDesktop.do", method = RequestMethod.POST)
	public @ResponseBody
	String addSelectdDesktop(@RequestParam String selIps) {
		String result = "0";
		List<Desktop> desktopList = edesktopService.findByDesktopIds(selIps.substring(0, selIps.length() - 1));
		List<Desktop> desktopList2 = edesktopService.selectAllEdesktopFromDesktop();
		List<Desktop> desktopListAllTemporary = edesktopService.selectAllEdesktopFromDesktopTemporary();
		final Map<String, Desktop> desktopListMap = new HashMap<String, Desktop>();
		final Map<String, Desktop> desktopListTemporaryMap = new HashMap<String, Desktop>();
		try {
			result = "1";
			for (Desktop desktop : desktopList2) {
				desktopListMap.put(desktop.getInstance_id(), desktop);
			}
			for (Desktop desktop : desktopList) {
				if (!desktopListMap.containsKey(desktop.getInstance_id())) {
					desktop.setDistribute_status("空闲");
					edesktopService.addDesktop(desktop);
				} else {
					edesktopService.updateDesktop(desktop);
				}
			}
			for (Desktop desktop : desktopListAllTemporary) {
				desktopListTemporaryMap.put(desktop.getInstance_id(), desktop);
			}
			for (Desktop desktop : desktopList2) {
				System.out.println(!desktopListTemporaryMap.containsKey(desktop.getInstance_id()));
				if (!desktopListTemporaryMap.containsKey(desktop.getInstance_id())) {
					if ("空闲".equals(desktop.getDistribute_status()) || desktop.getDistribute_status() == null) {
						edesktopService.deleteDesktopById(desktop.getId());
					} else {
						edesktopService.updateDesktopStatus(desktop);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

	/**
	 * 显示所有的用户
	 */
	@RequestMapping(value = "/showAllEusers.do", method = RequestMethod.POST)
	public @ResponseBody
	List<EdesktopUser> showAllEusers() {
		List<EdesktopUser> edesktopUsers = edeskUserService.selectEusers();
		return edesktopUsers;
	}

	/**
	 * 显示没关联的用户
	 */
	@RequestMapping(value = "/showUnAssEusers.do", method = RequestMethod.POST)
	public @ResponseBody
	List<EdesktopUser> showUnAssEusers() {
		List<EdesktopUser> edesktopUsers = edeskUserService.findAllUnAssDesktop();
		return edesktopUsers;
	}

	/**
	 * 显示和桌面关联的用户
	 */
	@RequestMapping(value = "/showDesktopAssEusers.do", method = RequestMethod.POST)
	public @ResponseBody
	List<EdesktopUser> showDesktopAssEusers(@RequestParam String edesktopId) {
		List<EdesktopUD> edesktopUDLst = edeskUDService.findByEdesktopId(edesktopId);
		List<EdesktopUser> edesktopUsers = new ArrayList<EdesktopUser>();
		String euserIds = "";
		for (EdesktopUD edesktopUD : edesktopUDLst) {
			euserIds += "'" + edesktopUD.geteUserId() + "',";
		}
		if (!"".equals(euserIds)) {
			edesktopUsers = edeskUserService.findByIds(euserIds.substring(0, euserIds.length() - 1));
		}

		return edesktopUsers;
	}

	/**
	 * 显示和桌面未关联的用户
	 */
	@RequestMapping(value = "/showDesktopUnAssEusers.do", method = RequestMethod.POST)
	public @ResponseBody
	List<EdesktopUser> showDesktopUnAssEusers(@RequestParam String edesktopId) {
		// 所有用户
		List<EdesktopUser> edesktopUsers = showAllEusers();
		// 显示和桌面关联的用户
		List<EdesktopUser> edesktopAssUsers = showDesktopAssEusers(edesktopId);
		List<EdesktopUser> edesktopNotAssUsers = new ArrayList<EdesktopUser>(); // 保存没有和当前桌面关联的用户
		final Map<String, EdesktopUser> edesktopAssUsersListMap = new HashMap<String, EdesktopUser>();
		for (EdesktopUser edesktopUser : edesktopAssUsers) {
			edesktopAssUsersListMap.put(String.valueOf(edesktopUser.getId()), edesktopUser);
		}
		for (EdesktopUser edesktopUser2 : edesktopUsers) {
			if (!edesktopAssUsersListMap.containsKey(String.valueOf(edesktopUser2.getId()))) {
				edesktopNotAssUsers.add(edesktopUser2);
			}
		}
		return edesktopNotAssUsers;
	}

	/**
	 * 检查分配
	 */
	@RequestMapping(value = "/checkDistributestatus.do", method = RequestMethod.POST)
	public @ResponseBody
	String checkDistributestatus(@RequestParam String edesktopsIds) {
		String result = "1";
		if (!"".equals(edesktopsIds)) {
			List<Desktop> edesktopList = edesktopService.findByIdsFromDesktop(edesktopsIds.substring(0, edesktopsIds.length() - 1));
			for (Desktop desktop : edesktopList) {
				if ("分配".equals(desktop.getDistribute_status())) {
					result = "0";
					break;
				}
			}
		}
		return result;
	}

	public static boolean isHave(String[] strs, String s) {
		/*
		 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 */
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(s)) {// 循环查找字符串数组中的每个字符串中是否和所有查找的内容相等
				return true;// 查找到了就返回真，不在继续查询
			}
		}
		return false;// 没找到返回false
	}

	/**
	 * 桌面和用户关联（编辑）
	 */
	@RequestMapping(value = "/editDesktopAndEuser.do", method = RequestMethod.POST)
	public @ResponseBody
	String editDesktopAndEuser(@RequestParam String edesktopsId, @RequestParam String euserIds) {
		String result = "0";
		try {
			if (edesktopsId != null && !"".equals(edesktopsId)) {
				List<EdesktopUD> edesktopUDList1 = edeskUDService.findByEdesktopId(edesktopsId);
				String[] euserId = euserIds.split(",");
				String euserIdExit = "";
				String euserIdAdd = "";
				for (EdesktopUD edesktopUD : edesktopUDList1) {
					euserIdExit += "'" + edesktopUD.geteUserId() + "',";
					String edesktopUDEuserID = "'" + edesktopUD.geteUserId() + "'";
					if (!isHave(euserId, edesktopUDEuserID)) {// 调用自己定义的函数isHave，如果包含则返回true,否则返回false
						edeskUserService.reduceEuserDesktopCount(edesktopUD.geteUserId());// 如果去除了一个用户，减少桌面数量
					}
				}
				String[] euserIdExitArr = euserIdExit.split(",");
				if (!euserIds.equals("") && euserIds != null) {
					for (String eid : euserId) {
						if (!isHave(euserIdExitArr, eid)) {// 调用自己定义的函数isHave，如果包含则返回true,否则返回false
							euserIdAdd += eid + ",";// 增加了一个用户
						}

					}
					edeskUserService.addEuserDesktopCount(euserIdAdd.substring(0, euserIdAdd.length() - 1));// 增加桌面数量
				}
				edeskUDService.deleteByEdesktopId(edesktopsId);// 添加桌面和用户的关系
				for (String strId : euserId) {
					if (!"".equals(strId)&&strId!=null) {
						EdesktopUD edesktopUD = new EdesktopUD();
						edesktopUD.setDesktopId(edesktopsId);
						edesktopUD.seteUserId(strId);
						edeskUDService.insertDesktopEuser(edesktopUD);
					}

				}
				List<EdesktopUD> edesktopUDList = edeskUDService.findByEdesktopId(edesktopsId);
				Desktop desktopDisStuts = new Desktop();
				if (edesktopUDList.size() > 0) {
					// 改变桌面分配状态
					desktopDisStuts.setId(Integer.valueOf(edesktopsId));
					desktopDisStuts.setDistribute_status("分配");
					edesktopService.updateDistributeStatus(desktopDisStuts);
				} else {
					desktopDisStuts.setId(Integer.valueOf(edesktopsId));
					desktopDisStuts.setDistribute_status("空闲");
					edesktopService.updateDistributeStatus(desktopDisStuts);
				}

				// 改变用户桌面数量
				result = "1";
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;

	}

	/**
	 * 桌面和用户关联（批量分配）
	 */
	@RequestMapping(value = "/desktopAndEuser.do", method = RequestMethod.POST)
	public @ResponseBody
	String desktopAndEuser(@RequestParam String edesktopsIds, @RequestParam String euserIds) {
		String result = "0";
		try {
			// 用户和桌面随机分配
			String[] edesktopsIdsArr = edesktopsIds.substring(0, edesktopsIds.length() - 1).split(",");
			String[] euserIdsArr = euserIds.substring(0, euserIds.length() - 1).split(",");
			List<String> euserIdsList = new ArrayList<String>();
			List<String> edesktopsIdsList = new ArrayList<String>();
			for (String euserId : euserIdsArr) {
				euserIdsList.add(euserId);
			}
			for (String edesktopsId : edesktopsIdsArr) {
				edesktopsIdsList.add(edesktopsId);
			}
			// 顺序打乱
			Collections.shuffle(euserIdsList);
			Collections.shuffle(edesktopsIdsList);
			for (int i = 0; i < edesktopsIdsList.size(); i++) {
				String edesktopsId = edesktopsIdsList.get(i);
				String euserId = euserIdsList.get(i);
				EdesktopUD edesktopUD = new EdesktopUD();
				edesktopUD.seteUserId(euserId);
				edesktopUD.setDesktopId(edesktopsId);
				edeskUDService.insertDesktopEuser(edesktopUD);
			}
			// 改变桌面分配状态
			edesktopService.updateDesktopDistributeStatus(edesktopsIds.substring(0, edesktopsIds.length() - 1));
			// 改变用户桌面数量
			edeskUserService.addEuserDesktopCount(euserIds.substring(0, euserIds.length() - 1));
			result = "1";
		} catch (Exception e) {
			log.error(e.getMessage());
			// TODO: handle exception
		}

		return result;

	}
}
