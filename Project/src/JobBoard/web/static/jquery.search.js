// Created using the jQuery boilerplate code from http://jqueryboilerplate.com/

// the semi-colon before function invocation is a safety net against concatenated
// scripts and/or other plugins which may not be closed properly.
;(function ( $, window, document, undefined ) {

    var pluginName = "search",
        defaults = {
            url: null,
            map: null,
            map_template: null,
            list_element: null,
            list_template: null,
            data: function() {
                return null;
            },
            min_length: 2,
            timeout: 1000
        };

    // The actual plugin constructor
    function Search( element, options ) {
        this.element = element;

        // jQuery has an extend method which merges the contents of two or
        // more objects, storing the result in the first object. The first object
        // is generally empty as we don't want to alter the default options for
        // future instances of the plugin
        this.settings = $.extend( {}, defaults, options );

        this._defaults = defaults;
        this._name = pluginName;

        this._lastterm = null;
        this._timer = null;

        this.init();
    }

    Search.prototype = {

        init: function() {
            var plugin = this;

            $(plugin.element).keyup(function() {
                var term = $(this).val();
                clearTimeout(plugin.timer);
                plugin.timer = setTimeout(function() {
                    if (term.length < plugin.settings.min_length) {
                        return;
                    }
                    if (term  == plugin._lastterm) {
                        // Do not referesh the results if the term is the same.
                        return;
                    }
                    plugin._lastterm = term;

                    if (plugin.settings.list_element != null)
                    {
                        $(plugin.settings.list_element).empty();
                    }
                    if (plugin.settings.map != null)
                    {
                        plugin.settings.map.clearMarkers();
                    }
                    $.ajax({
                        url: plugin.settings.url,
                        data: plugin.settings.data(),
                        success: function(data) {
                            if (plugin.settings.list_element != null)
                            {
                                $(plugin.settings.list_template).tmpl(data.iterable).appendTo(plugin.settings.list_element);
                            }
                            if (plugin.settings.map != null)
                            {
                                $.each(data.iterable, function(i, item) {
                                    plugin.settings.map.addMarker(item.location, $(plugin.settings.map_template).tmpl(item).html());
                                });
                            }
                        }
                    });
                }, plugin.settings.timeout);
            });
        }
    };

    $.fn[pluginName] = function ( options ) {
        return this.each(function () {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Search( this, options ));
            }
        });
    };


})( jQuery, window, document );
