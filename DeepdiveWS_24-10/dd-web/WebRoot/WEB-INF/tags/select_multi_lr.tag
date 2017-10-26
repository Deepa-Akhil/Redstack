<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ attribute name="availableOptionsElementId" required="true" type="java.lang.String" %>
<%@ attribute name="availableOptionsValuePath" required="true" type="java.lang.String" %>
<%@ attribute name="runtimeMultiInputBean" required="true" type="com.mmi.common.pojo.SelectMultiInputBean" %>
<%@ attribute name="moveActionsAjaxTarget" required="true" type="java.lang.String" %>
<%@ attribute name="moveAvailableUrlPath" required="true" type="java.lang.String" %>
<%@ attribute name="moveSelectedUrlPath" required="true" type="java.lang.String" %>
<%@ attribute name="selectedOptionsElementId" required="true" type="java.lang.String" %>
<%@ attribute name="selectedOptionsValuePath" required="true" type="java.lang.String" %>
<%@ attribute name="titleVar" required="false" type="java.lang.String" %>

<table class="w660px">
	<tr>
		<td class="w280px">
			<div>
				<form:select id="${availableOptionsElementId}" 
					path="${availableOptionsValuePath}" 
					multiple="true" size="5" class="w280px ${runtimeMultiInputBean.styleClass}" 
					onfocus="resetMultiSelect('${selectedOptionsElementId}');enableElement('moveAvailable_${selectedOptionsElementId}');disableElement('moveSelected_${selectedOptionsElementId}');">
					<c:forEach var="option" items="${runtimeMultiInputBean.availableOptions}" varStatus="counter">
						<form:option value="${option.value}">${option.description}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</td>
		<td class="w100px">
			<div>
				<table class="w100">
					<tr>
						<td class="text-align-center">
							<input type="button" id="moveAvailable_${selectedOptionsElementId}" value="&gt;&gt;" onclick="ajax('${moveActionsAjaxTarget}','${moveAvailableUrlPath}');" />
						</td>
					</tr>
					<tr>
						<td class="text-align-center">
							<input type="button" id="moveSelected_${selectedOptionsElementId}" value="&lt;&lt;" onclick="ajax('${moveActionsAjaxTarget}','${moveSelectedUrlPath}');" />
						</td>
					</tr>
				</table>
			</div>
		</td>
		<td class="w280px">
			<div>
				<form:select id="${selectedOptionsElementId}" 
					path="${selectedOptionsValuePath}" title="${titleVar}"
					multiple="true" size="5" class="w280px ${runtimeMultiInputBean.styleClass} ${runtimeMultiInputBean.styleClassAlt}" 
					onfocus="resetMultiSelect('${availableOptionsElementId}');enableElement('moveSelected_${selectedOptionsElementId}');disableElement('moveAvailable_${selectedOptionsElementId}');">
					<c:forEach var="option" items="${runtimeMultiInputBean.selectedOptions}" varStatus="counter">
						<form:option value="${option.value}">${option.description}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</td>
	</tr>
</table>