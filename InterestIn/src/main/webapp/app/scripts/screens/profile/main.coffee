define [
    "backbone.controller"
    "./view"
    "Mem"
    "modules/profile"
], ( Controller, View, Mem, ProfileModule ) ->
    class ProfileScreenController extends Controller
        routes:
            "profile": "profile"
            "profile/edit": "edit"

        onBeforeRoute: ->
            @profileModule = Mem.set( "profileModule", ProfileModule )

            Mem.manage()

            return if @container

            @container = new View().render()

        profile: ->
            @profileModule.show( @container.getContainer() )

        edit: ->
            @profileModule.edit( @container.getContainer() )

        remove: ->
            @container = null