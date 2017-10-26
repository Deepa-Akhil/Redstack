//
// OPEN WINDOW WITH NO SCROLL BARS
//
function openWindowNoScroll(url,height,width) {

	winWindowNoscroll = open(url,
			"winNoscrollWindow","height="+height+",width="+width+",top=50,left=50,toolbar=no,menubar=no,"+
			"scrollbars=no,resizable=yes,location=no,directories=no,status=yes");
		      if (winWindowNoscroll.opener == null)
		      {
		          winWindowNoscroll.opener = self;
		      }

}

function openWindow(url,height,width) {

	winWindowNoscroll = open(url,
			"winNoscrollWindow","height="+height+",width="+width+",top=50,left=50,toolbar=no,menubar=no,"+
			"scrollbars=no,resizable=yes,location=no,directories=no,status=yes");
		      if (winWindowNoscroll.opener == null)
		      {
		          winWindowNoscroll.opener = self;
		      }

}

//
//OPEN WINDOW WITH SCROLL BARS
//
function openWindowWithScroll(url,height,width) {
	winWindowWithScroll = open(url,
		"winScrollWindow","height="+height+",width="+width+",top=50,left=50,toolbar=no,menubar=no,"+
		"scrollbars=yes,resizable=yes,location=no,directories=no,status=yes");
	   if (winWindowWithScroll.opener == null)
	   {
		   winWindowWithScroll.opener = self;
	   }
}