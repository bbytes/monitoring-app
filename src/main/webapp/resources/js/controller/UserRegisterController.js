'use strict';


var UserRegisterController = function($scope, $http,$location) {
    $scope.user = new Object();
    $scope.editMode = false;

  
    $scope.addNewUser = function() {
        $scope.resetError();
        
        $http({
        	   method : 'POST',
        	   url :'addUser',
        	   data :$scope.user,
        	   headers : {
        		 
        	    'Content-Type' : 'application/json'
        	   }
        	  }).success(function(data) {
        		 /* $scope.signup.texts.push({'text':data.text})*/
        		alert("successfully added user details");
        	  
        		  $location.path("/index");
        	  }).error(function(data) {
        	   alert("error " + data);
        	  });
      
    };

   

   
   

    $scope.resetUserForm = function() {
        $scope.resetError();
        $scope.user = {};
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