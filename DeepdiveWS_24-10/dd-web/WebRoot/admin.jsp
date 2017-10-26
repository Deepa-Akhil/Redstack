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
		<div class="h60px w100 bg-333333">
			<table>
				<tr>
					<td>
						<div style="padding:5px 0 0 10px;">
							<img src="${pageContext.request.contextPath}/images/logo.gif" class="blockdisplay" />
						</div>
					</td>
					<td>
						<div class="w100px">&nbsp;</div>
					</td>
					<td>
						<div style="padding:36px 20px 0 0;">
							<strong class="font-white">STATUS</strong>
						</div>
					</td>
					<td>
						<div style="padding:36px 20px 0 0;">
							<strong class="font-white">LOAD</strong>
						</div>
					</td>
					<td>
						<div style="padding:36px 20px 0 0;">
							<strong class="font-white">ANALYZE</strong>
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
		<div class="h6px w100 green">&nbsp;</div>
		<div class="w100" style="border-bottom: 1px solid #ddd;">
			<table class="w100">
				<tr>
					<td style="width:1018px">
						<div >
							<img src="${pageContext.request.contextPath}/images/map.gif" class="blockdisplay" />
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
		<div style="padding:10px;">
			<span>
				<a class="default" onclick="javascript:showDetail('600','658','/upload/city','modalId','overlay_secondary','4000');">Load City Data</a>
			</span>
		</div>
		<div style="padding:10px;">
			<span>
				<a class="default" onclick="javascript:showDetail('600','658','/upload/airport','modalId','overlay_secondary','4000');">Load Airport Data</a>
			</span>
		</div>
		<div style="padding:10px;">
			<span>
				<a class="default" onclick="javascript:showDetail('600','658','/upload/seaport','modalId','overlay_secondary','4000');">Load Seaport Data</a>
			</span>
		</div>
		<div style="padding:10px;">
			<span>
				<a class="default" onclick="javascript:showDetail('600','658','/upload/aircarrier','modalId','overlay_secondary','4000');">Load Air Carrier Data</a>
			</span>
		</div>
		<div style="padding:10px;">
			<span>
				<a class="default" onclick="javascript:showDetail('600','658','/upload/seacarrier','modalId','overlay_secondary','4000');">Load Sea Carrier Data</a>
			</span>
		</div>
		<div id="bottom" class="center" style="padding-top:2px;">
			<span class="font-white">&copy; 2011-2014 Reynolds, Fourie & Associates</span>
		</div>
	</body>
</html>