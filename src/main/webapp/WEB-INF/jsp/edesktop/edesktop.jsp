<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	//System.out.println(contextPath);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>" type="text/css">
<meta charset="utf-8">
<title>易桌面--桌面管理</title>
<style type="text/css">
span {
	padding: 2px 5px 2px 0;
}
</style>

<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/edesktop.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<script type="text/javascript">
	//初始化生成列表
   $(document).ready(function(){
		initpcs();
	});
   
	contextPath = "<%=request.getContextPath()%>";
	
	var pcPageNum = "<%=request.getParameter("pcPageNum") == null ? 1 : request.getParameter("pcPageNum")%>";
	var stringPageSize = "20";

	function showAllStatusInfo() {
		selectByStatus("0");//让0代表全部
		document.getElementById('distributeStatus').style.display = 'none';
	}
	function showStatusEmpty() {
		selectByStatus("1");//让1代表空闲
		document.getElementById('distributeStatus').style.display = 'none';
	}
	function showStatusDistribute() {
		selectByStatus("2");//让2代表分配
		document.getElementById('distributeStatus').style.display = 'none';
	}
</script>
</head>
<body>

	<!-- body begin -->

	<div id="root-panel">
		<div id="ui-layout-top" class="ui-layout-top">
			<%@include file="../page-top.jsp"%>
		</div>
		<div id="ui-layout-center" class="ui-layout-center">
			<div class="layout-center">
				<div id="edesktop" class="nav-bar">
					<div id="edesktop-span" class="edesktop-span nav-title">桌面管理</div>
					<div id="edesktop-input" class="nav-button">
						<input id="edesktop-import" type="button" value="导入" onclick="importDialog()" /> <input id="edesktop-distribute" type="button" value="批量分配" onclick="edesktopDistribute()" />
					</div>
				</div>
				<!-- <div id="u1_line" class="u1_line"></div> -->
				<div>
					<input id="fuzzy-search" class="fuzzy-search" type="text" data-label="textfield" placeholder="按用户名或状态查询" value=""> <input id="btnQuery" class="btnQuery" type="button" onclick="selectByConditions()" value="查询">
				</div>
				<div id="container-1" class="layout-center tbody">
					<div id="fragment-1">
						<table id="pc_detail" class="pc_detail" cellspacing="0" cellpadding="0" width="100%" border="0">
						</table>
						<div id="distributeStatus" class="distributeStatusInfo tdFont" style="overflow: hidden; display: none">
							<div style="cursor: pointer;" id="status_all" onclick="showAllStatusInfo()">全部</div>
							<div class="lineclass"></div>
							<div style="cursor: pointer;" id="status_empty" onclick="showStatusEmpty()">空闲</div>
							<div class="lineclass"></div>
							<div style="cursor: pointer;" id="status_distribute" onclick="showStatusDistribute()">分配</div>
						</div>
						<table class="pager" cellspacing="0" cellpadding="0" width="100%" border="0">
							<tr>
								<td align="center"><span>共<span id="pc_totalNumber"></span>条记录 　共<span id="pc_totalPage"></span>页 　 当前第<span id="pc_pageNum">1</span>页 　<span id="pc_first" class="a-span" onclick="pcfirst()">首页</span> 　<span id="pc_pre" class="a-span" onclick="pcPre()">上一页</span> 　<span id="pc_next" class="a-span" onclick="pcNext()">下一页</span> 　<span id="pc_last" class="a-span" onclick="pclast()">末页</span></span></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="ui-layout-left" class="ui-layout-left ui-layout-pane ui-layout-pane-west" style="position: absolute; margin: 0px; left: 10px; right: auto; top: 70px; bottom: 6px; height: 550px; z-index: 0; width: 178px; display: block; visibility: visible;">
			<%@include file="../left-menu.jsp"%>
		</div>
	</div>

</body>
</html>
