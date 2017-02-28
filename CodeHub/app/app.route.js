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

// codehub.run(function ($rootScope, $location, AuthenticationFactory) {

//     $rootScope.$on('$locationChangeStart', function (event, next, current) {
//         //$scope.$emit('LOAD');
//         // iterate through all the routes
//         for (var i in window.routes) {
//             // if routes is present make sure the user is authenticated 
//             // before login using the authentication service            
//             if (next.indexOf(i) != -1) {
//                 // if trying to access page which requires login and is not logged in 
//                 // $rootScope.user = AuthenticationFactory.loadUserFromCookie();
//                 console.log($rootScope.user);
//                 // $rootScope.authenticated = AuthenticationFactory.getUserIsAuthenticated();
//                 console.log($rootScope.authenticated);

//                 if (window.routes[i].requireLogin && !AuthenticationFactory.getUserIsAuthenticated()) {
//                     $location.path('/home');
//                 }
//                 // else if ((AuthenticationFactory.getUserIsAuthenticated())
//                     // &&
//                     // (window.routes[i].roles.indexOf(AuthenticationFactory.getRole()) == -1)) {
//                     // $location.path('/error');
//                 }
//             }
//         }
        
//     })

// });