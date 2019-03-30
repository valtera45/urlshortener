(function($) {

    $.urlerrorhandler = {
        parseError: parseError
    };

    /**
     * Parses the ajax response and returns the most
     * appropriate message.
     * @param {*} request 
     * @param {*} textStatus 
     * @param {*} error 
     */
    function parseError(request, textStatus, error) {
        let message = 'error';
        if (request && request.responseJSON) {
            message = request.responseJSON.message;
        } else if (request && request.error) {
            message = request.responseJSON.error;
        } else if (textStatus !== '' && textStatus !== undefined) {
            message = textStatus;
        } else if (error !== '' && error !== undefined) {
            message = error;
        }
        return message;
    }

})(jQuery);