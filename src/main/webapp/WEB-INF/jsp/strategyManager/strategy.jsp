<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--策略管理</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/edesktop.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/domain.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/strategy.js'/>"></script>
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
	-webkit-border-radius: 5px;
	-mov-border-radius: 5px;
	border-radius: 5px;
	cursor: pointer;
	margin-left: 62%;
	width: 88px;
	height: 25px;
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
		jQuery(document).ready(function(){
			initStrategy();
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
					<div class="nav-title">外设管理</div>
					<div class="nav-button">
						<input id=strategy_add type="button" class="Button1" onclick="strategyAdd()" value="添加策略" style="display:" />
					</div> 
				</div>
				<div id="strategy_container_1" style="display: none;" class="layout-center tbody">
					<div id="strategy_fragment_1">
						<table id="strategy_detail" class=strategy_detail cellspacing="0" cellpadding="0" width="100%" border="0">
						</table>
						<table class="pager" cellspacing="0" cellpadding="0" width="100%" border="0">
							<tr>
								<td align="center"><span>共<span id="strategy_totalNumber"></span>条记录 　共<span id="strategy_totalPage"></span>页 　当前第<span id="strategy_pageNum">1</span>页 　<span id="strategy_first" class="a-span" onclick="strategyFirst()">首页</span> 　<span id="strategy_pre" class="a-span" onclick="strategyPre()">上一页</span> 　<span id="strategy_next" class="a-span" onclick="strategyNext()">下一页</span> 　<span id="strategy_last" class="a-span" onclick="strategyLast()">末页</span></span></td>
							</tr>
						</table>
					</div>
				</div>
				<div id="strategy_container_2" style="display: none" class="layout-center tbody">
					<div id="strategy_fragment_2">
						<span style="color: red;">当前没有添加任何策略</span>
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

