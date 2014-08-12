define [
    "jquery"
    "core/hostMapping"
], ( $, hostMapping ) ->

    class Logout

        call: ( params ) ->

            $.ajax(
                url:            hostMapping.getHostName( "spring" ) + "/j_spring_security_logout"
                method:         "POST"
                contentType:    "application/json"
            )