var forum = angular.module('forumModule', []);

forum.factory('ForumFactory', ['$http', '$q',   
        function($http, $q){

            //Linking backend project
            var url = 'http://localhost:7070/webapp';

            return {

                addForumCategory : addForumCategory,
                fetchForumCategories : fetchForumCategories,
            };

            //function to add a new forum Forum Category
            function addForumCategory(category) {

                var deferred = $q.defer();

                $http.post(url + '/forum/category/new', category).then(
                    function(response) {
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

            //Function to fetch list of forum categories
            function fetchForumCategories() {
                
                var deferred = $q.defer();

                $http.get(url + '/forum/categories/list').then(
                    function(response) {
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }
        }])