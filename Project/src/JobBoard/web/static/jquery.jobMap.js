(function($) {

    // jQuery plugin definition to be able to easily interface with
    // the Google Maps API v3 to display jobs with markers.
    // Requires that the Google Maps API javascript is already loaded

    // This function returns a plugin reference which can be used
    // for maniplating the map. See the $.jobMap function.
    $.fn.jobMap = function(options) {
        // if plugin has not already been attached to the element
        if (undefined == $(this).data('pluginName')) {
            var plugin = new $.jobMap(this, options);
            $(this).data('pluginName', plugin);
        }
        return plugin;
    };

    // Actual jQuery function to create the map and provide public functions.
    $.jobMap = function(element, options) {
        // Local varaible to reference the plugin
        var plugin = this;

        // Load the settings for the plugin given the defaults and the passed
        // options
        var defaults = {
            zoom: 10
        };
        plugin.settings = $.extend({}, defaults, options);


        // Local function to get the latatude and longatude of an address using the
        // Google API
        var geocoder = new google.maps.Geocoder();
        function _latLng(address, callback) {
            geocoder.geocode( { 'address': address}, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    callback(results[0]);
                } else {
                    // TODO: Better way to report this error?
                    console.err("Geocode was not successful for the following reason: " + status);
                }
            });
        }


        // Add the data required for the Google Map to the element
        $(element).data('bounds', new google.maps.LatLngBounds());
        var mapOptions = {
            zoom: plugin.settings.zoom,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        $(element).data('map', new google.maps.Map(element.get()[0], mapOptions));
        // Informational window reference used to display marker click data
        $(element).data('infowindow', new google.maps.InfoWindow());
        // Keep a list of markers which have been added
        $(element).data('markers', []);

        // Function to add a marker to the map.
        // Usage: map.addMarker(address, popup_content)
        // Where address is a address which Google Maps can
        // interperate and popup_content is the HTML shown when
        // a user clicks the marker.
        plugin.addMarker = function(address, popup_content) {
            // Local varables for the element data
            var map = $(element).data('map');
            var bounds = $(element).data('bounds');
            var info_window = $(element).data('infowindow');

            // Try and load the latatude and longatude
            _latLng(address, function(result) {
                var loc = result.geometry.location;

                // Create the marker on the map
                var marker = new google.maps.Marker({
                    map: map,
                    position: loc
                });

                // Bind the info window to click event on the marker
                google.maps.event.addListener(marker, 'click', (function() {
                    info_window.setContent(popup_content);
                    info_window.open(map, marker);
                }));

                // Auto centre and pan map if we have more than one marker,
                // otherwise centre the map around this location.
                bounds.extend(loc);
                $(element).data('markers').push(marker);
                if ($(element).data('markers').length > 1)
                {
                    map.fitBounds(bounds);
                }
                else
                {
                    map.setCenter(loc);
                }
            });
        };

        // Function to clear all the markers from the map
        plugin.clearMarkers = function() {
            // Local varables for the element data
            $.each($(element).data('markers'), function(index, value) {
                value.setMap(null);
            });
            $(element).data('markers', []);
            $(element).data('bounds', new google.maps.LatLngBounds());
        };
    };
})(jQuery);
