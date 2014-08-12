# The hostMapping module can be used to retrieve the correct server name and
# configuration depending on your environment.
# The corresponding settings will need to be provided in the root settings module
#
define [
    "core/settings"
], ( settings ) ->

    # Initialise the hostMapping and hostConfig settings
    #
    # An example configuration could look like this:
    #
    #   hostMapping:
    #       "www.myhost.com":   "production"
    #       "acc.myhost.com":   "acceptance"
    #       "tst.myhost.com":   "testing"
    #       "localhost":        "development"
    #
    #   hostConfig:
    #       "production":
    #           "api":      "https://api.myhost.com"
    #           "content":  "http://www.myhost.com"
    #       "acceptance":
    #           "api":      "https://api-acc.myhost.com"
    #           "content":  "http://acc.myhost.com"
    #       "testing":
    #           "api":      "https://api-tst.myhost.com"
    #           "content":  "http://tst.myhost.com"
    #       "development":
    #           "api":      "https://api-tst.myhost.com"
    #           "content":  "http://localhost"
    #

    # Setup host mappings
    #
    settings.set( "hostMapping",
        "localhost":                "development"
        "add-test-url":             "testing"
        "add-prod-url":             "production"
    )

    settings.set( "hostConfig",
        "testing":
            "api": ""
        "production":
            "api": ""
        "development":
            # "api": "http://192.168.152.1:8080/InterestIn/api"
            # "spring": "http://192.168.152.1:8080/InterestIn"
            "api": "http://localhost:8080/InterestIn/api"
            "spring": "http://localhost:8080/InterestIn"
    )

    class HostMapping
        overrideMapping: ( newMapping ) ->
            settings.set( "currentHostMapping", newMapping )

        determineTarget: ( hostname ) ->
            # Non browser environments or developer builds will want to override
            # detected settings
            #
            overrideMapping = settings.get( "overrideMapping" )
            if overrideMapping?
                settings.set( "currentHostMapping", overrideMapping )

                console.log( "[hostMapping] Override active: #{settings.get('overrideMapping')}" )
            else
                allHostMappings = settings.get( "hostMapping" )

                # Determine target based on hostname for browsers and mobile web views
                #
                if not hostname? and document?
                    hostname = document.location.hostname if document?

                if allHostMappings[ hostname ]?
                    settings.set( "currentHostMapping", allHostMappings[ hostname ] )
                    console.log( "[hostMapping] Target found: #{settings.get('currentHostMapping')}" )

                else
                    settings.set( "currentHostMapping", "production" )
                    console.log( "[hostMapping] No target found, defaulting to production" )

        getHostName: ( hostType = "api" ) ->
            # Determine the target environment if needed
            #
            @determineTarget() if not settings.get( "currentHostMapping" )?

            allHostConfigs     = settings.get( "hostConfig" )
            currentHostMapping = settings.get( "currentHostMapping" )

            return allHostConfigs[ currentHostMapping ][ hostType ]

        extractHostName: ( url ) ->
            url.replace( /(^https?:)?\/\//, "" ).split( "/" ).slice( 0, 1 ).pop().split( ":" ).slice( 0, 1 ).pop()

    return new HostMapping()