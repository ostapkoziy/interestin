define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/utils"
], ( $, _, Backbone, JST, utils ) ->
    class FriendsOverview extends Backbone.View
        className: "friends-view"
        template: JST[ "app/scripts/modules/friends/views/templates/overview.hbs" ]

        render: ->
            @$el.html( @template() )

            return this
