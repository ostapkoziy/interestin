define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/logged-user"
], ( $, _, Backbone, JST, LoggedUser ) ->
    class FullWidthLayout extends Backbone.View
        el: "#wrap"
        layoutName: "full-width"
        template: JST[ "app/scripts/layouts/full-width/template.hbs" ]

        initialize: ->
            @needRender = true

        render: ->
            # Render new layout in case it's not active
            #
            if @$el.attr( "data-layout" ) is @layoutName
                @needRender = false
            else
                @$el.html( @template() )
                @$el.attr( "data-layout", @layoutName )

            return this

        needsRendering: -> @needRender

        getContainer: ->
            @$( "#main-content" )