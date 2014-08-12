define [
    "core/presenter"
    "./views/overview"
], ( Presenter, FriendsView ) ->
    # This is main module file, it should bring together views and models, and expose API for using
    #
    class FriendsModule

        overview: ( $container ) ->
            Presenter.show( $container, new FriendsView().render().el )
