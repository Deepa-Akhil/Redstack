;(function($) {
    $.VAT = function(elements, options) {
        var defaults = {
            style_disabled_labels:  true
        },
        // to avoid confusions, use "plugin" to reference the current instance of the object
        plugin = this;
        // this will hold the merged default, and user-provided options
        plugin.settings = {}
        // the "constructor" method that gets called when the object is created
        var init = function() {
            // the plugin's final properties are the merged default and user-provided options (if any)
            plugin.settings = $.extend({}, defaults, options);
            // replace elements
            plugin.update(elements);
        }
        
        /**
         *  If you dynamically add or enable/disable controls, call this method to update the elements' style
         *
         *  @param  mixed    A jQuery object or a collection of jQuery objects as returned by jQuery's selector engine
         *
         *  @return void
         */
        plugin.update = function(elements) {
            // if invalid collection of element to replace
            if (undefined == elements || typeof elements.each != 'function')
                // replace all replaceable elements
                elements = $('input[type="checkbox"], input[type="radio"]');
            // iterate through replaceable elements
            elements.each(function() {
                var
                    // reference to jQuery version of the element that needs to be replaced
                    $element = $(this),
                    // reference to the actual DOM element
                    element = this,
                    // get the type of the element
                    type =  $element.is('input:checkbox') ? 'checkbox' :
                            ($element.is('input:radio') ? 'radio' : false);
                // if element is a supported type
                if (type) {
                    // make the first letter capital
                    type = type.charAt(0).toUpperCase() + type.slice(1);
                    var
                        // reference to the replacement div
                        replacement = $element.data('VAT_' + type),
                        // get some of the element's attributes
                        attributes = {
                            'checked':      $element.attr('checked'),
                            'disabled':     $element.attr('disabled')
                        }
                    // if element is not a list or a multi-select box
                    if (!(type == 'Select' && (attributes.multiple || attributes.size))) {
                        // if element was replaced before, remove the replacement from
                        // the DOM as we will be creating it again
                        if (replacement) replacement.remove();
                        var
                            // get the element's position, relative to the offset parent
                            position = $element.position(),
                            // get some of the element's CSS properties
                            styles = {
                                'width':            $element.outerWidth(),
                                'height':           $element.outerHeight(),
                                'marginLeft':       parseInt($element.css('marginLeft'), 10) || 0,
                                'marginTop':        parseInt($element.css('marginTop'), 10) || 0
                            },
                            // create the replacement div
                            replacement =
                                jQuery('<div>', {'class': 'VAT_' + type}).
                                // the replacement div will be invisible for now
                                css('visibility', 'hidden');
                        	replacement.bind('click', function() {
                                // trigger the original element's "onChange" event
                                $element.trigger('change');
                            });
                        // add the replacement div to the DOM, right next to the element it replaces
                        // we need to add it to the DOM now so that we can use width(), outerWidth(), etc functions later
                        replacement.insertBefore($element);
                        // get the original element's events
                        var events = $element.data('events');
                        // if there are any events attached to the original element
                        if (events)
                            // iterate through the attached event types
                            for (var event_type in events)
                                // iterate through all the events of a specific type
                                for (var idx in events[event_type])
                                    // copy the event to the replacement element
                                    // (make sure that the function context is the original element -> $.proxy)
                                    replacement[event_type]($.proxy(events[event_type][idx].handler, $element.get(0)));
                        // if element is a checkbox or radio button
                        if (type != 'Select')
                            // place the replacement div so that it's *exactly* above the original element
                            replacement.css({
                                'left':         position.left + ((styles.width - replacement.width()) / 2) + styles.marginLeft,
                                'top':          position.top + ((styles.height - replacement.height()) / 2) + styles.marginTop
                            // style the element according to its state
                            }).addClass(
                                // is checked (but not disabled)
                                (attributes.checked && !attributes.disabled ? 'VAT_' + type + '_Checked' : '') +
                                // is disabled (but not checked)?
                                (attributes.disabled && !attributes.checked ? 'VAT_' + type + '_Disabled' : '') +
                                // is both disabled and checked
                                (attributes.disabled && attributes.checked ? ' VAT_' + type + '_Checked_Disabled' : '')
                            );
                        // alert('replacement: ' + replacement.attr('class'));
                        // handle events on the original element
                        $element.bind({
                            // when the element receives focus
                            'focus': function() {
                                // add a class to the replacement div
                                replacement.addClass('VAT_' + type + '_Focus');
                            },
                            // when the element loses focus
                            'blur': function() {
                                // remove a class from the replacement div
                                replacement.removeClass('VAT_' + type + '_Focus');
                            },
                            // when the original element's state changes
                            'change': function() {
                                // if element is not disabled
                                if (!$element.attr('disabled')) {
                                    // if we're doing checkboxes
                                    if (type == 'Checkbox') {
                                        // toggle a class on the replacement div
                                        replacement.toggleClass('VAT_Checkbox_Checked');
                                        //alert("replacement: " + replacement.attr('class'));
                                        // set the "checked" attribute accordingly
                                        if (replacement.hasClass('VAT_Checkbox_Checked'))
                                            $element.attr('checked', 'checked');
                                        else
                                            $element.removeAttr('checked', 'checked');
                                    // if we're doing radio buttons
                                    } else if (type == 'Radio') {
                                        // find all radio buttons sharing the name of the currently clicked
                                        // and iterate through the found elements
                                        $('input:radio[name=' + $element.attr('name') + ']').each(function() {
                                            // reference to the jQuery version of the element
                                            var $control = $(this);
                                            // remove the "checked" attribute
                                            $control.removeAttr('checked');
                                            // remove a class from the replacement div
                                            $control.data('VAT_Radio').removeClass('VAT_Radio_Checked');
                                        });
                                        // add class to replacement div of the currently clicked element
                                        replacement.addClass('VAT_Radio_Checked');
                                        // set the "checked" attribute of the currently clicked element
                                        // remember, we remove these in the lines above
                                        $element.attr('checked', 'checked');
                                    // if select boxes
                                    }
                                }
                            }
                        // make the original element *almost* invisible
                        // (if the element is visible it will be part of the tab-order and accessible by keyboard!)
                        // and save a reference to the replacement div
                        }).css('opacity', '0.0001').data('VAT_' + type, replacement);
                        // make the replacement div visible
                        replacement.css('visibility', 'visible');
                    // if a multi-select box or a list
                    }
                }
            });
        }
        // call the "constructor" method
        init();
    }
})(jQuery);