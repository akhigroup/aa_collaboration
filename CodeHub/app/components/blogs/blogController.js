blog.controller('blogController', ['blogFactory', 'BlogCommentFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(blogFactory, BlogCommentFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

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

    ///setting up the fields for creating new blog comment - should be same as fields in the entity class
    self.blogComment = {

        id : null,
        title : '',
        blogComment : ' ',
        commentDate : '',
        userId : '',
        username : '',
    }

    // For viewing single Blog
    self.singleBlog = {};
    
    // For posting date on blog
    self.postDate = {};

    self.bloglist = [];

    //calling list of user's blogs
    self.myblogs = [];

    //for displaying blog comments
    self.blogCommentList = [];

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
        debugger;
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        blogFactory.viewBlog(blogId)
            .then (
                function(blog) {
                    debugger;
                    self.singleBlog = blog;
                    console.log(self.singleBlog.postDate);
                    self.singleBlog.postDate = new Date(self.singleBlog.postDate[0],self.singleBlog.postDate[1] - 1,self.singleBlog.postDate[2]);
                },
                function(errResponse) {
                    console.error('Failure!');
                }
            );

    }

    //function for adding a new blog comment
    self.addBlogComment = function () {
        
        // console.log(blog)
        //Setting the user id and username
        self.blogComment.userId = user.id;
        self.blogComment.username = user.username;
        // self.blogComment.blogId = $routeParams.id;
         //calling the addBlog method in the factory
         BlogCommentFactory.addBlogComment(self.blogComment)
            .then (
                function(blogComment) {
                    
                    self.blogComment =  blogComment;
                    $route.reload();
                    var id = $routeParams.id;
                    $location.path('/blog/' + id);
                    // console.log(self.blogComment); 
                    // $location.path('/blog/' + $routeParams.id);
                    // self.blogComment.title = '';
                    // self.blogComment.blogComment = '';
                }, function (errResponse) {
                    console.error('Failure!');
                }
            );
         
    }

    

    //calling method for bloglist
     bloglist();

    //Function to view list of all blogs
    function bloglist() {

        // var status = "APPROVED"

        blogFactory.bloglist()
            .then (
                function(blogs) {
               
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

    
     blogCommentlist();

    //Function to view list of all blog comments
    function blogCommentlist() {

        
        var blogId = $routeParams.id;
        blogFactory.blogCommentlist(blogId)
            .then (
                function(blogComments) {
                       
                    self.blogCommentList = blogComments;
                    for(var [blogComment] in self.blogCommentList) {
                        self.blogCommentList[blogComment].commentDate = new Date(self.blogCommentList[blogComment].commentDate[0],self.blogCommentList[blogComment].commentDate[1] - 1,self.blogCommentList[blogComment].commentDate[2]);
                    }
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
        }]);