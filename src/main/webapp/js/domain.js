//打开添加信息
function addDomain() {
	var content1 =
			+ '<table id="addDomainPre" class="companyTable">'
			+ '	<tr>'
			+ '		<th>域服务器域名:</th>'
			+ '		<td>'
			+ '			<input type="text" id="domainName" value="" style="" />'
			+ '		</td>'
			+ '	</tr>'
			+ '   <tr>'
			+ '		<th>&nbsp&nbsp域服务器IP:</th>'
			+ '		<td>'
			+ '			<input type="text" id="domainIp" value="" style="" />'
			+ '		</td>'
			+ '	</tr>'
			+ '	<tr>'
			+ '		<td colspan="2" align="center" class="btn">'
			+ '			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="保存"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'
			+ '		</td>' + '	</tr>' + '</table>';

	dlg = new Dialog(content1, {
		title : '添加域环境',
		afterClose : function() {
		},
		beforeHide : function() {
			var domainName = $('#domainName').val();
			var domainIp = $('#domainIp').val();
			addDomainInfo(domainName, domainIp);
		},
		modal : false
	});
	dlg.show();

}
// 添加域环境信息
function addDomainInfo(domainName, domainIp) {
	$.ajax({
		type : "POST",
		url : contextPath + "/addDomainInfo.do",
		data : {
			domainName : domainName,
			domainIp : domainIp
		},
		success : function(obj) {
			if (obj == 1) {
				$("#addDomain").attr("style", 'display:none');
				$("#editDomain").attr("style", 'display:');
				$("#domain_exist").attr("style", 'display:');
				$("#domain_none").attr("style", 'display:none');
				$("#domainNameShow").text(domainName);
				$("#domainIpShow").text(domainIp);
				$("domainName").val(domainName);
				$("domainIp").val(domainIp);
			} else {
				alert("域环境添加失败");
				window.location.reload();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("域环境添加失败");
			window.location.reload();
		},
		dataType : "json"
	});
}
// 打开修改信息
function editDomain() {
	var content2 = 
			+ '<table id="modifyDomainPre" class="companyTable">'
			+ '	<tr>'
			+ '		<th>域服务器域名:</th>'
			+ '		<td>'
			+ '			<input type="text" id="editDomainName" value="" style="" />'
			+ '		</td>'
			+ '	</tr>'
			+ '   <tr>'
			+ '		<th>&nbsp&nbsp域服务器IP:</th>'
			+ '		<td>'
			+ '			<input type="text" id="editDomainIp" value="" style="" />'
			+ '		</td>'
			+ '	</tr>'
			+ '	<tr>'
			+ '		<td colspan="2" align="center" class="btn">'
			+ '			<input type="button" class="Button" onclick="dlg.hide();dlg.close();" value="保存"/> <input type="button" class="Button" onclick="dlg.close()" value="取消"/>'
			+ '		</td>' + '	</tr>' + '</table>';

	dlg = new Dialog(content2, {
		title : '修改域环境',
		afterClose : function() {
		},
		beforeHide : function() {
			var domainName = $('#editDomainName').val();
			var domainIp = $('#editDomainIp').val();
			editDomainInfo(domainName, domainIp);
		},
		modal : false
	});
	var domainNameShow = document.getElementById("domainNameShow").innerHTML;
	var domainIpShow = document.getElementById("domainIpShow").innerHTML;
	$("input[type='text'][id='editDomainName']").val(domainNameShow);
	$("input[type='text'][id='editDomainIp']").val(domainIpShow);
	dlg.show();
}
// 更改域环境
function editDomainInfo(domainName, domainIp) {
	$.ajax({
		type : "POST",
		url : contextPath + "/editDomainInfo.do",
		data : {
			domainName : domainName,
			domainIp : domainIp
		},
		success : function(obj) {
			if (obj == 1) {
				$("#addDomain").attr("style", 'display:none');
				$("#editDomain").attr("style", 'display:');
				$("#domain_exist").attr("style", 'display:');
				$("#domain_none").attr("style", 'display:none');
				$("#domainNameShow").text(domainName);
				$("#domainIpShow").text(domainIp);
			} else {
				alert("域环境修改失败");
				window.location.reload();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("域环境修改失败");
			window.location.reload();
		},
		dataType : "json"
	});

}