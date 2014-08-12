define [
    "backbone"
    "screens/login"
    "screens/profile"
    "screens/message"
    "screens/friends"
], ( Backbone, LoginController, ProfileController, MessageController, FriendsController ) ->
    class Router extends Backbone.Router

        controllers: {}

        initialize: ->
            @controllers.login = new LoginController( router: @ )
            @controllers.profile = new ProfileController( router: @ )
            @controllers.messages =  new MessageController( router: @ )
            @controllers.friends =  new FriendsController( router: @ )