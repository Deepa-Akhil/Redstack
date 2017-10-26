<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<body>
		<div>
			<table class="w100">
				<c:forEach 
					var="record" 
					items="${fileUploadSessionBean.mappedHeadingList}" 
					varStatus="recordCounter">
					<tr>
						<td class="w50">
							<div class="h20px text-a3" id="combo-lbl1-${recordCounter.index}" style="background-color: #f9f9f9;">
								<span>${record.formattedLabel}</span>
								<img id="img-lbl-${recordCounter.index}" alt="Share" src="images/Connection.gif" style="width: 40px;margin-left: 90%;margin-top: -28px;display: none;" data-toggle="tooltip" data-placement="right" title="Share"/>
							</div>
						</td>
						<td class="w50">
							<div class="h22px input-a2" id="combo-lbl2-${recordCounter.index}" style="background-color: #f9f9f9;">
								<t:select id="sel-${recordCounter.index}" valuePath="fileUploadSessionBean.mappedHeadingList[${recordCounter.index}].selectInputBean.valueElement.value" 
									selectInputBean="${record.selectInputBean}" 
									onchange="ajax('ajaxHeadingsDiv','/upload/headings/select.onchange');"
									cssClass="w275px fontNew" />
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
	<script type="text/javascript">
		$( document ).ready(function() {
			var a1 =  document.getElementsByTagName('select');
			for(var i=0; i<a1.length; i++) {
			  if($("#sel-"+i).val() != ""){
				var table = document.getElementById("img-lbl-"+i);
				if(table!=null)
					table.style.display = 'inline';
				var combo1 = document.getElementById("combo-lbl1-"+i);
				var combo2 = document.getElementById("combo-lbl2-"+i);
				if(combo1!=null)
					combo1.style.backgroundColor = '#e9f3e9';
				if(combo2!=null)
					combo2.style.backgroundColor = '#e9f3e9';
			  }
			}
		});
		
	</script>
</html>