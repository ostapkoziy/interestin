define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/api"
    "core/utils"
], ( $, _, Backbone, JST, api, utils ) ->
    class Login extends Backbone.View
        template: JST[ "app/scripts/modules/login/views/templates/register.hbs" ]

        initialize: ->

            # Bind backbone.validation to our view
            #
            Backbone.Validation.bind(@)

        events: ->
            "submit #register": "register"

        render: ->
            @$el.html( @template() )

            return this

        register: ( e ) ->
            e.preventDefault()

            data =
                email:            @$el.find( "[name=email]"    ).val()
                password:         @$el.find( "[name=password]" ).val()
                confirm_password: @$el.find( "[name=confirm_password]" ).val()

            @model.set( data )

            if @model.isValid( true )
                @$( ".loader" ).show()

                # Model is valid and we don't want to sent password confirmation to backend
                #
                @model.unset( "confirm_password" )

                @model.save().then(
                    ( data ) =>
                        @model.set( data )
                        utils.alert( "Your confirmation email was sent. Please check your email." )
                ,   ( error ) =>
                        utils.alert( "Can't register. Please, try again.", "warning" )
                ).always( =>
                    @$( ".loader" ).hide()
                ).done()

