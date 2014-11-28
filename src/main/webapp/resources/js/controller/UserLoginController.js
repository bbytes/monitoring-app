'use strict';


var UserLoginController = function($scope, $rootScope, $location,$timeout,Login,$http) {

	

	$scope.login = function(passwordId, loginform) {
		
		/*$scope.loginErr = false;*/
		
		$scope.loginSubmitted = true;
		if(loginform.$valid){
			/*password = $("#" + passwordId).val();*/
			var data = {
				j_username : $scope.email,
				j_password : $scope.password
			};
			
			data = $.param(data);
			Login.login(data, function(data) {
				
				if (data == 'failure') {
					alert("Check credentials");
					$rootScope.loggedInUser = null;
					$scope.email = '';
					$scope.password = '';
				} else {
					
					$rootScope.loggedInUser = data;
					$location.path("/home");
					/*appAuth.redirectToAttemptedUrl();*/
					
					
				}
				return false;
			});
		}
	};
		
	$scope.resetUserForm = function() {
		$scope.resetError();
		$scope.email = '';
		$scope.password = '';

		$scope.editMode = false;
	};

	$scope.resetError = function() {
		$scope.error = false;
		$scope.errorMessage = '';
	};

	$scope.setError = function(message) {
		$scope.error = true;
		$scope.errorMessage = message;
	};

	$scope.predicate = 'id';
};

var HomeController = function($scope, $rootScope, $location,$timeout,Login) {
	
	

};

