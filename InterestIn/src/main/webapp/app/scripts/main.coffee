#/*global require*/
'use strict'

require.config
  shim:
    "underscore":
      exports: "_"
    "backbone":
      deps: [
        "underscore"
        "jquery"
      ]
      exports: "Backbone"
    "backbone.validation":
      deps: [
        "backbone"
      ]
    "backbone.controller":
      deps: [
        "underscore"
        "backbone"
      ]
    "mem":
      deps: [
        "underscore"
      ]
      exports: "Mem"
    "handlebars":
      exports: "Handlebars"
    "modernizr":
      exports: 'Modernizr'
    "foundation":
      deps: [
        "jquery"
        "modernizr"
      ]
      exports: "Foundation"
    "foundation.reveal":
      deps: [
        "foundation"
      ]
    "foundation.tooltip":
      deps: [
        "foundation"
      ]
    "jquery.serializeObject":
      deps: [
        "jquery"
      ]
  paths:
    "jquery": "../bower_components/jquery/jquery"
    "jquery.serializeObject": "../bower_components/jQuery.serializeObject/jquery.serializeObject"
    "jquery.ui": "../bower_components/jquery-ui/ui/jquery-ui"
    "backbone": "../bower_components/backbone/backbone"
    "backbone.validation": "../bower_components/backbone-validation/dist/backbone-validation-amd"
    "backbone.pageable": "../bower_components/backbone-pageable/lib/backbone-pageable"
    "backbone.controller": "vendor/backbone.controller"
    "Mem": "vendor/mem"
    "underscore": "../bower_components/underscore/underscore"
    "handlebars": "../bower_components/handlebars/handlebars"
    "modernizr": "../bower_components/modernizr/modernizr"
    "foundation": "../bower_components/foundation/js/foundation/foundation"
    "foundation.reveal": "../bower_components/foundation/js/foundation/foundation.reveal"
    "foundation.tooltip": "../bower_components/foundation/js/foundation/foundation.tooltip"

  packages: [
    # Modules
    #
    "modules/login"
    "modules/navigation"
    "modules/profile"
    "modules/message"
    "modules/friends"

    # Shared modules
    #
    "shared/submit-button"
    "shared/dialog"

    # Screens
    #
    "screens/login"
    "screens/profile"
    "screens/message"
    "screens/friends"

    # Layouts
    #
    "layouts/main"
    "layouts/full-width"
  ]

require [
  "jquery"
  "backbone"
  "backbone.validation"
  "backbone.pageable"
  "modernizr"
  "foundation"
  "jquery.serializeObject"
  "core/setup"
  "core/logged-user"
  "core/router"
], ($, Backbone, BackboneValidation, BackbonePageable, Modernizr, Foundation, jQuerySerializeObject, setup, LoggedUser, Router ) ->
  # Initialize foundation core
  #
  $( document ).foundation()

  # Initialize routing
  #
  new Router()

  # Fetch account details
  #
  # LoggedUser.fetch().then( ->

    # And now we can start the application
    #
  Backbone.history.start()
  # )