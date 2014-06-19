
		//上一页
		function euserPre(){
			var euser_pageNum = $("#euser_pageNum").html();
			if(euser_pageNum!=1){
				$("#euser_pageNum").html(parseInt(euser_pageNum)-1);
				initEuser();
			}
		}
		//下一页
		function euserNext(){
			var euser_pageNum = $("#euser_pageNum").html();
			var euser_totalPage = $("#euser_totalPage").html();
			if(euser_pageNum!=euser_totalPage){
				$("#euser_pageNum").html(parseInt(euser_pageNum)+1);
				initEuser();
			}
		}
		//首页
		function euserfirst(){
			$("#euser_pageNum").html(1);
			initEuser();
		}
		//末页
		function euserlast(){
			var euser_totalPage = $("#euser_totalPage").html();
			$("#euser_pageNum").html(euser_totalPage);
			initEuser();
		}
		
		//列表
		function initEuser(){
			var loginId = $("#login_id").val();
			if(loginId == "请输入用户名"){
				var loginId = "";
			}
			var euserPageNum = $("#euser_pageNum").html();
			var stringEuser = "<tr><th style='text-align:left;'>用户名</th><th>显示名</th><th>组名称</th><th>状态</th><th>桌面</th><th>操作</th></tr>";
			$.ajax({
				type: "POST",
				url: contextPath  + "/euser_list.do",
				data:{StringPageNum:euserPageNum,StringPageSize:StringPageSize,loginId:loginId},
				success: function(pages) {
					var eusers = pages.list;
				  	for(var i=0;i<eusers.length;i++){
				  		 var euser = eusers[i];
				  		if(euser.groupId != 0){
							if(euser.desktopCount != 0){
								//alert("euser.groupId != 0 && euser.desktopCount != 0");
								stringInstance = "<tr><th>桌面名称</th><th>IP</th><th>配置</th></tr>";
								$.ajax({
									type: "POST",
									cache:false, 
								    async:false,
									url: contextPath  + "/ud_selectByEuserId.do",
									data:{eUserId:euser.id},
									success: function(eUDs) {
										if(eUDs.length != 0){
											for(var i=0;i<eUDs.length;i++){
										  		var eUD = eUDs[i];
										  		$.ajax({
													type: "POST",
													cache:false, 
												    async:false,
													url: contextPath  + "/desktop_selectById.do",
													data:{id:eUD.desktopId},
													success: function(obj) {
														stringInstance +=  "<tr style='background-color:#ffffff' id='tr_id'>"+
														"<td style='text-align:left;' id='loginId'>"+obj.instance_name+"</td>"+
														"<td style='text-align:left;' id='loginId'>"+obj.ip+"</td>"+
														"<td style='text-align:left;' id='loginId'>"+obj.instance_type+"</td>"+
														"</tr>";
														$("#tooltip_instances").html(stringInstance);
													},
													dataType : "json"
											    });
											}
										}
									},
									dataType : "json"
							    }); 
								
								$.ajax({
									type: "POST",
									cache:false, 
								    async:false,
									url: contextPath  + "/group_getById.do",
									data:{id:euser.groupId},
									success: function(obj) {
										var groupName = obj.groupName;
										stringEuser += "<tr style='background-color:#ffffff' id='tr_id'>"+
									    "<td style='text-align:left;' id='loginId'>"+euser.loginId+"</td>"+
									    "<td style='text-align:center;' id='userName'>"+euser.userName+"</td>"+
									    "<td style='text-align:center;' id='groupName'>"+groupName+"</td>"+
									    "<td style='text-align:center;' id='task'>"+"分配"+"</td>"+
									    "<td style='text-align:center;' class='desktopCount'" +
									    "id='desktopCount'><span class='tooltip' data-tooltip=''>"+euser.desktopCount+
									    "<div class='tooltip-content'>" +
									    "<table id='tooltip_instances' class='' cellspacing='0' cellpadding='0' width='100%' border='0'>"+
									    stringInstance+
							            "</table>" +
									    "</div>" +
									    "</span></td>"+
									    "<td style='text-align:center;'>[<a href='#' onclick='euser_modifyPre("+euser.id+")' >编辑</a>] [<a href='#' onclick='euser_delete("+euser.id+")' >删除</a>]</td>"+
										"</tr>";
									},
									dataType : "json"
							    });
							}else{
								//alert("euser.groupId != 0 && euser.desktopCount == 0");
								$.ajax({
									type: "POST",
									cache:false, 
								    async:false,
									url: contextPath  + "/group_getById.do",
									data:{id:euser.groupId},
									success: function(obj) {
										var groupName = obj.groupName;
										stringEuser += "<tr style='background-color:#ffffff' id='tr_id'>"+
									    "<td style='text-align:left;' id='loginId'>"+euser.loginId+"</td>"+
									    "<td style='text-align:center;' id='userName'>"+euser.userName+"</td>"+
									    "<td style='text-align:center;' id='groupName'>"+groupName+"</td>"+
									    "<td style='text-align:center;' id='task'>"+"空闲"+"</td>"+
									    "<td style='text-align:center;' class='desktopCount' id='desktopCount'>"+"0"+"</td>"+
									    "<td style='text-align:center;'><a href='#' onclick='euser_modifyPre("+euser.id+")' >编辑</a> <a href='#' onclick='euser_delete("+euser.id+")' >删除</a></td>"+
										"</tr>";
									},
									dataType : "json"
							    });
								
							}
						}else{
							if(euser.desktopCount != 0){
								stringInstance = "<tr><th>桌面名称</th><th>IP</th><th>配置</th></tr>";
								$.ajax({
									type: "POST",
									cache:false, 
								    async:false,
									url: contextPath  + "/ud_selectByEuserId.do",
									data:{eUserId:euser.id},
									success: function(eUDs) {
										if(eUDs.length != 0){
											for(var i=0;i<eUDs.length;i++){
										  		var eUD = eUDs[i];
										  		$.ajax({
													type: "POST",
													cache:false, 
												    async:false,
													url: contextPath  + "/desktop_selectById.do",
													data:{id:eUD.desktopId},
													success: function(obj) {
														stringInstance +=  "<tr style='background-color:#ffffff' id='tr_id'>"+
														"<td style='text-align:left;' id='loginId'>"+obj.instance_name+"</td>"+
														"<td style='text-align:left;' id='loginId'>"+obj.ip+"</td>"+
														"<td style='text-align:left;' id='loginId'>"+obj.instance_type+"</td>"+
														"</tr>";
														$("#tooltip_instances").html(stringInstance);
													},
													dataType : "json"
											    });
											}
										}
									},
									dataType : "json"
							    }); 
								stringEuser += "<tr style='background-color:#ffffff' id='tr_id'>"+
							    "<td style='text-align:left;' id='loginId'>"+euser.loginId+"</td>"+
							    "<td style='text-align:center;' id='userName'>"+euser.userName+"</td>"+
							    "<td style='text-align:center;' id='groupName'>"+"--"+"</td>"+
							    "<td style='text-align:center;' id='task'>"+"分配"+"</td>"+
							    "<td style='text-align:center;' class='desktopCount'" +
							    "id='desktopCount'><span class='tooltip' data-tooltip=''>"+euser.desktopCount+
							    "<div class='tooltip-content'>" +
							    "<table id='tooltip_instances' class='' cellspacing='0' cellpadding='0' width='100%' border='0'>"+
							    stringInstance+
					            "</table>" +
							    "</div>" +
							    "</span></td>"+
							    "<td style='text-align:center;'><a href='#' onclick='euser_modifyPre("+euser.id+")' >编辑</a> <a href='#' onclick='euser_delete("+euser.id+")' >删除</a></td>"+
								"</tr>";
							}else{
								//alert("euser.groupId == 0 && euser.desktopCount == 0");
								stringEuser += "<tr style='background-color:#ffffff' id='tr_id'>"+
							    "<td style='text-align:left;' id='loginId'>"+euser.loginId+"</td>"+
							    "<td style='text-align:center;' id='userName'>"+euser.userName+"</td>"+
							    "<td style='text-align:center;' id='groupName'>"+"--"+"</td>"+
							    "<td style='text-align:center;' id='task'>"+"空闲"+"</td>"+
							    "<td style='text-align:center;' class='desktopCount' id='desktopCount'>"+"0"+"</td>"+
							    "<td style='text-align:center;'><a href='#' onclick='euser_modifyPre("+euser.id+")' >编辑</a> <a href='#' onclick='euser_delete("+euser.id+")' >删除</a></td>"+
								"</tr>";
							}
						}
					  }
				  	$("#euser_totalNumber").html(pages.totalNumber);
					$("#euser_totalPage").html(pages.totalPage);
					$("#euser_detail").html(stringEuser);  
				},
				dataType : "json"
			});
		}
		
		//条件查询
		function select_by_condition(){
			initEuser();
			$("#login_id").val("");
		}
		
		//添加
		function euser_add(){
			var content =
				'<table id="addPre" style="width: 600px; height: 400px;border-:0px solid #DBDBDB">'+
				'   <tr style="height:20px;">'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;"><span id="userName_span" style="color:#FF0033; display:none">* </span>显示名：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="userName" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			<span style="">员工的真实姓名</span>'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;"><span id="loginId_span" style="color:#FF0033; display:none">* </span>用户名：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="loginId" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			<span id="loginId_span2" style="color:#FF0033; display:none">用户名已存在！</span>'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;"><span id="password_span" style="color:#FF0033; display:none">* </span>密码：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="password" value="" style="" />'+
				'		</td>'+
				'       <td style="width:178px; height:20px; padding:1px 1px 1px;">'+
				'			<span style="">长度6~14位，由字母、数字、符号组成，区分大小写</span>'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:113px; height:20px; padding:1px 1px 1px; text-align: right;"><span id="confirmPassword_span" style="color:#FF0033; display:none">* </span>确认密码：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="confirmPassword" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			<span style="">请再次输入新密码</span>'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">部门：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<select id="department" name="department"></select>'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">邮箱：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="email" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">电话：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="phoneNumber" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'	<tr>'+
				'		<td colspan="3" align="center" style="height: 50px; width: 600px; padding: 5px 28px 0 0; text-align: right;">'+
				'			<input type="button" class="Button2" onclick="dlg.hide();" value="添加"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'+
				'		</td>'+
				'	</tr>'+
				'</table>';
			dlg = new Dialog(content,{
				title:'添加员工',
				afterClose:function(){ 
				},
				beforeHide:function(){
					checkEuser();
				},
				modal:false
			});
			$.ajax({
				type: "POST",
				cache:false, 
			    async:false,
				url: contextPath  + "/dept_select.do",
				success: function(obj) {
					if(obj!=null && obj!=""){
						$("#department").append("<option value='please select your options'>--请选择部门--</option>");
			    		for(var i = 0;i<obj.length;i++){
			    			$("#department").append("<option value='"+obj[i].deptName+"'>"+obj[i].deptName+"</option>");
			    		}
			    	}else{
					    $("#department").append("<option value='please select your options'>--无--</option>");
			    	}
				},
				dataType : "json"
		    });
					
			dlg.show();		
			
			function checkEuser(){
				var userName = $("input[type='text'][id='userName']").val();
				var loginId = $("input[type='text'][id='loginId']").val();
				var password = $("input[type='text'][id='password']").val();
				//var password = $("#password").val();
				alert(password);
				var confirmPassword = $("input[type='text'][id='confirmPassword']").val();
				if(userName != null && userName != ""){
					$("#userName_span").hide();
					if(loginId != null && loginId != ""){
						$("#loginId_span").hide();
						//(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{6,14}
						var reg = new RegExp("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).{6,14}") ;
						if(password != null && password != "" && reg.test(password)){
							$("#password_span").hide();
							if(confirmPassword != null && confirmPassword != "" && confirmPassword == password){
								$("#confirmPassword_span").hide();
								euserAdd();
							}else{
								$("#confirmPassword_span").show();
							}
						}else{
							$("#password_span").show();
						}
					}else{
						$("#loginId_span").show();
					}
				}else{
					$("#userName_span").show();
				}
				
				$.ajax({
					type: "POST",
					url: contextPath  + "/selectEusers.do",
					success: function(list) {
						if(list.length != 0){
							for(var i=0;i<list.length;i++){
						  		var euser = list[i];
						  		if(euser.loginId == loginId){
						  			$("#loginId_span2").show();
						  		}else{
						  			$("#loginId_span2").hide();
						  		}
							}
						}
					},
					dataType : "json"
			    });
			}
			
			function euserAdd(){
				var userName = $("input[type='text'][id='userName']").val();
				var loginId = $("input[type='text'][id='loginId']").val();
				var password = $("input[type='text'][id='password']").val();
				var confirmPassword = $("input[type='text'][id='confirmPassword']").val();
				var deptName = $("#department").val();
				var email = $("input[type='text'][id='email']").val();
				var phoneNumber = $("input[type='text'][id='phoneNumber']").val();
				var status = "空闲";
				$.ajax({
					type: "POST",
					url: contextPath  + "/dept_selectByDeptName.do",
					data:{deptName:deptName},
					success: function(obj) {
						var departmentId = obj.id;
						$.ajax({
							type: "POST",
							url: contextPath  + "/eUser_add.do",
							data:{userName:userName,loginId:loginId,departmentId:departmentId,status:status,desktopCount:0,password:password,confirmPassword:confirmPassword,email:email,phoneNumber:phoneNumber,domainId:1},
							success: function(obj) {
								dlg.close();
								initEuser();
							},
							dataType : "json"
					    });
					},
					dataType : "json"
			    });
			}
			
		}
		//编辑
		function euser_modifyPre(id){
			var content =
				'<table id="addEuser" style="width: 600px; height: 400px;border-:0px solid #DBDBDB">'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">用户名：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<span style="" id="loginId"></span>'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'   <tr style="height:20px;">'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;"><span id="userName_span" style="color:#FF0033; display:none">* </span>显示名：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="userName" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			<span style="">员工的真实姓名</span>'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">部门：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<select id="department" name="department"></select>'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">邮箱：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="email" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'   <tr>'+
				'		<th style="width:50px; height:20px; padding:1px 1px 1px; text-align: right;">电话：</th>'+
				'		<td style="width:100px; height:20px; padding:1px 1px 1px;">'+
				'			<input type="text" id="phoneNumber" value="" style="" />'+
				'		</td>'+
				'       <td style="width:70px; height:20px; padding:1px 1px 1px;">'+
				'			'+
				'		</td>'+
				'	</tr>'+
				'	<tr>'+
				'		<td colspan="3" align="center" style="height: 50px; width: 600px; padding: 5px 28px 0 0; text-align: right;">'+
				'			<input type="button" class="Button2" onclick="dlg.hide();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'+
				'		</td>'+
				'	</tr>'+
				'</table>';
			dlg = new Dialog(content,{
				title:'编辑员工',
				afterClose:function(){ 
				},
				beforeHide:function(){
					euser_modify(id);
				},
				modal:false
			});
			$.ajax({
				type: "POST",
				cache:false, 
			    async:false,
				url: contextPath  + "/dept_select.do",
				success: function(obj) {
					if(obj!=null && obj!=""){
						$("#department").append("<option value='please select your options'>--请选择部门--</option>");
			    		for(var i = 0;i<obj.length;i++){
			    			$("#department").append("<option value='"+obj[i].deptName+"'>"+obj[i].deptName+"</option>");
			    		}
			    	}else{
					    $("#department").append("<option value='please select your options'>--无--</option>");
			    	}
				},
				dataType : "json"
		    });
			$.ajax({
				type: "POST",
				cache:false, 
			    async:false,
				url: contextPath  + "/euser_selectById.do",
				data:{id:id},
				success: function(obj) {
					$("span[id='loginId']").html(obj.loginId);
					$("input[type='text'][id='userName']").val(obj.userName);
					$("input[type='text'][id='email']").val(obj.email);
					$("input[type='text'][id='phoneNumber']").val(obj.phoneNumber);
					$.ajax({
						type: "POST",
						cache:false, 
					    async:false,
						url: contextPath  + "/dept_modifyPre.do",
						data:{id:obj.departmentId},
						success: function(dept) {
							$("#department option[value='"+ dept.deptName +"']").attr("selected",true);
						},
						dataType : "json"
				    });
				},
				dataType : "json"
		    });
					
			dlg.show();	
			
			function euser_modify(id){
				var userName = $("input[type='text'][id='userName']").val();
				var deptName = $("#department").val();
				var email = $("#email").val();
				var phoneNumber = $("#phoneNumber").val();
				$.ajax({
					type: "POST",
					url: contextPath  + "/euser_modify.do",
					data:{id:id,userName:userName,deptName:deptName,email:email,phoneNumber:phoneNumber},
					success: function(obj) {
						if(obj=="1"){
							window.location.href =contextPath  + "/showEuser.htm";
						}
					},
					dataType : "json"
				});
			}
		}
		
		//删除
		function euser_delete(id){
			var content1 =
				'<table id="delete" style="width: 416px; height: 170px;border-:0px solid #DBDBDB">'+
				'   <tr>'+
				'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="">您确定要删除该员工吗？</span></td>'+
				'	</tr>'+
				'	<tr>'+
				'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
				'			<input type="button" class="Button2" onclick="dlg1.hide();dlg1.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg1.close()" value="取消"/>'+
				'		</td>'+
				'	</tr>'+
				'</table>';
			var content2 =
				'<table id="delete" style="width: 416px; height: 170px;border-:0px solid #DBDBDB">'+
				'   <tr>'+
				'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="">请先将员工关联的桌面信息释放，再进行删除操作！</span></td>'+
				'	</tr>'+
				'	<tr>'+
				'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
				'			<input type="button" class="Button2" onclick="dlg2.hide();dlg2.close();" value="确定"/>'+
				'		</td>'+
				'	</tr>'+
				'</table>';
			dlg1 = new Dialog(content1,{
				title:'删除员工',
				afterClose:function(){ 
				},
				beforeHide:function(){
					deleteEuser();
				},
				modal:false
			});
			dlg2 = new Dialog(content2,{
				title:'提示',
				afterClose:function(){ 
				},
				beforeHide:function(){
					dlg2.close();
				},
				modal:false
			});
			
			$.ajax({
				type: "POST",
				url: contextPath  + "/euser_selectById.do",
				data:{id:id},
				success: function(obj) {
					if(obj.desktopCount == 0){
						dlg1.show();
					}else{
						dlg2.show();
					}
				},
				dataType : "json"
			});
			
			function deleteEuser(){
				$.ajax({
					type: "POST",
					url: contextPath  + "/euser_delete.do",
					data:{id:id},
					success: function(obj) {
						if(obj=="1"){
							window.location.href =contextPath  + "/showEuser.htm";
						}
					},
					dataType : "json"
				}); 
			}
		}
		
		//批量导入
		function euser_excle(){
			var content =
			'<table style="width: 380px; height: 75px;border-:0px solid #DBDBDB">'+
			'	<tr>'+
			'		<th style="width:19%; height:35%; padding:0 0 0 0;">文件选择：</th>'+
			'		<td style="width:19%; height:35%; padding:0 0 0 0;">'+
			'			<input type="file" id="picpath" class="picpath" name="picpath"/>'+
			'		</td>'+
			'	</tr>'+
			'	<tr>'+
			'		<td colspan="2">'+
			'			<hr/>'+
			'		</td>'+
			'	</tr>'+
			'	<tr>'+
			'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 0 0 0 0; text-align: right;">'+
			'			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'+
			'		</td>'+
			'	</tr>'+
			'</table>';
				dlg = new Dialog(content,{
					title:'上传Excel',
					afterClosee:function(){
						this.close();
					},
					beforeHide:function(){
						return upload_excel();
					},
					modal:true
					});
				dlg.show();
		}
		
		function upload_excel(){
			var value=document.getElementById("picpath").value;
			if(value.indexOf("\\") != -1){
				value = value.substring(value.lastIndexOf("\\")+1,value.lenght);
			}
		    if(value==""){
		       alert("请选择上传文件。");
		       return false;
		    }
		   /* var reg = /[\u4e00-\u9fa5]/;
		    if(reg.test(value)){
				alert("文件名不能是中文。");
				return false;
			}*/
		    
			var regexp=/^.*\.xls$/;
	    	if(regexp.test(value)){
	    		$.ajaxFileUpload({
					url:contextPath+"/uploadFile.do",
					secureuri:false,
					fileElementId:'picpath',
					dataType: 'json',
					data:{"item":"ajax"},
					success: function (data){
						if(data.msg=='0'){
							if(data.msg=="ObjectAlreadyExists"){
								uploadSucc("red");
								alert("上传失败，文件已存在。");
							}
						}else{
							excel_filename = data.msg;
							alert("上传成功。");
							take_excle(excel_filename);
						}
					},
					error: function (data, status, e){
						if(XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
							window.location.reload();
						} else {
							console.log(textStatus.error + "" + errorThrown);
						}
					}
				});
	    	}else{
	    		var content =
					'<table style="width: 380px; height: 75px;border-:0px solid #DBDBDB">'+
					'	<tr>'+
					'		<td colspan="2" style="width:19%; height:35%; padding:0 0 0 0;">'+
					'			<span style="color:#FF0033;">文件不是 .xls 格式，请重新选择！</span>'+
					'		</td>'+
					'	</tr>'+
					'	<tr>'+
					'		<th style="width:19%; height:35%; padding:0 0 0 0;">文件选择：</th>'+
					'		<td style="width:19%; height:35%; padding:0 0 0 0;">'+
					'			<input type="file" id="picpath" class="picpath" name="picpath"/>'+
					'		</td>'+
					'	</tr>'+
					'	<tr>'+
					'		<td colspan="2">'+
					'			<hr/>'+
					'		</td>'+
					'	</tr>'+
					'	<tr>'+
					'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 0 0 0 0; text-align: right;">'+
					'			<input type="button" class="Button" onclick="dlg1.hide();dlg1.close();" value="确定"/> <input type="button" class="Button" onclick="dlg1.close()" value="取消"/>'+
					'		</td>'+
					'	</tr>'+
					'</table>';
					dlg1 = new Dialog(content,{
						title:'上传Excel',
						afterClosee:function(){
							this.close();
						},
						beforeHide:function(){
							return upload_excel();
						},
						modal:true
						});
					dlg1.show();
	    	}
		}
		
		function take_excle(excel_filename){
			var excel_name = excel_filename.split("_");
			var downloaduri = excel_name[1];
			var haomiao = excel_name[2];
			$.ajax({
				type: "POST",
				url: contextPath  + "/euser_excle.do",
				data:{downloaduri:downloaduri,haomiao:haomiao},
				success: function(obj) {
					if(obj == "1"){
						alert("数据已写入！");
						window.location.href =contextPath  + "/showEuser.htm";
					}else if(obj == "2"){
						var content =
							'<table style="width: 380px; height: 75px;border-:0px solid #DBDBDB">'+
							'	<tr>'+
							'		<td colspan="2" style="width:19%; height:35%; padding:0 0 0 0;">'+
							'			<span style="color:#FF0033;">Excel内有已存在的用户名，请重新选择！</span>'+
							'		</td>'+
							'	</tr>'+
							'	<tr>'+
							'		<th style="width:19%; height:35%; padding:0 0 0 0;">文件选择：</th>'+
							'		<td style="width:19%; height:35%; padding:0 0 0 0;">'+
							'			<input type="file" id="picpath" class="picpath" name="picpath"/>'+
							'		</td>'+
							'	</tr>'+
							'	<tr>'+
							'		<td colspan="2">'+
							'			<hr/>'+
							'		</td>'+
							'	</tr>'+
							'	<tr>'+
							'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 0 0 0 0; text-align: right;">'+
							'			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'+
							'		</td>'+
							'	</tr>'+
							'</table>';
							dlg = new Dialog(content,{
								title:'上传Excel',
								afterClosee:function(){
									this.close();
								},
								beforeHide:function(){
									return upload_excel();
								},
								modal:true
								});
							dlg.show();
					}else if(obj == "0"){
						var content =
							'<table style="width: 380px; height: 75px;border-:0px solid #DBDBDB">'+
							'	<tr>'+
							'		<td colspan="2" style="width:19%; height:35%; padding:0 0 0 0;">'+
							'			<span style="color:#FF0033;">Excel内容格式不对，请重新选择！</span>'+
							'		</td>'+
							'	</tr>'+
							'	<tr>'+
							'		<th style="width:19%; height:35%; padding:0 0 0 0;">文件选择：</th>'+
							'		<td style="width:19%; height:35%; padding:0 0 0 0;">'+
							'			<input type="file" id="picpath" class="picpath" name="picpath"/>'+
							'		</td>'+
							'	</tr>'+
							'	<tr>'+
							'		<td colspan="2">'+
							'			<hr/>'+
							'		</td>'+
							'	</tr>'+
							'	<tr>'+
							'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 0 0 0 0; text-align: right;">'+
							'			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'+
							'		</td>'+
							'	</tr>'+
							'</table>';
							dlg = new Dialog(content,{
								title:'上传Excel',
								afterClosee:function(){
									this.close();
								},
								beforeHide:function(){
									return upload_excel();
								},
								modal:true
								});
							dlg.show();
					}
				},
				dataType : "json"
			});
			
		}