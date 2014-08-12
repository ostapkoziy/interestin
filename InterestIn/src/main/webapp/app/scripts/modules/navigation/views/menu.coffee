define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/logged-user"
], ( $, _, Backbone, JST, LoggedUser ) ->
    class Menu extends Backbone.View
        template: JST[ "app/scripts/modules/navigation/views/templates/menu.hbs" ]

        events: ->
            "click a": "select"

        render: ->
            @$el.html( @template() )

            return this

        select: ( e ) ->
            $target = $( e.currentTarget )

            # Prevent logout navigation and trigger it's event to show confirm dialog
            #
            if $target.hasClass( "logout" )
                e.preventDefault()

                @trigger( "logout" )
            else
                @$( "a.active" ).removeClass( "active" )
                $target.addClass( "active" )

        setActive: ( active ) ->
            # Remove query parameters and than search foe link
            #
            if active.indexOf( "?" ) > -1
                active = active.substr( 0, active.indexOf( "?" ) )

            $active = @$( "a[href=#" + active + "]" )

            if $active.length isnt 0
                @$( "a.active" ).removeClass( "active" )
                $active.addClass( "active" )


