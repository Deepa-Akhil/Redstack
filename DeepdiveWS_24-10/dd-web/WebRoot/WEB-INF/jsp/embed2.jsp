<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing Trusted Authentication</title>
<script type="text/javascript" src="http://analyticscloud.trade/javascripts/api/viz_v1.js"></script> 
</head>
<body>
<object class="tableauViz" width="1200" height="2000" style="display:none;"> 
    <param name="name" value="${name}" /> 
    <param name="ticket" value="${tokenID}" /> 
</object> 
<%-- <iframe src="http://5.79.21.84/trusted/${tokenID}/views/RedwoodDataLoadQuality/LoadSummary?:embed=yes" width="800" height="600"></iframe> --%> 
</body>
</html>