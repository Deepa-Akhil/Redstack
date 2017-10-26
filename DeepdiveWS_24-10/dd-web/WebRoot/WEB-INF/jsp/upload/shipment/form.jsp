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
	</style>
	</head>
	<body style="background-color: #F1F1F1;">
		<form:form 
			name="uploadForm"
			method="post" 
			action="save" 
	        enctype="multipart/form-data"
	        target="pageFrame"
	        onchange="uploadForm.submit();">
	        <!-- <input type="file" name="file" id="fileSelect"/> -->
	        <div class="fileUpload btn btn-default" id="fileSelect">
               <span>Upload</span>
               <input id="uploadBtn5" type="file" name="file" 
                   class="upload"/>
            </div>
	        <span>${error}</span>
	    </form:form>
	</body>
    <script language="javascript" src="${pageContext.request.contextPath}/javascript/jquery-2.2.4.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#fileSelect").change(function() {
				var table = parent.document.getElementById("row-2");
				table.style.display = 'inline';
				var table = parent.document.getElementById("row-1");
				table.style.display = 'none';
		    });
		});
	</script>
</html>