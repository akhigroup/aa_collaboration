window.routes = {

    "/home": {
        templateUrl : 'app/components/authentication/authentication.html',
        controller : 'authenticationController'
    },

    "/user": {
        templateUrl : 'app/components/user/profile.html',
        controller : 'userController'
    }
}




codehub.config(['$routeProvider', function($routeProvider){

    //for page navigartion through url
    for(var path in window.routes) {
        $routeProvider.when(path, window.routes[path]); 
    }

    $routeProvider.otherwise({redirectTo: '/home'});
}]);