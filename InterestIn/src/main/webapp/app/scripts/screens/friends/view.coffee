define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "layouts/main"
], ( $, _, Backbone, JST, MainLayout ) ->
    class MessageScreen extends MainLayout