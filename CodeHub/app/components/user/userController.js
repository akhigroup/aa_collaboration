user.controller('userController', 
[
    'userFactory',
    'AuthenticationFactory',
    'ForumFactory',
    'blogFactory',
    'jobFactory',
    'eventFactory', 
    '$timeout', 
    '$cookies', 
    '$routeParams', 
    '$location',
    '$rootScope',
    '$route', 
    
    function(
        userFactory, 
        AuthenticationFactory, 
        ForumFactory, 
        blogFactory, 
        jobFactory, 
        eventFactory, 
        $timeout, 
        $cookies, 
        $routeParams, 
        $location,
        $rootScope,
        $route) {

    var self = this;

    //Load user from cookie
    self.user = AuthenticationFactory.loadUserFromCookie();

     // calling list of forums
    self.forums = [];

    //calling list of blogs
    self.bloglist = [];

    //array for job list
    self.joblist = [];

    //For fetching events
    self.eventlist = [];

    //Fetching list of events created by user
    self.myEvents = [];

    self.picture = undefined;

    self.user.profile = self.user.profile + '?decached=' + Math.random();

    self.hasApplied = false;

    self.appliedJobCount = []; 

    self.appliedFor = [];

    self.joinedEventCount = [];

    self.user = [];
     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

     //calling method in formfactory to fetch forums
     ForumFactory.fetchForums().then(
                        function(forums) {
                            self.forums = forums;
                            console.log(self.forums);
                    }, function(errResponse) {
                            console.log('Failure!');
                        }
                    );

    //calling method in blogfactory to fetch blogs
     blogFactory.bloglist()
            .then (
                function(blogs) {
                    self.bloglist = blogs;
                    for(var [blog] in self.bloglist) {
                        // console.log(self.bloglist[blog].postDate);
                        self.bloglist[blog].postDate = new Date(self.bloglist[blog].postDate[0],self.bloglist[blog].postDate[1] - 1,self.bloglist[blog].postDate[2]);
                        // console.log(self.bloglist[blog].postDate);
                    }
                    console.log(self.bloglist.postDate);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
            


    jobFactory.joblist()
            .then (
                function(jobs) {   
                    debugger;
                    self.joblist = jobs;
                    for(var [job] in self.joblist) {
                        // console.log(self.bloglist[blog].postDate);
                        self.joblist[job].postDate = new Date(self.joblist[job].postDate[0],self.joblist[job].postDate[1] - 1,self.joblist[job].postDate[2]);
                    }
                    // console.log(self.joblist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );

        //calling the function in the event factory
        eventFactory.eventlist()
            .then (
                function(events) {
                    debugger;
                    self.eventlist = events;
                    
                    for(var [events] in self.eventlist) {
                        self.eventlist[events].postDate = new Date(self.eventlist[events].postDate[0],self.eventlist[events].postDate[1] - 1,self.eventlist[events].postDate[2]);
                        console.log( self.eventlist[events].postDate)    
                    }
                     for(var [startDate] in self.eventlist) {
                        self.eventlist[startDate].startDate = new Date(self.eventlist[startDate].startDate[0],self.eventlist[startDate].startDate[1] - 1,self.eventlist[startDate].startDate[2]);
                    }
                     for(var [endDate] in self.eventlist) {
                        self.eventlist[endDate].endDate = new Date(self.eventlist[endDate].endDate[0],self.eventlist[endDate].endDate[1] - 1,self.eventlist[endDate].endDate[2]);
                    }
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );

    
    userEventList();

     //calling method to fetch user's events
     function userEventList() {
    
        var id = user.id;
        userFactory.userEventList(id)
                .then (
                    function(events) {
                        self.myEvents = events;
                    },
                    function(errResponse) {
                        console.log('Failure!');
                    }
                );
        

    }

    // to upload the file    
    self.uploadFile = function () {
        if(self.picture == undefined) {
            return;
        }    
    	
        userFactory.uploadFile(self.picture)
        .then(
            function(response){
                debugger;
                $rootScope.message = 'Profile picture updated successfully!';
                self.user.profile = response.message + '?decached=' + Math.random();
                // update the controller user too
                $rootScope.user.profile = response.message + '?decached=' + Math.random();
                // need to update the cookie value too
                AuthenticationFactory.saveUser($rootScope.user);
                 $('#change-photo').modal('close');
                // hide the card panel by setting the rootScope.message as undefined
                // $timeout(function() {                    
                //     $rootScope.message = undefined;
                // },2000);

            },
            function(error){
                console.log(error);
            } 
        )
    

    }

    //Method to apply for job
    self.applyJob = function(id) {
          jobFactory.applyJob(id)
            .then (
                function(job) {
                debugger;
                 $route.reload();
                 Materialize.toast('Applied for job successfully!', 3000);
                 self.appliedFor.push(id);  
                },
                function(errResponse) {
                }
            );
    }

    //Method to join event
    self.joinEvent = function(id) {
          eventFactory.joinEvent(id)
            .then (
                function(event) {
                debugger;
                 $route.reload();
                 Materialize.toast('Event joined successfully!', 3000);
                 self.appliedFor.push(id);  
                },
                function(errResponse) {
                }
            );
    }

     //function to fetch user and user detail
     self.fetchUser = function() {
         debugger;
         var id = $routeParams.id;
          userFactory.fetchUser(id)
                .then (
                    function(user) {
                        debugger;
                        self.user = user;
                        self.user.user.birthDate = new Date( self.user.user.birthDate[0], self.user.user.birthDate[1] - 1, self.user.user.birthDate[2]);

                        self.joinedEventCount = self.user.joinedEvents.length;
                        self.appliedJobCount = self.user.appliedJobList.length;

                        for(var [postDate] in self.user.events) {
                        self.user.events[postDate].postDate = new Date(self.user.events[postDate].postDate[0],self.user.events[postDate].postDate[1] - 1,self.user.events[postDate].postDate[2]);   
                        }
                        for(var [startDate] in self.user.events) {
                            self.user.events[startDate].startDate = new Date(self.user.events[startDate].startDate[0],self.user.events[startDate].startDate[1] - 1,self.user.events[startDate].startDate[2]);
                        }
                        for(var [endDate] in self.user.events) {
                            self.user.events[endDate].endDate = new Date(self.user.events[endDate].endDate[0],self.user.events[endDate].endDate[1] - 1,self.user.events[endDate].endDate[2]);
                        }
                         for(var [postDate] in self.user.joinedEvents) {
                        self.user.joinedEvents[postDate].postDate = new Date(self.user.joinedEvents[postDate].postDate[0],self.user.joinedEvents[postDate].postDate[1] - 1,self.user.joinedEvents[postDate].postDate[2]);   
                    }
                     for(var [startDate] in self.user.joinedEvents) {
                            self.user.joinedEvents[startDate].startDate = new Date(self.user.joinedEvents[startDate].startDate[0],self.user.joinedEvents[startDate].startDate[1] - 1,self.user.joinedEvents[startDate].startDate[2]);
                        }
                        for(var [endDate] in self.user.joinedEvents) {
                            self.user.joinedEvents[endDate].endDate = new Date(self.user.joinedEvents[endDate].endDate[0],self.user.joinedEvents[endDate].endDate[1] - 1,self.user.joinedEvents[endDate].endDate[2]);
                        }
                    
                    
                    },
                    function(errResponse) {
                        
                    }
                );
     }


}])