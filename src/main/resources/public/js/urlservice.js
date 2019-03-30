(function($) {

    const TYPE_POST = "POST";
    const STATUS_SUCCESS = "success";
    const STATUS_ERROR = "error";

    $.urlservice = {
        TYPE_POST: TYPE_POST,
        STATUS_ERROR: STATUS_ERROR,
        STATUS_SUCCESS: STATUS_SUCCESS,
        makeRequest: makeRequest
    };

    /**
     * Makes an HTTP request via jQuery .ajax() with the provided parameters.
     * @param {*} url the URL to receieve the request.
     * @param {*} method the type of method, i.e. UrlService.TYPE_GET, UrlService.TYPE_POST
     * @param {*} contentType the content type of the ajax request, 'application/json, for example.'
     * @param {*} data optional - UrlService.TYPE_POST requires a data object.  
     * For posting a new URL, this will be 'text/plain.'
     * @param {*} callback callback function with response from ajax call. The first parameter will be
     * a text string indicating success or error.  For success, also returned is an object
     * that will contain 'data' and 'textStatus.'  For type of error, the object will return the 'request',
     * the 'textStatus,' and the 'error.'
     */
    function makeRequest(url, method, callback, contentType, data) {
        const self = this;
        let args = {
            url: url,
            method: method,
            contentType: contentType,
            success: function(data, textStatus) {
                var result = {
                    data: data,
                    textStatus: textStatus
                }
                callback(self.STATUS_SUCCESS, result);
            },
            error: function(request, textStatus, error) {
                var result = {
                    request: request,
                    textStatus: textStatus,
                    error: error
                }
                callback(self.STATUS_ERROR, result);
            }
        };

        if (method === TYPE_POST) {
            $.extend(args, { data: data });
        }

        $.ajax(args);
    }
})(jQuery);