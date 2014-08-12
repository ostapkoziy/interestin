define [
    "jquery"
    "core/hostMapping"
], ( $, hostMapping ) ->

    class Login

        call: ( params ) ->

            $.ajax(
                url:            hostMapping.getHostName( "spring" ) + "/j_spring_security_check"
                method:         "POST"
                contentType:    "application/json"
                data:           JSON.stringify( params )
            )