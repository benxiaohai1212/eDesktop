function initStrategy(){
			var strategyPageNum = $("#strategy_pageNum").html();
			var strategyPageSize=15;
			var stringstrategy = "<tr><th style='text-align:cenrer' >策略名称</th><th style='text-align:cenrer';>创建时间</th></tr>";
			$.ajax({
				type: "POST",
				url: contextPath  + "/strategyList.do",
				data:{strategyPageNum:strategyPageNum,strategyPageSize:strategyPageSize},
				success: function(pages) {
					var strategys = pages.list;
					if (strategys.length==0) {
						$("#strategy_container_1").attr("style", 'display:none');
						$("#strategy_container_2").attr("style", 'display:');
					} else {
						$("#strategy_container_2").attr("style", 'display:none');
						$("#strategy_container_1").attr("style", 'display:');
					  	for(var i=0;i<strategys.length;i++){
					  		 var strategy = strategys[i];
							 stringstrategy += "<tr style='background-color:#ffffff'>"+
							    "<td style='text-align:cenrer;'>"+strategy.strategiesName+"</td>"+
							    "<td style='text-align:left;'>"+strategy.create_time+"</td>"+
								"</tr>";
						  }
					  	$("#strategy_totalNumber").html(pages.totalNumber);
						$("#strategy_totalPage").html(pages.totalPage);
						$("#strategy_detail").html(stringstrategy);  
					}
			
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("域环境添加失败");
					window.location.reload();
				},
				dataType : "json"
			});
		}
		
		//上一页
		function strategyPre(){
			var strategyPageNum = $("#strategy_pageNum").html();
			if(strategyPageNum!=1){
				$("#strategy_pageNum").html(parseInt(strategyPageNum)-1);
				initStrategy();
			}
		}
		//下一页
		function strategyNext(){
			var strategyPageNum = $("#strategy_pageNum").html();
			var strategyTotalPage = $("#strategy_totalPage").html();
			if(strategyPageNum!=strategyTotalPage){
				$("#strategy_pageNum").html(parseInt(strategyPageNum)+1);
				initStrategy();
			}
		}
		//首页
		function strategyFirst(){
			$("#strategy_pageNum").html(1);
			initStrategy();
		}
		//末页
		function strategyLast(){
			var strategyTotalPage = $("#strategy_totalPage").html();
			$("#strategy_pageNum").html(strategyTotalPage);
			initStrategy();
		}
		//添加 策略
		function strategyAdd(){
			var content =
			'<table id="addPre" style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'+
			'   <tr>'+
			'		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="font-weight:bold;">策略名称：</span></td>'+
			'		<td style="width:52%; height:35%; padding:17px 10px 20px;">'+
			'			<input type="text" id="strategyName" value="" style="" />'+
			'		</td>'+
			'	</tr>'+
			'	<tr>'+
			'		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'+
			'			<input type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'+
			'		</td>'+
			'	</tr>'+
			'</table>';
				dlg = new Dialog(content,{
					title:'添加 策略',
					afterClose:function(){ 
					},
					beforeHide:function(){
						addStrategy();
					},
					modal:false
				});
				dlg.show();
		}
		
	    function addStrategy(){
	    	var strategyName = $("#strategyName").val();
	    	//首先检查输入的策略名是否重复
			$.ajax({
				type: "POST",
				url: contextPath  + "/checkStrategyNameUnique.do",
				data:{strategyName:strategyName},
				success: function(result) {
					if(result=="0"){
						$.ajax({
							type: "POST",
							url: contextPath  + "/addStrategy.do",
							data:{strategyName:strategyName},
							success: function(obj) {
								if(obj=="1"){
									initStrategy();
								}else{
									alert("策略添加失败");
						   	 		window.location.reload();
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
							   alert("策略添加失败");
				   	 		   window.location.reload();
				   	 		},
							dataType : "json"
						});
					}else if(result=="1"){
						alert("策略名称重复");
						$("#strategyName").val(" ");
					}else {
						alert("输入的策略名称错误");
			   	 		window.location.reload();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				   alert("策略添加失败");
	   	 		   window.location.reload();
	   	 		},
				dataType : "json"
			});
    	
			
		}
	
