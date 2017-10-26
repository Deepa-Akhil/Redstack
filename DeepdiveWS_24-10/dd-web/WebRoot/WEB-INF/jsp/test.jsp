<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=7;IE=edge"/>
	<title>DEEPDIVE 1.02</title>
	<link media="all" rel="stylesheet" href="${pageContext.request.contextPath}/includes/light.css" type="text/css" />
	<link href="${pageContext.request.contextPath}/includes/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
		.fileUpload {
		    display: inline;
		    margin-right: 10px;
		    overflow: hidden;
		    position: relative;
		    padding: 2px 8px;
		}

    	.fileUpload input.upload {
	        position: absolute;
	        top: 0;
	        right: 0;
	        margin: 0;
	        padding: 0;
	        font-size: 20px;
	        cursor: pointer;
	        opacity: 0;
	        filter: alpha(opacity = 0);
	    }

	    .upload p {
	        line-height: 25px;
	        margin-bottom: 20px;
	    }
	    
	    td { text-align:left; font-family: verdana,arial; color: #064073; font-size: 0.9em; width: 200px;padding: 10px;}
	.input-stl { border: 1px solid #CCCCCC; border-radius: 5px; color: #666666; display: inline-block; font-size: 0.9em;  padding: 5px; width: 100%; height: 25px;}
	input[type="button"], input[type="reset"], input[type="submit"]{ height: auto; width: auto; cursor: pointer; background-color: #3366FF; color: white;width: 40%;float: right; text-align:right; margin-top: 10px; margin-left:7px;margin-right: 2%;text-align: center;height: 35px;}
	table.center { margin-left:auto; margin-right:auto; }
	.error { font-family: verdana,arial; color: #D41313; font-size: 0.9em; }
	.font-red {color:red;}
	</style>
</head>
<body>
<%-- <div class="footer" style="background-color: black;height: 70px;width: 101.3%;margin-top: -8px;margin-left: -10px;">
     <img alt="Login Image" src="${pageContext.request.contextPath}/images/logo_new.jpg" style="margin-top: 1.2%;">
</div> --%>
	<div style="text-align: center;">
	<div style="padding: 25px;margin-top: 5%;">
<form action="/ddweb/emailLoad" method="POST" role="form" name="f" enctype="multipart/form-data">
<c:if test="${not empty param.message}">
		<c:if test="${param.message eq 'success'}">
      		<span style="color:green;">Loaded Successfully</span>
   		 </c:if>
   		 <c:if test="${param.message ne 'success'}">
    		  <span class="font-red">Error while loading the file</span>
     	 </c:if>
    </c:if>

<input type="hidden" name="action" value="login">
<input type="hidden" name="hide" value="">
<table class='center' style="margin-top: 60px;">
<tr><td></td><td>
       <div class="fileUpload btn btn-default" id="fileSelect">
               <span>Upload</span>
               <input id="uploadBtn5" type="file" name="file" 
                   class="upload"/>
            </div>
</tr>
<tr><td >User ID : </td><td><input type="number" name="userId" placeholder="User ID" class="input-stl"></td></tr>
<tr><td >Package ID : </td><td><input type="number" name="packageId" placeholder="Package ID" class="input-stl"></td></tr>
<tr><td >Rows To Remove : </td><td><input type="number" name="rowsToRemove" placeholder="Rows To Remove" class="input-stl"></td></tr>
<tr><td></td><td><input type="submit" value="Submit"></td></tr>
<tr><td></td><td >&nbsp;</td></tr>
</table>
</form>
</div></div>
<div class="footer" style="background-color: black;height: 55px;width: 101.3%;margin-top: -8px;margin-left: -10px;position: fixed;bottom: 0;">
    <%--  <img alt="Login Image" src="${pageContext.request.contextPath}/images/logo_new.jpg" style="margin-top: 1%;"> --%>
</div>
</body>
</html>