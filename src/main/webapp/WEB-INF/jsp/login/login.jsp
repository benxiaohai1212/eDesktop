<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易桌面--登录</title>
<script src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/formStyles.css'/>"
	type="text/css">
<style type="text/css">
.container {
	background: url("images/login/backgroud.jpg");
}
</style>

<script type="text/javascript">
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null);
		if(e.keyCode==13){
			//执行的方法
			login();
		}
	} 
		jQuery(document).ready(function(){
			
		});
		contextPath = "<%=request.getContextPath()%>";
		function login(){
			var username = $("#username").val();
			var password = $("#password").val();
			$.ajax({
				type: "POST",
				url: contextPath  + "/login.do",
				data:{username:username,password:password},
				success: function(obj) {
					if(obj==0){
						document.getElementById("username").value = '';
						document.getElementById("password").value = '';
						document.getElementById("error_message_span").innerHTML="用户名或密码错误";
					//	alert("用户名或者密码错误请重新输入。");
					}else if(obj==1){
						window.location.href = contextPath + "/edesktop.htm?username="+username;
					}else{
						alert("没有该用户。");
						window.location.href = contextPath;
					}
				},
				dataType : "json"
			});	
		}
</script>
</head>
<body>
	<div id="page_login">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td align="center">
						<div id="page_login_user_password">
							<div id="bg0"></div>
							<div id="bg1">
								<img height="72px" src="images/login/loginuser.png"
									style="position: absolute; z-index: 23; left: 2%; text-align: left; top: 20px"
									id="logoImg">
							</div>
							<div id="bg3"></div>
							<div id="bg5"></div>
							<div id="bg6"></div>
							<input type="hidden" value="UTF-8" name="_charset_">
							<div class="dialog_content">
								<div
									style="height: 455px; width: 500px; overflow: hidden; margin: 0 auto; background: url('images/login/pic.png') no-repeat; position: relative; z-index: 23;"
									class="inner">
									<table align="center" id="centered" class="login_class">
										<tbody>
											<tr>
												<td><div id="distance" style="height: 10px;"></div></td>
											</tr>
											<tr>
												<td class="login_username_class">
													<div style="height: 37px; padding-top: 50px;">
														<div
															style="position: relative; z-index: 24; margin: 0 71px; top: 0px; width: 300px; font-size: 14px; font-weight: bold;"
															id="error_message">
															<span id="error_message_span"></span>
														</div>
													</div>
													<div id="usernamediv">
														<input type="text" autofocus="autofocus" placeholder="用户名"
															name="username" maxlength="50" value="" id="username">
													</div>
													<div id="passworddiv">
														<input type="password" placeholder="密码" value="" size="20"
															name="password" id="password" value="">
													</div>
												</td>
											</tr>
											<tr>
												<td class="value  login_username_td_class"></td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="2">
													<div class="button_bar">
														<div class="formButton">
															<input id="login" class="ok" type="button" name="button"  value="   " onclick="login()">
														</div>
													</div>
												</td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>

