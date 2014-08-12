define [
    "jquery"
    "core/hostMapping"
], ( $, hostMapping ) ->

    class Activation

        call: ( params ) ->

            $.ajax(
                url:            hostMapping.getHostName( "api" ) + "/users/activate"
                method:         "POST"
                contentType:    "application/json"
                data:           params
            )