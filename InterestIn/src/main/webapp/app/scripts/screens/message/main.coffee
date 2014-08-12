define [
    "backbone.controller"
    "./view"
    "Mem"
    "modules/message"
], ( Controller, View, Mem, MessageModule ) ->
    class MessageScreenController extends Controller
        routes:
            "messages": "overview"

        onBeforeRoute: ->
            @messageModule = Mem.set( "messageModule", MessageModule )

            Mem.manage()

            return if @container

            @container = new View().render()

        overview: ->
            @messageModule.overview( @container.getContainer() )

        remove: ->
            @container = null