

authenticate.controller('authenticationController', 
[
        'AuthenticationFactory', 
        '$rootScope', 
        '$location', 
        '$timeout', 
        '$scope', 
        '$route', 

function(AuthenticationFactory, $rootScope, $location, $timeout, $scope, $route){

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

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for registering user will come here
     self.register = function () {
         debugger;
         var date = new Date(self.client.birthDate).toISOString().slice(0, 10);
          self.client.birthDate = date;
          console.log("Date " + self.client.birthDate);
        AuthenticationFactory.register(self.client)
            .then(
            function (user) {
                  console.log("Success");
                AuthenticationFactory.setUserIsAuthenticated(false);
                $rootScope.authenticated = false;
                self.register = true;
                $rootScope.msg = "Registration successful! You will get an email after approval.";
                $route.reload();
                $location.path('/home')
                Materialize.toast('Registration successful!', 2000);
            },
            function (errorResponse) {
                console.log(errorResponse);
                AuthenticationFactory.setUserIsAuthenticated(false);
                $rootScope.authenticated = false;
                self.error = true;
            }
            )
    };

    setting();

    //Method to check whether username already exist
    self.checkUsername = function () {
        // debugger;
        //If username is undefined and has some characters
        if(self.client.username !== undefined && self.client.username.length > 0) {

        AuthenticationFactory.checkUsername(self.client.username).then (
            function (response) {
                console.log(response);
                if(response.statusText === 'Found') {
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

        AuthenticationFactory.login(self.credentials).then (

            function (user) {
                debugger;
                AuthenticationFactory.setUserIsAuthenticated(true);
                AuthenticationFactory.setRole(user.role);
                $rootScope.authenticated = true;
                AuthenticationFactory.saveUser(user);
                switch(user.role) {
                    // case 'ADMIN' :
                    case 'User' :
                        $location.path('/user');
                        console.log('success');
                        break;
                    default :
                        $location.path('/home');
                }
            }, function(error) {
                AuthenticationFactory.setUserIsAuthenticated(false);
                $rootScope.authenticated = false;
                if($rootScope.message) $rootScope.message = false;
                self.error = true;
            }
        )
    }
}]);