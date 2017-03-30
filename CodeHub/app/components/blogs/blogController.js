blog.controller('blogController', ['blogFactory', 
        '$timeout', '$cookies', '$routeParams', function(blogFactory, 
        $timeout, $cookies, $routeParams ) {

    var self = this;

    ///setting up the fields for creating new blog - should be same as fields in the entity class
    self.blog = {

        id : null,
        name : ' ',
        description : '',
        postDate : '',
        userId : '',
        userName : '',
    }

    // For viewing single Blog
    self.singleBlog = {};

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for adding a new blog
    self.addBlog = function () {
        debugger;

        //Setting the user id and username
        self.blog.userId = user.id;
        self.blog.userName = user.username;
        
         //calling the addBlog method in the factory
         blogFactory.addBlog(self.blog)
            .then (
                function(blog) {
                    self.blog =  blog;
                }, function (errResponse) {
                    console.error('Failure!');
                }
            );
         
    }

    //function for viewing single blog
    self.viewBlog = function() {
        //Assigning blog id to variable blog id
        var blogId = $routeParams.id;
        blogFactory.viewBlog(blogId)
            .then (
                function(blog) {
                    self.singleBlog = blog;
                },
                function(errResponse) {
                    console.error('Failure!');
                }
            );

    }

}])