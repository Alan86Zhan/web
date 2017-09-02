<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>'upload.jsp' starting page</title>
    
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
  
  <body style="background: url(res/login10.png);background-size:cover;font-family: 微软雅黑;">
  <div id="main">
		<center><h3>请上传文件(please upload file)</h3></center>
   <form action="${pageContext.request.contextPath}/servlet/UploadHandleServlet" enctype="multipart/form-data" method="post">
  用户名(username)：<input type="text" name="username"><br/>
  .pem文件(.pem file)：<input type="file" name="file1"><br/>
  .zip文件(.zip file)：<input type="file" name="file2"><br/>
  <input type="submit" value="submit">
 </form>
 <a style="margin-left: 10px;"><font size="2"><i>注意：请对应输入以.pem结尾的文件和打包为以.zip结尾的文件
 (Note:Please ensure input file's name end with .pem or.zip)。</i></font></a>
 </div>
  </body>
</html>
