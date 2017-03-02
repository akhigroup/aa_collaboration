window.routes = {

    "/home": {
        templateUrl : 'app/components/authentication/authentication.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: false,
        roles: ['GUEST']
    },

    "/user": {
        templateUrl : 'app/components/user/profile.html',
        controller : 'userController',
        controllerAs : 'userCtrl',
        requireLogin: true,
        roles: ['USER']
    }
}




codehub.config(['$routeProvider', function($routeProvider){

    //for page navigartion through url
    for(var path in window.routes) {
        $routeProvider.when(path, window.routes[path]); 
    }

    $routeProvider.otherwise({redirectTo: '/home'});

    // $locationProvider.hashPrefix('!');
}]);

//Rest server from where data is coming
codehub.constant('url', 'http://localhost:7070/webapp');

codehub.run(function ($rootScope, $location, AuthenticationFactory) {

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // For interating through all the routes
        for (var i in window.routes) {          
            if (next.indexOf(i) != -1) {
 
                $rootScope.user = AuthenticationFactory.loadUserFromCookie();
                console.log($rootScope.user);
                $rootScope.authenticated = AuthenticationFactory.getUserIsAuthenticated();
                console.log($rootScope.authenticated);

                //if trying to access page that requires login and user is not authenticated redirect to login page
                if (window.routes[i].requireLogin && !AuthenticationFactory.getUserIsAuthenticated()) {
                    $location.path('/home');
                }
                
            }
        }
        
    });

    $rootScope.logout = function() {
    //calling the log out function created in the AuthenticationFactory
    AuthenticationFactory.logout($rootScope.user).then(
        function() {
            debugger;
            AuthenticationFactory.setUserIsAuthenticated(false);
            $rootScope.authenticated = false;
            $rootScope.message = "Logout successfully!";
            $location.path('/home');
        }
    );
};

});

