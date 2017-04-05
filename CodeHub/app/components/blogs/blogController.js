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
    
    // For posting date on blog
    self.postDate = {};

    self.bloglist = [];

    //calling list of user's blogs
    self.myblogs = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for adding a new blog
    self.addBlog = function () {

        //Setting the user id and username
        self.blog.userId = user.id;
        self.blog.userName = user.username;
        self.blogPostDate = "";
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
                    console.log(self.singleBlog.blog.postDate);
                    self.blogPostDate = new Date(self.singleBlog.blog.postDate[0],self.singleBlog.blog.postDate[1] - 1,self.singleBlog.blog.postDate[2]);
                },
                function(errResponse) {
                    console.error('Failure!');
                }
            );

    }

    //calling method for bloglist
     bloglist();

    //Function to view list of all blogs
    function bloglist() {

        // var status = "APPROVED"
        debugger;
        blogFactory.bloglist()
            .then (
                function(blogs) {
                    debugger;   
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
    }

    
        }]);