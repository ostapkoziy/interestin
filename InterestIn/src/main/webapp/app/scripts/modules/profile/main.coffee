define [
    "core/presenter"
    "core/logged-user"
    "./models/profile"
    "./views/profile"
    "./views/edit"
], ( Presenter, AccountModel, ProfileModel, ProfileView, EditProfileView ) ->
    # This is main module file, it should bring together views and models, and expose API for using
    #
    class ProfileModule

        show: ( $container ) ->
            profile =  new ProfileModel()

            Presenter.show( $container, new ProfileView( model: profile ).el )

            profile.fetch()

        edit: ( $container ) ->
            profile =  new ProfileModel()

            Presenter.show( $container, new EditProfileView( model: profile ).el )

            profile.fetch()

