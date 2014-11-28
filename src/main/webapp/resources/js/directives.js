'use strict';

/* Directives */


var AppDirectives = angular.module('ServiceMonitor.directives', []);
AppDirectives.directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }]);

angular.module('ServiceMonitor').directive('ngEnter',function(){
	return function(scope,element,attrs){
		element.bind("keydown keypress",function(event){
			if(event.which===13){
				scope.$apply(function(){
					scope.$eval(attrs.ngEnter);
				});
				event.preventDefault();
			}
		});
	};
});
angular.module('ServiceMonitor').directive('startstop',function(){
	return {
        restrict: 'A',
        template: '<input value="" class="btn btn-success btn-lg">',
        link: function(scope, elem, attrs) {
          elem.bind("click", function(){
            console.log('startstop clicked', elem)
            if(elem.val() == "start") {
               elem.val("stop");   
            }
            else {
               elem.val("start");
            }
          })
        }
      }
});