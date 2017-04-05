user.controller('userController', 
[
    'userFactory',
    'ForumFactory',
    'blogFactory',
    'jobFactory', 
    '$timeout', 
    '$cookies', 
    '$routeParams', 
    '$location', 
    
    function(userFactory, ForumFactory, blogFactory, jobFactory,  $timeout, $cookies, $routeParams, $location) {

    var self = this;

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

    // For posting date on blog
    self.birthDate = {};
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
                        
                        self.myblogs = blogs;
                        //Fomatting birthdate to display in the users info - nothing to do with blog list
                        user.birthDate =  new Date(user.birthDate[0],user.birthDate[1] - 1,user.birthDate[2]);
                    },
                    function(errResponse) {
                        console.log('Failure!');
                    }
                );
        

    }

    userJobList();

     //calling method to fetch user's jobs
     function userJobList() {
     debugger;
        var id = user.id;
        userFactory.userJobList(id)
                .then (
                    function(jobs) {
                        debugger;
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
                    self.joblist = jobs;
                    for(var [job] in self.joblist) {
                        // console.log(self.bloglist[blog].postDate);
                        self.joblist[job].postDate = new Date(self.joblist[job].postDate[0],self.joblist[job].postDate[1] - 1,self.joblist[job].postDate[2]);
                        // console.log(self.bloglist[blog].postDate);
                    }
                    console.log(self.joblist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    
    }])