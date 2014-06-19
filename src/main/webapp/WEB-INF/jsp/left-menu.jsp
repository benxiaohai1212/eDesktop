<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function toUrl(href){
		window.location.href = href;
	}

	$(document).ready(function(){
		/* 选中效果 */
		var thisURL = document.URL;
		thisUPage_s = thisURL.substring(thisURL.indexOf("/",10),thisURL.length);
		$(".left-menuitem").find("a").each(function(index){
			 if ($(".left-menuitem a:eq("+index+")").attr("href") == thisUPage_s) { 
				 $(".left-menuitem a:eq("+index+")").parent().css("background","url('<c:url value='/images/left_menu_highlight.png'/>') no-repeat scroll left transparent");
				 return false; 
			  }
		});
		/* 
		// 点击span内文字和横线都起作用，但点击列表项后会将当前的menu-group收起后再展开
		$(".menu-group").click(function(){
			  $(this).children("span").nextAll(".left-menuitem").toggle();
		}); 
		*/
		/* 指点击span内文字才起作用，点击横线不起作用 */
		$(".menu-group").children("span").css("cursor","pointer").click(function(){
			if($(this).nextAll(".left-menuitem").css('display') == "block"){
				$(this).nextAll(".left-menuitem").hide();
				$(this).nextAll(".left-menuitem").attr("display","none")
				$(this).prev("img").attr("src","<c:url value='/images/sq.png'/>");
				$(this).prev("img").attr("title","展开");
			}else{
				$(this).nextAll(".left-menuitem").show();
				$(this).nextAll(".left-menuitem").attr("display","block");
				$(this).prev("img").attr("src","<c:url value='/images/zk.png'/>");
				$(this).prev("img").attr("title","收起");
			}
			//$(this).nextAll(".left-menuitem").toggle();
		});
//		点击展开/收起图片时控制菜单项的显示隐藏
		$(".menu-group").children("img").css("cursor","pointer").click(function(){
			if($(this).attr("title") === "收起" && $(this).nextAll(".left-menuitem").css('display') == "block"){
				$(this).nextAll(".left-menuitem").hide();
				$(this).nextAll(".left-menuitem").attr("display","none")
				$(this).attr("src","<c:url value='/images/sq.png'/>");
				$(this).attr("title","展开");
			}else if($(this).attr("title") === "展开" && $(this).nextAll(".left-menuitem").css('display') == "none"){
				$(this).nextAll(".left-menuitem").show();
				$(this).nextAll(".left-menuitem").attr("display","block");
				$(this).attr("src","<c:url value='/images/zk.png'/>");
				$(this).attr("title","收起");
			}
		});
	}); 
</script>
<div class="menu-group">
	<img title="收起" src="<c:url value='/images/zk.png'/>" /><span>用户管理</span>
	<div class="left-menuitem" title="公司管理"
		onclick='toUrl(<%=request.getContextPath()%>/company.htm?username=<%=request.getAttribute("username")%>)'>
		<a href="<%=request.getContextPath()%>/company.htm?username=<%=request.getAttribute("username")%>">公司管理</a>
	</div>
	<div class="left-menuitem" title="部门管理"
		onclick='toUrl(<%=request.getContextPath()%>/department.htm?username=<%=request.getAttribute("username")%>)'>
		<a href="<%=request.getContextPath()%>/department.htm?username=<%=request.getAttribute("username")%>">部门管理</a>
	</div>
	<div class="left-menuitem" title="用户管理"
		onclick='toUrl(<%=request.getContextPath()%>/euser.htm?username=<%=request.getAttribute("username")%>)'>
		<a href="<%=request.getContextPath()%>/euser.htm?username=<%=request.getAttribute("username")%>">员工管理</a>
	</div>
</div>
<div class="menu-group">
	<img title="收起" src="<c:url value='/images/zk.png'/>" /><span>桌面管理</span>
	<div class="left-menuitem" title="桌面管理"
		onclick='toUrl(<%=request.getContextPath()%>/edesktop.htm?username=<%=request.getAttribute("username")%>)'>
		<a href="<%=request.getContextPath()%>/edesktop.htm?username=<%=request.getAttribute("username")%>">桌面管理</a>
	</div>
</div>
<div class="menu-group">
	<img title="收起" src="<c:url value='/images/zk.png'/>" /><span>域环境设置</span>
	<div class="left-menuitem" title="域环境设置"
		onclick='toUrl(<%=request.getContextPath()%>/domain.htm?username=<%=request.getAttribute("username")%>)'>
		<a
			href="<%=request.getContextPath()%>/domain.htm?username=<%=request.getAttribute("username")%>">域环境设置</a>
	</div>
</div>
<div class="menu-group">
	<img title="收起" src="<c:url value='/images/zk.png'/>" /><span>策略管理</span>
	<div class="left-menuitem" title="组管理"
		onclick='toUrl(<%=request.getContextPath()%>/group.htm?username=<%=request.getAttribute("username")%>)'>
		<a
			href="<%=request.getContextPath()%>/group.htm?username=<%=request.getAttribute("username")%>">组管理</a>
	</div>
	<div class="left-menuitem" title="策略管理"
		onclick='toUrl(<%=request.getContextPath()%>/strategy.htm?username=<%=request.getAttribute("username")%>)'>
		<a
			href="<%=request.getContextPath()%>/strategy.htm?username=<%=request.getAttribute("username")%>">策略管理</a>
	</div>
</div>

