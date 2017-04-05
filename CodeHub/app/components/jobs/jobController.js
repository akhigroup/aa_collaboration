job.controller('jobController', ['jobFactory', 
        '$timeout', '$cookies', '$routeParams', '$location', function(jobFactory, 
        $timeout, $cookies, $routeParams, $location) {

    var self = this;

    ///setting up the fields for creating new blog - should be same as fields in the entity class
    self.job = {

        id : null,
        companyName : ' ',
        subTitle : '',
        about : '',
        jobProfile : '',
        qualification : '',
        contactInfo : '',
        postDate : '',
        userId : '',
        username : '',
    }

    //For fetching list of jobs
    self.joblist = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for adding a new job
    self.addJob = function () {
        debugger;
        //Setting the user id and username
        self.job.userId = user.id;
        self.job.username = user.username;
        self.postDate = "";
         //calling the addJob method in the factory
         jobFactory.addJob(self.job)
            .then (
                function(job) {
                    debugger;
                    self.job =  job;
                    console.log(self.job); 
                    $location.path('/jobs/list');
                }, function (errResponse) {
                    console.error('Failure!');
                }
            );
         
    }

    //calling method for joblist
     joblist();

    //Function to view list of all jobs
    function joblist() {
         jobFactory.joblist()
            .then (
                function(jobs) {   
                    self.joblist = jobs;
                    for(var [job] in self.joblist) {
                        // console.log(self.bloglist[blog].postDate);
                        self.joblist[job].postDate = new Date(self.joblist[job].postDate[0],self.joblist[job].postDate[1] - 1,self.joblist[job].postDate[2]);
                        // console.log(self.bloglist[blog].postDate);
                    }
                    console.log(self.joblist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }        
    
}])