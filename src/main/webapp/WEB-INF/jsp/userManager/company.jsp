<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--公司管理</title>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>"type="text/css">
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/company.js'/>"></script>
<style type="text/css">
/* .companyTable { width:555px; border-:0px solid #DBDBDB; }
	    .companyTable th { width:40%; padding:5px 10px; text-align: right; }
	    .companyTable td { width:50%; padding:5px; text-align: left; }
	    .companyTable td.btn { width:100%; padding:5px; text-align: right; } */
</style>

    <script type="text/javascript">
        contextPath = "<%=request.getContextPath()%>";
		jQuery(document).ready(function(){
			$.ajax({
				type: "POST",
				url: contextPath  + "/selectCompany.do",
				success: function(company) {
				  	if(company.id != 0){
				  		$("#company_add").attr("style",'display:none');
						$("#company_text").attr("style",'display:');
				  		$("#markId").text(company.markId);
						$("#companyName").text(company.fullName);
						$("#shortName").text(company.shorName);
						$("#cloudUser").text(company.cloudUser);
				  	}else{
				  		$("#basis-from1").hide();
				  		$("#basis-from2").show();
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
					<div id="edesktop-span" class="edesktop-span nav-title">公司管理</div>
					<div class="nav-button">
						<input id="company_add" type="button" class="Button" onclick="company_list()" value="添加公司" style="display:" /> 
						<input id="company_text" type="button" class="Button" onclick="company_show()" value="编辑信息" style="display: none" />
					</div>
				</div>
				<div class="layout-center tbody">
					<div class="contentPanel">
						<div class="basis-from">
							<table>
								<tr>
									<td>ID标识：</td>
									<td><span id="markId">标识信息</span></td>
								</tr>
								<tr>
									<td>公司名称：</td>
									<td><span id="companyName">公司具体名称</span></td>
								</tr>
								<tr>
									<td>公司简称：</td>
									<td><span id="shortName">公司简称公司简称公司简称公司简称></span></td>
								</tr>
								<tr>
									<td>云用户：</td>
									<td><span id="cloudUser">云用户信息</span></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="basis-from" id="basis-from2" style="display: none;">
					<table>
						<tr>
							<td><span id="markId">当前没有公司,如果需要,请点击添加公司</span></td>
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

