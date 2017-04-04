authenticate.controller('authenticationController', 
[
        'AuthenticationFactory',
        'ForumFactory',
        'blogFactory', 
        '$rootScope', 
        '$location', 
        '$timeout', 
        '$scope', 
        '$route', 

function(AuthenticationFactory, ForumFactory, blogFactory, $rootScope, $location, $timeout, $scope, $route){

    var self = this;
    self.credentials = {};
    self.error = false;
    self.authError = false;
    //flag to display whether the username exist or not
    self.usernameExist = false;

    //setting up the fields for registration - should be same as fields in the entity class
    self.client = {
        id :  null,
        username : '',
        firstname : '',
        lastname : '',
        password : '',
        confirmPassword : '',
        emailId : '',
        birthDate: '' ,
        gender : ''
    };
    
    // calling list of forums
    self.forums = [];

    //calling list of blogs
    self.bloglist = [];

    //calling list of user's blogs
    self.myblogs = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for registering user will come here
     self.register = function () {
         
         var date = new Date(self.client.birthDate).toISOString().slice(0, 10);
          self.client.birthDate = date;
          
        AuthenticationFactory.register(self.client)
            .then(
            function (user) {
                 
                AuthenticationFactory.setUserIsAuthenticated(false);
                $rootScope.authenticated = false;
                self.register = true;
                $rootScope.msg = "Registration successful! You will get an email after approval.";
                $route.reload();
                $location.path('/home')
                Materialize.toast('Registration successful!', 2000);
            },
            function (errorResponse) {
               
                AuthenticationFactory.setUserIsAuthenticated(false);
                $rootScope.authenticated = false;
                self.error = true;
            }
            )
    };

    //calling method in formfactory to fetch forums
     ForumFactory.fetchForums().then(
                        function(forums) {
                            self.forums = forums;
                            console.log(self.forums);
                    }, function(errResponse) {
                            console.log('Failure!');
                        }
                    );

    //calling method in blogfactory to fetch blogs
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

    userBlogList();

     //calling method to fetch user's blogs
     function userBlogList() {
     debugger;
    //  var id = user.id;
     blogFactory.userBlogList()
            .then (
                function(blogs) {
                    debugger;
                    self.myblogs = blogs;
                    
                    console.log(myblogs);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
     }


    //Method to check whether username already exist
    self.checkUsername = function () {
        
        var username = self.client.username;
        //If username is undefined and has some characters
        if( username !== undefined && username.length > 0) {

        AuthenticationFactory.checkUsername(username).then (
            function (response ) {
               
               
                if(response.status === 302) {
                    self.usernameExist = true;
                    //setting the validity as false if the username already exist
                    $scope.register.reg_username.$setValidity("reg_username", false)
                } else {
                    self.usernameExist = false;
                    //setting the validity as true if the username already exist
                    $scope.register.reg_username.$setValidity("reg_username", true)
                }
            }, function (error) {
                self.usernameExist = false;
                $scope.register.reg_username.$setValidity("reg_username", true)
            }
        );
        }
       
    };

    //Method for user login 
    self.login = function() {
        debugger;
        AuthenticationFactory.login(self.credentials).then (

            function (user) {
                debugger;

                if(self.credentials.username == null || self.credentials.password == null) {
                    self.error = true;
                    $rootScope.message = "Please provide both username and password";
                }
                else if(user.username == null || user.password == null) {
                    self.error = true;
                    $rootScope.message = "Incorrect username or password";
                } else if(user.status == 'PENDING') {
                    self.error = true;
                    $rootScope.message = "Apparently your registeration request is not approved yet!";
                } else if(user.status == 'REJECT') {
                    self.error = true;
                    $rootScope.message = "Your registeration request has been rejected!";
                } else {
                     AuthenticationFactory.setUserIsAuthenticated(true);
                     AuthenticationFactory.setRole(user.role);
                     $rootScope.authenticated = true;
                     $rootScope.message = "Welcome" + user.username;
                     AuthenticationFactory.saveUser(user);
                      switch(user.role) {
                        case 'Super_Admin':
                            self.isSuperAdmin = true;
                            $location.path('/user');
                            break;
                        case 'Admin':
                            self.isAdmin = true;
                            $location.path('/user');
                            break;
                        case 'Employer':
                            self.isEmployer = true;
                            $location.path('/user');
                            break;
                        case 'User' :
                            self.isUser = true;
                            $location.path('/user');
                            break;
                        default :
                            $location.path('/home');
                    }
                    $rootScope.isLogin = true;
                }   
            },
                function(error) {
                     AuthenticationFactory.setUserIsAuthenticated(false);
                     $rootScope.authenticate = false;
                     self.error = true;
                });
    }

}

]);
