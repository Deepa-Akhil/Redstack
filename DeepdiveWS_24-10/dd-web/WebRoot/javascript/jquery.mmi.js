$(function() {
	$('.load').bind('click', function() {
		var element = this;
		var ajaxIndexContentDiv = $('#ajaxIndexContentDiv');
		ajaxIndexContentDiv.css({'height' : '264px', 'width' : '100%'});
		var bottomDiv = $('#bottom');
		bottomDiv.css({'position' : 'absolute'});
	});
	
	$('.analyze').bind('click', function() {
		var element = this;
		var ajaxIndexContentDiv = $('#ajaxIndexContentDiv');
		ajaxIndexContentDiv.css({'height' : '864px', 'width' : '100%'});
		var bottomDiv = $('#bottom');
		bottomDiv.css({'position' : 'relative'});
	});
	
	$('.blackout').bind('click', function() {
		var aElement = this;
		var modalWidth = $(aElement).attr('modalWidth');
		var modalHeight = $(aElement).attr('modalHeight');
		var action = $(aElement).attr('action');
		$(aElement).buildBlackoutModal(modalWidth, modalHeight, action);
		ajax('modalId',action);
		return false;
	});
	
	function findModal(element) {
		var elementClass = $(element).attr('class');
		if (elementClass == 'modal') {
			$(element).fadeOut();
			var elementOverlayId = $(element).attr('overlayId');
			var overlayElement = $('#' + elementOverlayId);
			overlayElement.fadeOut();
			return true;
		} else
			return findModal($(element).parent());
	}
	
	$.fn.overlay = function(blackoutId) {
		var element = this;
		element.fadeIn();
		element.css({left: jQuery(window).width() / 2-element.width() / 2,top: jQuery(window).height() / 2-element.height() / 2});
		//$(blackoutId).css({display: 'none', opacity: 0.7, position: 'fixed', width: jQuery(document).width()+100, height: jQuery(document).height()+100, top: -100 + 'px', left: -100 + 'px' });
		$(blackoutId).css({visibility: 'visible', opacity: 0.7});
		$(blackoutId).fadeIn('', function() {
			$(blackoutId).bind('click', function() {/*$(blackoutId).fadeOut(); element.fadeOut();*/});
		});
	};
	
	$.fn.reload = function() {
		$('.closeModal').bind('click', function() {
			var element = this;
			var parent = $(element).parent();
			return findModal($(parent));
		});
	};
	
	$.fn.buildBlackoutModal = function(modalWidth, modalHeight, action, iModalId, iOverlayId, izIndex) {
		var oldModalOuterDiv = $('#' + 'modalOuterDivId');
		if (oldModalOuterDiv)
			oldModalOuterDiv.remove();
		var windowWidth = $(window).width();
		var windowHeight = $(window).height();
		var modalOuterDiv = $('<div></div>');
		var overlayId = iOverlayId || 'overlay_blackout';
		var zIndex = izIndex || '2100';
		modalOuterDiv.attr({'id' : 'modalOuterDivId', 'class' : 'modal', 'targetId' : modalId, 'overlayId' : overlayId});
		modalOuterDiv.css({'width' : (modalWidth+'px'), 'height' : (modalHeight+'px'), 'background-color' : 'rgba(255,255,255,0.5)', 'position' : 'fixed', 'display' : 'none', 'text-align' : 'center', 'top' : '50%', 'left' : '50%', 'z-index' : zIndex, 'overflow' : 'hidden', 'border-radius': '10px'});
		var modalContentDiv = $('<div></div>');
		var modalId = iModalId || 'modalId';
		modalContentDiv.attr({'id' : modalId, 'targetId' : modalId});
		modalContentDiv.css({'background-color' : 'rgba(255,255,255,1)', 'width' : '100%', 'height' : '100%'});
		$(modalOuterDiv).append($(modalContentDiv));
		$('body').append($(modalOuterDiv));
		$(modalOuterDiv).overlay('#' + overlayId);
	};
	
	$.fn.buildLookupModal = function(iElement, iModalWidth, iModalHeight, iTop, iLeft) {
		var jqElement = $(iElement);
		var modalOuterDivId = jqElement.attr('modalOuterId') || 'modalOuterDivId';
		var modalOuterDiv = $('#' + modalOuterDivId);
		var modalOverlayDivId = $(iElement).attr('modalOverlayId') || 'overlay_lookup';
		if (modalOuterDiv)
			modalOuterDiv.remove();
		var offset = jqElement.offset();
		var left = offset.left + iLeft;
		var top = offset.top + iTop;
		var modalOuterDiv = $('<div></div>');
		modalOuterDiv.attr({'id' : modalOuterDivId, 'class' : 'modal', 'overlayId' : modalOverlayDivId});
		modalOuterDiv.css({'width' : (iModalWidth+'px'), 'height' : (iModalHeight+'px'), 'background-color' : 'rgba(255,255,255,0.5)', 'position' : 'fixed', 'display' : 'none', 'text-align' : 'center', 'top' : (top+'px'), 'left' : left+'px', 'z-index' : '10200', 'overflow' : 'hidden', 'border' : '10px solid rgba(50,50,50,0.5)'});
		var modalContentDiv = $('<div></div>');
		var modalId = jqElement.attr('modalId');
		modalContentDiv.attr({'id' : modalId, 'targetId' : modalId});
		modalContentDiv.css({'background-color' : 'rgba(255,255,255,1)', 'width' : '100%', 'height' : '100%'});
		$(modalOuterDiv).append($(modalContentDiv));
		$('body').append($(modalOuterDiv));
		modalOuterDiv.fadeIn();
		$('#' + modalOverlayDivId).css({visibility: 'visible'});
		$('#' + modalOverlayDivId).fadeIn();
		$('#' + modalOverlayDivId).bind('click', function() {
			$('#' + modalOverlayDivId).css({visibility: 'hidden'});
			modalOuterDiv.fadeOut();
		});
	};
});