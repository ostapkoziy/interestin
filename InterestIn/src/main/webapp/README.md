# Git Workflow

Main repository: https://bitbucket.org/Mindmarker/analytics

The repository consists of two branches:

1. master - this branch always contains a stable version and is used for production releases
2. develop - for testing purposes and deploys to the QA server will occur from this branch

Each developer should have his own fork. Within an individual development fork, the developer can create local branches for specific tasks or issues. Local branches shouldn't be pushed to main repo.
In order to synchronize the development form with the main repository,
a push should be done from local fork's develop to main repository's develop branch.
To push changes to the main repository, please use "Pull request" to develop branch only.

Before doing any pushes do not forget to pull latest changes from remote.

Sources: http://nvie.com/posts/a-successful-git-branching-model/

# Setup front end

1. Install node modules

```npm install```

2. Install Bower dependencies

```bower install```

### Development workflow
Developemnt workflow using Grunt, Bower and npm.

To get start working with app front end, run the following command:

```grunt serve```

This will open server on localhost:9000. Watch and Livereload is automatically enabled, so any changes in **CoffeeScript** of **SCSS** files will be automaticall compiled and browser tab will be refreshed.

To create app build ('dist' folder) run:

```grunt build```

### App technology stack

- Backbone.JS
- jQuery
- Underscore.JS
- Require.JS
- Handlebars
- Foundation CSS framework
- CoffeeScript
- SASS