<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/head.jsp" />
    <link href="includes/bootstrap.min.css" rel="stylesheet">
    <link href="includes/dd.css" rel="stylesheet">
    <script src="javascript/jquery.dd.js" type="text/javascript"></script>
    <style type="text/css">
    body {font:12px arial, sans-serif;}
    .dropdown-menu{font:12px arial, sans-serif;}
    #ajaxModalPageDiv{color: black;}
    div.text-a2{height: 30px}
    div.input-a2, div.text-a3, div.text-e3, div.text-e3{height: 30px;}
    div.text-c3{height: 24px;}
    .fontNew{font-size: 13px;}
	img {
		display: inline;
	}
    </style>
    <script type="text/javascript" src="http://analyticscloud.trade/javascripts/api/viz_v1.js"></script> 
</head>

<body style="background-color: #fafafa;margin-left: 80px;">
<div id="overlay_blackout" style="position:fixed; width:100%; height:100%; margin-left:0px; margin-top:0px; left:0px; top:0px; display:block; visibility:hidden;"></div>
		<div id="overlay_secondary" style="position:fixed; width:100%; min-height:520px;margin-left:0px; margin-top:0px; left:0px; top:0px; display:block; visibility:hidden; z-index:3000; background-color:#333;height: 100% !important;"></div>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: white;border-color: #f4f4f4;border-color: #f4f4f4;max-height: 52px;">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" style="width: 75%;">
                <li id="back-butt" style="display: none;">
               		<a href="#" onclick="hidePackageDetail();"> <img alt="Back" src="images/go_previous.png" style="width: 23px;margin-left: -75px;" data-toggle="tooltip" data-placement="right" title="Back"/></a>
                 </li>
                 <li>
                       <img alt="Login Image" src="${pageContext.request.contextPath}/images/Icon_TP.png" style="width:60%;">
                 </li> 
                 <c:forEach var="menu" items="${menus}" varStatus="i">
                  	<li>
                       <a href="/ddweb/client?menuID=${i.index}" >${menu.menuName}</a>
                    </li>
                 </c:forEach>
                   <!-- 
                    <li>
                        <a href="/ddweb/client?menuID=2" >MANAGE<b class="caret"></b></a>
                       </li>
                    <li>
                        <a href="#">OPTIMIZE</a>
                    </li> -->
                </ul>
                <ul class="nav navbar-nav navbar-center">
                  <li>
                
                        <a href="#" style="color: #3399CC"><img alt="Upload" src="images/header-icon3.jpg" style="width: 322px;height: 60px;display: inline-block;margin-top: -19px;margin-left: -250px;display: inline-block;"/></a>
                 </li>
                 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                 <li style="margin-top: -12px;">
                
                        <a href="/ddweb/j_spring_security_logout" style="color: #3399CC"><img alt="Upload" src="images/userImg.jpg" style="width: 36px;display: inline-block;"/>${userName} (Logout)</a>
                        <dd style="margin-top: -23px;padding-left: 55px;font-size: x-small;">${compName}</dd>
                 </li>
                 <!-- <li>
                        <a href="/ddweb/j_spring_security_logout">LOGOUT</a>
                    </li> -->
                </ul>
                
            </div>
        </div>
        <img alt="Upload" src="images/11.jpg" style="width: 1599px;height: 5px;margin-top: -19px;margin-left: -250px;margin-top: -51px;display: inline-block;"/>
    </nav>
    <br/><br/><br/><br/><br/>
<object class="tableauViz" width="1300" height="2000" margin-top="150px" padding-left="80px" style="display:none;"> 
    <param name="name" value="${name}" /> 
    <param name="ticket" value="${tokenID}" /> 
</object> 
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/jquery.dataTables.js"></script>
</body>
<script type="text/javascript">
$( document ).ready(function() {
	
	try {
		$("body select").msDropDown();
	} catch(e) {
		//alert(e.message);
	}
	$("#serverType_msdd").css("padding","0px 0px");
	$("#serverType_msdd").css("width","200px");
	$("#tableauPlaceholder").css("margin-top","80px");
	$("#tableauPlaceholder").css("margin-left","20px");
	$("#tableauPlaceholder").css("padding-left","80px");
});
</script>
</html>
