var request = angular.module('requestModule',  []);

request.factory('requestFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';
        
        return {
            //for fetching pending user list and changing their status
            pendingUserList : pendingUserList,
            changeStatus : changeStatus,

            //for fetching pending forum request list and changing their status
            fetchForumRequests: fetchForumRequests,
            changeFRStatus : changeFRStatus,
            
            //for fetching pending blog list and changing their status
            pendingBlogList : pendingBlogList,
            changeBlogStatus : changeBlogStatus,
        };
    
         //Function to fetch pending user list
        function pendingUserList() {
            var deferred = $q.defer();
            
            $http.get(url + '/user/request/list')
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

        //Function to change status of pending users
        function changeStatus(id) {
            var deferred = $q.defer();
            
            $http.post(url + '/user/request/approval/' + id)
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

         //Function to fetch forum join request with pending status
            function fetchForumRequests() {

                var deferred = $q.defer();
                $http.get(url + '/forum/request/list')
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

        //Function to change status pending forum request
        function changeFRStatus(id) {
            var deferred = $q.defer();
            
            $http.post(url + '/forum/request/approval/' + id)
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

        //Function to fetch the list of pending blogs
        function pendingBlogList() {
            var deferred = $q.defer();
            
            $http.get(url + '/blog/request/list')
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

        //Function to change status of pending blogs
        function changeBlogStatus(id) {
            var deferred = $q.defer();
            
            $http.post(url + '/blog/request/approval/' + id)
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