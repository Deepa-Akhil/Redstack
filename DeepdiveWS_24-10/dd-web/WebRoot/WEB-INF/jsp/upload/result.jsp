<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<body>
		<div>
		<img alt="CSV" src="images/CSV_Icon.gif" style="width: 25px;display: inline-block;"/>
			<span><strong>${files}</strong> uploaded successfully.</span>
		</div>
		<c:choose>
			<c:when test="${fileUploadSessionBean.hasUnknownHeadings}">
				<div class="w100" style="margin-top:20px;">
					<div class="w100 h20px text-c3">
						<strong class="normal">Some column headers are unknown. Please map the headings below.</strong>
					</div>
					<form id="ajaxHeadingsForm" name="ajaxHeadingsForm">
						<div id="ajaxHeadingsDiv" class="w100"
							formName="ajaxHeadingsForm"
							method="post"
							targetId="ajaxHeadingsDiv">
							<jsp:include page="/WEB-INF/jsp/upload/headings.jsp" />
						</div>
					</form>
				</div>
			</c:when>
			<c:otherwise>
			<div class="h46px" 
						style="margin-top:4px; margin-bottom:4px; 
								border-top:1px solid green; border-bottom:1px solid green;
								padding:0 0 0 10px;
								background-color:rgb(233,243,233);height: 63px;">
						<c:forEach var="infoMsg" items="${fileUploadSessionBean.infoMsgList}" varStatus="i">
							<div style="margin-top:5px; color:green;">${infoMsg}</div>
						</c:forEach>
			</div>
				<c:choose>
					<c:when test="${fileUploadSessionBean.hasErrors}">
						<div class="w100 h20px text-a">
							<span class="font-red"><strong>File processing failed:</strong></span>
						</div>
						<div class="w100 h478px" style="overflow-x:hidden;overflow-y:auto;">
							<c:forEach var="error" items="${fileUploadSessionBean.errorList}" varStatus="counter">
								<div class="w100 h20px text-a">
									<span class="font-red">${error}</span>
								</div>
							</c:forEach>
						</div>
					</c:when>
					<c:when test="${fileUploadSessionBean.hasWarnings}">
						<div class="w100 h20px text-a">
							<span class="font-green"><strong>File processed successfully, but with warnings:</strong></span>
						</div>
						<div class="w100 h478px" style="overflow-x:hidden;overflow-y:auto;">
							<c:forEach var="warning" items="${fileUploadSessionBean.warningList}" varStatus="counter">
								<div class="w100 h20px text-a">
									<span class="font-orange">${warning}</span>
								</div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="w100 h20px text-a">
							<span class="font-green"><strong>File processed successfully.</strong></span>
						</div>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</body>
</html>