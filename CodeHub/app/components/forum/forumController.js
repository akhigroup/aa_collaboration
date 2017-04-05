forum.controller('forumController', ['ForumFactory', '$timeout', '$cookies', '$routeParams', '$location', '$route', function(ForumFactory, 
        $timeout, $cookies, $routeParams, $location, $route) {

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
                                console.log('Failure!');
                            }
                        );
            }

            fetchForums();
            //method to fetch all the forum categories
            function fetchForums() {
                console.log('method called');
                
                ForumFactory.fetchForums().then(
                        function(forums) {
                            
                            self.forums = forums;
                            console.log(self.forums);
                    }, function(errResponse) {
                            console.log('Failure!');
                        }
                    );
            }

            //function for viewing single forum
            self.viewForum = function() {
                //Assigning forum id to variable forumId
                var forumId = $routeParams.id;
                ForumFactory.viewForum(forumId)
                    .then (
                        function(forum) {
                            debugger;
                            self.singleForum = forum;
                            // console.log(self.singleForum.postDate);
                            self.singleForum.postDate = new Date(self.singleForum.postDate[0],self.singleForum.postDate[1] - 1,self.singleForum.postDate[2]);
                        },
                        function(errResponse) {
                            console.error('Failure!');
                        }
                    );

            }

}])