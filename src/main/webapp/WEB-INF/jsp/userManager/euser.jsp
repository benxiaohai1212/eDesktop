<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--用户管理</title>
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/ajaxfileupload.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/euser.js'/>"></script>
<style type="text/css">
<%-- #fragment-1 {
	border: 1px solid #97a5b0;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.euser_detail {
	width: 100%;
	border-spacing: 0;
	border-collapse: collapse;
}

.euser_detail td {
	border: 1px solid #DBDBDB;
	text-align: center;
}

.euser_detail th {
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
}
 --%>
.tooltip {
	cursor: pointer;
	display: inline-block;
	position: relative;
	white-space: nowrap;
}

.tooltip-content {
	opacity: 0;
	visibility: hidden;
	font: 12px Arial, Helvetica;
	text-align: center;
	border-color: #aaa #555 #555 #aaa;
	border-style: solid;
	border-width: 1px;
	width: 150px;
	padding: 15px;
	position: absolute;
	bottom: 40px;
	left: 50%;
	margin-left: -76px;
	background-color: #fff;
}

.tooltip-content:after,.tooltip-content:before {
	border-right: 16px solid transparent;
	border-top: 15px solid #fff;
	bottom: -15px;
	content: "";
	position: absolute;
	left: 50%;
	margin-left: -10px;
}

.tooltip-content:before {
	border-right-width: 25px;
	border-top-color: #555;
	border-top-width: 15px;
	bottom: -15px;
}

.tooltip:hover .tooltip-content {
	opacity: 1;
	visibility: visible;
	bottom: 30px;
}
</style>

<script type="text/javascript">
        contextPath = "<%=request.getContextPath()%>";
        var euserPageNum = "<%=request.getParameter("euserPageNum")==null?1:request.getParameter("euserPageNum")%>";
    	var StringPageSize = 20;
		jQuery(document).ready(function(){
			$.ajax({
				type: "POST",
				url: contextPath  + "/selectEusers.do",
				success: function(list) {
				  	if(list.length == 0){
				  		$("#content-1").hide();
				  		$("#content-2").show();
				  	}else{
				  		initEuser();
				  	}
				},
				dataType : "json"
			});
			
			$('[data-tooltip]').addClass('tooltip');
			$('.tooltip').each(function() {
					$(this).append('' + $(this).attr('data-tooltip') + '');
			});

			if ($.browser.msie && $.browser.version.substr(0,1)<7){
			  $('.tooltip').mouseover(function(){
							$(this).children('.tooltip-content').css('visibility','visible');
					  }).mouseout(function(){
							$(this).children('.tooltip-content').css('visibility','hidden');
					  })
			}
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
					<div class="nav-title">员工管理</div>
					<div class="nav-button">
						<input id="euser_add" type="button" class="Button1" onclick="euser_add()" value="添加员工" style="display:" /> <input id="euser_excle" type="button" class="Button1" onclick="euser_excle()" value="导入Excel" style="display:" />
					</div>
				</div>
				<div class="fragment-1" id="content-1">
					<div id="btnDiv" style="padding: 10px;">
						<input id="login_id" type="text" value="请输入用户名" onfocus="javascript:if(this.value=='请输入用户名')this.value='';"> <input id="select_by_condition" type="button" class="Button1" onclick="select_by_condition()" value="查询" style="display:" />
					</div>
					<div id="container-1"  class="layout-center tbody">
						<div id="fragment-1">
							<table id="euser_detail" class="euser_detail" cellspacing="0" cellpadding="0" width="100%" border="0">
							</table>
							<table class="pager" cellspacing="0" cellpadding="0" width="100%" border="0">
								<tr id="tr1">
									<td align="center"><span>共<span id="euser_totalNumber"></span>条记录 共<span id="euser_totalPage"></span>页 当前第<span id="euser_pageNum">1</span>页 <span id="euser_first" class="a-span" onclick="euserfirst()">首页</span> <span id="euser_pre" class="a-span" onclick="euserPre()">上一页</span> <span id="euser_next" class="a-span" onclick="euserNext()">下一页</span> <span id="euser_last" class="a-span" onclick="euserlast()">末页</span></span></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div id="content-2" style="display: none;" class="layout-center tbody">
					<table>
						<tr>
							<td align="center"><span id="markId">当前没有员工,如果需要,请点击添加员工</span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="ui-layout-left" class="ui-layout-left ui-layout-pane ui-layout-pane-west" style="position: absolute; margin: 0px; left: 10px; right: auto; top: 70px; bottom: 6px; height: 550px; z-index: 0; width: 178px; display: block; visibility: visible;">
			<%@include file="../left-menu.jsp"%>
		</div>
	</div>

</body>
</html>

