var user = angular.module('userModule', []);

user.factory('userFactory', ['$http', '$q',

    function($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';
        
        return {
            userBlogList : userBlogList,
            userJobList : userJobList,
        };

         //Function to fetch userblog list
         
         function userBlogList(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(url + '/user/blogs/list/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

        //Function to fetch userjob list
         
         function userJobList(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(url + '/user/jobs/list/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }
    }
])