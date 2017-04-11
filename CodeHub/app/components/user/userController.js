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

    //calling list of user's blogs
    self.myblogs = [];

    //array for job list
    self.joblist = [];

    //array for jobs created by user
    self.myjobs = [];

    //For fetching events
    self.eventlist = [];

    //Fetching list of events created by user
    self.myEvents = [];

    self.picture = undefined;

    self.user.profile = self.user.profile + '?decached=' + Math.random();

    self.hasApplied = false;

    self.jobsApplied = [];

    self.appliedJobCount = []; 

    self.appliedFor = [];

    self.eventsJoinedList = [];

    self.joinedEventCount = [];
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
            
     userBlogList();

     //calling method to fetch user's blogs
     function userBlogList() {
        
        var id = user.id;
        userFactory.userBlogList(id)
                .then (
                    function(blogs) {
                        debugger;
                        self.myblogs = blogs;
                        //Fomatting birthdate to display in the users info - nothing to do with blog list
                        debugger;
                        console.log(user.birthDate);
                        user.birthDate =  new Date(user.birthDate[0],user.birthDate[1] - 1,user.birthDate[2]);
                        $rootScope.user.birthDate = user.birthDate;
                    },
                    function(errResponse) {
                        console.log('Failure!');
                    }
                );
        

    }

    userJobList();

     //calling method to fetch user's jobs
     function userJobList() {
        var id = user.id;
        userFactory.userJobList(id)
                .then (
                    function(jobs) {
                         self.myjobs = jobs;
                         console.log(self.myjobs);
                    },
                    function(errResponse) {
                        console.log('Failure!');
                    }
                );
     }

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

     //calling method to fetch user's blogs
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

     //calling method to fetch user's jobs
     self.fetchJobsApplied = function() {
        debugger;
        var id = user.id;
        userFactory.fetchJobsApplied(id)
                .then (
                    function(jobs) {
                        
                        self.jobsApplied = jobs;
                        self.appliedJobCount = self.jobsApplied.length;
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

     //calling method to fetch events user applied for
     self.fetchEventJoined = function() {
        debugger;
        var id = user.id;
        userFactory.fetchEventJoined(id)
                .then (
                    function(events) {
                        self.eventsJoinedList = events;
                        self.joinedEventCount =  self.eventsJoinedList.length;

                        for(var [events] in  self.eventsJoinedList) {
                         self.eventsJoinedList[events].postDate = new Date( self.eventsJoinedList[events].postDate[0], self.eventsJoinedList[events].postDate[1] - 1, self.eventsJoinedList[events].postDate[2]);
                        console.log(  self.eventsJoinedList[events].postDate)    
                        }
                        for(var [startDate] in  self.eventsJoinedList) {
                             self.eventsJoinedList[startDate].startDate = new Date( self.eventsJoinedList[startDate].startDate[0], self.eventsJoinedList[startDate].startDate[1] - 1, self.eventsJoinedList[startDate].startDate[2]);
                        }
                        for(var [endDate] in  self.eventsJoinedList) {
                             self.eventsJoinedList[endDate].endDate = new Date( self.eventsJoinedList[endDate].endDate[0], self.eventsJoinedList[endDate].endDate[1] - 1, self.eventsJoinedList[endDate].endDate[2]);
                        }
                    },
                    function(errResponse) {
                        
                    }
                );
     }


}])