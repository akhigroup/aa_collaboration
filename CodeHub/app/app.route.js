window.routes = {

    //For home page
    "/home": {
        templateUrl : 'app/components/authentication/authentication.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: false,
        roles: ['GUEST']
    },

    //For user home page
    "/user": {
        templateUrl : 'app/components/user/profile.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For user's profile
     "/user/profile": {
        templateUrl : 'app/components/user/userProfile.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For assigning role to the user and to update or delete user
    "/manage/users": {
        templateUrl : 'app/components/user/manageUser.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin']
    },

    //For accepting requests of user
    "/requests/users": {
        templateUrl : 'app/components/user/userRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },


    //Form for creating new blog
    "/blog/new": {
        templateUrl : 'app/components/blogs/newBlog.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For viewing single blog
    "/blog/:id": {
        templateUrl : 'app/components/blogs/blog.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },
     
    //For viewing list of all blogs
     "/blogs/all": {
        templateUrl : 'app/components/blogs/bloglist.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For updating or deleting any blog
     "/manage/blogs": {
        templateUrl : 'app/components/blogs/manageBlogs.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting request of blogs
    "/requests/blogs": {
        templateUrl : 'app/components/blogs/blogRequests.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

     //For viewing list of forum categories and adding a new one
     "/forum/categories/list": {
        templateUrl : 'app/components/forum/forumCategories.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['Super_Admin']
    },

    //For viewing list of forum topics
     "/forum/topics/list": {
        templateUrl : 'app/components/forum/topicList.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For viewing single forum topic
    "/forum/topic": {
        templateUrl : 'app/components/forum/topic.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //Form for creating new forum topic
    "/forum/topic/new": {
        templateUrl : 'app/components/forum/newTopic.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For managing forum topics
    "/manage/forums": {
        templateUrl : 'app/components/forum/manageForums.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting request of forum topics
    "/requests/forums": {
        templateUrl : 'app/components/forum/forumRequests.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For viewing event list
    "/events/list": {
        templateUrl : 'app/components/events/eventlist.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //Form for creating new event
    "/event/new": {
        templateUrl : 'app/components/events/newEvent.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin', 'Employer']
    },

    //For updating and deleting event
     "/manage/events": {
        templateUrl : 'app/components/events/manageEvents.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting new event requests 
    "/requests/events": {
        templateUrl : 'app/components/events/eventsRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For viewing job list
    "/jobs/list": {
        templateUrl : 'app/components/jobs/joblist.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //Form for creating new job
    "/job/new": {
        templateUrl : 'app/components/jobs/newJob.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin', 'Employer']
    },

    //For updating and deleting jobs
     "/manage/jobs": {
        templateUrl : 'app/components/jobs/manageJobs.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting request of new job
     "/requests/jobs": {
        templateUrl : 'app/components/jobs/jobsRequests.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For navigating to error page
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
            if (next.indexOf(i) != -1 || (i.indexOf("/:id") != -1 )) {
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

