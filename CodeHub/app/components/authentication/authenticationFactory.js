var authenticate = angular.module('authenticationModule', ['ngCookies']);

authenticate.factory('AuthenticationFactory', ['$http', '$q', '$rootScope', '$cookies',
    function ($http, $q, $rootScope, $cookies) {

        //Linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';
        var userIsAUthenticated = false;
        var role = 'GUEST';

       return  {
            setUserIsAuthenticated : setUserIsAuthenticated,
            getUserIsAuthenticated: getUserIsAuthenticated,
            loadUserFromCookie: loadUserFromCookie,
            checkUsername : checkUsername,
            saveUser: saveUser,
            setRole: setRole,
            getRole: getRole,
            login: login,
            register: register,
            logout: logout
        };

        function setUserIsAuthenticated(value) {

            userIsAuthenticated = value;
        }

        function getUserIsAuthenticated() {

            return userIsAuthenticated;
        }

        //Loading user inside cookies
        function loadUserFromCookie() {
            user = $cookies.getObject('user');
            console.log(user)
            if (user) {
                userIsAuthenticated = true;
                $rootScope.authenticated = true;
                $rootScope.message = 'Welcome ' + user.username;
                $rootScope.firstName = user.firstname;
                $rootScope.lastName = user.lastname;
                $rootScope.emailId = user.emailId;
                $rootScope.gender = user.gender;
                $rootScope.userId = user.id;
                console.log($rootScope.idd);
                role = user.role;
                console.log(role);
            } else {
                userIsAuthenticated = false;
                role = 'GUEST';
            }
            return user;
        }

        //Method for checking username
        function checkUsername(userName) {

            var deferred = $q.defer();
            $http.post(url + '/checkuser', userName).then (
                function(response) {
                    console.log('Success');
                    deferred.resolve(response);
                }, function(error) {
                    console.log('Failed');
                    deferred.resolve(error);
                }
            );
            return deferred.promise;
        }

        //saving user inside cookies
        function saveUser(user) {

            $cookies.putObject('user', user);
            role = user.role;
            userIsAuthenticated = true;

        }

        function setRole(value) {

            role = value;
        }

        function getRole() {

            return role;
        }

        function login(credentials) {

        }

        function register(user) {
            console.log(user);

            var deferred = $q.defer();
            $http.post(url + '/register', user).then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while registering');
                    deferred.reject(errResponse);
                }
                );
            return deferred.promise;
        }

        function logout(userId) {

        }

    }]);