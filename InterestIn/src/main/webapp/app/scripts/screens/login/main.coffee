define [
    "backbone.controller"
    "./view"
    "Mem"
    "modules/login"
    "core/api"
    "core/logged-user"
], ( Controller, View, Mem, LoginModule, api, LoggedUser ) ->
    class LoginScreenController extends Controller
        routes:
            "": "login"
            "login": "login"
            "logout": "logout"
            "register": "register"
            "activation/:key": "activation"

        onBeforeRoute: ->
            @loginModule = Mem.set( "loginModule", LoginModule )

            Mem.manage()

            return if @container

            @container = new View().render()

        login: ->
            if LoggedUser.isLogged()
                @navigate( "profile", trigger: true )
            else
                @loginModule.showLoginForm( @container.getContainer() )

        register: ->
            if LoggedUser.isLogged()
                @navigate( "profile", trigger: true )
            else
                @loginModule.showRegisterForm( @container.getContainer() )

        activation: ( key )->
            @loginModule.activation( key )

        logout: ->
            api.call( "logout" ).then( =>
                LoggedUser.clear()
                @navigate( "login", trigger: true )
            ).done()

        remove: ->
            @container = null