<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
function logoff(){
	window.location.href = contextPath + "/login.htm";
}
</script>
<div id="page-top">
	<div id="component_pageHeader"></div>
	<div id="component_pageHeader_bgtile"></div>
	<div id="component_vertialline"></div>
	<div id="component_pageHeader_bgicon"></div>
	<div id="component_vertialline2"></div>
	<div id="login_username">
	<label><%=request.getAttribute("username")%>　欢迎您</label></div>
	<div id="navButton_logoff"> <a onclick="logoff()" style="cursor: pointer;">退出 </a></div>
</div>

