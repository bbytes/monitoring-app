'use strict';

var ServiceMonitor = {};
// Declare app level module which depends on filters, and services
var ServiceMonitor = angular.module('ServiceMonitor', ['ngResource','ngRoute','ServiceMonitor.directives','ServiceMonitor.filters'
]);

ServiceMonitor.value('redirectToUrlAfterLogin', { url: '/home' });

ServiceMonitor.service('appAuth', function ($location, redirectToUrlAfterLogin) {
	  return {
	    saveAttemptUrl: function() {
	      if($location.path().toLowerCase() != '/login') {
	        redirectToUrlAfterLogin.url = $location.path();
	      }
	      else
	        redirectToUrlAfterLogin.url = '/home';
	    },
	    redirectToAttemptedUrl: function() {
	      var redirectUrl = redirectToUrlAfterLogin.url;
	      redirectToUrlAfterLogin.url = "/home";
	      $location.path(redirectUrl);
	    }
	  };
	});

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
	
} ]).run([ '$rootScope', '$location', 'Login', 'appAuth',function($rootScope,$location, Login, appAuth){
	$rootScope.loggedInUser = null;
	Login.getLoggedInUser(function(data){
		if(!data){
			appAuth.saveAttemptUrl();
			$rootScope.loggedInUser = null;
			
		} else {
			$rootScope.loggedInUser = data;
			
		}
	});
}]);
ServiceMonitor.config([ "$httpProvider", function($httpProvider) {
	$httpProvider.responseInterceptors.push(function($q, $location, $injector, $rootScope, appAuth) {
		return function(promise) {
			return promise.then(function(response) {
				return response;
			}, function(response) {
				if (response.status === 401){
			        appAuth.saveAttemptUrl();
			        $rootScope.loggedInUser = null;
			        $location.url('user/login');
			      /*  $("#myModal").modal('show');*/
				}
				return $q.reject(response);
			});
		};
	});
} ]);

