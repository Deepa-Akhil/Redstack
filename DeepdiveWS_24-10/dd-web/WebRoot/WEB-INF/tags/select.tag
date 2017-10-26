<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ attribute name="valuePath" required="true" type="java.lang.String" %>
<%@ attribute name="selectInputBean" required="true" type="com.enterprise.common.pojo.SelectInputBean" %>
<%@ attribute name="ajaxTargetElement" required="false" type="java.lang.String" %>
<%@ attribute name="ajaxTargetURL" required="false" type="java.lang.String" %>
<%@ attribute name="onchange" required="false" type="java.lang.String" %>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean" %>
<%@ attribute name="titleVar" required="false" type="java.lang.String" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>

<form:select id="${id}" path="${valuePath}" 
	cssClass="${cssClass} ${selectInputBean.valueElement.styleClass}"
	onchange="evalFunctions(\"${onchange}\");" disabled="${disabled}" title="${titleVar}">
	<c:forEach var="option" items="${selectInputBean.options}" varStatus="counter">
		<form:option value="${option.value}">${option.description}</form:option>
	</c:forEach>
</form:select>