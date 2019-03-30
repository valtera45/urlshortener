(function($) {

    const HOST_NAME = window.location.hostname;
    const HREF = window.location.href;
    const API_ENDPOINT = '/api/v1/urls';

    $.urlconstants = {
        HOST_NAME: HOST_NAME,
        HREF: HREF,
        API_URL: API_ENDPOINT
    };

})(jQuery);