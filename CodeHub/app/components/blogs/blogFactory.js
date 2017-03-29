var blog = angular.module('blogModule',  []);

blog.factory('blogFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var blogUrl = 'http://localhost:7070/webapp';
        
        return {
            addBlog : addBlog
        };

        //Function to add the blog 
        function addBlog(blog) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.post(blogUrl + '/blog/new', blog).then (

                function(response) {
                    deferred.resolve(response.data);
                }, 
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }


    }
])