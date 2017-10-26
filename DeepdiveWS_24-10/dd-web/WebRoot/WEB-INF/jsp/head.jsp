	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=7;IE=edge"/>
	<title>REDWOOD TRADECLOUD</title>
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
	<link media="all" rel="stylesheet" href="includes/reset.css" type="text/css" />
	<link media="all" rel="stylesheet" href="includes/all.css" type="text/css" />
	<link media="all" rel="stylesheet" href="includes/layout.css" type="text/css" />
	<link media="all" rel="stylesheet" href="includes/overlay.css" type="text/css" />
	<script language="javascript" src="javascript/jquery-2.2.4.min.js"></script>
	<script language="javascript" src="javascript/jquery.mmi.js"></script>
	<script language="javascript" src="javascript/ajax.js"></script>
	<script language="javascript" src="javascript/common.js"></script>
	<script language="javascript" src="javascript/menus.js"></script>
	<script language="javascript" src="javascript/openWindow.js"></script>
	<script type="text/javascript">//<![CDATA[
		var mobi = ['opera', 'iemobile', 'webos', 'android', 'blackberry', 'ipad', 'safari'];
		var midp = ['blackberry', 'symbian'];
		var ua = navigator.userAgent.toLowerCase();
		if ((ua.indexOf('midp') != -1) || (ua.indexOf('mobi') != -1) || ((ua.indexOf('ppc') != -1) && (ua.indexOf('mac') == -1)) || (ua.indexOf('webos') != -1)) {
			document.write('<link rel="stylesheet" href="includes/allmobile.css" type="text/css" media="all"/>');
			if (ua.indexOf('midp') != -1) {
				for (var i = 0; i < midp.length; i++) {
					if (ua.indexOf(midp[i]) != -1) {
						document.write('<link rel="stylesheet" href="includes/' + midp[i] + '.css" type="text/css"/>');
					}
				}
			}
			else {
				if ((ua.indexOf('mobi') != -1) || (ua.indexOf('ppc') != -1) || (ua.indexOf('webos') != -1)) {
					for (var i = 0; i < mobi.length; i++) {
						if (ua.indexOf(mobi[i]) != -1) {
							if ((mobi[i].indexOf('blackberry') != -1) && (ua.indexOf('6.0') != -1)) {
								document.write('<link rel="stylesheet" href="css/' + mobi[i] + '6.0.css" type="text/css"/>');
							}
							else {
								document.write('<link rel="stylesheet" href="css/' + mobi[i] + '.css" type="text/css"/>');
							}
							break;
						}
					}
				}
			}
		} else if (ua.indexOf('msie') != -1) {
			if (ua.indexOf('msie 6') != -1) {
				document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/ie6.css" type="text/css" />');
			} else if (ua.indexOf('msie 7') != -1) {
				document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/ie7.css" type="text/css" />');
			} else if (ua.indexOf('msie 8') != -1) {
				document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/ie8.css" type="text/css" />');
			} else if (ua.indexOf('msie 9') != -1) {
				document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/ie9.css" type="text/css" />');
			} else {
				document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/ie.css" type="text/css" />');
			}
		} else if (ua.indexOf('firefox') != -1) {
			document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/firefox.css" type="text/css" />');
		} else if (ua.indexOf('chrome') != -1) {
			document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/chrome.css" type="text/css" />');
		} else if (ua.indexOf('safari') != -1) {
			document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/includes/safari.css" type="text/css" />');
		}
		if ((navigator.userAgent.indexOf('iPhone') != -1) || (navigator.userAgent.indexOf('iPad') != -1)) {
			document.write('<meta name="viewport" content="width=device-width" />');
		}			
		//]]>
	</script>