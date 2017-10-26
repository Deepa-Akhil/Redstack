<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=7;IE=edge"/>
		<link media="all" rel="stylesheet" href="${pageContext.request.contextPath}/includes/light.css" type="text/css" />
	</head>
	<body>
		<form:form 
			name="uploadForm"
			method="post" 
			action="save"
	        enctype="multipart/form-data"
	        target="pageFrame"
	        onchange="uploadForm.submit();">
	        <input type="file" name="file" />
	        <span>${error}</span>
	    </form:form>
	</body>
</html>