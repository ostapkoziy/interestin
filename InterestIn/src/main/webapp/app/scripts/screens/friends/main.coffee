define [
    "backbone.controller"
    "./view"
    "Mem"
    "modules/friends"
], ( Controller, View, Mem, FriendModule ) ->
    class FriendScreenController extends Controller
        routes:
            "friends": "overview"

        onBeforeRoute: ->
            @friendModule = Mem.set( "friendModule", FriendModule )

            Mem.manage()

            return if @container

            @container = new View().render()

        overview: ->
            @friendModule.overview( @container.getContainer() )

        remove: ->
            @container = null