define [
    "jquery"
    "underscore"
    "backbone"
    "templates"
    "layouts/full-width"
], ( $, _, Backbone, JST, FullWidthLayout ) ->
    class LoginScreen extends FullWidthLayout