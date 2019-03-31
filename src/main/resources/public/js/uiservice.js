$(document).ready(function() {
    $('#create-url-form').submit(function(event) {
        event.preventDefault();
        postNewUrl();
    });
});

const postNewUrl = function() {
    const urlValue = $('#url-value').val();
    if (urlValue === '') {
        $('#error-message').text('The url must not be empty.');
        return;
    }

    $.urlservice.makeRequest($.urlconstants.API_URL, $.urlservice.TYPE_POST, function(status, result) {
        if (status === $.urlservice.STATUS_SUCCESS) {
            $('#returned-url').hide().fadeIn('slow').text('The new URL is: ' + $.urlconstants.HREF + result.data.shortUrl);
            $('#error-message').fadeOut('slow');
        } else {
            $('#error-message').fadeIn('slow');
            $('#error-message').text($.urlerrorhandler.parseError(result.request, result.textStatus, result.error));
        }
    }, 'text/plain', urlValue, );
}