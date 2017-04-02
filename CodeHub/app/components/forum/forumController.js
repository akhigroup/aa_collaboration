forum.controller('forumController', ['ForumFactory', '$timeout', '$cookies', '$routeParams', '$location', '$route', function(ForumFactory, 
        $timeout, $cookies, $routeParams, $location, $route) {

            var self = this;

            //Setting up the field for creating new forum category
            self.Forum = {

                id : null,
                name : '',
                description : '',
            }

            //array for displaying list of forum categories
            self.forums = [];

            // calling jQuery once controller has loaded
            $timeout(function () {
                setting();
            }, 100);

            //method for adding new category
            self.addForum = function() {
                debugger;
                ForumFactory.addForum(self.Forum) 
                        .then(
                            function(forum) {
                                debugger;
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
                debugger;
                ForumFactory.fetchForums().then(
                        function(forums) {
                            debugger;
                            self.forums = forums;
                            console.log(self.forums);
                    }, function(errResponse) {
                            console.log('Failure!');
                        }
                    );
            }

}])