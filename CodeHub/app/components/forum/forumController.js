forum.controller('forumController', ['ForumFactory', '$timeout', '$cookies', '$routeParams', '$location', '$route', '$q',
function(ForumFactory, 
        $timeout, $cookies, $routeParams, $location, $route, $q) {

            var self = this;

            //Setting up the field for creating new forum category
            self.Forum = {

                id : null,
                name : '',
                description : '',
                postDate : '',
                userId : '',
                userName : ''
            }


            //array for displaying list of forum categories
            self.forums = [];

             // For viewing single forum
             self.singleForum = {};

             //For list of participated users
             self.participatedUsers = []; 

             //Flag to see whether user is particant or not
            self.isParticipant = false;

            //Flag to check request status
            self.isApproved = false;

            //For storing participant status
            self.participantStatus = "PENDING";

            // calling jQuery once controller has loaded
            $timeout(function () {
                setting();
            }, 100);

            //method for adding new category
            self.addForum = function() {
                
                //Setting the user id and username
                self.Forum.userId = user.id;
                self.Forum.userName = user.username;
                
                ForumFactory.addForum(self.Forum) 
                        .then(
                            function(forum) {
                                
                                self.Forum = forum;
                                $route.reload();
                                $('#category').modal('close');
                            }, function(errResponse) {
                                
                            }
                        );
            }

            fetchForums();
            //method to fetch all the forum categories
            function fetchForums() {
                
                
                ForumFactory.fetchForums().then(
                        function(forums) {
                            self.forums = forums;
                    }, function(errResponse) {
                        }
                    );
            }

            //function for viewing single forum
            self.viewForum = function() {
                //Method for fetching particapted user list
                 getParticipatedUsers().then(
                        function(participatedUsers){
                            self.participatedUsers = participatedUsers;
                            debugger;
                             console.log(self.isParticipant);
                            for(var id in self.participatedUsers) {
                                console.log(self.participatedUsers[id].userId);
                                if(user.id == self.participatedUsers[id].userId) { //If user id matches with those of participant user set the flag as true
                                    self.isParticipant = true;
                                    console.log(self.participatedUsers[id].status);
                                    self.participantStatus = self.participatedUsers[id].status;                       
                                    break;                     
                                    // console.log(self.isParticipant);
                                }
                            
                            }
                            if(self.participantStatus == "APPROVED") {    //if user is participant
                                         self.isApproved = true;
                            }

                            //Assigning forum id to variable forumId
                            var forumId = $routeParams.id;
                            ForumFactory.viewForum(forumId)
                                .then (
                                    function(forum) {
                                        self.singleForum = forum;
                                        self.singleForum.postDate = new Date(self.singleForum.postDate[0],self.singleForum.postDate[1] - 1,self.singleForum.postDate[2]);
                                    },
                                    function(errResponse) {
                                    }
                                );
                        } 
                 );

            }

            //Function to send forum join request
            self.joinRequest = function() {
                 ForumFactory.joinRequest()
                    .then (
                        function(forum) {
                            $route.reload();
                        },
                        function(errResponse) {
                        }
                    );
            }

            //Function to fetch the list of participated users
            function getParticipatedUsers() {
                var deferred = $q.defer();
                var forumId = $routeParams.id;
                ForumFactory.getParticipatedUsers(forumId)
                    .then (
                        function(participatedUsers) {
                            
                         deferred.resolve(participatedUsers);
                        },
                        function(errResponse) {
                        }
                    );

                    return deferred.promise;
            }

}])