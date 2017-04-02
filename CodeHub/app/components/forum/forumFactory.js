var forum = angular.module('forumModule', []);

forum.factory('ForumFactory', ['$http', '$q',   
        function($http, $q){

            //Linking backend project
            var url = 'http://localhost:7070/webapp';

            return {

                addForum : addForum,
                fetchForums : fetchForums,
            };

            //function to add a new forum Forum Category
            function addForum(forum) {

                var deferred = $q.defer();

                $http.post(url + '/forum/new', forum).then(
                    function(response) {
                        debugger;
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

            //Function to fetch list of forum categories
            function fetchForums() {
                
                var deferred = $q.defer();

                $http.get(url + '/forum/list').then(
                    function(response) {
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }
        }])