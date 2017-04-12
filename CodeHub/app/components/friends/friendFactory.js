var friend = angular.module('friendModule',  []);

friend.factory('friendFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';

        return {
            fetchUsers : fetchUsers,
            sendRequest : sendRequest,
            fetchRequest : fetchRequest
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

        //function to send friend request
        function sendRequest(id) {
            var deferred = $q.defer();

            var initId = user.id
            $http.post(url + '/user/friendRequest/' + id, initId)
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

        //function to fetch friend requests
        function fetchRequest() {
            var deferred = $q.defer();

            var userId = user.id
            $http.get(url + '/user/friendRequest/list/' + userId)
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