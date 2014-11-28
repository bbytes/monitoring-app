'use strict';

/* Filters */

var AppFilters = angular.module('ServiceMonitor.filters', []);
AppFilters.filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    };
  }]);


/*ServiceMonitor.filter('orderObjectBy',function(){
	return function(user,field,reverse){
	var filtered =[];
	angular.forEach(user,function(users){
		filtered.push(users);
	});
	filtered.sort(function(a,b){
		
		return (a[field] > b[field] ? 1 : -1);
    });
    if(reverse) filtered.reverse();
    return filtered;

};
});*/