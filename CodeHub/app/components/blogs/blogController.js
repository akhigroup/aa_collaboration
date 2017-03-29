blog.controller('blogController', ['blogFactory', 
        '$timeout', '$cookies', function(blogFactory, 
        $timeout, $cookies ) {

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

}])