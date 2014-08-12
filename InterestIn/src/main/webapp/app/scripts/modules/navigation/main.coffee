define [
    "./views/menu"
    "./views/admin-menu"
    "./views/navigation"
    "shared/dialog"
], ( MenuView, AdminMenuView, NavigationView, Dialog ) ->
    class NavigationModule
        setModel: ( @model ) ->

        showMenu: ( $container, activeItem ) ->
            @menu = new MenuView().render()
            @menu.setActive( activeItem )
            $container.html( @menu.el )

            @menu.on( "logout", @confirmLogout )

        showNavigation: ( $container ) ->
            navigation = new NavigationView( model: @model )

            navigation.on( "action", ( href ) =>
                @menu.setActive( href.substr( 1 ) )
            ) if @menu

            navigation.on( "logout", @confirmLogout )

            $container.html( navigation.render().el )

        confirmLogout: ->
            dialog = new Dialog(
                title: "Confirmation"
                body: "You are about to log out. Are you sure?"
                type: "tiny"
                onConfirm: =>
                    Backbone.history.navigate( "logout", trigger: true )

                    dialog.close()
            )

            dialog.open()