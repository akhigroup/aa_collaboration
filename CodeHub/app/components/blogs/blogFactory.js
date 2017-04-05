var blog = angular.module('blogModule',  []);

blog.factory('blogFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var blogUrl = 'http://localhost:7070/webapp';
        
        return {
            addBlog : addBlog,
            viewBlog : viewBlog,
            bloglist : bloglist,
        };

        //Function to add the blog 
        function addBlog(blog) {
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


        //Function for viewing single blog using blog id as a parameter
        function viewBlog(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(blogUrl + '/blog/' + id)
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

        //Function to fetch blog list
        function bloglist() {
             console.log('Inside factory now');
            var deferred = $q.defer();
            debugger;
            $http.get(blogUrl + '/blog/list/status')
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