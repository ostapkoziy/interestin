define [
    "./menu"
    "templates"
], ( Menu, JST ) ->
    class AdminMenu extends Menu
        template: JST[ "app/scripts/modules/navigation/views/templates/admin-menu.hbs" ]