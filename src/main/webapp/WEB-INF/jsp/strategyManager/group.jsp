<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--组管理</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/edesktop.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/domain.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/group.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<style type="text/css">
<%-- #fragment-1 {
	border: 1px solid #97a5b0;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.Button1 {
	border-color: #C0C3C2;
	border-radius: 5px;
	border-style: solid;
	border-width: 1px;
	cursor: pointer;
	font-size: 14px;
	height: 25px;
	margin-left: 80%;
	width: 88px;
}

.Button1 :hover {
	border: 1px solid #686969
}

#titlePanel input:hover {
	border: 1px solid #686969
}

.Button2 {
	background: url("<%=contextPath%>/images/button.jpg") repeat-x;
	border: 1px solid #C0C3C2;
	-webkit-border-radius: 5px;
	-mov-border-radius: 5px;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 80px;
} --%>
</style>

<script type="text/javascript">
        contextPath = "<%=request.getContextPath()%>";
    	var strategyPageSize = 20;
		jQuery(document).ready(function(){
		  initGroup();
		});
    </script>
</head>
<body>
	<div id="root-panel">
		<div id="ui-layout-top" class="ui-layout-top">
			<%@include file="../page-top.jsp"%>
		</div>
		<div id="ui-layout-center" class="ui-layout-center">
			<div class="layout-center">
				<div class="nav-bar">
					<div class="nav-title">组管理</div>
					<div class="nav-button">
						<input id=group_add type="button" class="Button1" onclick="groupAdd()" value="新建组" style="display:" />
					</div>
				</div>
				<div id="group_container_1" style="display: none; font-size: 15px;"  class="layout-center tbody">
					<div id="group_fragment_1">
						<table id="group_detail" class="group_detail" cellspacing="0" cellpadding="0" width="100%" border="0">
						</table>
						<table class="pager" cellspacing="0" cellpadding="0" width="100%" border="0">
							<tr>
								<td align="center"><span>共<span id="group_totalNumber"></span>条记录 　共<span id="group_totalPage"></span>页 　当前第<span id="group_pageNum">1</span>页　 <span id="group_first" class="a-span" onclick="groupFirst()">首页</span>　 <span id="group_pre" class="a-span" onclick="groupPre()">上一页</span> 　<span id="group_next" class="a-span" onclick="groupNext()">下一页</span> 　<span id="group_last" class="a-span" onclick="groupLast()">末页</span></span></td>
							</tr>
						</table>
					</div>
				</div>
				<div id="group_container_2" style="display: none" class="layout-center tbody">
					<div id="group_fragment_2"　style="text-align: center;">
						<span style="color: red;">当前没有添加组,如果需要，请点击新建组</span>
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

