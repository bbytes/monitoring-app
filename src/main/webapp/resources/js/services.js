ServiceMonitor.service('Login', function(httpService) {
	this.login = function(data, callback) {
		httpService.postRequest('j_spring_security_check', data, 'application/x-www-form-urlencoded', callback);
	};
	
	
	
	this.isLoggedIn = function(callback) {
		httpService.getRequest('login/isLoggedIn', callback);
	};
	
	this.getLoggedInUser = function(callback) {
		httpService.getRequest('login/loggedInUser', callback);
	};
	
	this.logout = function(callback) {
		httpService.getRequest('j_spring_security_logout', callback);
	};
});

ServiceMonitor.service('SessionService',function()
{
	var user = null;
    return {
        getUser : function() {
            return user;
        },
        setUser : function(newUser) {
            user= newUser;
        }
    };
});



