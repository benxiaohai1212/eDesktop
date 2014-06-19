<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="org.dom4j.*"%>
<%
	String contextPath = request.getContextPath();
	//System.out.println(contextPath);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>
<style type="text/css">
  #fragment-1 {  border:1px solid #97a5b0;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px; } 
  .pc_detail { width:100%; border-spacing: 0;border-collapse: collapse; }
  .pc_detail td { border:1px solid #DBDBDB; text-align: center;}
  .pc_detail th { border:1px solid #DBDBDB; background:url('../images/ui-bg_glass_75_e6e6e6_1x400.png'); padding:8px; }
  .Button {
	background: url("../images/button.jpg") repeat-x;
	border:1px solid #C0C3C2;
	-webkit-border-radius:5px;
	-mov-border-radius:5px;
	border-radius:5px;
	cursor:pointer;
  }
  
  .pcTable { width:555px; border-:0px solid #DBDBDB; }
  .pcTable th { width:40%; padding:5px 10px; text-align: right; }
  .pcTable td { width:50%; padding:5px; text-align: left; }
  .pcTable td.btn { width:100%; padding:5px; text-align: center; }
  
</style>
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/dialog.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/js/auto_import_list.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dialog.js'/>"></script>
<script type="text/javascript">
	contextPath = "<%=request.getContextPath()%>";
	var user_name = "<%=request.getAttribute("user_name")%>";
	var pcPageNum = "<%=request.getParameter("pcPageNum")==null?1:request.getParameter("pcPageNum")%>";
	var StringPageSize = 20;
	//初始化生成列表
	$(document).ready(function(){
		initpcs();
	});
	
</script>

</head>
<body>
<div style="margin:10px;">
        <div id="btnDiv" style="padding:10px;">
            <span>用户名：</span>
			<input id="user_name1" type="text" style="width: 100px;"/>
			<span>IP：</span>
			<input id="ip1" type="text" style="width: 100px;"/>
			<span>云主机：</span>
			<input id="instance_name1" type="text" style="width: 100px;"/>
		    <input type="button" class="Button" onclick="select_by_conditions()" value="查询"/>
		    <a href="<%=contextPath%>/jsp/pc_show.jsp" style="margin: 10px 10px 10px 10px ; cursor: pointer;">添加云主机</a>
		    <a href="" style="margin: 10px 10px 10px 10px ; cursor: pointer;" onclick="auto_add()">导入云主机</a>
		</div>
        <div id="container-1">
            <div id="fragment-1">
                <table id="pc_detail" class="pc_detail" cellspacing="0" cellpadding="0" width="100%" border="0">
				</table>
				 <table class="pc_detail" cellspacing="0" cellpadding="0" width="100%" border="0">
				 	<tr>
				 	    <td colspan="10" ><span>共<span id="pc_totalNumber"></span>条记录 共<span id="pc_totalPage"></span>页 当前第<span id="pc_pageNum">1</span>页 <span id="pc_first" style="text-decoration: none;cursor:pointer;" onclick="pcfirst()">首页</span>
				 	          <span id="pc_pre" style="text-decoration: none;cursor:pointer;" onclick="pcPre()">上一页</span> <span id="pc_next" style="text-decoration: none;cursor:pointer;" onclick="pcNext()">下一页</span> <span id="pc_last" style="text-decoration: none;cursor:pointer;" onclick="pclast()">末页</span></span>
				 	    </td>
				 	</tr>
				 </table>
            </div>
          </div>
</div>
</body>
</html>
