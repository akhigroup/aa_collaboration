var admin = angular.module('adminModule',  []);

blog.factory('adminFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:7070/webapp';
        
        return {
            pendingUserList : pendingUserList,
            changeStatus : changeStatus,
        };

        //Function to fetch pending user list
        function pendingUserList() {
            console.log('Inside factory now');
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

        //Function to fetch pending user list
        function changeStatus(id) {
            console.log('Inside factory now');
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

    

}])