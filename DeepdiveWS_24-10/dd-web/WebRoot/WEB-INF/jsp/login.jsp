<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=7;IE=edge"/>
	<title>REDWOOD TRADECLOUD</title>
	<style type="text/css">
	table {border-collapse: collapse;}
	td {padding-top: .5em; padding-bottom: .5em;}
	td { text-align:left; font-family: verdana,arial; color: #064073; font-size: 0.9em; width: 300px;}
	 input { border: 1px solid #CCCCCC; border-radius: 5px; color: #666666; display: inline-block; font-size: 0.9em;  padding: 5px; width: 100%; height: 35px;}
	/* input[type="button"], input[type="reset"], input[type="submit"] { height: auto; width: auto; cursor: pointer; background-color: #3366FF; color: white;width: 30%;float: right; text-align:right; margin-top: 10px; margin-left:7px;margin-right: -5%;text-align: center;height: 35px;} */
	table.center { margin-left:auto; margin-right:auto; }
	.error { font-family: verdana,arial; color: #D41313; font-size: 0.9em; }
	</style>
	 <jsp:include page="/WEB-INF/jsp/head.jsp" />
    <link href="includes/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%-- <div class="footer" style="background-color: black;height: 70px;width: 101.3%;margin-top: -8px;margin-left: -10px;">
     <img alt="Login Image" src="${pageContext.request.contextPath}/images/logo_new.jpg" style="margin-top: 1.2%;">
</div> --%>
	<div style="text-align: center;">
	<div style="background: ; padding: 15px;margin-top: 5%;">
	
<img alt="Login Image" src="${pageContext.request.contextPath}/images/Logo.png" style="width:18%;margin-left: 53px;margin-top: 11px;margin-bottom: -69px;">
<form action="/ddweb/j_spring_security_check" method="POST" role="form" name="f">
<input type="hidden" name="action" value="login">
<input type="hidden" name="hide" value="">
<table style="margin-top: 60px;margin-left: 5%;">
<tr><td style="text-align: center;height: 35px;"><%-- <img alt="Login Image" src="${pageContext.request.contextPath}/images/tradelab3a.gif" style="width: 60%"> --%></td></tr>
<tr><td><input type="text" name="j_username" placeholder="Email" style="max-height: 35px;  height: 35px;"></td></tr>
<tr><td style="text-align: center;height: 12px;"></td></tr>
<tr><td><input type="password" name="j_password" placeholder="Password" style="max-height: 35px;  height: 35px;"></td></tr>
<tr><td style="text-align: center;height: 12px;"></td></tr>
<tr><td><input class="btn btn-success" type="submit" value="Sign in"></td></tr>
<tr><td >&nbsp;</td></tr>
</table>
</form>
</div></div>
<%-- <div class="footer" style="background-color: black;height: 60px;width: 101.3%;margin-top: -8px;margin-left: -10px;position: fixed;bottom: 0;">
      <img alt="Login Image" src="${pageContext.request.contextPath}/images/Vista_Footer.gif" style="margin-top: 1%;width: 10%;margin-left: 10px;display: block;"> 
      <img alt="Login Image" src="${pageContext.request.contextPath}/images/Footer.gif" style="width:100%;height: 7%;">
</div> --%>
</body>
</html>