<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<body>
		<table class="w100" id="w100w100">
			<tr id="row-1" >
				<td class="w170px">
					<div class="h20px text-a3">
						<span>Select the file to upload</span>
					</div>
				</td>
				<td>
					<div class="h20px text-e3" >
						<iframe 
							id="pageFrame" 
							name="pageFrame" 
							src="${modalTemplateBean.iframeSrc}" 
							width="600px" 
							height="25px" 
							frameborder="0" 
							scrolling="no"
							marginheight="0px"
							marginwidth="0px">
						</iframe>
					</div>
				</td>
			</tr>
			<tr id="row-2" >
			<td colspan="2" class="w170px" >
			<div class="h20px text-e3" style="text-align: center;margin-top: 220px;background-color: #fff;display: none;">
			<img style="vertical-align:middle;display: inline-block;" alt="Processing..." src="${pageContext.request.contextPath}/images/wait24trans.gif" />
			<span >Mapping Shipment File...</span>
			</div> 
			</td>
			</tr>
		</table>
	</body>
</html>