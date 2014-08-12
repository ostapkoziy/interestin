define [
    "jquery"
    "underscore"    
    "templates"
], ( $, _, JST ) ->

    class submitButton
        template: JST[ "app/scripts/shared/submit-button/template.hbs" ]
       
        defaultSettings:
            buttonText: "Save"
            
        constructor: ( params = {} ) ->
            @settings = _.defaults( params, @defaultSettings )
            
        render: ->            
            @$el = $( @template( @settings ) )

            return @$el

        stateLoad:  ->
            # Selector string as param
            #            
            @$el.addClass( "state-loading" )
            buttonWidth = @$el.width()
                        
            setInterval ( =>            
                progressWidth = @$el.find( ".progress-inner" ).width()              
                
                if ( parseInt( progressWidth, 10 ) > parseInt( buttonWidth, 10 ) )                                      
                    @$el.find( ".progress-inner" ).remove()
                    @$el.find( ".progress" ).append( '<span class="progress-inner" style="width : 0; opacity : 1"></span>' )
                else                    
                    @$el.find( ".progress-inner" ).width( progressWidth + 100 )                   
            ), 10

        stateFinishLoad: ->            
            @$el.removeClass( "state-loading" )            
     