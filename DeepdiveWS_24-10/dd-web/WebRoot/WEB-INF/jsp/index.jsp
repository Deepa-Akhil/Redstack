<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/head.jsp" />
    <link href="includes/bootstrap.min.css" rel="stylesheet">
    <link href="includes/dd.css" rel="stylesheet">
    <script src="javascript/jquery.dd.js" type="text/javascript"></script>
    <style type="text/css">
    body {font:12px arial, sans-serif;}
    .dropdown-menu{font:12px arial, sans-serif;}
    #ajaxModalPageDiv{color: black;}
    div.text-a2{height: 30px}
    div.input-a2, div.text-a3, div.text-e3, div.text-e3{height: 30px;}
    div.text-c3{height: 24px;}
    .fontNew{font-size: 13px;}
	img {
		display: inline;
	}
    </style>
</head>

<body>
<div id="overlay_blackout" style="position:fixed; width:100%; height:100%; margin-left:0px; margin-top:0px; left:0px; top:0px; display:block; visibility:hidden;"></div>
		<div id="overlay_secondary" style="position:fixed; width:100%; min-height:520px;margin-left:0px; margin-top:0px; left:0px; top:0px; display:block; visibility:hidden; z-index:3000; background-color:#333;height: 100% !important;"></div>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #f4f4f4;border-color: #f4f4f4;">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" style="width: 75%;">
                <li id="back-butt" style="display: none;">
               		<a href="#" onclick="hidePackageDetail();"> <img alt="Back" src="images/go_previous.png" style="width: 23px;margin-left: -75px;" data-toggle="tooltip" data-placement="right" title="Back"/></a>
                 </li>
                 <li>
                       <img alt="Login Image" src="${pageContext.request.contextPath}/images/Icon_TP.png" style="width:60%;">
                 </li> 
                    <li>
                       <a href="/ddweb/index" >SERVERS</a>
                    </li>
                    <li>
                        <a href="http://5.79.21.84/" >ANALYZE<!-- <b class="caret"></b> --></a>
                       <!--  <ul class="dropdown-menu">
                            <li>
                                <a onclick="javascript:showDetail('800','658','/upload/shipment','modalId','overlay_secondary','4000');savePackageIntoBean(8);"><i class="fa fa-list fa-fw"></i>SHIPMENT</a>
                            </li>
                            <li>
                                <a href="#"></i>RATES</a>
                            </li>
                            <li>
                                <a href="javascript:showDetail('800','658','/upload/invoice','modalId','overlay_secondary','4000');"><i class="fa fa-desktop fa-fw"></i>INVOICES</a>
                            </li>
                        </ul> -->
                    </li>
                    <li>
                        <a href="#">PROFILE</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                 <li>
                
                        <a href="#" style="color: #3399CC"><img alt="Upload" src="images/User_Icon.gif" style="width: 25px;display: inline-block;"/>${userName}</a>
                 </li>
                 <li>
                        <a href="/ddweb/j_spring_security_logout">LOGOUT</a>
                    </li>
                </ul>
                
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

<div class="container">
	<div class="row" id="main-table">
	 <div class="col-lg-12">
	 <!--  <select class="form-control" class="pull-left" id="loadType" name="loadType" style="margin-top: 12%;margin-left: 8%;width: 20%;">
       		<option value="1" >Shipment</option>
       		<option value="2" >Rates</option>
       		<option value="3" >Invoice</option>
		</select>
	 	 <button data-target="#packageModal" data-toggle="modal" class="btn btn-primary pull-left"  style="margin-top: 12%;margin-left: 8%;" onclick="loadPopupText();">Create Instance</button> -->
	 	 
	 	 <div class="form-group" style="margin-top: 10%;">
						 <div class="col-sm-5 col-xs-8" style="width: 18%;margin-left: 6%;margin-top: -14px;">
						 		<span style="opacity:0.7;margin-left: 3%;font-size: 10px;">Connected to..</span><br/>
						 		<!-- <strong style="margin-left: 3%;font-size: 10px;">Tradelab Cloud Server</strong> -->
							 <select class="form-control icon-menu" id="serverType" name="serverType" style="padding: 1px 4px;">
				        		<option value="LON" data-image="images/map_icon.png">REDWOOD CLOUD (LON)</option>
				      		 </select>
						 </div>
						 
						 <div class="col-sm-5 col-xs-8" style="width: 20%;margin-left: 10px;">
							<button data-target="#packageModal" data-toggle="modal" class="btn btn-primary pull-left"  onclick="loadPopupText();" style="padding: 3px 16px;height: 29px;background-color: #005d9b;  border-color: #005d9b;">Create Instance</button>
						 </div>
						</div>
	 </div>
		<table class="table table-condensed table-striped table-hover no-margin  button-section" style="margin-top: 14%;margin-left:8%; width: 80%;text-align: center;" id="package-table">
          <!--  <thead>
           		<tr> <th></th><th></th> <th></th> <th></th> <th></th>  </tr>
           </thead> -->
            <tbody>
            	<c:forEach var="pkg" items="${packages}" varStatus="i">
            	<tr style="border-bottom: 1px solid #ddd;" id="package-${pkg.id}">
	            	<td style="width: 5%;vertical-align: middle;">
	            		<a href="#"> <img alt="Upload" src="images/TP_Server.gif" style="width: 100%;"/></a>
	            	</td>
	            	<td style="width: 50%;">
	            	<span style="font-size: 15px;font-weight: bold;display:block;margin-top: 1%;">${pkg.name}</span>
	            	<span style="display: block;opacity:0.7;margin-top: 1%;margin-bottom: 2%;" > Updated
	            	<strong>
	            	 	<fmt:formatDate pattern="MMM d, yyyy" value="${pkg.lastUpdated}" />
	            	 	<%-- <fmt:formatDate pattern="MMM d, yyyy" value="${now}" /> --%>
	            	</strong>
	            	 by <strong>${pkg.lastEditedUser.firstName} ${pkg.lastEditedUser.lastName}<img alt="Upload" src="images/User_Icon.gif" style="width: 20px;display: inline-block;"/></strong></span>
	            	</td>
	            	<td style="width: 2%;vertical-align: middle;">
	            		<a href="javascript:loadPackageDetail(${pkg.id});" > <img alt="Upload" src="images/TP_Update.gif" style="width: 25px;" data-toggle="tooltip" data-placement="right" title="Update"/></a>
	            	</td>
	            	<td style="width: 8%;vertical-align: middle;">
	            		<span style="opacity:0.8;">Update</span>
	            	</td>
	            	<td style="width: 2%;vertical-align: middle;">
	            		<a href="#" onclick="loadUpdatePackageModal(${pkg.id}, '${pkg.name}', '${pkg.description}');"> <img alt="Edit" src="images/TP_Edit.gif" style="width: 20px;" data-toggle="tooltip" data-placement="right" title="Edit"/></a>
	            	</td>
	            	<td style="width: 2%;vertical-align: middle;">
	            		<a href="#" onclick="deletePackage(${pkg.id});"> <img alt="Archive" src="images/TP_Archive.gif" style="width: 20px;" data-toggle="tooltip" data-placement="right" title="Archive"/></a>
	            	</td>
	            	<td style="width: 2%;vertical-align: middle;">
	            		<a href="#" onclick="loadHistoryModal(${pkg.id});"> <img alt="History" src="images/History.png" style="width: 20px;" data-toggle="tooltip" data-placement="right" title="Load History"/></a>
	            	</td>
	            	<td style="width: 2%;vertical-align: middle;">
	            		<a href="#"> <img alt="Share" src="images/TP_Share.gif" style="width: 28px;" data-toggle="tooltip" data-placement="right" title="Share"/></a>
	            	</td>
	            	<td style="vertical-align: middle;">
	            		<span style="opacity : .6;">Share</span>
	            	</td>
            	</tr>
            	</c:forEach>
            </tbody>
        </table>
						<div id="ajaxAsyncDiv"
							class="h0px w0px overflow-hidden"
							method="post"
							formName="${asyncSessionObject.formName}"
							targetId="ajaxAsyncDiv">
							<c:import url="/ajax/async" />	
						</div>
	</div>
	 <div class="modal-dialog" id="package-details" style="min-height: 490px;margin-top: 5%;display: none;">
            <div style="width: 150%;margin-left: -30%;">
		       <div class="modal-header" style="border-bottom: none;">
		        <div class="h6px" style="height: 25px;">
					<img alt="Upload" src="images/TP_Server.gif" style="width: 35px;display: inline-block;">
					<span style="font-size: 15px;font-weight: bold;margin-top: 1%;" id="pkg-name"></span>
				</div>
		      </div>
                <div class="modal-body clearfix">
                 <div class="form-group" >
						 <div class="col-sm-5 col-xs-8" style="width: 20%;margin-left: 10px;">
							<button class="btn btn-primary pull-left" style="padding: 3px 16px;height: 29px;background-color: #286090; border-color: #204d74" onclick="savePackageIntoBean(0);showDetail('800','600','/upload/shipment','modalId','overlay_secondary','4000');">Create new map</button>
						 </div>
						</div>
	 			</div>
		            <table class="table table-bordered table-hover table-striped" id="table-package-detail" style="margin-left: 20px;width: 95%;">
		                <thead>
		                    <tr>
		                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">Connection Type</th>
		                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">Created</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Schedule</th>
		                    <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3 " bgcolor="#D1D1D1" style="text-align : center;">Load Status</th>
		                    <th class="col-lg-2 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Last Upadated</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Alias Settings</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Email Set up</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Delete</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    
		                </tbody>
		                
		            </table>
                </div>
     </div>
</div>
<%-- <div class="modal fade" id="historyModal">
  <div class="modal-dialog">
    <div class="modal-content">
    <form   name="packageForm" id="packageForm"
			method="post" 
			action="/ddweb/upload/shipment/savePackage" 
	        enctype="form-data">
      <div class="modal-header" style="border-bottom: none;">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
       <div class="modal-body clearfix" style="padding: 35px;">
       		  <input type="hidden" name="id" value="0" id="txt-id"/>
            <input type="text" placeholder="Title" class="form-control " style="width: 65%;max-height: 40px;height: 35px;" name="name" id="txt-name" data-original-title="TTT" onkeyup="removeBorder('txt-name');">
           <br/>
            <textarea class="form-control" placeholder="Description" style="height:80px;" name="description" id="ta-description" onkeyup="removeBorder('ta-description');"></textarea>
        </div>
        <div class="modal-footer">
	        <button type="submit" class="btn btn-primary" id="save-pack" >Print</button>
      	</div>
      	</form>
    </div>
   
  </div>
</div> --%>

<div class="modal fade photoTag" id="historyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 155%;margin-left: -25%;">
		               <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="package-name"></h4>
		      </div>
                <div class="modal-body clearfix">
                <br/>
		            <table class="table table-bordered table-hover table-striped" id="table-history">
		                <thead>
		                    <tr>
		                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">Date</th>
		                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">Load Type</th>
		                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">User</th>
		                    <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">File Name</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Total</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Successfull</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Failed</th>
		                    <th class="col-lg-1 col-md-1 col-sm-1 col-xs-1 " bgcolor="#D1D1D1" style="text-align : center;">Success %</th>
		                    <th class="col-lg-1 col-md-2 col-sm-2 col-xs-2 " bgcolor="#D1D1D1" style="text-align : center;">Web/Email</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    
		                </tbody>
		                
		            </table>
                    <div style="text-align:right;margin-top:10px;">
        			   <button type="button" class="btn btn-primary" id="btn-print">Print</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
 <div class="modal fade" id="packageModal">
  <div class="modal-dialog">
    <div class="modal-content">
    <form   name="packageForm" id="packageForm"
			method="post" 
			action="/ddweb/upload/shipment/savePackage" 
	        enctype="form-data">
      <div class="modal-header" style="border-bottom: none;">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="package-head"></h4>
      </div>
       <div class="modal-body" style="padding: 35px;">
       		  <input type="hidden" name="id" value="0" id="txt-id"/>
            <input type="text" placeholder="Title" class="form-control " style="width: 65%;max-height: 40px;height: 35px;" name="name" id="txt-name" data-original-title="TTT" onkeyup="removeBorder('txt-name');">
           <br/>
            <textarea class="form-control" placeholder="Description" style="height:80px;" name="description" id="ta-description" onkeyup="removeBorder('ta-description');"></textarea>
        </div>
        <div class="modal-footer">
	        <button type="button" class="btn btn-default" id="btn-clear">Clear</button>
	        <button type="submit" class="btn btn-primary" id="save-pack" ></button>
      	</div>
      	</form>
    </div>
   
  </div>
</div>
<div class="modal fade" id="deleteModal">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header" style="border-bottom: none;;">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Are you sure to Archive this data?</h4>
        	<span style="opacity : .6;">The data will be taken offline and reconnected on demand.</span>
      </div>
			<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button type="button" class="btn btn-primary" id="deleteCnfd">Yes</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="delete-subpkg-mod">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header" style="border-bottom: none;;">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Are you sure to Delete this mapping?</h4>
      </div>
			<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button type="button" class="btn btn-primary" id="delete-subpkg-Cnfd">Yes</button>
      </div>
    </div>
  </div>
</div>
</div>
 <%-- <div class="footer" style="background-color: black;height: 50px;">
      <img alt="Login Image" src="${pageContext.request.contextPath}/images/Vista_Footer.gif" style="margin-top: 1%;width:10%;display: block;">
      <img alt="Login Image" src="${pageContext.request.contextPath}/images/Footer.gif" style="width:100%;height: 7%;">
 </div> --%>
    <!-- Bootstrap Core JavaScript -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/jquery.dataTables.js"></script>
</body>
<script type="text/javascript">
var idGlob;
$( document ).ready(function() {
	$("[data-toggle=tooltip]").tooltip();
	$("#btn-clear").click(function (){
		$('#txt-name').val('');
	    $("#ta-description").val('');
	});
	$('#packageForm').submit(function() {
		var name = $("#txt-name").val();
 		var desc = $("#ta-description").val();
 		if (!checkElem("txt-name" , name, "Name", 3)) {
	    	 return false;
	    }
 		if (!checkElem("ta-description" , desc, "Description", 8)) {
	    	 return false;
	    }
		return true;
	});
	
	$('#deleteCnfd').click(function() {
    	$('#deleteModal').modal('hide');
    	movePackageArcheived(idGlob);
    });
	
	$('#delete-subpkg-Cnfd').click(function() {
    	$('#delete-subpkg-mod').modal('hide');
    	deleteSubPkg(idGlob);
    });
	
	try {
		$("body select").msDropDown();
	} catch(e) {
		//alert(e.message);
	}
	$("#serverType_msdd").css("padding","0px 0px");
	$("#serverType_msdd").css("width","200px");
});
function savePackageIntoBean(id){
 	$.ajax({
		    url: "/ddweb/packageUpload?subPackageId="+id, 
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

function movePackageArcheived(id){
 	$.ajax({
		    url: "/ddweb/movePackageArcheived?packageId="+id, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    	$('#package-'+idGlob).remove();
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
	  });
}

function deleteSubPkg(id){
 	$.ajax({
		    url: "/ddweb/deleteSubPkg?subPkgId="+id, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) {
    	    	$('#subpkg-'+id).remove();
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
	  });
}

function getLoadHistory(id){
 	$.ajax({
		    url: "/ddweb/getLoadHistory?packageId="+id, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    	var jsondata = JSON.parse(data);
    	    	var tabVal = "";
    	    	var packageName = " ";
    	    	for(var i = 0; i < jsondata.length; i++) {
    	    	    var obj = jsondata[i];
    	    	    tabVal = tabVal+'<tr><td >   '+obj.date+'  </td><td >  '+obj.type+'   </td><td >  '+obj.user+'   </td><td style="text-align: left;">   '+obj.fileName+'  </td><td style="text-align: right;">  '+obj.tlShp+'   </td><td style="text-align: right;">   '+obj.scShp+'  </td><td style="text-align: right;">  '+obj.fldShp+'   </td><td style="text-align: right;">   '+obj.percent+'  </td><td style="text-align: left;">   '+obj.loadOn+'  </td></tr>';
    	    	    packageName = obj.pkg;
    	    	} 
    	    	$("#package-name").html(packageName + " Load History");
    	    	$("#table-history tbody").html(tabVal);
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
	  });
}
function loadPopupText(){
	$('#package-head').html("New Package");
	$('#save-pack').html("Save");
	$('#txt-name').val('');
    $("#ta-description").val('');
    document.getElementById("txt-name").style["background-color"] = "";
    document.getElementById("ta-description").style["background-color"] = "";
}

function loadUpdatePackageModal(id) {
	$('#packageModal').modal('show');
	$('#package-head').html("Update Package");
	$('#save-pack').html("Update");
	$('#txt-name').val(name);
	$('#txt-id').val(id);
	$('#ta-description').val(description);
	document.getElementById("txt-name").style["background-color"] = "aliceblue";
	document.getElementById("ta-description").style["background-color"] = "aliceblue";
}
function removeBorder(id){
	$("#"+id).css( "border-color", "" );
	$("#"+id).tooltip('hide')
   	     .attr('data-original-title', "")
   	     .tooltip('fixTitle')
   	     .tooltip('show');
}
function checkElem(id , val , field, len){
	if (val==null || val=="") {
	   	 document.getElementById(id).focus();
	   	 $("#"+id).tooltip('hide')
	          .attr('data-original-title', "Please Enter "+field)
	          .tooltip('fixTitle')
	          .tooltip('show');
	   	 $("#"+id).css( "border-color", "red" );
	   	 return false;
	}
	if(val.length < len) {
		document.getElementById(id).focus();
	   	 $("#"+id).tooltip('hide')
	          .attr('data-original-title', "Please Enter a valid "+field)
	          .tooltip('fixTitle')
	          .tooltip('show');
	   	 $("#"+id).css( "border-color", "red" );
	   	 return false;
	}
    $("#"+id).tooltip('hide')
  	     .attr('data-original-title', "")
  	     .tooltip('fixTitle')
  	     .tooltip('show');
   	$("#"+id).css( "border-color", "" );
	return true;
}
function deletePackage(id){
	idGlob = id;
	$('#deleteModal').modal('show');
}

function deleteSubPackage(id){
	idGlob = id;
	$('#delete-subpkg-mod').modal('show');
}

function loadHistoryModal(id) {
	getLoadHistory(id);
	$('#historyModal').modal('show');
}

function loadPackageDetail(id) {
	setPackageID(id);
	$('#main-table').fadeOut();
	loadPackageDetailModal(id);
}

function hidePackageDetail() {
	$('#main-table').fadeIn();
	$('#package-details').fadeOut();
	$('#back-butt').fadeOut();
}

function loadPackageDetailModal(id){
 	$.ajax({
		    url: "/ddweb/getSubPackageDetails?packageId="+id, 
    	    type: 'POST', 
    	    dataType: 'html', 
    	    contentType: "application/json;",
    	    data: "", processData:false,
    	    success: function(data) { 
    	    	var jsondata = JSON.parse(data);
    	    	var tabVal = "";
    	    	var jsonarr = jsondata.detailArray;
    	    	for(var i = 0; i < jsonarr.length; i++) {
    	    	    var obj = jsonarr[i];
    	    	    tabVal = tabVal+'<tr id="subpkg-'+obj.id+'"><td > <a href="javascript:savePackageIntoBean('+obj.id+');showDetail(\'800\',\'600\',\'/upload/shipment\',\'modalId\',\'overlay_secondary\',\'4000\');"> '+obj.type+' </a></td><td > '+obj.createdDate+' </td><td >'+obj.schedule+'</td><td >'+obj.loadStatus+'</td><td >'+obj.updatedDate+'</td><td > <a href="#" > Edit </a></td><td > <a href="#" > Edit </a></td><td style="text-align: center;"> <a href="#" > <img alt="Archive" src="images/remove.png" style="width: 15px;" data-toggle="tooltip" data-placement="right" title="Delete" onclick="deleteSubPackage('+obj.id+');"/></a> </td></tr>';
    	    	}
    	    	$("#pkg-name").html(jsondata.packageName+" Details");
    	    	$("#table-package-detail tbody").html(tabVal);
    	    	$('#package-details').fadeIn();
    	    	$('#back-butt').fadeIn();
    	    },
    	    error:function(data,status,er) { 
    	        alert("error: "+data+" status: "+status+" er:"+er);
    	    }
	  });
}

function setPackageID(id) {
	$.ajax({
	    url: "/ddweb/setPackageID?packageId="+id, 
	    type: 'POST', 
	    dataType: 'html', 
	    contentType: "application/json;",
	    data: "", processData:false,
	    success: function(data) { 
	    	//$('#package-'+idGlob).remove();
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
  	});
}
</script>
</html>
