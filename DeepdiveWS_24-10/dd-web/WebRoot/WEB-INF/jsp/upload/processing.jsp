<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<body>
		<div >
		<img alt="CSV" src="images/CSV_Icon.gif" style="width: 25px;display: inline-block;"/>
			<span><strong>${files}</strong> uploaded successfully.</span>
		</div>
		<div class="h20px text-e3" id="ship-map-stat" style="text-align: center;margin-top: 220px;background-color: #fff;">
			<img style="vertical-align:middle;display: inline-block;" alt="Processing..." src="${pageContext.request.contextPath}/images/wait24trans.gif" />
			<span >Publishing data to cloud servers...</span>
		</div> 
		<c:choose>
			<c:when test="${fileUploadSessionBean.hasUnknownHeadings}">
				<div class="h461px" style="margin-top:16px; overflow-y:auto;height: 405px;min-height: 400px;">
					<div class="h20px text-c3" style="background-color: #009900">
						<strong class="normal">Below are all the file heading columns. Please map the headings to the repository columns.</strong>
					</div>
					<img alt="Login Image" src="${pageContext.request.contextPath}/images/Band.gif" style="width: 99.99%;margin-top: -10px;">
					<%-- <div class="h46px" 
						style="margin-top:4px; margin-bottom:4px; 
								border-top:1px solid green; border-bottom:1px solid green;
								padding:0 0 0 10px;
								background-color:rgb(233,243,233);height: 60px;">
						<c:forEach var="infoMsg" items="${fileUploadSessionBean.infoMsgList}" varStatus="i">
							<div style="margin-top:5px; color:green;">${infoMsg}</div>
						</c:forEach>
					</div> --%>
					<form id="ajaxHeadingsForm" name="ajaxHeadingsForm">
						<div id="ajaxHeadingsDiv" class="w100"
							formName="ajaxHeadingsForm"
							method="post"
							targetId="ajaxHeadingsDiv">
							<jsp:include page="/WEB-INF/jsp/upload/headings.jsp" />
						</div>
					</form>
				</div>
				<div class="h24px text-align-center" style="margin-top:7px;">
					<input type="button" class="btn btn-primary" onclick="ajax('ajaxModalPageDiv','${fileUploadSessionBean.requestMapping}');" value="Submit" id="submit-butt"/>
				</div>
			</c:when>
			<c:otherwise>
				<div class="w100 h20px text-a2">
					<span id="textUpdatable">Processing file (Do not close window) ...</span>
				</div>
				<script>evalFunctions("${fileUploadSessionBean.javascript}", null);</script>
			</c:otherwise>
		</c:choose>
	</body>
	<script type="text/javascript" >
		$(document).ready(function() {
			$("#ship-map-stat").hide();
			$('#submit-butt').click(function(){
				$(".h461px").hide();
				$("#submit-butt").hide();
				$("#ship-map-stat").show();
			});
		});
	</script>
</html>