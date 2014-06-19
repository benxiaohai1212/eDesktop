//usering start
//pc上一页
function pcPre() {
	var pc_pageNum = $("#pc_pageNum").html();
	if (pc_pageNum != 1) {
		$("#pc_pageNum").html(parseInt(pc_pageNum) - 1);
		initpcs();
	}
}
// pc下一页
function pcNext() {
	var pc_pageNum = $("#pc_pageNum").html();
	var pc_totalPage = $("#pc_totalPage").html();
	if (pc_pageNum != pc_totalPage) {
		$("#pc_pageNum").html(parseInt(pc_pageNum) + 1);
		initpcs();
	}
}

// pc首页
function pcfirst() {
	$("#pc_pageNum").html(1);
	initpcs();
}
// pc末页
function pclast() {
	var pc_totalPage = $("#pc_totalPage").html();
	$("#pc_pageNum").html(pc_totalPage);
	initpcs();
}
// import上一页
function imPre(dlg) {
	var import_pageNum = $("#import_pageNum").html();
	var importNowNum = 0;
	if (import_pageNum != 1) {
		$("#import_pageNum").html(parseInt(import_pageNum) - 1);
		importNowNum = parseInt(import_pageNum) - 1;
		selectAllEdesktop(dlg, importNowNum);
		$("#import_pageNum").html(parseInt(import_pageNum) - 1);
	}
}
// import下一页
function imNext(dlg) {
	var import_pageNum = $("#import_pageNum").html();
	var import_totalPage = $("#import_totalPage").html();
	var importNowNum = 0;
	if (import_pageNum != import_totalPage) {
		$("#import_pageNum").html(parseInt(import_pageNum) + 1);
		importNowNum = parseInt(import_pageNum) + 1;
		selectAllEdesktop(dlg, importNowNum);
		$("#import_pageNum").html(parseInt(import_pageNum) + 1);

	}
}

// import首页
function imfirst(dlg) {
	$("#import_pageNum").html(1);
	importNowNum = 1;
	selectAllEdesktop(dlg, importNowNum);
}
// import末页
function imlast(dlg) {
	var import_totalPage = $("#import_totalPage").html();
	var importNowNum = 0;
	$("#import_pageNum").html(parseInt(import_totalPage));
	importNowNum = parseInt(import_totalPage);
	selectAllEdesktop(dlg, importNowNum);
	$("#import_pageNum").html(parseInt(import_totalPage));
}

function selectByConditions() {
	var fuzzySearchValue = $("#fuzzy-search").val();
	var stringPageNum = $("#pc_pageNum").html();
	var stringPageSize = 20;
	var stringPC = "<tr><th style='width:10%'> <input type='checkbox'  name='messagecheckbox' id='allCheckbox'   value='' onclick='selectAllOrFalse()'/><label for='allCheckbox'>全选</label></th><th>桌面名称</th><th>IP地址</th><th>配置</th><th><a id='statusInfo' style='cursor: pointer;' onclick='showDistributeStatus()'>状态▽</a></th><th>操作</th></tr>";
	$.ajax({
				type : "POST",
				url : contextPath + "/selectByConditions.do",
				data : {
					stringPageNum : stringPageNum,
					stringPageSize : stringPageSize,
					fuzzySearchValue : fuzzySearchValue
				},
				success : function(p) {
					var pcs = p.list;
					for (var i = 0; i < pcs.length; i++) {
						var pc = pcs[i];
						stringPC += "<tr style='background-color:#ffffff'>"
								+ "<td style='text-align:left;padding:2px 5px;'><input type='checkbox'  name='messagecheckbox' id='"
								+ pc.id
								+ "'class='checkhr'  value='' onclick='changeColor("
								+ pc.id
								+ ")'/></td>"
								+ "<td style='text-align:left;'>"
								+ pc.instance_name
								+ "</td>"
								+ "<td style='text-align:left;'>"
								+ pc.ip
								+ "</td>"
								+ "<td style='text-align:left;'>"
								+ pc.instance_type
								+ "</td>"
								+ "<td style='text-align:left;'>"
								+ pc.distribute_status
								+ "</td>"
								+ "<td style='text-align:left; cursor: pointer;'>[<a onclick='modifyEdesktopPre("
								+ pc.id + ")'>编辑</a>]</td>" + "</tr>";
					}
					$("#pc_totalNumber").html(p.totalNumber);
					$("#pc_totalPage").html(p.totalPage);
					$("#pc_detail").html(stringPC);
				},
				dataType : "json"
			});
}
function selectByStatus(value) {
	var fuzzySearchValue = value;
	var stringPageNum = $("#pc_pageNum").html();
	var stringPageSize = 20;
	var stringPC = "<tr><th> <input type='checkbox'  name='messagecheckbox' id='allCheckbox'   value='' onclick='selectAllOrFalse()'/><label for='allCheckbox'>全选</label></th><th>桌面名称</th><th>IP地址</th><th>配置</th><th><a id='statusInfo' style='cursor: pointer;' onclick='showDistributeStatus()'>状态▽</a></th><th>操作</th></tr>";
	$.ajax({
				type : "POST",
				url : contextPath + "/selectByStatusInfo.do",
				data : {
					stringPageNum : stringPageNum,
					stringPageSize : stringPageSize,
					fuzzySearchValue : fuzzySearchValue
				},
				success : function(p) {
					var pcs = p.list;
					for (var i = 0; i < pcs.length; i++) {
						var pc = pcs[i];
						stringPC += "<tr style='background-color:#ffffff'>"
								+ "<td style='text-align:left;'><input type='checkbox'  name='messagecheckbox' id='"
								+ pc.id
								+ "'class='checkhr'  value='' onclick='changeColor("
								+ pc.id
								+ ")'/></td>"
								+ "<td style='text-align:left;'>"
								+ pc.instance_name
								+ "</lable></td>"
								+ "<td style='text-align:left;'>"
								+ pc.ip
								+ "</td>"
								+ "<td style='text-align:left;'>"
								+ pc.instance_type
								+ "</td>"
								+ "<td style='text-align:left;'>"
								+ pc.distribute_status
								+ "</td>"
								+ "<td style='text-align:left; cursor: pointer;'>[<a onclick='modifyEdesktopPre("
								+ pc.id + ")'>编辑</a>]</td>" + "</tr>";
					}
					$("#pc_totalNumber").html(p.totalNumber);
					$("#pc_totalPage").html(p.totalPage);
					$("#pc_detail").html(stringPC);
				},
				dataType : "json"
			});
}
function initpcs() {
	selectByConditions();

}
function showDistributeStatus() {
	var sbtitle = document.getElementById('distributeStatus');
	var statusInfo=document.getElementById('statusInfo');
	sbtitle.style.display = 'block';
}
// 获取元素的纵坐标
function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}

// 获取元素的横坐标
function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset;
}
function importDialog() {
	$.ajax({
		type : "POST",
		url : contextPath + "/autoImport.do",
		data : {},
		success : function(result) {
			if (result == "1") {
				alert("result" + result);
				initDialog();
			} else if (result == "2") {
				alert("请先添加公司");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				alert("error");
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
/**
 * 改变颜色
 */
function changeColor(id) {
	if ($("#" + id).attr('checked') == undefined) {
		$("#" + id).removeAttr("checked");
		$("#" + id).parent("td").parent("tr").css("background", "");
	} else {
		$("#" + id).attr("checked", "checked");
		$("#" + id).parent("td").parent("tr").css("background", "#fbec87");
	}
}

// 全选
function selectAllOrFalse() {
	if ($("#allCheckbox").attr('checked') == undefined) {
		$(".checkhr").removeAttr("checked");
		$(".checkhr").parent("td").parent("tr").css("background", "");
	} else {
		$(".checkhr").attr("checked", "checked");
		$(".checkhr").parent("td").parent("tr").css("background", "#fbec87");
	}
}

// 全选
function selectAllOrFalse1() {
	if ($("#allcheckbox1").attr('checked') == undefined) {
		$(".checkhr1").removeAttr("checked");
		$(".checkhr1").parent("td").parent("tr").css("background", "");
	} else {
		$(".checkhr1").attr("checked", "checked");
		$(".checkhr1").parent("td").parent("tr").css("background", "#fbec87");
	}
}
// 选择要导入的桌面
function selectImportEdesktop() {
	var selip = document.getElementsByTagName("input");
	var selIps = '';
	for (var i = 0, len = selip.length; i < len; i++) {
		if (selip[i].type == "checkbox") {
			if (selip[i].checked) {
				if (selip[i].id != "allcheckbox1") {
					selIps += parseInt(selip[i].id) + ',';
				}

			}

		}
	}

	if (selIps == '') {
		alert("请选择您要导入的信息。");
		return;
	} else {
		$.ajax({
			type : "POST",
			url : contextPath + "/addSelectdDesktop.do",
			data : {
				selIps : selIps
			},
			success : function(result) {
				if (result == 1) {
					window.location.reload();
				}
			},
			dataType : "json"
		});

	}
}
// 从临时表里面导入所有的桌面
function selectAllEdesktop(dlg, importNowNum) {
	var stringPageNum = 1;
	var importPageNum = document.getElementById("import_pageNum");
	if (importPageNum) {
		stringPageNum = $("#import_pageNum").html();
	}
	var stringPageSize = 10;
	var content = '<table id="modifyEdesktop" class="pcTableEdesktop">'
			+ '<tr><th><input type="checkbox"  name="messagecheckbox" id="allcheckbox1"   value="" onclick="selectAllOrFalse1()"/><label for="allcheckbox1">全选</label></th><th>主机名</th><th>IP</th><th>主机配置</th></tr>';
	var showContent = '';

	$
			.ajax({
				type : "POST",
				url : contextPath + "/selectAllEdesktop.do",
				data : {
					stringPageNum : stringPageNum,
					stringPageSize : stringPageSize
				},
				success : function(p) {
					var pcs = p.list;
					for (var i = 0; i < pcs.length; i++) {
						var pc = pcs[i];
						content += "<tr style='background-color:#ffffff'>"
								+ "<td style='text-align:left;'><input type='checkbox'  name='messagecheckbox' id='"
								+ pc.id
								+ "'class='checkhr1'  value='' onclick='changeColor("
								+ pc.id + ")'/></td>"
								+ "<td style='text-align:left;'>"
								+ pc.instance_name + "</td>"
								+ "<td style='text-align:left;'>" + pc.ip
								+ "</td>" + "<td style='text-align:left;'>"
								+ pc.instance_type + "</td>" + "</tr>";
					}
					content += '</table>'
					content += '<table class="pc_detail_edskNumber" cellspacing="0" cellpadding="0"width="100%" border="0">'
					content += '<tr><td colspan="10"><span>共<span id="import_totalNumber"></span>条记录共<span id="import_totalPage"></span>页 当前第<span id="import_pageNum">1</span>页   <span id="imfirst"style="text-decoration: none; cursor: pointer;"onclick="imfirst(dlg);">首页</span> <span id="impre"style="text-decoration: none; cursor: pointer;"onclick="imPre(dlg);">上一页</span> <span id="imnext" style="text-decoration: none; cursor: pointer;" onclick="imNext(dlg);">下一页</span> <span id="imlast" style="text-decoration: none; cursor: pointer;" onclick="imlast(dlg);">末页</span></span></td></tr>'
					content += '<tr><td colspan="2" align="center" class="btn"><input type="button" class="Button" onclick="dlg.hide();dlg.close()" value="确定"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/></td></tr>'
					content += '</table>'
					showContent += '<table id="import_detail" class="pc_detail" cellspacing="0"cellpadding="0" width="100%" border="0"></table>'
					/*
					 * dlg = new Dialog(content, { title : '导入', afterClosee :
					 * function() { this.close(); }, beforeHide : function() {
					 * //return selectAllEdesktop(stringPageNum,stringPageSize); },
					 * modal : true }); dlg.show();
					 */
					dlg.show();
					dlg.setContent(content);

					$("#import_totalNumber").html(p.totalNumber);
					$("#import_totalPage").html(p.totalPage);
					$("#import_pageNum").html(importNowNum);
					// $("#import_detail").html(content);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if (XMLHttpRequest.status == 403
							&& errorThrown == "Forbidden") {
						alert("error");
						window.location.reload();
					} else {
						console.log(textStatus.error + "" + errorThrown);
					}
				},
				dataType : "json"
			});

}

function initDialog() {
	var content = '<table id="modifyEdesktop" class="pcTableEdesktop">'
			+ '<tr><th><input type="checkbox"  name="messagecheckbox" id="allcheckbox1"   value="" onclick="selectAllOrFalse1()"/><label for="allcheckbox1">全选</label></th><th>主机名</th><th>IP</th><th>主机配置</th></tr></table>';
	dlg = new Dialog(content, {
		title : '导入',
		afterClosee : function() {
			this.close();
		},
		beforeHide : function() {
			selectImportEdesktop();
		},
		modal : true
	});
	selectAllEdesktop(dlg);
	/* dlg.show(); */
	return dlg;

}
// 打开编辑话框
function modifyEdesktopPre(id) {
	var content = '<form id="myform" name="myform"  method="post" >'
			+ '<table id="editEuserDlg" name="editEuserDlg"  style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'
			+ '<tr align="center">'
			+ '	<td style="text-align: right;">'
			+ '		<input type="radio" id="allEuser" value="" name="Euser" style="" checked="checked" onclick="showDesktopUnAssEusers('
			+ id
			+ ')"/>'
			+ '	    <label for="allEuser">所有员工</label> '
			+ '		<input type="radio" id="unAssEuser" value="" name="Euser" style="" onclick="showUnAssEusers()"/>'
			+ '	    <label for="unAssEuser">未分配</label>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td style="text-align: center;">'
			+ '	</td>'
			+ '</tr>'
			+ '<tr><th width="45%" style="background-color:#000 color: #fff;">未选用户</th><th></th><th width="45%"  style="background-color:#000 color: #fff;">所选用户</th></tr>'
			+ '<tr align="center">'
			+ '	<td style="text-align: right;">'
			+ '		<select multiple="multiple" id="left_select_euser" style="width: 170px; height:200px" ></select>'
			+ '	</td>'
			+ '	<td>'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" <--- " onclick="addEuserFromRightToLeft(document.myform)">'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" ---> " onclick="moveEuserFromLeftToRight(document.myform)">'
			+ '	</td>'
			+ '	<td>'
			+ '		<select multiple="multiple" id="right_select_euser" style="width: 170px; height:200px"></select>'
			+ '	</td>'
			+ '</tr>'
			+ '<tr>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'
			+ '		<input  id="euserOk" type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'
			+ '	</td>' + '</tr>' + '</table>' + '</form>';
	dlg = new Dialog(content, {
		title : '编辑',
		afterClose : function() {
			window.location.reload();
		},
		beforeHide : function() {
			editDesktopAndEuser(id);
		},
		modal : false
	});
	var checkId = $('input:radio:checked')[0].id
	if (checkId == "allEuser") {
		showDesktopUnAssEusers(id);
	} else if (checkId == "unAssEuser") {
		showUnAssEusers();
	}
	showDesktopAssEusers(id);
	dlg.show();

}
// 显示桌面关联的用户
function showDesktopAssEusers(id) {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showDesktopAssEusers.do",
		data : {
			edesktopId : id
		},
		success : function(euserList) {
			var opt = document.getElementById("right_select_euser").options;
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for (var i = 0; i < euserList.length; i++) {
					var euser = euserList[i];
					addOption(opt, euser.userName, euser.id);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("出现错误");
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
// 列出该桌面没有关联的用户
function showDesktopUnAssEusers(id) {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showDesktopUnAssEusers.do",
		data : {
			edesktopId : id
		},
		success : function(euserList) {
			var opt = document.getElementById("left_select_euser").options;
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for (var i = 0; i < euserList.length; i++) {
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
// 桌面和用户的分配关系（编辑）
function editDesktopAndEuser(edesktopsId) {
	alert("edesktopsId桌面和用户的分配关系（编辑）" + edesktopsId);
	var euserIds = "";
	$("#right_select_euser option").each(function selectEuserOption() {
		if ($(this).val() != null) {
			euserIds += "'" + $(this).val() + "',";
		}
	});
	alert(euserIds);
	$.ajax({
		type : "POST",
		url : contextPath + "/editDesktopAndEuser.do",
		data : {
			edesktopsId : edesktopsId,
			euserIds : euserIds
		},
		success : function(result) {
			if (result == "1") {
				alert(result);
				window.location.reload();
			} else {
				alert("关联失败");
			}
			window.location.reload();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("出现错误，请联系管理员");
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}
// 打开批量分配话框
function edesktopDistribute() {
	var selip = document.getElementsByTagName("input");
	var checkNum = 0;
	var edesktopsIds = "";
	for (var i = 0, len = selip.length; i < len; i++) {
		if (selip[i].type == "checkbox") {
			if (selip[i].checked) {
				if (selip[i].id != "allcheckbox1") {
					checkNum = checkNum + 1;
					edesktopsIds += "'" + parseInt(selip[i].id) + "',";
				}

			}

		}
	}
	$.ajax({
		type : "POST",
		url : contextPath + "/checkDistributestatus.do",
		data : {
			edesktopsIds : edesktopsIds
		},
		success : function(result) {
			if (result == "1") {
				desktopdialogContent(edesktopsIds, checkNum);
			} else {
				alert("批量分配的时候不能有已分配的主机，请您重新选择");
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("云平台连接失败");
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});

}

function desktopdialogContent(edesktopsIds, checkNum) {
	var content = '<form id="myform" name="myform"  method="post" >'
			+ '<table id="editEuserDlg" name="editEuserDlg"  style="width: 400px; height: 200px;border-:0px solid #DBDBDB">'
			+ '<tr align="center">'
			+ '	<td style="text-align: center;">'
			+ '	   <label>您已选择<span id="desktopNum"></span>个桌面</label>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td style="text-align: center;">'
			+ '	</td>'
			+ '</tr>'
			+ '<tr align="center">'
			+ '	<td style="text-align: right;">'
			+ '		<input type="radio" id="allEuser" value="" name="Euser" style="" checked="checked" onclick="showAllEusers()"/>'
			+ '	    <label for="allEuser">所有员工</label> '
			+ '		<input type="radio" id="unAssEuser" value="" name="Euser" style="" onclick="showUnAssEusers()"/>'
			+ '	    <label for="unAssEuser">未分配</label>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td style="text-align: center;">'
			+ '	   <label>已选择<span id="euserNum"></span>位员工</label>'
			+ '	</td>'
			+ '</tr>'
			+ '<tr><th width="45%" style="background-color:#000 color: #fff;">所有用户</th><th></th><th width="45%"  style="background-color:#000 color: #fff;">所选用户</th></tr>'
			+ '<tr align="center">'
			+ '	<td style="text-align: right;">'
			+ '		<select multiple="multiple" id="left_select_euser" style="width: 170px; height:200px" ></select>'
			+ '	</td>'
			+ '	<td>'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" <--- " onclick="addEuserFromRightToLeft(document.myform)">'
			+ '		<input type="button" class="Button" style="margin:2px;" value=" ---> " onclick="moveEuserFromLeftToRight(document.myform)">'
			+ '	</td>'
			+ '	<td>'
			+ '		<select multiple="multiple" id="right_select_euser" style="width: 170px; height:200px"></select>'
			+ '	</td>'
			+ '</tr>'
			+ '<tr>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td>'
			+ '	</td>'
			+ '	<td colspan="2" align="center" style="height: 60%; width: 100%; padding: 5px 28px 0 0; text-align: right;">'
			+ '		<input  id="euserOk" type="button" class="Button2" onclick="dlg.hide();dlg.close();" value="确定"/> <input type="button" class="Button2" onclick="dlg.close()" value="取消"/>'
			+ '	</td>' + '</tr>' + '</table>' + '</form>';
	dlg = new Dialog(content, {
		title : '批量分配',
		afterClose : function() {
			window.location.reload();
		},
		beforeHide : function() {
			desktopAndEuser(edesktopsIds);
		},
		modal : false
	});
	var checkId = $('input:radio:checked')[0].id
	if (checkId == "allEuser") {
		showAllEusers();
	} else if (checkId == "unAssEuser") {
		showUnAssEusers()
	}
	// showAllEusers();
	// showAllUnAssEusers();
	$("#desktopNum").html(checkNum);
	$("#euserNum").html(myform.right_select_euser.length);
	if ($("#desktopNum").html() != $("#euserNum").html()
			|| $("#desktopNum").html() == 0) {
		document.getElementById("euserOk").disabled = true;
	} else {
		document.getElementById("euserOk").disabled = false;
	}
	dlg.show();

}
// 桌面和用户的关系
function desktopAndEuser(edesktopsIds) {
	alert("edesktopsIds" + edesktopsIds);
	var euserIds = "";
	$("#right_select_euser option").each(function selectStrategyOption() {
		if ($(this).val() != null) {
			euserIds += "'" + $(this).val() + "',";
		}
	});
	alert(euserIds);
	$.ajax({
		type : "POST",
		url : contextPath + "/desktopAndEuser.do",
		data : {
			edesktopsIds : edesktopsIds,
			euserIds : euserIds
		},
		success : function(result) {
			if (result == "1") {
				window.location.reload();
			} else {
				alert("关联失败");
			}
			window.location.reload();
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
// 列出所有的用户
function showAllEusers() {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showAllEusers.do",
		data : {},
		success : function(euserList) {
			var opt = document.getElementById("left_select_euser").options;
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for (var i = 0; i < euserList.length; i++) {
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
// 列出所有的未分配桌面的用户
function showUnAssEusers() {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showUnAssEusers.do",
		data : {},
		success : function(euserList) {
			var opt = document.getElementById("left_select_euser").options;
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for (var i = 0; i < euserList.length; i++) {
					var euser = euserList[i];
					addOption(opt, euser.userName, euser.id);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("出现错误");
			if (XMLHttpRequest.status == 403 && errorThrown == "Forbidden") {
				window.location.reload();
			} else {
				console.log(textStatus.error + "" + errorThrown);
			}
		},
		dataType : "json"
	});
}

// 把用户从右边移到左边
function addEuserFromRightToLeft(myform) {
	var i;
	for (i = 0; i < myform.right_select_euser.length; i++) {
		if (myform.right_select_euser.options[i].selected == true) {
			myform.left_select_euser.options[myform.left_select_euser.length] = new Option(
					myform.right_select_euser.options[i].text,
					myform.right_select_euser.options[i].value);
			myform.right_select_euser.options[i] = new Option("");
			myform.right_select_euser.options.remove(i);
			i--;
		}
	}
	$("#euserNum").html(myform.right_select_euser.length);
	if ($("#desktopNum").html() != $("#euserNum").html()
			|| $("#desktopNum").html() == 0) {
		document.getElementById("euserOk").disabled = true;
	} else {
		document.getElementById("euserOk").disabled = false;
	}
}

// 把用户从左边的移动到右边：
function moveEuserFromLeftToRight(myform) {
	var i;
	for (i = 0; i < myform.left_select_euser.length; i++) {
		if (myform.left_select_euser.options[i].selected == true) {
			myform.right_select_euser.options[myform.right_select_euser.length] = new Option(
					myform.left_select_euser.options[i].text,
					myform.left_select_euser.options[i].value);
			myform.left_select_euser.options.remove(i);
			i--;
		}
	}
	$("#euserNum").html(myform.right_select_euser.length);
	if ($("#desktopNum").html() != $("#euserNum").html()
			|| $("#desktopNum").html() == 0) {
		document.getElementById("euserOk").disabled = true;
	} else {
		document.getElementById("euserOk").disabled = false;
	}
}

// 列出已选择的用户
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
			var opt = document.getElementById("right_select_euser").options;
			if (opt) {
				alert(1);
			} else {
				alert(2);
			}
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for (var i = 0; i < euserList.length; i++) {
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
// 清楚选项
function clearOptions(obj) {
	var len = obj.length;
	// alert(" len "+len);
	for (var i = len - 1; i >= 0; i--)
		obj.remove(i);
}
// 添加选项
function addOption(obj, key, val) {
	obj.add(new Option(key, val));
}
// 列出未选择的用户
function showAllUnAssEusers() {
	$.ajax({
		type : "POST",
		cache : false,
		async : false,
		url : contextPath + "/showAllUnAssEuser.do",
		data : {},
		success : function(euserList) {
			var opt = document.getElementById("left_select_euser").options;
			clearOptions(opt);
			if (euserList != null && euserList.length > 0) {
				for (var i = 0; i < euserList.length; i++) {
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

// usering end

