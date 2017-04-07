admin.controller('adminController', ['adminFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(adminFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

    //For storing list of pending users
    self.pendingUserList = [];

    //For fetching list of forumRequest with pending status
    self.forumRequest = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

     //calling method for bloglist
     pendingUserList();

    //Function to view list of all pending users
    function pendingUserList() {

        // var status = "APPROVED"
        
        adminFactory.pendingUserList()
            .then (
                function(pendingUsers) {
               
                    self.pendingUserList = pendingUsers;
                    for(var [birthDate] in self.pendingUserList) {
                        self.pendingUserList[birthDate].birthDate = new Date(self.pendingUserList[birthDate].birthDate[0],self.pendingUserList[birthDate].birthDate[1] - 1,self.pendingUserList[birthDate].birthDate[2]);
                    }
                    // console.log(self.bloglist.postDate);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }

    //Function to change status of user registration
    self.changeStatus = function(id) {
        console.log(id);
        console.log('test');
        
        adminFactory.changeStatus(id)
            .then (
                function(user) {
                    console.log('success!');
                    $route.reload();
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }

     //calling method to fetch forum fetchForumRequests
    fetchForumRequests();
            
            //Function to fetch forum requests
            function fetchForumRequests() {
                
                adminFactory.fetchForumRequests()
                    .then (
                        function(forumRequests) {
                            
                           self.forumRequest = forumRequests;
                           console.log(self.forumRequest);
                        },
                        function(errResponse) {
                            console.error('Failure!');
                        }
                    );

            }

            //Function to change status of forumRequests
            self.changeFRStatus = function(id) {
                
                adminFactory.changeFRStatus(id)
                    .then (
                        function(forumRequest) {
                            
                            console.log('success!');
                            $route.reload();
                        },
                        function(errResponse) {
                            console.log('Failure!');
                        }
                    );
    }
        
    
}])