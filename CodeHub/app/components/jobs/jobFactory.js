var job = angular.module('jobModule', []);

job.factory('jobFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var jobUrl = 'http://localhost:7070/webapp';
        
        return {
            addJob : addJob,
            joblist : joblist
        }

        //Function to add the job 
        function addJob(job) {
            var deferred = $q.defer();

            $http.post(jobUrl + '/job/new', job).then (

                function(response) {
                    deferred.resolve(response.data);
                }, 
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        //Function to fetch job list
        function joblist() {
             console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(jobUrl + '/job/list/status')
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
]
);
