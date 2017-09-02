<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'message.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
  	#main{
  		position:fixed;
  		width: 400px;
  		height: 300px;
  		top:100%;
  		left:50%;
  		margin-top: -300px;
  		margin-left:-200px;
  		
  	}
  	td{
  		padding: 5px;
  	}
  	input{
  		width:100%;
  		height:30px;
  	}
  	</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background: url(res/222.png);background-size:cover;">
   <center>
    <div id="main">
    ${message}&nbsp;&nbsp;<br>
   您上传的用户是: ${usermessage}
   <br>
		<a href="http://52.80.17.26:8080/Fileupload/usertag/${usermessage}" target="_blank">查看上传的文件列表</a></div>
     </center>
  </body>
</html>
