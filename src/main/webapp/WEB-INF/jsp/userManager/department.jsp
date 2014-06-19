<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--部门管理</title>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/department.js'/>"></script>
<style type="text/css">
<%-- #fragment-1 {
	border: 1px solid #97a5b0;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.dept_detail {
	width: 100%;
	border-spacing: 0;
	border-collapse: collapse;
}

.dept_detail td {
	border: 1px solid #DBDBDB;
	text-align: center;
}

.dept_detail th {
	border: 1px solid #DBDBDB;
	background:
		url('<%=contextPath%>/images/ui-bg_glass_75_e6e6e6_1x400.png');
	padding: 8px;
}

.Button1 {
	background: url("<%=contextPath%>/images/button.jpg") repeat-x;
	border: 1px solid #C0C3C2;
	-webkit-border-radius: 5px;
	-mov-border-radius: 5px;
	border-radius: 5px;
	cursor: pointer;
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
        var deptPageNum = "<%=request.getParameter("deptPageNum")==null?1:request.getParameter("deptPageNum")%>";
    	var StringPageSize = 10;
		jQuery(document).ready(function(){
			$.ajax({
				type: "POST",
				url: contextPath  + "/selectDepts.do",
				success: function(list) {
				  	if(list.length == 0){
				  		$("#fragment-1").hide();
				  		$("#fragment-2").show();
				  	}else{
				  		initdept();
				  	}
				},
				dataType : "json"
			});
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
					<div class="nav-title">部门管理</div> 
					<div class="nav-button">
						<input id="department_add" type="button" class="Button1" onclick="department_add()" value="添加部门" style="display:" />
					</div>
				</div>
				<div id="container-1" class="layout-center tbody">
					<div id="fragment-1">
						<table id="dept_detail" class="dept_detail" cellspacing="0"
							cellpadding="0" width="100%" border="0">
						</table>
						<table class="pager" cellspacing="0" cellpadding="0"
							width="100%" border="0">
							<tr>
								<td align="center">
									<span>共<span id="dept_totalNumber"></span>条记录　共<span id="dept_totalPage"></span>页 　当前第<span id="dept_pageNum">1</span>页　 <span id="dept_first" class="a-span" onclick="deptfirst()">首页</span> 　<span id="dept_pre" class="a-span" onclick="deptPre()">上一页</span>　 <span id="dept_next" class="a-span" onclick="deptNext()">下一页</span>　 <span id="dept_last" class="a-span" onclick="deptlast()">末页</span></span>
								</td>
							</tr>
						</table>
					</div>
					<div class="fragment-2" id="fragment-2" style="display: none;">
						<table>
							<tr>
								<td><span id="markId">当前没有部门,如果需要,请点击添加部门</span></td>
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

