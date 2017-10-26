var isIE;

function GetXmlHttpObject() {
	try {
		// Internet Explorer
		isIE = true;
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		// Firefox, Opera 8.0+, Safari
		try {
			isIE = false;
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			isIE = true;
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}

function ajaxImpl(elementId, method, contentType, queryString, groupId) {
	this.xmlhttp = null;
	this.resetData = function(method,contentType) {
		this.method = method;
		this.execute = false;
		this.element = elementId;
		this.elementObj = null;
		this.responseStatus = new Array(2);
		this.queryString = queryString;
		this.contentType = contentType;
		this.groupId = groupId;
	};

	this.resetFunctions = function() {
		this.onCompletion = function() {};
	};

	this.reset = function(method,contentType) {
		this.resetFunctions();
		this.resetData(method,contentType);
	};

	this.createAJAX = function() {
		try {
			this.xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e1) {
			try {
				this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
				this.xmlhttp = null;
			}
		}
		if (!this.xmlhttp) {
			if (typeof XMLHttpRequest != "undefined") {
				this.xmlhttp = new XMLHttpRequest();
			} else {
				this.failed = true;
			}
		}
	};

	this.execAJAX = function(urlstring) {
		if (this.failed) {
			this.onFail();
		} else {
			if (this.element) {
				this.elementObj = document.getElementById(this.element);
			}
			if (this.xmlhttp) {
				var self = this;
				if (this.method.toUpperCase() == "GET") {
					this.xmlhttp.open(this.method, urlstring, true);
					try {
						this.xmlhttp.setRequestHeader("Content-Type", this.contentType);
					} catch (e) {}
				} else {
					this.xmlhttp.open(this.method, urlstring, true);
					try {
						this.xmlhttp.setRequestHeader("Content-Type", this.contentType);
					} catch (e) {}
				}
				document.body.style.cursor = 'wait';
				this.xmlhttp.onreadystatechange = function() {
					switch (self.xmlhttp.readyState) {
						case 4: {
							self.response = self.xmlhttp.responseText;
							self.responseXML = self.xmlhttp.responseXML;
							self.responseStatus[0] = self.xmlhttp.status;
							self.responseStatus[1] = self.xmlhttp.statusText;
							if (self.execute)
								self.runResponse();
							if (self.elementObj) {
								elemNodeName = self.elementObj.nodeName;
								elemNodeName.toLowerCase();
								if (elemNodeName == "input"
										|| elemNodeName == "select"
										|| elemNodeName == "option"
										|| elemNodeName == "textarea") {
									self.elementObj.value = self.response;
								} else {
									var content = self.response;
									if (self.groupId) {
										var groupedAjaxRequestsObj = groupedAjaxRequestsObjArr[self.groupId];
										if (groupedAjaxRequestsObj) {
											var ajaxRequestObj = groupedAjaxRequestsObj.getObjectByTargetElementId(self.element);
											if (ajaxRequestObj)
												ajaxRequestObj.setResponse(content);
										}
									} else {
										//self.elementObj.innerHTML = content;
										var jqElement = self.elementObj;
										$(jqElement).html(content);
										var a = $(content);
										$(a).reload();
									}
								}
							}
							if (self.responseStatus[0] == "200")
								self.onCompletion();
							self.queryString = "";
						}
						default: {
							document.body.style.cursor = 'default';
						}
					}
				};
				this.xmlhttp.send(self.queryString);
			}
		}
	};
	method = method || "GET";
	contentType = contentType || "application/x-www-form-urlencoded; charset=utf-8";
	this.queryString = queryString || "";
	this.groupId = groupId || null;
	this.reset(method,contentType);
	this.createAJAX();
}