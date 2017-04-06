admin.controller('adminController', ['adminFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(adminFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

    //For storing list of pending users
    self.pendingUserList = [];

     //calling method for bloglist
     pendingUserList();

    //Function to view list of all pending users
    function pendingUserList() {

        // var status = "APPROVED"
        debugger;
        adminFactory.pendingUserList()
            .then (
                function(pendingUsers) {
               
                    self.pendingUserList = pendingUsers;
                    for(var [birthDate] in self.pendingUserList) {
                        self.pendingUserList[birthDate].birthDate = new Date(self.pendingUserList[birthDate].birthDate[0],self.pendingUserList[birthDate].birthDate[1] - 1,self.pendingUserList[birthDate].birthDate[2]);
                    }
                    // console.log(self.bloglist.postDate);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }

    //Function to change status of user registration
    self.changeStatus = function(id) {
        console.log(id);
        console.log('test');
        debugger;
        adminFactory.changeStatus(id)
            .then (
                function(user) {
                    console.log('success!');
                    $route.reload();
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
        
    
}])