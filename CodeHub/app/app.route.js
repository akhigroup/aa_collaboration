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

    "/manage/users": {
        templateUrl : 'app/components/user/manageUser.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/requests/users": {
        templateUrl : 'app/components/user/userRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/blog": {
        templateUrl : 'app/components/blogs/blog.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/blog/new": {
        templateUrl : 'app/components/blogs/newBlog.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/blogs/all": {
        templateUrl : 'app/components/blogs/bloglist.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/manage/blogs": {
        templateUrl : 'app/components/blogs/manageBlogs.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/requests/blogs": {
        templateUrl : 'app/components/blogs/blogRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/forum/topics/list": {
        templateUrl : 'app/components/forum/topicList.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/forum/topic": {
        templateUrl : 'app/components/forum/topic.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/forum/topic/new": {
        templateUrl : 'app/components/forum/newTopic.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/manage/forums": {
        templateUrl : 'app/components/forum/manageForums.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/requests/forums": {
        templateUrl : 'app/components/forum/forumRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/events/list": {
        templateUrl : 'app/components/events/eventlist.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/event/new": {
        templateUrl : 'app/components/events/newEvent.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/manage/events": {
        templateUrl : 'app/components/events/manageEvents.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/requests/events": {
        templateUrl : 'app/components/events/eventsRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/jobs/list": {
        templateUrl : 'app/components/jobs/joblist.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    "/job/new": {
        templateUrl : 'app/components/jobs/newJob.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/manage/jobs": {
        templateUrl : 'app/components/jobs/manageJobs.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     "/requests/jobs": {
        templateUrl : 'app/components/jobs/jobsRequests.html',
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

