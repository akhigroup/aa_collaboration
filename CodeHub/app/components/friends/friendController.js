friend.controller('friendController', [
    'friendFactory', 
    '$timeout', 
    '$cookies', 
    '$routeParams', 
    '$location', 
    '$route', 
    function(friendFactory,$timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

    //For storing user list
    self.userslist = [];

    //For temporary storing list of user
    self.tempUsers = [];

    self.countUsers = {};

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //Function to fetch site users will come here
    self.fetchusers = function() {
        
        friendFactory.fetchUsers()
            .then (
                function(users) {
                   
                    var index = 0;  //setting up an var index as 0
                    for (var tempuser in  users) {   //traversing through array to remove user with Super admin role 
                        var tempId = users[tempuser].id;
                        if( tempId != user.id) {    //if role is not super admin add the user to new list
                             self.tempUsers[index++] =  users[tempuser]; 
                        }
                    }
                     self.userslist = self.tempUsers;
                     for(var birthDate in  self.userslist) {
                         self.userslist[birthDate].birthDate = new Date( self.userslist[birthDate].birthDate[0], self.userslist[birthDate].birthDate[1] - 1, self.userslist[birthDate].birthDate[2]);
                    }
                     debugger;
                     self.countUsers = self.userslist.length;
                }, function (errResponse) {
                }
            );
    }
        
    
}])