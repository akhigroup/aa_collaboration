admin.controller('adminController', ['adminFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(adminFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

    //For temparary storing list of users
    self.tempUserList = [];

    //For list of approved user list
    self.approvedUserList = [];

    //For list of approved blog list
    self.approvedBlogList = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //Function to fetch approved User List
    self.approvedUserList = function() {
        debugger;   
         adminFactory.approvedUserList()
            .then (
                function(approvedUsers) {
                    debugger;
                    var index = 0;  //setting up an var index as 0
                    for (var user in approvedUsers) {   //traversing through array to remove user with Super admin role
                        var role = approvedUsers[user].role; 
                        if( role != 'Super_Admin') {    //if role is not super admin add the user to new list
                            self.tempUserList[index++] = approvedUsers[user]; 
                        } 
                    }
                     self.approvedUserList = self.tempUserList; //assigning temp user list to approvedUserList
                    
                },
                function(errResponse) {
                }
            );
    }

    //Function to fetch approved blog List
    self.approvedBlogList = function() {
        
         adminFactory.approvedBlogList()
            .then (
                function(approvedBlogs) {
                    self.approvedBlogList = approvedBlogs; 
                    for (var postDate in self.approvedBlogList) {   
                        self.approvedBlogList[postDate].postDate = new Date(self.approvedBlogList[postDate].postDate[0],self.approvedBlogList[postDate].postDate[1] - 1,self.approvedBlogList[postDate].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }
        
    
}])