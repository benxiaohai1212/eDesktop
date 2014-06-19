<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--域环境设置</title>
<script type="text/javascript"src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/edesktop.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/domain.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>"type="text/css">
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<style type="text/css">
</style>

<script type="text/javascript">
    contextPath = "<%=request.getContextPath()%>";
	jQuery(document).ready(function() {
		$.ajax({
			type : "POST",
			url : contextPath + "/selectDomain.do",
			success : function(domain) {
				if (domain.id != null &&domain.id !=0) {
				    $("#addDomain").attr("style",'display:none');
				    $("#editDomain").attr("style",'display:');
				    $("#domain_exist").attr("style",'display:');
				    $("#domain_none").attr("style",'display:none');
				    $("#domainNameShow").text(domain.domainName);
				    $("#domainIpShow").text(domain.domainIp);
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
					<div class="nav-title">域环境设置</div>
					<div class="nav-button">
						<input id="addDomain" type="button" class="Button" onclick="addDomain()" value="添加域环境" style="display:" /> 
						<input id="editDomain" type="button" class="Button" onclick="editDomain()" value="编辑域环境" style="display: none" />
					</div>
				</div>
				<div  class="layout-center tbody">
					<div class="contentPanel" id="domain_exist" style="display: none;">
						<div class="basis-from">
							<table>
								<tr>
									<td>域服务器域名：</td>
									<td><span id="domainNameShow"></span></td>
								</tr>
								<tr>
									<td>域服务器IP：</td>
									<td><span id="domainIpShow"></span></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="contentPanel" id="domain_none" style="display:">
						<div class="basis-from" style="text-align: center;">
							<span>您还没有添加域环境，请先添加</span>
						</div>
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

