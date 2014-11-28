'use strict';


var UserLoginController = function($scope, $rootScope, $location,$timeout,appAuth,Login,$http) {

	

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
					/*$scope.loginErr = true;*/
					//$location.path("/index");
					/*$timeout(function(){
						$scope.loginErr = false;
					}, 5000);*/
				} else {
					/*$scope.loginErr = false;*/
					alert("successfully logged in..");
					$rootScope.loggedInUser = data;
					//$location.path("user/home");
					appAuth.redirectToAttemptedUrl();
					
					
				}
				return data;
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

var HomeController = function($scope, $rootScope, $location,$timeout,appAuth,Login) {
	
	

};

