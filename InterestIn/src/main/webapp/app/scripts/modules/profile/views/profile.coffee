define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/utils"
    "shared/submit-button"
], ( $, _, Backbone, JST, utils, SubmitButton) ->
    class Profile extends Backbone.View
        className: "profile-view"
        template: JST[ "app/scripts/modules/profile/views/templates/profile.hbs" ]

        initialize: ( params ) ->
            @listenTo( @model, "sync", @render )

        render: ->
            @$el.html( @template( @model.toJSON() ) )

            return this
