define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "core/logged-user"
    "modules/navigation"
], ( $, _, Backbone, JST, LoggedUser, NavigationModule ) ->
    class MainLayout extends Backbone.View
        el: "#wrap"
        layoutName: "main"
        template: JST[ "app/scripts/layouts/main/template.hbs" ]

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

                @navigationModule = Mem.set( "navigationModule", NavigationModule )

                # Take only the first part of fragment to search needed menu item
                #
                fragment = Backbone.history.fragment

                if fragment.indexOf( "/" ) > -1
                    fragment = fragment.substr( 0, fragment.indexOf( "/" ) )

                # Render menu and navigation
                #
                @navigationModule.setModel( LoggedUser )
                @navigationModule.showMenu( @getMenuContainer(), fragment )
                @navigationModule.showNavigation( @getNavigationContainer() )

            return this

        needsRendering: -> @needRender

        getContainer: ->
            @$( "#main-content" )

        getMenuContainer: ->
            @$( "#menu" )

        getNavigationContainer: ->
            @$( "#navigation" )