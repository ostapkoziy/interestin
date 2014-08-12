define [
    "./models/user"
    "./views/login"
    "./views/register"
    "core/api"
], ( UserModel, LoginView, RegisterView, api ) ->
    class LoginModule

        showLoginForm: ( $container ) ->
            $container.html( new LoginView().render().el )

        showRegisterForm: ( $container ) ->
            userModel = new UserModel()

            $container.html( new RegisterView( model: userModel ).render().el )

        activation: ( key ) ->
            api.call( "activate", key ).then(
                    ( data ) =>
                        Backbone.history.navigate( "login", trigger: true )
                    ( error ) ->
                        utils.alert( "Activation key is invalid.", "warning" )
                ).done()