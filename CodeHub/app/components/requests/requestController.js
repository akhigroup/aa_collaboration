request.controller('requestController', ['requestFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(requestFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

     //For storing list of pending users
    self.pendingUsers = [];

     //For fetching list of forumRequest with pending status
    self.forumRequest = [];

    //For list of pending blogs
    self.pendingBlogs = [];

      // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

      //Function to view list of all pending users
        self. pendingUserList = function() {

        // var status = "APPROVED"
        
        requestFactory.pendingUserList()
            .then (
                function(pendingUsers) {
               
                    self.pendingUsers = pendingUsers;
                    for(var [birthDate] in self.pendingUserList) {
                        self.pendingUserList[birthDate].birthDate = new Date(self.pendingUserList[birthDate].birthDate[0],self.pendingUserList[birthDate].birthDate[1] - 1,self.pendingUserList[birthDate].birthDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }

    //Function to change status of user registration
    self.changeStatus = function(id) {
        
        requestFactory.changeStatus(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
            
            //Function to fetch forum requests
            self.fetchForumRequests = function() {
                
                requestFactory.fetchForumRequests()
                    .then (
                        function(forumRequests) {
                           self.forumRequest = forumRequests;
                        },
                        function(errResponse) {
                        }
                    );

            }

            //Function to change status of forumRequests
            self.changeFRStatus = function(id) {
                
                requestFactory.changeFRStatus(id)
                    .then (
                        function(forumRequest) {
                            $route.reload();
                        },
                        function(errResponse) {
                        }
                    );
    }

     //Function to fetch pending blog list
    self.pendingBlogList = function() {
         requestFactory.pendingBlogList()
            .then (
                function(pendingBlogs) {
                    self.pendingBlogs = pendingBlogs;
                    for(var postDate in self.pendingBlogs) {
                        self.pendingBlogs[postDate].postDate = new Date(self.pendingBlogs[postDate].postDate[0],self.pendingBlogs[postDate].postDate[1] - 1,self.pendingBlogs[postDate].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );

    }

    //Function to change blog status
    self.changeBlogStatus = function(id) {
        debugger;
         requestFactory.changeBlogStatus(id)
            .then (
                function(blog) {
                    $route.reload();
                    Materialize.toast('Blog Approved!', 2000);
                    pendingBlogList();
                     
                },
                function(errResponse) {
                }
            );
    }

        
    
}])