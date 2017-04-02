forum.controller('forumController', ['ForumFactory', '$timeout', '$cookies', '$routeParams', '$location', '$route', function(ForumFactory, 
        $timeout, $cookies, $routeParams, $location, $route) {

            var self = this;

            //Setting up the field for creating new forum category
            self.ForumCategory = {

                id : null,
                name : '',
                description : '',
            }

            //array for displaying list of forum categories
            self.forumCategories = [];

            // calling jQuery once controller has loaded
            $timeout(function () {
                setting();
            }, 100);

            //method for adding new category
            self.addCategory = function() {
                ForumFactory.addForumCategory(self.ForumCategory) 
                        .then(
                            function(category) {
                                debugger;
                                self.ForumCategory = category;
                                $route.reload();
                                $('#category').modal('close');
                            }, function(errResponse) {
                                console.log('Failure!');
                            }
                        );
            }

            fetchForumCategories();
            //method to fetch all the forum categories
            function fetchForumCategories() {
                console.log('method called');
                debugger;
                ForumFactory.fetchForumCategories().then(
                        function(categories) {
                            debugger;
                            self.forumCategories = categories;
                            // console.log(self.forumCategories["0"].id);
                    }, function(errResponse) {
                            console.log('Failure!');
                        }
                    );
            }

}])