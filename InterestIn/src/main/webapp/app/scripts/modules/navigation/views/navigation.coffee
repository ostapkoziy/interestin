define [
    "jquery"
    "jquery.ui"
    "underscore"
    "backbone"
    "templates"
    "core/logged-user"
    "shared/dialog"
], ( $, jqueryUi, _, Backbone, JST, LoggedUser, Dialog ) ->
    class Navigation extends Backbone.View
        template: JST[ "app/scripts/modules/navigation/views/templates/navigation.hbs" ]

        events:
            "mouseover i": "navInfo"
            "mouseout #nav-menu": "cleanNavInfo"
            "click .fa-navicon": "toggleNavMenu"
            "click .bar-menu": "toggleTabletMenu"

        render: ->
            @$el.html( @template() )

            return this

        navInfo: ( e ) ->
            @$( "#nav-info" ).fadeOut( 50, =>
                @$( "#nav-info" ).html( @$( e.currentTarget ).attr( "data-info" ) ).fadeIn()
            )

        cleanNavInfo: ( e ) ->
            @$( "#nav-info" ).empty()

        toggleNavMenu: ( e ) ->
            if @$( e.currentTarget ).attr( "data-colapsed" ) is "false"
                $( "#nav-menu, #menu-bar" ).fadeOut()
                @$( e.currentTarget ).attr( "data-colapsed", "true" ).removeClass( "rotate-back" ).addClass( "rotate-forward" )
            else
                $( "#nav-menu, #menu-bar" ).fadeIn()
                @$( e.currentTarget ).attr( "data-colapsed", "false" ).removeClass( "rotate-forward" ).addClass( "rotate-back" )

        logout: ( e ) ->
            e.preventDefault()

            @trigger( "logout" )

        toggleTabletMenu: ( e ) ->
            e.stopPropagation()
            console.log 'here'
            $menu = $( "#menu" )
            console.log $menu
            if $menu.hasClass( "show" )
                $menu.removeClass( "show" )
                $menu.off( "click", @stopPropagation )
                setTimeout( =>
                    $( document ).off( "click", @_automaticHide )
                , 100 )
            else
                $menu.addClass( "show" )
                $menu.on( "click", @stopPropagation )
                setTimeout( =>
                    $( document ).on( "click", @_automaticHide )
                , 100 )

        _automaticHide: ->
            $menu = $( "#menu" )
            $menu.removeClass( "show" )
            $menu.off( "click", @stopPropagation )
            $( document ).off( "click", @_automaticHide )
