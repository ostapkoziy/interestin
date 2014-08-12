define [
    "jquery"
    "underscore"
    "foundation.reveal"
    "templates"
], ( $, _, Reveal, JST ) ->

    class Dialog
        template: JST[ "app/scripts/shared/dialog/template.hbs" ]

        defaultSettings:
            title: ""
            body: ""
            type: "small"
            confirmButtonText: "Yes"
            cancelButtonText: "Cancel"
            confirmButtonUrl: ""
            confirmButtonTarget: "_self"
            confirmPreventDefault: true
            confirmOnly: false
            onConfirm: false
            customButton: false
            loader: false

        constructor: ( params = {} ) ->
            settings = _.defaults( params, @defaultSettings )

            @$el = $( @template( settings ) )

            $( "body" ).append( @$el )

            @dialog = $( "#dialog" )

            @dialog.find( ".confirm" ).click( ( e ) =>
                if settings.confirmPreventDefault
                    e.preventDefault()

                if _.isFunction( settings.onConfirm )
                    settings.onConfirm( e )
                else
                    @close()
            )

            @dialog.find( ".cancel" ).click( ( e ) =>
                e.preventDefault()
                @close()
            )

            $( document ).foundation(
                reveal:
                    close_on_background_click : false
                    closed: =>
                        @destroy()
            )

        find: ( selector ) ->
            @dialog.find( selector )

        open: ->
            @dialog.foundation( "reveal", "open" )
            return this

        setContent: ( content ) ->
            @$el.find( ".content" ).html( content )

        setTitle: ( title ) ->
            @$el.find( "h4" ).text( title )

        showError: ( errorText ) ->
            @$el.find( "div.alert" ).text( errorText ).slideDown( "fast" )

        hideLoader: ->
            @$el.find( ".loader" ).addClass( "hide" )

        close: ->
            @dialog.foundation( "reveal", "close" )

        destroy: ->
            @dialog.remove()
            @dialog = null