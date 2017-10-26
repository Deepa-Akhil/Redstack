<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

<%@ attribute name="urlPrefix" required="true" type="java.lang.String" %>
<%@ attribute name="ajaxTargetElement" required="true" type="java.lang.String" %>
<%@ attribute name="multiPageDataContainerBean" required="true" type="com.enterprise.common.pojo.MultiPageDataContainerBean" %>

<ul>
	<li>
		<div style="margin-right:4px;">
			<a onclick="ajax('${ajaxTargetElement}','${urlPrefix}/${multiPageDataContainerBean.first}');">
				<spring:message code="search.results.paging.first" />
			</a>
		</div>
	</li>
	<li>
		<div style="margin-right:6px;">
			<a onclick="ajax('${ajaxTargetElement}','${urlPrefix}/${multiPageDataContainerBean.prev}');">
				<spring:message code="search.results.paging.prev" />
			</a>
		</div>
	</li>
	<li>&nbsp;</li>
	<c:forEach var="page" items="${multiPageDataContainerBean.pages}" varStatus="pageCounter">
		<c:choose>
			<c:when test="${multiPageDataContainerBean.pageIndex eq page.page}">
				<li><a class="current" onclick="ajax('${ajaxTargetElement}','${urlPrefix}/${page.page}');">${page.page}</a></li>
			</c:when>
			<c:otherwise>
				<li><a onclick="ajax('${ajaxTargetElement}','${urlPrefix}/${page.page}');">${page.page}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<li>&nbsp;</li>
	<li>
		<div style="margin:0 4px 0 6px;">
			<a onclick="ajax('${ajaxTargetElement}','${urlPrefix}/${multiPageDataContainerBean.next}');">
				<spring:message code="search.results.paging.next" />
			</a>
		</div>
	</li>
	<li>
		<div style="margin-right:6px;">
			<a onclick="ajax('${ajaxTargetElement}','${urlPrefix}/${multiPageDataContainerBean.last}');">
				<spring:message code="search.results.paging.last" />
			</a>
		</div>
	</li>
</ul>