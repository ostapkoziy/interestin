define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/utils"
], ( $, _, Backbone, JST, utils ) ->
    class MessagesOverview extends Backbone.View
        className: "messages-view"
        template: JST[ "app/scripts/modules/message/views/templates/overview.hbs" ]

        render: ->
            @$el.html( @template() )

            return this
