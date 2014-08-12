define [
    "underscore"
    "backbone"
    "core/hostMapping"
], ( _, Backbone, hostMapping ) ->
    'use strict';

    class LoggedUser extends Backbone.Model
        urlRoot: "user.json"

        validation:
            password:
                required: true
            email:
                required: true
                pattern: "email"

        #TODO Set true until connected to api
        #
        _loggedIn: false

        isLogged: -> @_loggedIn


    # Singleton model
    #
    user = new LoggedUser()

    user.on( "change:email", ( model, value, options ) ->
        if value is undefined
            user._loggedIn = false
        else
            user._loggedIn = true
    )