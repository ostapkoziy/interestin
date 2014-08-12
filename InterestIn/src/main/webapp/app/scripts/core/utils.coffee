define [
    "jquery"
], ( $ ) ->

    class Utils
        $template: $( "<div/>" ).attr( "data-alert", "" ).attr( "class", "alert-box hide" )

        alert: ( message, type = 'success' ) ->
            @$template.text( message ).addClass( type )
            @$template.appendTo( ".messages .columns" )
            @$template.slideDown()

            if type is "success"
                setTimeout( =>
                    @$template.slideUp( => @$template.remove() )
                , 5000 )

        clearAlert: ->
            $( ".messages .columns" ).empty()


    new Utils