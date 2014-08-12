define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/utils"
], ( $, _, Backbone, JST, utils ) ->
    class EditProfile extends Backbone.View
        className: "profile-view edit-profile-view"
        template: JST[ "app/scripts/modules/profile/views/templates/edit.hbs" ]

        events:
            "submit #edit-user": "saveProfile"

        initialize: ->
            @listenToOnce( @model, "sync", @render )

            # Bind backbone.validation to our view
            #
            Backbone.Validation.bind( this )

        render: ->
            @$el.html( @template( @model.toJSON() ) )

        saveProfile: ( e ) ->
            e.preventDefault()

            @model.set( $( e.target ).trimInputs().serializeObject() )

            # Save this to backend
            #
            if @model.isValid( true )
                @model.save().then(
                    ( data ) =>
                        Backbone.history.navigate( "#profile", trigger: true )
                        utils.alert( "Profile was successfully updated." )

                ,   ( error ) =>
                        errorStatus = $.parseJSON( error.responseText ).message
                        utils.alert( errorStatus, "warning" )

                ).done()