<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/WEB-INF/jsp/head.jsp" />
	</head>
	<body>
		<div id="overlay_blackout" style="position:absolute; width:100%; height:100%; margin-left:0px; margin-top:0px; left:0px; top:0px; display:block; visibility:hidden;"></div>
		<div id="overlay_secondary" style="position:absolute; width:100%; height:100%; margin-left:0px; margin-top:0px; left:0px; top:0px; display:block; visibility:hidden; z-index:3000; background-color:#333;"></div>
		<div class="h30px w100 bg-333333">
			<table>
				<tr>
					<td>
						<div class="w100px">&nbsp;</div>
					</td>
					<td>
						<div style="padding:5px 0 0 0;">
							<ul id="sddm">
							    <li>
							    	<a href="#" onmouseout="mclosetime()" onclick="#">
							    		<span>STATUS</span>
							    	</a>
							    </li>
							</ul>
						</div>
					</td>
					<td>
						<div style="padding:5px 0 0 0;">
							<ul id="sddm">
							    <li>
							    	<a href="#" onmouseover="mopen('load')" onmouseout="mclosetime()">
							    		<span>LOAD</span>
							    	</a>
							    	<div id="load" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">		    	
							        	<a onclick="javascript:showDetail('800','658','/upload/shipment','modalId','overlay_secondary','4000');">SHIPMENTS</a>
							        	<a onclick="#">RATES</a>
							        	<a onclick="javascript:showDetail('800','658','/upload/invoice','modalId','overlay_secondary','4000');">INVOICES</a>
							        </div>
							    </li>
							</ul>
							<div style="clear:both;"></div>
						</div>
					</td>
					<td>
						<div style="padding:5px 0 0 0;">
							<ul id="sddm">
							    <li>
							    	<a href="#" onmouseout="mclosetime()" onclick="ajax('ajaxIndexContentDiv','/tableau');">
							    		<span>ANALYZE</span>
							    	</a>
							    </li>
							</ul>
						</div>
					</td>
					<td>
						<div id="ajaxAsyncDiv"
							class="h0px w0px overflow-hidden"
							method="post"
							formName="${asyncSessionObject.formName}"
							targetId="ajaxAsyncDiv">
							<c:import url="/ajax/async" />	
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="h10px overflow-hidden">&nbsp;</div>
		<div style="position:absolute; top:0px; left:25px;">
			<img src="${pageContext.request.contextPath}/images/dd_logo_small.gif" class="blockdisplay" />
		</div>
		<div class="w100"
			id="ajaxIndexContentDiv"
			method="post"
			targetId="ajaxIndexContentDiv"> 
			<c:import url="/load" />
		</div>
	</body>
</html>