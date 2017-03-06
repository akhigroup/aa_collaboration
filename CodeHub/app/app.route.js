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
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/user/profile": {
        templateUrl : 'app/components/user/userProfile.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/error": {
        templateUrl : 'app/components/authentication/error.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: false,
        roles: ['GUEST']
    }
};




codehub.config(['$routeProvider', '$httpProvider',  function($routeProvider, $httpProvider){

     

    //for page navigartion through url
    for(var path in window.routes) {
        $routeProvider.when(path, window.routes[path]); 
    }
    
    $routeProvider.otherwise({redirectTo: '/home'});

     
}]);

//Rest server from where data is coming
// codehub.constant('url', 'http://localhost:7070/webapp');

codehub.run(function ($rootScope, $location, AuthenticationFactory) {

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // For interating through all the routes
        for (var i in window.routes) {          
            if (next.indexOf(i) != -1) {
 
                $rootScope.user = AuthenticationFactory.loadUserFromCookie();
                $rootScope.authenticated = AuthenticationFactory.getUserIsAuthenticated();


                //if trying to access page that requires login and user is not authenticated redirect to login page
                
                if (window.routes[i].requireLogin && !AuthenticationFactory.getUserIsAuthenticated()) {
                    $location.path('/home');
                } 
                else if((AuthenticationFactory.getUserIsAuthenticated()) 
                        &&
                        (window.routes[i].roles.indexOf(AuthenticationFactory.getRole())==-1)) {
                        $location.path('/error');
                        }
                
            }
        }
        
    });

    $rootScope.logout = function() {
    //calling the log out function created in the AuthenticationFactory
    AuthenticationFactory.logout($rootScope.user).then(
        function() {
            
            AuthenticationFactory.setUserIsAuthenticated(false);
            $rootScope.authenticated = false;
            $rootScope.message = "Logout successfully!";
            $location.path('/home');
        }
    );
};

});

