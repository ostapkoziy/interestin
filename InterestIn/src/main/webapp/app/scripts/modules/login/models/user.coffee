define [
    "backbone"
    "core/hostMapping"
], ( Backbone, hostMapping ) ->
    "use strict";

    class UserModel extends Backbone.Model
        urlRoot: hostMapping.getHostName( "api" ) + "/users"

        validation:
            email:
                required: true
                minLength: 6
                maxLength: 255
            password:
                required: true
                minLength: 6
                maxLength: 255
            confirm_password:
                equalTo: "password"