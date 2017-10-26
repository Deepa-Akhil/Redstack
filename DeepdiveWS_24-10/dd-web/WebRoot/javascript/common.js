var ajaxObjects = new Array();
var boxFramesQueue = new Array();
var viewPortX = viewPortY = 0;

function getElementAttribute(element,attr) {
	var attribute = null;
	if (element) {
		if (element.getAttribute) {
			attribute = element.getAttribute(attr);
		} else
			if (element.attributes[attr])
				attribute = element.attributes[attr].value;
	}
	return attribute;
}

function setElementAttribute(element,attr,value) {
	if (element) {
		if (element.getAttribute) {
			element.setAttribute(attr,value);
			element.attributes[attr].value = value;
		} else
			element.attributes[attr].value = value;
	}
}

function elements(formName) {
	var form = document.forms[formName];	
	var allElements = [];
	if (form && form != undefined && form != null) {
		var inputElements = form.getElementsByTagName('input');
		var selectElements = form.getElementsByTagName('select');
		var textAreaElements = form.getElementsByTagName('textarea');
		for (var i=0; i<inputElements.length; i++)
			allElements.push(inputElements[i]);
		for (var i=0; i<selectElements.length; i++)
			allElements.push(selectElements[i]);
		for (var i=0; i<textAreaElements.length; i++)
			allElements.push(textAreaElements[i]);
	}
	return allElements;
}

function ajaxRequestOnInterval(elementId, millis) {
	component = setTimeout(function() {
		var element = document.getElementById(elementId);
		if (element)
			ajax(elementId);
	},millis);
}

function url(formName) {
	var urlString = '';
	if (formName) {
		var inputElements = elements(formName);
		for (var i=0; i<inputElements.length; i++) {
			var inputElementName = inputElements[i].name;
			var inputElementVal = inputElements[i].value;
			if (inputElements[i].type == 'checkbox') {
				if (inputElements[i].checked)
					inputElementVal = 'true';
				else
					inputElementVal = 'false';
			} else if (inputElements[i].type == 'radio') {
				if (!inputElements[i].checked)
					inputElementName = null;
			} else if (inputElements[i].type == 'select') {
				// do nothing
			} else if (inputElements[i].type == 'select-multiple') {
				if (inputElementName) {
					var selectElement = inputElements[i];
					var options = selectElement.getElementsByTagName('option');
					if (options) {
						inputElementVal = '';
						for (var j=0; j<options.length; j++) {
							var selected = options[j].selected;
							if (selected) {
								var optionVal = options[j].value;
								if (j == 0)
									inputElementVal = inputElementVal.concat(optionVal);
								else
									inputElementVal = inputElementVal.concat("," + optionVal);
							}
						}
					}
				}
			} else if (inputElements[i].type == 'button')
				inputElementName = null;
			//construct the url
			if (inputElementName)
				urlString = urlString + '&' + inputElementName + '=' + inputElementVal;
		}	
	}
	return urlString;
}

function ajax_get(elementId,action) {
	var element = document.getElementById(elementId);
	var context = '/ddweb';
	if (element) {
		action = action || getElementAttribute(element,'action');
		var targetId = getElementAttribute(element,'targetId');
		var target = document.getElementById(targetId);
		action = context + (action || '');
		ajaxObjects[action] = new ajaxImpl(targetId,'get');
		ajaxObjects[action].onCompletion = function() {
			executeJScripts(target);
		};
		ajaxObjects[action].execAJAX(action);
	}
}

function ajax2(elementId,action,formName) {
	var element = document.getElementById(elementId);
	var context = '/ddweb';
	if (element) {
		action = action || getElementAttribute(element,'action');
		//attributes
		var queryString = getElementAttribute(element,'queryString');
		var targetId = getElementAttribute(element,'targetId');
		var method = getElementAttribute(element,'method');
		var contentType = getElementAttribute(element,'contentType');
		//elements
		var target = document.getElementById(targetId);
		//url
		queryString = (queryString || '') + url(formName);
		//action
		action = context + (action || '');
		//operations
		ajaxObjects[action] = new ajaxImpl(targetId,method,contentType,queryString);
		ajaxObjects[action].onCompletion = function() {
			executeJScripts(target);
		};
		ajaxObjects[action].execAJAX(action);
	}
}

function ajax(elementId,action,confirmation,groupId,seqElementId,icontext) {
	var element = document.getElementById(elementId);
	var context = icontext || '/ddweb';
	if (element) {
		action = action || getElementAttribute(element,'action');
		seqElementId = seqElementId || getElementAttribute(element,'seqElementId');
		//attributes
		var formName = getElementAttribute(element,'formName');
		var queryString = getElementAttribute(element,'queryString');
		var targetId = getElementAttribute(element,'targetId');
		var method = getElementAttribute(element,'method');
		var contentType = getElementAttribute(element,'contentType');
		//elements
		var target = document.getElementById(targetId);
		var seqElement = document.getElementById(seqElementId);
		//url
		queryString = (queryString || '') + url(formName);
		//action
		action = context + (action || '');
		//operations
		ajaxObjects[action] = new ajaxImpl(targetId,method,contentType,queryString,groupId);
		ajaxObjects[action].onCompletion = function() {
			executeJScripts(target);
			if (seqElement)
				ajax(seqElementId);
		};
		//confirm
		if (confirmation == true) {
			var answer = confirm(confirmation);
			if (answer)
				ajaxObjects[action].execAJAX(action);		
		} else
			ajaxObjects[action].execAJAX(action);
	}
}

function executeJScripts(responseDiv) {
	if (responseDiv) {
		var scripts = responseDiv.getElementsByTagName('script');
		if (scripts) {
			for (var i=0; i<scripts.length; i++) {
				var scriptContentText = scripts[i].text;
				eval(scriptContentText);
			}
		}
	}
}

function reload(lang){
	top.location.href = '/ddweb/?lang=' + lang;
}

function contextRoot() {
	top.location.reload();
}

function GroupedAjaxRequestsObj() {
	this.ajaxRequestObjs = new Array();
	this.getObjectByElementId = function(elementId) {
		for (var i = 0; i < this.ajaxRequestObjs.length; i++) {
			var ajaxRequestObjElementId = this.ajaxRequestObjs[i].elementId;
			if (elementId == ajaxRequestObjElementId) {
				return this.ajaxRequestObjs[i];
			}
		}
	}
	this.getObjectByTargetElementId = function(targetElementId) {
		for (var i = 0; i < this.ajaxRequestObjs.length; i++) {
			var ajaxRequestObjTargetElementId = this.ajaxRequestObjs[i].targetElementId;
			if (targetElementId == ajaxRequestObjTargetElementId) {
				return this.ajaxRequestObjs[i];
			}
		}
	}
	this.isAllResponsesReceived = function() {
		var allResponsesReceived = true;
		for (var i = 0; i < this.ajaxRequestObjs.length; i++) {
			var iAjaxRequestObj = this.ajaxRequestObjs[i];
			if (!iAjaxRequestObj.response) {
				allResponsesReceived = false;
				break;
			}
		}
		return allResponsesReceived;
	}
}

function AjaxRequestObj() {
	this.elementId = null;
	this.optAction = null;
	this.confirmation = null;
	this.response = null;
	this.targetElementId = null;
	this.context = null;
	this.setElementId = function(elementId) {
		this.elementId = elementId;
	}
	this.setOptAction = function(optAction) {
		this.optAction = optAction;
	}
	this.setConfirmation = function(confirmation) {
		this.confirmation = confirmation;
	}
	this.setResponse = function(response) {
		this.response = response;
	}
	this.setTargetElementId = function(targetElementId) {
		this.targetElementId = targetElementId;
	}
	this.setContext = function(context) {
		this.context = context;
	}
}

var groupedAjaxRequestsObjArr = new Array();
function evalFunctions(iFunctions,iGroupId) {
	if (iFunctions) {
		var groupedAjaxRequestsObj = null;
		var currentFunctionTokens = iFunctions.split(";");
		if (iGroupId) {
			groupedAjaxRequestsObj = groupedAjaxRequestsObjArr[iGroupId];
			if (!groupedAjaxRequestsObj) {
				groupedAjaxRequestsObj = new GroupedAjaxRequestsObj();
				groupedAjaxRequestsObjArr[iGroupId] = groupedAjaxRequestsObj;
			}
		}
		for (var i = 0; i < currentFunctionTokens.length; i++) {
			var evalFunction = currentFunctionTokens[i];
			if (evalFunction && evalFunction.length > 0) {
				try {
					if (evalFunction.substring(0,4) == 'ajax') {
						if (iGroupId) {
							var ajaxRequestObj = new AjaxRequestObj();
							var evalFunctionSplit1Tokens = evalFunction.split("(");
							var evalFunctionDirtyParams = evalFunctionSplit1Tokens[1];
							var evalFunctionSplit2Tokens = evalFunctionDirtyParams.split(")");
							var evalFunctionCleanParams = evalFunctionSplit2Tokens[0];
							var parameters = evalFunctionCleanParams.split(",");
							for (var j = 0; j < parameters.length; j++) {
								var parameter = parameters[j].replace(/\'/g,"");
								if (j == 0) {
									var ajaxElement = document.getElementById(parameter);
									if (ajaxElement) {
										var targetElementId = getElementAttribute(ajaxElement, 'targetId');
										if (targetElementId)
											ajaxRequestObj.setTargetElementId(targetElementId);
										else
											ajaxRequestObj.setTargetElementId(parameter);
									} else
										ajaxRequestObj.setTargetElementId(parameter);
									ajaxRequestObj.setElementId(parameter);
								} else if (j == 1)
									ajaxRequestObj.setOptAction(parameter);
								else if (j == 2)
									ajaxRequestObj.setConfirmation(parameter);
								else if (j == 5)
									ajaxRequestObj.setContext(parameter);
							}
							groupedAjaxRequestsObj.ajaxRequestObjs.push(ajaxRequestObj);
						} else
							eval(evalFunction);
					} else
						eval(evalFunction);
				} catch (Exception) {}
			}
		}
		if (iGroupId) {
			showWait();
			for (var i = 0; i < groupedAjaxRequestsObj.ajaxRequestObjs.length; i++) {
				var ajaxRequestObj = groupedAjaxRequestsObj.ajaxRequestObjs[i];
				var iElementId = ajaxRequestObj.elementId;
				var iOptAction = ajaxRequestObj.optAction;
				var iConfirmation = ajaxRequestObj.confirmation;
				var iContext = ajaxRequestObj.context;
				ajax(iElementId,iOptAction,iConfirmation,iGroupId,null,iContext);
			}
			var responseInterval = setInterval(
				function() {
					if (groupedAjaxRequestsObj.isAllResponsesReceived()) {
						clearInterval(responseInterval);
						for (var i = 0; i < groupedAjaxRequestsObj.ajaxRequestObjs.length; i++) {
							var ajaxRequestObj = groupedAjaxRequestsObj.ajaxRequestObjs[i];
							var iTargetElementId = ajaxRequestObj.targetElementId;
							var targetElement = document.getElementById(iTargetElementId);
							if (targetElement) {
								var response = ajaxRequestObj.response;
								targetElement.innerHTML = response;
							}
						}
						groupedAjaxRequestsObjArr[iGroupId] = null;
						closeWait();
					}
				},1000
			);
		}
	}
}

function resetMultiSelect(objId) { 
	var selObject = document.getElementById(objId);
	if (selObject) {
		for (i=0; i<selObject.options.length; i++)
			selObject.options[i].selected = false;
	}	
}

function disableElement(objId) {
	var selObject = document.getElementById(objId);
	if (selObject)
		selObject.disabled = true;
}

function enableElement(objId) {
	var selObject = document.getElementById(objId);
	if (selObject)
		selObject.disabled = false;
}




function closeWait() {
	var light = document.getElementById("lightId");
	if (light) {
		light.style.display = "none";
		light.style.cursor='pointer';
		var lightParentObj = light.parentNode;
		if (lightParentObj)
			lightParentObj.removeChild(light);
		document.body.style.cursor='default';
	}
	document.body.style.overflow = 'none';
}

function showWait() {
	var extra = 'Y';
	var lightId = "lightId";
	var zindex = 10000;
	var opacity = 60;
	var bgcolor = '#ffffff';
	var light = document.getElementById(lightId);
	var tbody = document.getElementsByTagName("body")[0];
	var tnode = document.getElementById(lightId);
	if (!light) {
		tnode = document.createElement('div');						// Create the layer.
		tnode.id=lightId;											// Name it so we can find it later
	    tbody.appendChild(tnode);									// Add it to the web page
	    light = document.getElementById(lightId);
	    tnode = document.getElementById(lightId);
	}
	if (tnode && !(tnode.style.display=='block')) {
		tnode.style.position='absolute';							// Position absolutely
        tnode.style.top='0px';										// In the top
        tnode.style.left='0px';										// Left corner of the page
        tnode.style.overflow='hidden';								// Try to avoid making scroll bars
        tnode.style.display='none';									// Start out Hidden
        tnode.style.cursor='wait';
	}
	var pageWidth = '100%';
	var pageHeight = '100%';
	var marginLeft = '0px';
	var marginTop = '0px';
	var top = "0px";
	var left = "0px";
	var opaque = (opacity / 100);
	light.style.position='absolute';
	light.style.width = pageWidth;
	light.style.height = pageHeight;
	light.style.marginLeft = marginLeft;
	light.style.marginTop = marginTop;
	light.style.left = left;
	light.style.top = top;
	light.style.opacity = opaque;
	light.style.MozOpacity = opaque;
	light.style.filter = 'alpha(opacity=' + opacity + ')';
	light.style.zIndex = zindex;
	light.style.backgroundColor = bgcolor;
	light.style.display = 'block';
	document.body.style.overflow = 'hidden';
	//var boxObj = new BoxObj(lightId,null);
	//boxFramesQueue.push(boxObj);
}

function BoxObj(darkId,boxId) {
	this.darkId = darkId;
	this.boxId = boxId;
}

function showDetail(modalWidth, modalHeight, action, iModalId, iOverlayId, izIndex) {
	$(document).buildBlackoutModal(modalWidth, modalHeight, action, iModalId, iOverlayId, izIndex);
	var modalId = iModalId || 'modalId';
	ajax(modalId, action);
}

function showLookup(element, modalWidth, modalHeight, top, left, action) {
	$(document).buildLookupModal(element, modalWidth, modalHeight, top, left);
	ajax($(element).attr('modalId'), action);
}

function grayOut(vis, options, extra, boxWidth, boxHeight, action) {
	var darkId = "darkId";
	var boxId = "boxId";
	var options = options || {};
	var zindex = options.zindex || 50;
	var opacity = options.opacity || 70;
	//var opaque = (opacity / 100);
	var bgcolor = options.bgcolor || '#000000';
	var dark = document.getElementById(darkId);
	var tbody = document.getElementsByTagName("body")[0];
	var tnode = document.getElementById(darkId);
	var msgnode = document.getElementById(boxId);
	if (!dark) {
		tnode = document.createElement('div');						// Create the layer.
		tnode.id=darkId;											// Name it so we can find it later
		msgnode = document.createElement('div');					// Create the box layer.
		msgnode.id = boxId;											// Name it so we can find it later
		msgnode.setAttribute('targetId', boxId);
		msgnode.setAttribute('action', action);
		tbody.appendChild(msgnode);									// Add it to the grey screen
	    tbody.appendChild(tnode);									// Add it to the web page
	    dark = document.getElementById(darkId);
	    tnode = document.getElementById(darkId);
		msgnode = document.getElementById(boxId);
	}
	if (tnode && !(tnode.style.display=='block')) {
		tnode.style.position='absolute';							// Position absolutely
        tnode.style.top='0px';										// In the top
        tnode.style.left='0px';										// Left corner of the page
        tnode.style.overflow='hidden';								// Try to avoid making scroll bars
        tnode.style.display='none';									// Start out Hidden
	}
	if (msgnode && !(msgnode.style.display=='block')) {
		msgnode.style.position='fixed';								// Position fixed
        msgnode.style.display='none';								// Start out Hidden
        msgnode.style.width = boxWidth+"px";
        msgnode.style.height = boxHeight+"px";
        msgnode.style.marginLeft=((boxWidth/2) - boxWidth) + "px";
        msgnode.style.marginTop=((boxHeight/2) - boxHeight) + "px";
        msgnode.style.textAlign = 'center';
        msgnode.style.top= "50%";									// In the top
        msgnode.style.left="50%";									// Left corner of the page
	}
	if (vis) {
		var pageWidth = '100%';
		var pageHeight = '100%';
		var marginLeft = '0px';
		var marginTop = '0px';
		var top = "0px";
		var left = "0px";
		var opaque = (opacity / 100);
		//set the shader to cover the entire page and make it visible.
		dark.style.position='absolute';
		dark.style.width = pageWidth;
		dark.style.height = pageHeight;
		dark.style.marginLeft = marginLeft;
		dark.style.marginTop = marginTop;
		dark.style.left = left;
		dark.style.top = top;
		dark.style.opacity = opaque;
		dark.style.MozOpacity = opaque;
		dark.style.filter = 'alpha(opacity=' + opacity + ')';
		dark.style.zIndex = zindex;
		dark.style.backgroundColor = bgcolor;
		dark.style.display = 'block';
		if (extra == 'Y') {
			document.body.style.overflow = 'hidden';
		}
		var boxElement = document.getElementById(boxId);
		boxElement.style.display = "block";
		boxElement.style.zIndex = zindex + 10;
		boxElement.style.border = "none";
		boxElement.style.backgroundColor = "#FFF";
		boxElement.style.overflow = "hidden";
		ajax(boxId);
		var boxObj = new BoxObj(darkId,boxId);
		boxFramesQueue.push(boxObj);
	} else {
		dark.style.display = 'none';
	}
}

function disableWindowScroll() {
	viewPortX = document.body.scrollLeft + document.documentElement.scrollLeft;
	viewPortY = document.body.scrollTop + document.documentElement.scrollTop;
    document.documentElement.style.overflow = 'hidden';
    window.scrollTo(viewPortX, viewPortY);
}

function closeDetail() {
	var enableScrollingAfterClose = true;
	var currentBoxObj = boxFramesQueue.pop();
	if (currentBoxObj) {
		var dark = document.getElementById(currentBoxObj.darkId);
		if (dark == undefined)
			dark = parent.document.getElementById(currentBoxObj.darkId);
		var box = document.getElementById(currentBoxObj.boxId);
		if (box == undefined)
			box = parent.document.getElementById(currentBoxObj.boxId);
		if (box) {
			box.style.display = "none";
			var boxParentObj = box.parentNode;
			if (boxParentObj)
				boxParentObj.removeChild(box);
		}
		if (dark) {
			dark.style.display = "none";
			dark.style.cursor='pointer';
			var darkParentObj = dark.parentNode;
			if (darkParentObj)
				darkParentObj.removeChild(dark);
			document.body.style.cursor='pointer';
		}
	}
	//enable scrolling again
	if (enableScrollingAfterClose) {
		document.documentElement.style.overflow = '';
		window.scrollTo(viewPortX, viewPortY);
	}
}

function showConfirmation(modalWidth, modalHeight, action) {
	$(document).buildBlackoutModal(modalWidth, modalHeight, action, 'confirmationModalId', 'overlay_secondary', '5000');
	ajax('confirmationModalId', action);
}

function focusOnElement(id) {
	if (id) {
		var element = document.getElementById(id);
		if (element)
			element.focus();
	}
}