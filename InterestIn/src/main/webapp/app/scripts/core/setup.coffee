define [
    "jquery"
    "backbone"
    "underscore"
    "backbone.validation"
    "handlebars"
    "./logged-user"
], ( $, Backbone, _, Validation, Handlebars, LoggedUser ) ->

    # Setup default XHR options
    #
    $.ajaxSetup(
        xhrFields:
            withCredentials: true
        statusCode:
            401: ->
                try
                    Backbone.history.start()
                catch e
                    LoggedUser.clear()

                Backbone.history.navigate( "login", { replace: true, trigger: true } )
    )

    # Add custom backbone validation methods
    #
    _.extend( Validation.callbacks,
        valid: (view, attr, selector) ->
            $el = view.$( "[" + selector + '~="' + attr + '"]').removeClass( "error" )
            $el.next( ".error" ).remove()

        invalid: (view, attr, error, selector) ->
            $el = view.$( "[" + selector + '~="' + attr + '"]').addClass( "error" )
            if $el.next( "small" ).length is 0
                $error = $( "<small/>" ).attr( "class", "error" ).text( error )
                $el.after( $error )
    )

    # Trim all input values
    #
    $.fn.trimInputs = ->
        $this = $(this)

        $this.find( "input" ).each( ->
            $(this).val( $.trim( $(this).val() ) )
        )

        return $this

    Handlebars.registerHelper( "unescape", ( html ) ->
        $el =  $( "<div />" )
        text =  $el.html( html ).text()
        $el.remove()

        return text
    )

    Handlebars.registerHelper( "clamp", ( text, length ) ->
        if ( text.length > length )
            resultText = text.slice( 0, length-3 )
            return resultText + "..."
        else
            return text
    )