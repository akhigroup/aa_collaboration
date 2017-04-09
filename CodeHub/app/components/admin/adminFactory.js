var admin = angular.module('adminModule',  []);

admin.factory('adminFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';
        
        return {
            approvedUserList : approvedUserList,
            approvedBlogList : approvedBlogList,
        };

       
        //Function to fetch approved user list
        function approvedUserList() {
            var deferred = $q.defer();
            
            $http.get(url + '/user/manage/list')
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

        //Function to fetch approved blog list
        function approvedBlogList() {
            var deferred = $q.defer();
            
            $http.get(url + '/blog/manage/list')
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

        
}])