var friend = angular.module('friendModule',  []);

friend.factory('friendFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';

        return {
            fetchUsers : fetchUsers,
        };

        //Function to fetch users 
        function fetchUsers() {
            var deferred = $q.defer();

            $http.get(url + '/users/list')
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