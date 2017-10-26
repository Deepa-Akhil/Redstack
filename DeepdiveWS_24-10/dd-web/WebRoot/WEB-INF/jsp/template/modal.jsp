<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<body>
		<div class="box">
			<div class="box-im">
				<div class="box-rb">&nbsp;</div>
				<div class="box-lb">&nbsp;</div>
				<div class="box-cc">
					<div class="box-tb">&nbsp;</div>
					<div class="box-dc">
						<%-- #################### START data contents ##################### --%>
						<div class="h24px">
							<div class="w10px header-a float-r" style="background-color: white;">
								<span><a class="white-link closeModal" onclick="javascript:closeDetail();" style="color: gray;">X</a>&nbsp;</span>
							</div>
							<%-- <div class="h20px header-a" style="background-color: #336699;">
								<span><strong>${modalTemplateBean.heading}</strong></span>
							</div> --%>
						</div>
						<!-- <div class="h6px overflow-hidden">&nbsp;</div> -->
						<div class="h6px" style="margin-top: -20px;" id="detail-head-div">
							<img alt="Upload" src="images/TP_Server.gif" style="width: 35px;display: inline-block;"/>
							<span style="font-size: 15px;font-weight: bold;margin-top: 1%;" id="pkg-name">${fileUploadSessionBean.pkgName}</span>
							<span style="opacity:0.7;margin-top: 1%;margin-bottom: 2%;margin-left: 35%;">Updated 
	            	<strong id="lastUpdateOn">
	            	 	<%-- <fmt:formatDate pattern="MMM d, yyyy" value="${pkg.lastUpdated}" /> --%>
	            	 	${fileUploadSessionBean.lastUpdateOn}
	            	</strong>
	            	 by <strong id="lastEditUser">${fileUploadSessionBean.lastEditUser}<img alt="Upload" src="images/User_Icon.gif" style="width: 20px;display: inline-block;"/></strong></span>
						</div>
						<br/><br/><br/>
						<div class="form-group" id="loadtype-div">
						<label for="userRole" class="col-sm-3 col-xs-3  control-label" style="margin-top: 4px;width: 21%;">Select Load Type</label>
						 <div class="col-sm-5 col-xs-8" style="width: 20%;">
							 <select class="form-control" id="loadType" name="loadType" style="padding: 1px 4px;" onchange="setLoadType();">
				        		<option value="1" >Shipment</option>
				        		<option value="2" >Rates</option>
				        		<option value="3" >Invoice</option>
				        		<option value="4" >Order</option>
				        		<option value="5" >Custom</option>
				        		<option value="6" >Container status</option>
				      		 </select>
						 </div>
						 
						 <label class="col-sm-3 col-xs-3  control-label" style="margin-top: 4px;width: 21%;">Enable Alias Matching</label>
						 <div class="col-sm-5 col-xs-8" style="width: 20%;">
							<input type="checkbox" id="aliase-map" onchange="setAliasMapping();"/>
						 </div>
						</div>
						<div class="form-group" id="conflict-merge-div" style="display: none;">
						 <label class="col-sm-3 col-xs-3  control-label" style="margin-top: 4px;width: 21%;">Process Date Conflicts</label>
						 <div class="col-sm-5 col-xs-8" style="width: 20%;">
							<input type="checkbox" id="conflict-merge" onchange="setConflictMerging();"/>
						 </div>
						 
						 <label class="col-sm-3 col-xs-3  control-label" style="margin-top: 4px;width: 21%;">Reset Rate Profile</label>
						 <div class="col-sm-5 col-xs-8" style="width: 20%;">
							<input type="checkbox" id="rate-reset" onchange="resetRateProfile();"/>
						 </div>
						 </div>
						<br/><br/>
						<div>
							<div id="ajaxModalPageDiv"
								method="post"
								targetId="ajaxModalPageDiv">
								<jsp:include page="${modalTemplateBean.dataIncludePage}" />
							</div>
						</div>	
						<!-- <div style="position:absolute; bottom:10px; height:52px; left:10px; right:10px; border-top:1px solid #ddd; padding-top:4px;">
							<span style="color:#bbb; line-height:1.5em;">
								For assistance please contact the In-Country Technical Department.<br/>
								Alternatively contact the DeepDive Development Department at +27 (0)12 684 4095<br/>
								You can also request service by completing our online form.
							</span>
						</div> -->
						<%-- #################### END data contents ##################### --%>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	$( document ).ready(function() {
		getLoadTypes();
	});
	function setLoadType(){
	 	$.ajax({
		    url: "/ddweb/setLoadType?loadType="+$("#loadType").val(), 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
		 });
	}
	
	function setAliasMapping() {
		var isChkd = $('#aliase-map').is(':checked');
	 	$.ajax({
		    url: "/ddweb/setAliasMapping?isChkd="+isChkd, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
		});
	}
	function setConflictMerging() {
		var isChkd = $('#conflict-merge').is(':checked');
	 	$.ajax({
		    url: "/ddweb/setConflictMerging?isChkd="+isChkd, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
		});
	}
	function getLoadTypes(){
		$.ajax({
		    url: "/ddweb/getLoadTypes", 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) {
    	    	var jsondata = JSON.parse(data);
    	    	var jsonarr = jsondata.loadType;
    	    	var isShipmentDone = false;var isRateDone = false;var isInvoiceDone = false;var isOrderDone = false;var isCustomDone = false;var isContainerStatus = false;
    	    	for(var i = 0; i < jsonarr.length; i++) {
    	    		var obj = jsonarr[i];
    	    		if(obj == 'Shipment'){
    	    			isShipmentDone = true;
    	    		} else if(obj == 'Rate'){
    	    			isRateDone = true;
    	    		} else if(obj == 'Order'){
    	    			isOrderDone = true;
    	    		} else if(obj == 'Invoice'){
    	    			isInvoiceDone = true;
    	    		} else if(obj == 'Custom'){
    	    			isCustomDone = true;
    	    		}else if(obj == 'Container status'){
    	    			isContainerStatus = true;
    	    		}
    	    	}
    	    	var subPkgID = jsondata.subPkgID;
    	    	if(subPkgID==0){
    	    		$('#loadtype-div').show();
        	    	if(isShipmentDone && !isRateDone){
    	    			setLoadTypeForExisting(2);
    	    		} else if(isShipmentDone && isRateDone && !isInvoiceDone){
    	    			setLoadTypeForExisting(3);
    	    		} else if(isShipmentDone && isOrderDone && isInvoiceDone && !isOrderDone){
    	    			setLoadTypeForExisting(4);
    	    		} else if(isShipmentDone && isOrderDone && isInvoiceDone && isOrderDone && !isCustomDone){
    	    			setLoadTypeForExisting(5);
    	    		} else if(isShipmentDone && isOrderDone && isInvoiceDone && isOrderDone && isCustomDone && !isContainerStatus){
    	    			setLoadTypeForExisting(6);
    	    		} else {
        	    		setLoadTypeForExisting(1);
    	    		}
        	    	
        	    	if(isShipmentDone){
        	    		$("#loadType option[value='1']").remove();
        	    	} 
        	    	if(isRateDone){
        	    		$("#loadType option[value='2']").remove();
        	    	} 
					if(isInvoiceDone){
        	    		$("#loadType option[value='3']").remove();
        	    	}
					if(isOrderDone){
        	    		$("#loadType option[value='4']").remove();
        	    	}
					if(isCustomDone){
        	    		$("#loadType option[value='5']").remove();
        	    	}
					if(isContainerStatus){
        	    		$("#loadType option[value='6']").remove();
        	    	}
    	    	} else {
    	    		$('#conflict-merge-div').hide();
    	    		var jsonCurrentLoadType = jsondata.currentLoadType;
        	    	if(jsonCurrentLoadType == 'Shipment'){
        	    		setLoadTypeForExisting(1);
    	    		} else if(jsonCurrentLoadType == 'Rate'){
    	    			$('#conflict-merge-div').show();
    	    			setLoadTypeForExisting(2);
    	    		} else if(jsonCurrentLoadType == 'Order'){
    	    			setLoadTypeForExisting(4);
    	    		} else if(jsonCurrentLoadType == 'Invoice'){
    	    			setLoadTypeForExisting(3);
    	    		} else if(jsonCurrentLoadType == 'Custom'){
    	    			setLoadTypeForExisting(5);
    	    		} else if(jsonCurrentLoadType == 'Container status'){
    	    			setLoadTypeForExisting(6);
    	    		}
    	    		$('#loadtype-div').hide();
    	    	}
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
		});
	}
	
	function setLoadTypeForExisting(loadTypeID){
	 	$.ajax({
		    url: "/ddweb/setLoadType?loadType="+loadTypeID, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
		 });
	}
	
	function resetRateProfile(){
		var isChkd = $('#rate-reset').is(':checked');
	 	$.ajax({
		    url: "/ddweb/resetRateProfile?isChkd="+isChkd, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
		});
	}
	</script>
</html>