//打开添加信息
		function company_list(){
			var str = "";
			$.ajax({
				type: "POST",
				cache:false, 
			    async:false,
				url: contextPath  + "/companyList.do",
				success: function(allCompanies) {
				  	for(var i=0;i<allCompanies.length;i++){
					    var company = allCompanies[i];
					    var cloudUserId=company.cloudUser.id;
					    str += '<tr><td colspan="2"><input type="radio" id="company_'+company.id+'" value="'+company.name+'" name="companyName" style="" /><label for="company_'+company.id+'">'+company.name+'</label><input type="hidden" id="companyCloudUser_'+company.id+'" value="'+company.cloudUser.cloudUser+'"/><input type="hidden" id="companyCloudUserId_'+company.id+'" value="'+company.cloudUser.cloudUser+'"/><input type="hidden" id="cloudUserId_'+company.id+'" value="'+cloudUserId+'"/></td></tr>';
				  	}
				},
				dataType : "json"
			});	
			
		var content = '<form>'+
		'<table id="addPre" class="companyTable">'+
		'	<tr>'+
		'		<td colspan="2">'+
		'			<span>请选择将要添加的公司名称</span><input type="hidden" id="ggg" value="hhhhhhhhhhhhhh"/>'+
		'		</td>'+
		'	</tr>'+
		'   <laber class="strcss">'+
		str+
		'   </laber>'+
		'   <tr>'+
		'		<td>公司简称：</td>'+
		'		<td>'+
		'			<input type="text" id="add_shortName" value="" style="" />'+
		'		</td>'+
		'	</tr>'+
		'	<tr>'+
		'		<td colspan="2" align="center" class="btn">'+
		'			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="添加"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'+
		'		</td>'+
		'	</tr>'+
		'</table>';
			dlg = new Dialog(content,{
				title:'添加公司',
				afterClose:function(){ 
				},
				beforeHide:function(){
					var companyName = $('input:radio:checked').val();
					var shortName = $('#add_shortName').val();
					var companyId = $('input:radio:checked')[0].id.split("_")[1];
					var cloudUser = "companyCloudUser_"+companyId;
					var cloudUserName = $('#'+cloudUser).val();
					var cloudUserId1 = "cloudUserId_"+companyId;
					var cloudUserId = $('#'+cloudUserId1).val();
					addCompany(companyId,companyName,shortName,cloudUserId,cloudUserName);
				},
				modal:false
			});
			dlg.show();
			
		}
		function addCompany(markId,companyName,shorName,cloudUserId,cloudUserName){
			$.ajax({
				type: "POST",
				url: contextPath  + "/company_add.do",
				data:{markId:markId,fullName:companyName,shortName:shorName,cloudUserId:cloudUserId,cloudUser:cloudUserName},
				success: function(obj) {
					if(obj==1){
					  $("#company_add").attr("style",'display:none');
					  $("#company_text").attr("style",'display:');
					  $("#markId").text(markId);
					  $("#companyName").text(companyName);
					  $("#shortName").text(shorName);
					  $("#cloudUser").text(cloudUserName);
					}else{
						alert("公司添加失败");
			   	 		window.location.reload();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				   alert("公司添加失败");
	   	 		   window.location.reload();
	   	 		},
				dataType : "json"
			});
		}
		//编辑公司
		function company_show(){
			var content1 = '<form>'+
			'<table id="modifyPre" class="companyTable">'+
			'	<tr>'+
			'		<th>所选公司：</th>'+
			'		<td>'+
			'			<span id="fullName"></span>'+
			'		</td>'+
			'		<td>'+
			'			<a href="#" onclick="fullNameModifyPre()">更改</a>'+
			'		</td>'+
			'	</tr>'+
			'   <tr>'+
			'		<td>公司简称：</td>'+
			'		<td>'+
			'			<input type="text" id="shortName" value="" style="" />'+
			'		</td>'+
			'	</tr>'+
			'	<tr>'+
			'		<td colspan="2" align="center" class="btn">'+
			'			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="保存"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'+
			'		</td>'+
			'	</tr>'+
			'</table>';
			
			dlg = new Dialog(content1,{
				title:'编辑公司',
				afterClose:function(){
				},
				beforeHide:function(){
					shortNameModify();
				},
				modal:false
			});
			dlg.show();
			
			var companyName = $('#companyName').html();
			$.ajax({
				type: "POST",
				url: contextPath  + "/selectByFullName.do",
				data:{fullName:companyName},
				success: function(ec) {
					if(ec != null){
						$("#fullName").html(ec.fullName);
						$("input[type='text'][id='shortName']").val(ec.shortName);
					}else{
						alert("失败");
			   	 		window.location.reload();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				   alert("失败");
	   	 		   window.location.reload();
	   	 		},
				dataType : "json"
			});
			
			
		}
		
		//更改简称
		function shortNameModify(){
			var fullName = $("#fullName").html();
			var shortName = $("input[type='text'][id='shortName']").val();
			var markId = $("#markId").html();
			$.ajax({
				type: "POST",
				url: contextPath  + "/selectByFullName.do",
				data:{fullName:fullName},
				success: function(ec) {
					if(ec != null){
						$.ajax({
							type: "POST",
							url: contextPath  + "/modifyByFullName.do",
							data:{id:ec.id,markId:ec.markId,fullName:ec.fullName,shortName:shortName,cloudUserId:ec.cloudUserId,cloudUser:ec.cloudUser},
							success: function(obj) {
								if(obj == "1"){
									 $("#markId").text(markId);
									 $("#companyName").text(ec.fullName);
									 $("#shortName").text(shortName);
									 $("#cloudUser").text(ec.cloudUser);
								}else{
									alert("修改失败");
						   	 		window.location.reload();
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
							   alert("修改失败");
				   	 		   window.location.reload();
				   	 		},
							dataType : "json"
						});
					}else{
						alert("失败");
			   	 		window.location.reload();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				   alert("失败");
	   	 		   window.location.reload();
	   	 		},
				dataType : "json"
			});
			
		}
		
		//更改公司
		function fullNameModifyPre(){
			dlg.close();
			var str = "";
			$.ajax({
				type: "POST",
				cache:false, 
			    async:false,
				url: contextPath  + "/companyList.do",
				success: function(allCompanies) {
					for(var i=0;i<allCompanies.length;i++){
					    var company = allCompanies[i];
					    var cloudUserId=company.cloudUser.id;
					    str += '<tr><td colspan="2"><input type="radio" id="company_'+company.id+'" value="'+company.name+'" name="companyName" style="" /><label for="company_'+company.id+'">'+company.name+'</label><input type="hidden" id="companyCloudUser_'+company.id+'" value="'+company.cloudUser.cloudUser+'"/><input type="hidden" id="companyCloudUserId_'+company.id+'" value="'+company.cloudUser.cloudUser+'"/><input type="hidden" id="cloudUserId_'+company.id+'" value="'+cloudUserId+'"/></td></tr>';
				  	}
				},
				dataType : "json"
			});	
			
		var content = '<form>'+
		'<table id="addPre" class="companyTable">'+
		'	<tr>'+
		'		<td colspan="2">'+
		'			<span>请选择将要添加的公司名称</span><input type="hidden" id="ggg" value="hhhhhhhhhhhhhh"/>'+
		'		</td>'+
		'	</tr>'+
		'   <laber>'+
		str+
		'   </laber>'+
		'   <tr>'+
		'		<td>公司简称：</td>'+
		'		<td>'+
		'			<input type="text" id="add_shortName" value="" style="" />'+
		'		</td>'+
		'	</tr>'+
		'	<tr>'+
		'		<td colspan="2" align="center" class="btn">'+
		'			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="修改"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'+
		'		</td>'+
		'	</tr>'+
		'</table>';
			dlg = new Dialog(content,{
				title:'添加公司',
				afterClose:function(){
				},
				beforeHide:function(){
				  fullNameModify();
				},
				modal:false
			});
			dlg.show();
			//点击修改
			function fullNameModify(){
				var fullName = $('input:radio:checked').val();
				var shortName = $('#add_shortName').val();
				var companyId = $('input:radio:checked')[0].id.split("_")[1];
				var cloudUser = "companyCloudUser_"+companyId;
				var cloudUserName = $('#'+cloudUser).val();
				var cloudUserId1 = "cloudUserId_"+companyId;
				var cloudUserId = $('#'+cloudUserId1).val();
				
				var companyName=$('#companyName').html();
				var markId = $("#markId").html();
				$.ajax({
					type: "POST",
					url: contextPath  + "/selectByFullName.do",
					data:{fullName:companyName},
					success: function(ec) {
						if(ec != null){
							$.ajax({
								type: "POST",
								url: contextPath  + "/modifyByFullName.do",
								data:{id:ec.id,fullName:fullName,shortName:shortName,cloudUserId:cloudUserId,cloudUser:cloudUserName},
								success: function(obj) {
									if(obj == "1"){
										$("#markId").text(markId);
									    $("#companyName").text(fullName);
										$("#shortName").text(shortName);
										$("#cloudUser").text(cloudUserName);
									}else{
										alert("修改失败");
							   	 		window.location.reload();
									}
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
								   alert("修改失败");
					   	 		   window.location.reload();
					   	 		},
								dataType : "json"
							});
						}else{
							alert("失败");
				   	 		window.location.reload();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					   alert("失败");
		   	 		   window.location.reload();
		   	 		},
					dataType : "json"
				});
			}
		}