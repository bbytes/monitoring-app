var ServiceController = function($scope, $http, $rootScope, $route,$location) {
	$scope.user = new Object();
	/* alert($rootScope.loggedInUser.id); */
	$scope.addAllService = function() {
		$scope.user = '';
		$("#myModal").modal('show');
	};

	$scope.monitor_service = [ {
		value : "database",
		name : "Database"
	}, {
		value : "queue",
		name : "Queue"
	}, {
		value : "cache",
		name : "Cache"
	}, {
		value : "url",
		name : "URL"
	} ];
	$scope.addService = function() {

		if ($scope.user.services_name == "database") {

			$("#myModal1").modal('show');
			$("#myModal").modal('hide');
		}
		if ($scope.user.services_name == "queue") {

			$("#myModal2").modal('show');
			$("#myModal").modal('hide');
		}
		if ($scope.user.services_name == "cache") {
			$("#myModal3").modal('show');
			$("#myModal").modal('hide');
		}
		if ($scope.user.services_name == "url") {
			$("#myModal4").modal('show');
			$("#myModal").modal('hide');
		}

	};

	$scope.resetUserForm = function() {
		$scope.user = new Object();
		$scope.user.name = '';
		$scope.user.description = '';
		$scope.user.services_name = '';
		$scope.editMode = false;
	};

	$scope.addServiceNext = function() {

		if ($scope.user.service_type == "RabbitMq"
				|| $scope.user.service_type == "ActiveMq"
				|| $scope.user.service_type == "Kafka") {
			$("#myModal5").modal('show');
			$("#myModal2").modal('hide');
			$("#myModal4").modal('hide');
			$("#myModal").modal('hide');

		}

		if ($scope.user.service_type == "MySql"
				|| $scope.user.service_type == "Oracle"
				|| $scope.user.service_type == "MsSql"
				|| $scope.user.service_type == "Postgres") {
			$("#myModal6").modal('show');
			$("#myModal1").modal('hide');
			$("#myModal5").modal('hide');
			$("#myModal").modal('hide');

		}

		if ($scope.user.service_type == "Redis"
				|| $scope.user.service_type == "Memcache") {
			$("#myModal7").modal('show');
			$("#myModal3").modal('hide');
			$("#myModal5").modal('hide');
			$("#myModal").modal('hide');

		}

	};

	$scope.resetNextUserForm = function() {
		$scope.user.service_type = '';

	};

	$scope.resetSaveUserForm = function() {

		$scope.user = new Object();

		$scope.user.ip = '';
		$scope.user.port = '';
		$scope.user.schema_name = '';
		$scope.user.user_name = '';
		$scope.user.password = '';
		$scope.user.queue_name = '';

	};

	$scope.SaveQueue = function(uid) {

		$scope.user.userid = uid;

		$http({
			method : 'POST',
			url : 'SaveQueue',
			data : $scope.user,
			headers : {

				'Content-Type' : 'application/json'
			}
		}).success(function(data) {

			alert("successfully added Queue Service details");
			$scope.user = data;
			
			$("#myModal5").modal('hide');
		    $location.path("/home");
			$route.reload();
		}).error(function(data) {
			alert(data);
		});

		


	};

	$scope.SaveCache = function(uid) {

		$scope.user.userid = uid;

		$http({
			method : 'POST',
			url : 'SaveCache',
			data : $scope.user,
			headers : {

				'Content-Type' : 'application/json'
			}
		}).success(function(data) {

			alert("successfully added Cache Service details");
			
			$("#myModal7").modal('hide');
			$location.path("/home");
			$route.reload();
		}).error(function(data) {
			alert(data);
		});

		/*
		 * var userid = $rootScope.loggedInUser.id;
		 * 
		 * $http({ method : 'GET', url : 'getSavedCache?userid=' + userid, data :
		 * $scope.user, headers : {
		 * 
		 * 'Content-Type' : 'application/json' } }).success(function(data) {
		 * $scope.user = data; $route.reload(); });
		 */

	};

	$scope.SaveUrl = function(uid) {

		$scope.user.userid = uid;

		$http({
			method : 'POST',
			url : 'SaveUrl',
			data : $scope.user,
			headers : {

				'Content-Type' : 'application/json'
			}
		}).success(function(data) {

			alert("successfully added Url details");
			$scope.user = data;
			$("#myModal4").modal('hide');
			$location.path("/home");
			$route.reload();

		}).error(function(data) {
			alert(data);
		})

		/*
		 * var userid = $rootScope.loggedInUser.id;
		 * 
		 * $http({ method : 'GET', url : 'getSavedUrl?userid=' + userid, data :
		 * $scope.user, headers : {
		 * 
		 * 'Content-Type' : 'application/json' } }).success(function(data) {
		 * $scope.user = data; $route.reload(); });
		 */

	};

	$scope.SaveDatabase = function(uid) {

		$scope.user.userid = uid;

		$http({
			method : 'POST',
			url : 'SaveDatabase',
			data : $scope.user,
			headers : {

				'Content-Type' : 'application/json'
			}
		}).success(function(data) {

			alert("successfully added Database Service details");
			$scope.user = data;
			$("#myModal6").modal('hide');
			/*$location.path("/home");*/
			$route.reload();
		}).error(function(data) {
			alert(data);
		})
		/*
		 * var userid = $rootScope.loggedInUser.id;
		 * 
		 * $http({ method : 'GET', url : 'getSavedDatabase?userid=' + userid,
		 * data : $scope.user, headers : {
		 * 
		 * 'Content-Type' : 'application/json' } }).success(function(data) {
		 * $scope.user = data; $route.reload(); });
		 */

	};

};

var getServiceController = function($scope, $http, $rootScope, $location,
		$route,$interval) {
    
	
	var uid = $rootScope.loggedInUser.id;

	$http({
		method : 'GET',
		url : 'getAllServicesByUserId?userid=' + uid,
		data : $scope.user,
		headers : {

			'Content-Type' : 'application/json'
		}
	}).success(function(data) {
		$scope.user = data;

	});
	$http({
		method : 'GET',
		url : 'getAllServices',
		data : $scope.user,
		headers : {

			'Content-Type' : 'application/json'
		}
	}).success(function(data) {
		$scope.user = data;
		setInterval(function(){
		for (var i in $scope.user)
		{
			var sid = $scope.user[i].id;
			var services_name = $scope.user[i].services_name;
			var ip = $scope.user[i].ip;
			var port = $scope.user[i].port;
			var url = $scope.user[i].url;
		$http(
				{
					method : 'GET',
					url : 'pingService?id=' + sid + "&services_name="
							+ services_name + "&ip=" + ip + "&port=" + port
							+ "&url=" + url,
					data : $scope.user,
					headers : {

						'Content-Type' : 'application/json'
					}
				}).success(function(data) {
				
					
				$scope.pings = data;
			
				
				$scope.servicesStatus = {id : sid, status : data};
				

		})
		}
		},10000);
	});
	
	

		

	$scope.more = function(sid, services_name, uid) {

		$http(
				{
					method : 'GET',
					url : 'getServicesById?id=' + sid + "&services_name="
							+ services_name + "&userid=" + uid,
					data : $scope.user,
					headers : {

						'Content-Type' : 'application/json'
					}
				}).success(function(data) {

					
            $scope.serviceDetail = {id : sid,detail:data};
				/*alert($scope.serviceDetail.id);	*/
					
				
			for (var i in $scope.user){
				if($scope.user[i].id === $scope.serviceDetail.id){
					
					$("#myModalEdit").modal('show');
			}
			}
			

			/* $location.path("/home"); */
					/*user = ($filter('filter')({id : $routeParams.Id}));
					$scope.user = user[0];
					$scope.$apply();*/

		});

	};
	

	$scope.close = function() {
		$("#myModalEdit").modal('hide');
		
	};

	/*$scope.button = "Start";*/
	$scope.updateService = function(sid, services_name, isactive) {

		/*if(isactive===false){
			$scope.ButtonText = "Stop";
		}
		else{
			$scope.ButtonText = "Start";
		}
		*/
		
		$http(
				{
					method : 'GET',
					url : 'updateServicesById?id=' + sid + "&services_name="
							+ services_name + "&isactive=" + isactive,
					data : $scope.user,
					headers : {

						'Content-Type' : 'application/json'
					}
				}).success(function(data) {
                   
					
				$scope.serviceStartStop = {id : sid, isactive:isactive};
					for (var i in $scope.user)
					{
						if($scope.user[i].id === $scope.serviceStartStop.id && $scope.user[i].isactive === false){
							/*alert("hi");*/
							/*alert($scope.user[i].isactive);*/
							$scope.user[i].isactive = true;
							/*alert($scope.user[i].isactive);*/
							break;
					      }
						if($scope.user[i].id === $scope.serviceStartStop.id && $scope.user[i].isactive === true){
						
							/*alert("hello");*/
							/*alert($scope.user[i].isactive);*/
							$scope.user[i].isactive = false;
							/*alert($scope.user[i].isactive);*/
					       }
						
					}
					$location.path("/home");
					
					
					
		});
	};

	$scope.deleteService = function(sid, services_name) {

		$http(
				{
					method : 'GET',
					url : 'deleteServicesById?id=' + sid + "&services_name="
							+ services_name,
					data : $scope.user,
					headers : {

						'Content-Type' : 'application/json'
					}
				}).success(function(data) {
		          
					$scope.user = data;
					alert(data);
			$location.path("/home");

		})
	};
	

	$scope.ping = function(sid, services_name, ip, port, url) {
         
	/*	alert(sid);*/
		$http(
				{
					method : 'GET',
					url : 'pingService?id=' + sid + "&services_name="
							+ services_name + "&ip=" + ip + "&port=" + port
							+ "&url=" + url,
					data : $scope.user,
					headers : {

						'Content-Type' : 'application/json'
					}
				}).success(function(data) {
				
					
				$scope.pings = data;
				
				
				$scope.serviceStatus = {id : sid, status : data};
				

		})
	};
	
	
	
};

var logoutController = function($scope, $rootScope, $templateCache, $location,
		Login) {

	$scope.logout = function() {

		Login.logout(function(data) {
			if (data == "success") {
				$rootScope.loggedInUser = null;
				
				$templateCache.removeAll();
				$location.path("/index");
			}
		});
	};
};
