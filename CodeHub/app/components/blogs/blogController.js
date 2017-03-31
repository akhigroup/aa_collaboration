blog.controller('blogController', ['blogFactory', 
        '$timeout', '$cookies', '$routeParams', '$location', function(blogFactory, 
        $timeout, $cookies, $routeParams, $location) {

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
    self.postDate = {};

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for adding a new blog
    self.addBlog = function () {

        //Setting the user id and username
        self.blog.userId = user.id;
        self.blog.userName = user.username;
        
         //calling the addBlog method in the factory
         blogFactory.addBlog(self.blog)
            .then (
                function(blog) {
                    self.blog =  blog;
                    // console.log(self.blog.id)
                    var bId = self.blog.id 
                    $location.path('/blog/' + bId);
                }, function (errResponse) {
                    console.error('Failure!');
                }
            );
         
    }

    //function for viewing single blog
    self.viewBlog = function() {
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        blogFactory.viewBlog(blogId)
            .then (
                function(blog) {
                    debugger;
                    self.singleBlog = blog;
                    // console.log(self.singleBlog.blog.postDate);
                    var date = self.singleBlog.blog.postDate
                },
                function(errResponse) {
                    console.error('Failure!');
                }
            );

    }

}])