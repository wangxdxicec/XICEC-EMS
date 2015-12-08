<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'post.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="<%=basePath%>theme/c.js" type="text/javascript"></script>
<link href="<%=basePath%>theme/s.css" type="text/css" rel="stylesheet"></link>
  </head>
  
  <body>
  <div>
接口地址：<input id="url" style="width:638px" size="200" value="<%=basePath %>client/" />
</div>
<div>请求json</div>
<div>
<textarea id="content" rows="20" cols="100"></textarea>
</div>
<button id="ti">提交</button>
<div>反馈Json</div>
<div class="HeadersRow">
  <textarea id="RawJson" style="display: none;">
  </textarea>
</div>
<div id="ControlsRow">
  <input type="Button" value="格式化" onclick="Process()"/>
  <span id="TabSizeHolder">
    缩进量
    <select id="TabSize" onchange="TabSizeChanged()">
      <option value="1">1</option>
      <option value="2" selected="true">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
    </select>
  </span>
  <label for="QuoteKeys">
    <input type="checkbox" id="QuoteKeys" onclick="QuoteKeysClicked()" checked="true" /> 
    引号
  </label>&nbsp; 
  <a href="javascript:void(0);" onclick="SelectAllClicked()">全选</a>
  &nbsp;
  <span id="CollapsibleViewHolder" >
      <label for="CollapsibleView">
        <input type="checkbox" id="CollapsibleView" onclick="CollapsibleViewClicked()" checked="true" /> 
        显示控制
      </label>
  </span>
  <span id="CollapsibleViewDetail">
    <a href="javascript:void(0);" onclick="ExpandAllClicked()">展开</a>
    <a href="javascript:void(0);" onclick="CollapseAllClicked()">叠起</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(3)">2级</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(4)">3级</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(5)">4级</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(6)">5级</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(7)">6级</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(8)">7级</a>
    <a href="javascript:void(0);" onclick="CollapseLevel(9)">8级</a>
  </span>
</div>
<div id="Canvas" class="Canvas"></div>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>theme/m.js"></script>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">
var xmlHttp = null;
function createXMLHttpRequest() {
	if (xmlHttp == null) {
		if (window.XMLHttpRequest) {
			//Mozilla 浏览器
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			// IE浏览器
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch(e) {}
			}
		}
	}
}

$("#ti").click(function(){
	var url = $("#url").val();
	if(url==''){
		alert("地址不能为空");
		return;
	}
	var content = $("#content").val();
	if(content==''){
		alert("请求json数据不能为空");
		return;
	}
	if (xmlHttp == null) {
		createXMLHttpRequest();
		if (xmlHttp == null) {
			//alert('出错');
			return;
		}
	}
	
	xmlHttp.open("post", url);
	xmlHttp.onreadystatechange=function() {     
		if (xmlHttp.readyState==4 && xmlHttp.status==200) {      
			$("#RawJson").val(xmlHttp.responseText);
			Process();
		}else if(xmlHttp.status==404){
			$("#RawJson").val("{desc:'请求的地址有错'}");
			Process();
		}
	}
	xmlHttp.setRequestHeader("Content-Type", "application/json");
	xmlHttp.send(content);
	
})
</script>

  </body>
</html>
