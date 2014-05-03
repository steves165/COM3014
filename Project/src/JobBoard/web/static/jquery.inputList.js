(function($) {
    // Local jQuery plugin definition to save on
    // duplication.
    $.fn.inputList = function(options) {
        // Create some defaults, extending them with any options that were provided
        var settings = $.extend({
            'prefix': ''
        }, options);
        function get_del_element() {
            var del = document.createElement('a');
            $(del).html('Delete');
            $(del).attr({"href": "#"});
            $(del).click(function() {
                var value = $('input', $(this).parent()).val().trim();
                // If the item is blank just remove it rather than bothering
                // the user.
                var canRemove = (value === '');
                if (!canRemove) {
                    var message = 'Are you sure you want to delete the item "' + value + '"?';
                    if (!confirm(message)) {
                        return;
                    }
                }
                var list = $(this).parents('ul');
                $(this).parent().remove();
                // Rework id's so this element is really removed instead of
                // not provided which spring can serialise into a null element.
                $('li input', list).each(function(index) {
                    this.name = settings.prefix + '[' + index + ']';
                });
                // Required to stop the browser from going to the top of the page.
                return false;
            });
            return del;
        }

        var list = $('ul', this);
        $('li', list).each(function(index) {
            var del = get_del_element();
            $(this).append(del);
        });

        this.append('<input type="button" class="addbutton" value="Add"/>');
        $('.addbutton', this).click(function() {
            var index = $('li', list).length;

            var listItem = document.createElement('li');
            var text = document.createElement('input');
            text.name = settings.prefix + '[' + index + ']';
            $(text).addClass('text');

            var del = get_del_element();
            $(listItem).append(text).append(del);
            list.append(listItem);
        });
    };
})(jQuery);
