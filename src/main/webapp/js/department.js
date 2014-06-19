function initdept(){
			var deptPageNum = $("#dept_pageNum").html();
			var stringDept = "<tr><th style='text-align:left;'>部门名称</th><th>操作</th></tr>";
			$.ajax({
				type: "POST",
				url: contextPath  + "/dept_list.do",
				data:{StringPageNum:deptPageNum,StringPageSize:StringPageSize},
				success: function(pages) {
					var depts = pages.list;
				  	for(var i=0;i<depts.length;i++){
				  		 var dept = depts[i];
						 stringDept += "<tr style='background-color:#ffffff'>"+
						    "<td style='text-align:left;'>"+dept.deptName+"</td>"+
						    "<td style='text-align:center;'>[<a href='#' onclick='dept_modifyPre("+dept.id+")' >编辑</a>] [<a href='#' onclick='dept_delete("+dept.id+")' >删除</a>]</td>"+
							"</tr>";
					  }
				  	$("#dept_totalNumber").html(pages.totalNumber);
					$("#dept_totalPage").html(pages.totalPage);
					$("#dept_detail").html(stringDept);  
				},
				dataType : "json"
			});
		}
		
		//上一页
		function deptPre(){
			var dept_pageNum = $("#dept_pageNum").html();
			if(dept_pageNum!=1){
				$("#dept_pageNum").html(parseInt(dept_pageNum)-1);
				initdepts();
			}
		}
		//下一页
		function deptNext(){
			var dept_pageNum = $("#dept_pageNum").html();
			var dept_totalPage = $("#dept_totalPage").html();
			if(dept_pageNum!=dept_totalPage){
				$("#dept_pageNum").html(parseInt(dept_pageNum)+1);
				initdepts();
			}
		}
		//首页
		function deptfirst(){
			$("#dept_pageNum").html(1);
			initdepts();
		}
		//末页
		function deptlast(){
			var dept_totalPage = $("#dept_totalPage").html();
			$("#dept_pageNum").html(dept_totalPage);
			initdepts();
		}
		//添加部门
		function department_add(){
			var content =
			'<table id="addPre" style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'+
			'   <tr>'+
			'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="font-weight:bold;">部门职位：</span></td>'+
			'		<td style="width:52%; height:35%; padding:17px 10px 20px;">'+
			'			<input type="text" id="add_deptName" value="" style="" />'+
			'		</td>'+
			'	</tr>'+
			'	<tr>'+
			'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
			'			<input type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="添加"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'+
			'		</td>'+
			'	</tr>'+
			'</table>';
				dlg = new Dialog(content,{
					title:'添加部门',
					afterClose:function(){ 
					},
					beforeHide:function(){
						addDept();
					},
					modal:false
				});
				dlg.show();
			
				var content4 =
					'<table id="delete" style="width: 416px; height: 170px;border-:0px solid #DBDBDB">'+
					'   <tr>'+
					'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="">部门职位最多为10个字符！</span></td>'+
					'	</tr>'+
					'	<tr>'+
					'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
					'			<input type="button" class="Button2" onclick="dlg4.hide();dlg4.close();" value="确定"/>'+
					'		</td>'+
					'	</tr>'+
					'</table>';
				dlg4 = new Dialog(content4,{
					title:'添加部门',
					afterClose:function(){ 
					},
					beforeHide:function(){
						dlg4.close();
					},
					modal:false
				});
				
		    function addDept(){
		    	var deptName = $("#add_deptName").val();
		    	if(deptName.length>10){
		    		  dlg4.show();
		    		  $("input[type='text'][id='add_deptName']").val("");
		    	}else{
		    		$.ajax({
						type: "POST",
						url: contextPath  + "/dept_add.do",
						data:{deptName:deptName},
						success: function(obj) {
							if(obj=="1"){
								initdept();
							}else{
								alert("部门添加失败");
					   	 		window.location.reload();
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
						   alert("部门添加失败");
			   	 		   window.location.reload();
			   	 		},
						dataType : "json"
					});
		    	}
				
			}
		}
		
		//编辑部门
		function dept_modifyPre(id){
			var content =
			'<table id="modifyPre" style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'+
			'   <tr>'+
			'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="font-weight:bold;">部门职位：</span></td>'+
			'		<td style="width:52%; height:35%; padding:17px 10px 20px;">'+
			'			<input type="text" id="modify_deptName" value="" style="" />'+
			'		</td>'+
			'	</tr>'+
			'	<tr>'+
			'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
			'			<input type="button" class="Button2" onclick="dlg.hide();" value="修改"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'+
			'		</td>'+
			'	</tr>'+
			'</table>';
			dlg = new Dialog(content,{
				title:'编辑部门',
				afterClose:function(){ 
				},
				beforeHide:function(){
					dept_modify(id);
				},
				modal:false
			});
		
			$.ajax({
				type: "POST",
				url: contextPath  + "/dept_modifyPre.do",
				data:{id:id},
				success: function(obj) {
					var deptName = obj.deptName;
					$("input[type='text'][id='modify_deptName']").val(deptName);
				},
				dataType : "json"
		    });
			dlg.show();
			
			function dept_modify(id){
				var content3 =
					'<table id="delete" style="width: 416px; height: 170px;border-:0px solid #DBDBDB">'+
					'   <tr>'+
					'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="">部门职位最多为10个字符！</span></td>'+
					'	</tr>'+
					'	<tr>'+
					'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
					'			<input type="button" class="Button2" onclick="dlg3.hide();dlg3.close();" value="确定"/>'+
					'		</td>'+
					'	</tr>'+
					'</table>';
				dlg3 = new Dialog(content3,{
					title:'删除部门',
					afterClose:function(){ 
					},
					beforeHide:function(){
						dlg3.close();
					},
					modal:false
				});
				
		    	var deptName = $("#modify_deptName").val();
		    	  if(deptName.length>10){
		    		  dlg3.show();
		    		  $("input[type='text'][id='modify_deptName']").val("");
		    	  }else{
		    		  $.ajax({
							type: "POST",
							url: contextPath  + "/dept_modify.do",
							data:{id:id,deptName:deptName},
							success: function(obj) {
								if(obj=="1"){
									window.location.href =contextPath  + "/showDepartment.htm";
								}
							},
							dataType : "json"
						});
		    	  }
			}
		}
		
		function dept_delete(id){
			var content1 =
				'<table id="delete" style="width: 416px; height: 170px;border-:0px solid #DBDBDB">'+
				'   <tr>'+
				'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="">确定删除该部门吗？</span></td>'+
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
				'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="">请检查部门下是否有员工，再进行删除操作！</span></td>'+
				'	</tr>'+
				'	<tr>'+
				'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
				'			<input type="button" class="Button2" onclick="dlg2.hide();dlg2.close();" value="确定"/>'+
				'		</td>'+
				'	</tr>'+
				'</table>';
			dlg1 = new Dialog(content1,{
				title:'删除部门',
				afterClose:function(){ 
				},
				beforeHide:function(){
					deleteDept();
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
				url: contextPath  + "/euser_selectByDeptId.do",
				data:{id:id},
				success: function(obj) {
					if(obj.length == 0){
						dlg1.show();
					}else{
						dlg2.show();
					}
				},
				dataType : "json"
			});
			
			function deleteDept(){
				$.ajax({
					type: "POST",
					url: contextPath  + "/dept_delete.do",
					data:{id:id},
					success: function(obj) {
						if(obj=="1"){
							window.location.href =contextPath  + "/showDepartment.htm";
						}
					},
					dataType : "json"
				}); 
			}
			
		}