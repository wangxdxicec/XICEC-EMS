<%@ page contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>测试</title>
<script type="text/javascript" src="<%=basePath%>theme/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>theme/qunit-1.14.0.js"></script>
<link href="<%=basePath%>theme/qunit-1.14.0.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<script type="text/javascript">
asyncTest( "留言添加", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/saveComments",
		  data: '{exhibitorId:'+randomId+',userId:1,content:"留言内容",createOn:"2014-08-03"}',
		  success: function(result){
				assert.ok(true&&"操作成功"==result.des,"添加失败");
				start();  
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
asyncTest( "参观预登记添加", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/saveVisitorRegister",
		  data: '{id:'+randomId+',name:"张三",companyName:"公司",tel:"111",email:"d@d.com","position":"职位"}',
		  success: function(result){
				assert.ok(true&&"操作成功"==result.des,"添加失败");
				start();  
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
asyncTest( "参观预登记查询", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/getVisitorRegister",
		  data: '{userId:'+randomId+'}',
		  success: function(result){
				assert.ok(result.id==randomId&&"操作成功"==result.des,"查询参展预登记信息失败");
				start();  
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
asyncTest( "参观预登记修改", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/saveVisitorRegister",
		  data: '{id:'+randomId+',name:"张三",companyName:"公司",tel:"'+randomId+'",email:"d@d.com","position":"职位"}',
		  success: function(result){
			  $.ajax({
				  type: 'POST',
				  url: "http://localhost:8080/HuizhanApp/client/user/getVisitorRegister",
				  data: '{userId:'+randomId+'}',
				  success: function(result){
						assert.ok(result.tel==randomId&&"操作成功"==result.des,"查询参观预登记信息失败");
						start();  
					},
					error:function(){
						assert.ok(false,"服务器错误");
						start(); 
					},
				  contentType:"application/json"
				});
				assert.ok("操作成功"==result.des,"更新参观预登记信息失败");
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
var randomId=Math.round(Math.random()*1000000000);
asyncTest( "参展预登记添加", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/saveShowRegister",
		  data: '{id:'+randomId+',name:"张三",companyName:"公司",tel:"111",email:"d@d.com",loc:"1",items:[1,2]}',
		  success: function(result){
				assert.ok(true&&"操作成功"==result.des,"添加失败");
				start();  
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
asyncTest( "参展预登记查询", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/getShowRegister",
		  data: '{userId:'+randomId+'}',
		  success: function(result){
				assert.ok(result.id==randomId&&"操作成功"==result.des,"查询参展预登记信息失败");
				start();  
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
asyncTest( "参展预登记修改", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/saveShowRegister",
		  data: '{id:'+randomId+',name:"张三",companyName:"公司",tel:"'+randomId+'",email:"d@d.com",loc:"1",items:[1,2,3]}',
		  success: function(result){
			  $.ajax({
				  type: 'POST',
				  url: "http://localhost:8080/HuizhanApp/client/user/getShowRegister",
				  data: '{userId:'+randomId+'}',
				  success: function(result){
						assert.ok(result.tel==randomId&&"操作成功"==result.des,"查询参展预登记信息失败");
						start();  
					},
					error:function(){
						assert.ok(false,"服务器错误");
						start(); 
					},
				  contentType:"application/json"
				});
				assert.ok("操作成功"==result.des,"更新参展预登记信息失败");
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
asyncTest( "预登记展品列表", function( assert ) {
	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/HuizhanApp/client/user/allExhibitItems",
		  data: '{}',
		  success: function(result){
				assert.ok(result.items.length==3&&"操作成功"==result.des,"获取展品类表错误");
				start();  
			},
			error:function(){
				assert.ok(false,"服务器错误");
				start(); 
			},
		  contentType:"application/json"
		});
});
</script>
<div id="qunit"></div>
  <div id="qunit-fixture"></div>
</body>
</html>