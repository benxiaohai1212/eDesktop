function initGroup() {
	var groupPageNum = $("#group_pageNum").html();
	var groupPageSize = 15;
	var stringGroup = "<tr><th style='text-align:left;width:30%' >组名称</th><th style='text-align:left;width:30%';>创建时间</th><th style='text-align:center';>操作</th></tr>";
	$.ajax({
				type : "POST",
				url : contextPath + "/groupList.do",
				data : {
					groupPageNum : groupPageNum,
					groupPageSize : groupPageSize
				},
				success : function(pages) {
					var groups = pages.list;
					if (groups.length == 0) {
						$("#group_container_1").attr("style", 'display:none');
						$("#group_container_2").attr("style", 'display:');
					} else {
						$("#group_container_2").attr("style", 'display:none');
						$("#group_container_1").attr("style", 'display:');
						for ( var i = 0; i < groups.length; i++) {
							var group = groups[i];
							stringGroup += "<tr style='background-color:#ffffff'>"
									+ "<td style='text-align:left;width:30%'>"
									+ group.groupName
									+ "</td>"
									+ "<td style='text-align:left;width:30%'>"
									+ group.createTime
									+ "</td>"
									+ "<td style='text-align:center;width:30%'>[<a style='margin-left: 5px;' href='#' onclick='editStrategy("
									+ group.id
									+ ")' >编辑策略</a>][<a href='#' onclick='editGroupMember("
									+ group.id + ")' >编辑组员</a>]</td>" + "</tr>";
						}
						$("#group_totalNumber").html(pages.totalNumber);
						$("#group_totalPage").html(pages.totalPage);
						$("#group_detail").html(stringGroup);
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("组添加失败");
				},
				dataType : "json"
			});
}

// 上一页
function groupPre() {
	var groupPageNum = $("#group_pageNum").html();
	if (groupPageNum != 1) {
		$("#group_pageNum").html(parseInt(groupPageNum) - 1);
		initGroup();
	}
}
// 下一页
function groupNext() {
	var groupPageNum = $("#group_pageNum").html();
	var groupTotalPage = $("#group_totalPage").html();
	if (groupPageNum != groupTotalPage) {
		$("#group_pageNum").html(parseInt(groupPageNum) + 1);
		initGroup();
	}
}
// 首页
function groupFirst() {
	$("#group_pageNum").html(1);
	initGroup();
}
// 末页
function groupLast() {
	var groupTotalPage = $("#group_totalPage").html();
	$("#group_pageNum").html(groupTotalPage);
	initGroup();
}
// 添加组
function groupAdd() {
	var content = '<table id="addGroupPre" style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'
			+ '   <tr>'
			+ '		<td style="width:19%; height:35%; padding:17px 10px 20px;"><span style="font-weight:bold;">组名称：</span></td>'
			+ '		<td style="width:52%; height:35%; padding:17px 10px 20px;">'
			+ '			<input type="text" id="groupName" placeholder="组名称" value="" style="" />'
			+ '		</td>'
			+ '	</tr>'
			+ '	<tr>'
			+ '		<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'
			+ '			<input type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'
			+ '		</td>' + '	</tr>' + '</table>';
	dlg = new Dialog(content, {
		title : '新建组',
		afterClose : function() {
		},
		beforeHide : function() {
			addGroup();
		},
		modal : false
	});
	dlg.show();
}

function addGroup() {
	var groupName = $("#groupName").val();
	$.ajax({
		type : "POST",
		url : contextPath + "/checkGroupNameUnique.do",
		data : {
			groupName : groupName
		},
		success : function(result) {
			if (result == "0") {
				$.ajax({
					type : "POST",
					url : contextPath + "/addGroup.do",
					data : {
						groupName : groupName
					},
					success : function(obj) {
						if (obj == "1") {
							initGroup();
						} else {
							alert("组添加失败");
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("组添加失败");
					},
					dataType : "json"
				});
			}else if(result=="1"){
				alert("组名称重复");
				$("#groupName").val(" ");
			} else {
				alert("组添加失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("组添加失败");
		},
		dataType : "json"
	});


}
// 把策略从右边移到左边
function addStrategyFromRightToLeft(myform) {
	var i;
	for (i = 0; i < myform.right_select_strategy.length; i++) {
		if (myform.right_select_strategy.options[i].selected == true) {
			myform.left_select_strategy.options[myform.left_select_strategy.length] = new Option(
					myform.right_select_strategy.options[i].text,
					myform.right_select_strategy.options[i].value);
			myform.right_select_strategy.options[i] = new Option("");
			myform.right_select_strategy.options.remove(i);
			i--;
		}
	}
}

// 把策略左边的移动到右边：
function moveStrategyFromLeftToRight(myform) {
	var i;
	for (i = 0; i < myform.left_select_strategy.length; i++) {
		if (myform.left_select_strategy.options[i].selected == true) {
			myform.right_select_strategy.options[myform.right_select_strategy.length] = new Option(
					myform.left_select_strategy.options[i].text,
					myform.left_select_strategy.options[i].value);
			myform.left_select_strategy.options.remove(i);
			i--;
		}
	}
}
// 清楚选项
function clearOptions(obj) {
	var len = obj.length;
	// alert(" len "+len);
	for ( var i = len - 1; i >= 0; i--)
		obj.remove(i);
}
// 添加选项
function addOption(obj, key, val) {
	obj.add(new Option(key, val));
}
// 列出该组没有关联的策略
function showAllUnAssStrategy(id) {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showAllUnAssStrategy.do",
		data : {
			groupId : id
		},
		success : function(strategyList) {
			var opt = document.getElementById("left_select_strategy").options;
			clearOptions(opt);
			if (strategyList != null && strategyList.length > 0) {
				for ( var i = 0; i < strategyList.length; i++) {
					var strategy = strategyList[i];
					addOption(opt, strategy.strategiesName, strategy.id);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
// 列出所有和该组关联的策略
function showAllAssStrategy(id) {
	alert(id);
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showAllAssStrategy.do",
		data : {
			groupId : id
		},
		success : function(assStrategyList) {
			var opt = document.getElementById("right_select_strategy").options;
			clearOptions(opt);
			if (assStrategyList != null && assStrategyList.length > 0) {
				for ( var i = 0; i < assStrategyList.length; i++) {
					var strategy = assStrategyList[i];
					addOption(opt, strategy.strategiesName, strategy.id);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
// 编辑策略
function editStrategy(id) {
	var content = '<form id="myform" name="myform"  method="post" >'
			+ '<table id="editStrategyDlg" name="editStrategyDlg"  style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'
			+ '<tr style="font-size: 13px;"><td>选择希望附加的策略</td></tr>'
			+ '<tr><th width="45%" style="background-color:#000 color: #fff;">未选策略</th><th></th><th width="45%"  style="background-color:#000 color: #fff;">已选策略</th></tr>'
			+ '<tr align="center">'
			+ '	<td style="text-align: right;">'
			+ '		<select multiple="multiple" id="left_select_strategy" style="width: 170px; height:200px" ></select>'
			+ '	</td>'
			+ '	<td>'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" <--- " onclick="addStrategyFromRightToLeft(document.myform)">'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" ---> " onclick="moveStrategyFromLeftToRight(document.myform)">'
			+ '	</td>'
			+ '	<td>'
			+ '		<select multiple="multiple" id="right_select_strategy" style="width: 170px; height:200px"></select>'
			+ '	</td>'
			+ '</tr>'
			+ '<tr>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'
			+ '		<input type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'
			+ '	</td>' + '</tr>' + '</table>' + '</form>';
	dlg = new Dialog(content, {
		title : '编辑策略',
		afterClose : function() {
		},
		beforeHide : function() {
			groupAndStrategy(id);
		},
		modal : false
	});
	showAllAssStrategy(id);
	showAllUnAssStrategy(id);
	dlg.show();
}
// 组和策略的对应关系
function groupAndStrategy(id) {
/*	var sel = document.myform.right_select_strategy.options;
	if (sel.length <= 0) {
		alert('请选择一个策略。');
		return;
	}*/
	var StrateiesId = "";
	$("#right_select_strategy option").each(function selectStrategyOption() {
		if ($(this).val() != null) {
			StrateiesId += $(this).val() + ',';
		}
	});
	$.ajax({
		type : "POST",
		url : contextPath + "/groupAndStrategy.json",
		data : {
			groupId : id,
			StrateiesId : StrateiesId
		},
		success : function(ajaxResponse) {
			alert('编辑策略成功。');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
			s
		},
		dataType : "json"
	});
}
// 编辑组成员
function editGroupMember(id) {
	var content = '<form id="myform" name="myform"  method="post" >'
			+ '<table id="editGroupDlg" name="editGroupDlg"  style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'
			+ '<tr><th width="45%" style="background-color:#000 color: #fff;">未选用户</th><th></th><th width="45%"  style="background-color:#000 color: #fff;">已选用户</th></tr>'
			+ '<tr align="center">'
			+ '	<td style="text-align: right;">'
			+ '		<select multiple="multiple" id="left_select_group" style="width: 170px; height:200px" ></select>'
			+ '	</td>'
			+ '	<td>'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" <--- " onclick="addGroupFromRightToLeft(document.myform)">'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" ---> " onclick="moveGroupFromLeftToRight(document.myform)">'
			+ '	</td>'
			+ '	<td>'
			+ '		<select multiple="multiple" id="right_select_group" style="width: 170px; height:200px"></select>'
			+ '	</td>'
			+ '</tr>'
			+ '<tr>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'
			+ '		<input type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'
			+ '	</td>' + '</tr>' + '</table>' + '</form>';
	dlg = new Dialog(content, {
		title : '编辑组员',
		afterClose : function() {
		},
		beforeHide : function() {
			groupAndEuser(id);
		},
		modal : false
	});
	showAllAssEusers(id);
	showAllUnAssEusers();
	dlg.show();
}

//把用户从右边移到左边
function addGroupFromRightToLeft(myform) {
	var i;
	for (i = 0; i < myform.right_select_group.length; i++) {
		if (myform.right_select_group.options[i].selected == true) {
			myform.left_select_group.options[myform.left_select_group.length] = new Option(
					myform.right_select_group.options[i].text,
					myform.right_select_group.options[i].value);
			myform.right_select_group.options[i] = new Option("");
			myform.right_select_group.options.remove(i);
			i--;
		}
	}
}

// 把用户从左边的移动到右边：
function moveGroupFromLeftToRight(myform) {
	var i;
	for (i = 0; i < myform.left_select_group.length; i++) {
		if (myform.left_select_group.options[i].selected == true) {
			myform.right_select_group.options[myform.right_select_group.length] = new Option(
					myform.left_select_group.options[i].text,
					myform.left_select_group.options[i].value);
			myform.left_select_group.options.remove(i);
			i--;
		}
	}
}

//列出已选择的用户
function showAllAssEusers(id) {
	alert(id);
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showAllAssEuserByGroupId.do",
		data : {
			groupId : id
		},
		success : function(euserList) {
			var opt = document.getElementById("right_select_group").options;
			if (opt) {
				alert(1);
			} else {
				alert(2);
			}
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for ( var i = 0; i < euserList.length; i++) {
					var euser = euserList[i];
					addOption(opt, euser.userName, euser.id);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(2);
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
//列出未选择的用户
function showAllUnAssEusers() {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showAllUnAssEuser.do",
		data : {},
		success : function(euserList) {
			var opt = document.getElementById("left_select_group").options;
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for ( var i = 0; i < euserList.length; i++) {
					var euser = euserList[i];
					addOption(opt, euser.userName, euser.id);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(2);
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
//组和用户关联
function groupAndEuser(id) {
/*	var sel = document.myform.right_select_group.options;
	if (sel.length <= 0) {
		alert('请选择一个用户。');
		return;
	}*/
	var EuserIds = "";
	$("#right_select_group option").each(function selectEuserOption() {
		if ($(this).val() != null) {
			EuserIds += $(this).val() + ',';
		}
	});
	$.ajax({
		type : "POST",
		url : contextPath + "/groupAndEuser.json",
		data : {
			groupId : id,
			EuserIds : EuserIds
		},
		success : function(ajaxResponse) {
			alert('编辑组员成功。');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
			s
		},
		dataType : "json"
	});
}
