'use strict';

var ServiceMonitor = {};
// Declare app level module which depends on filters, and services
var ServiceMonitor = angular.module('ServiceMonitor', ['ngResource','ngRoute','ServiceMonitor.directives','ServiceMonitor.filters'
]);

ServiceMonitor.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'login',
		controller : UserLoginController
	}).when('/signup', {
		templateUrl : 'signup',
		controller : UserRegisterController
	}).when('/home',{
		templateUrl : 'login/home',
		controller : HomeController
	});
	
} ]).run([ '$rootScope', '$location', 'Login',function($rootScope,$location, Login){
	$rootScope.loggedInUser = null;
	Login.getLoggedInUser(function(data){
		if(!data){
			
			$rootScope.loggedInUser = null;
			$location.path("user/login");
		} else {
			$rootScope.loggedInUser = data;
			
		}
	});
}]);

