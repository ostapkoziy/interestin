define [
    "core/presenter"
    "./views/overview"
], ( Presenter, MessagesView ) ->
    # This is main module file, it should bring together views and models, and expose API for using
    #
    class MessageModule

        overview: ( $container ) ->
            Presenter.show( $container, new MessagesView().render().el )
