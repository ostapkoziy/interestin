define [
    "backbone"
    "core/hostMapping"
], ( Backbone, hostMapping ) ->
    "use strict";

    class Profile extends Backbone.Model
        urlRoot: "user.json"

        validation:
            first_name:
                required: true
                minLength: 3
                maxLength: 255
            last_name:
                required: true
                minLength: 3
                maxLength: 255
            email:
                required: true
                maxLength: 128
                pattern: "email"
