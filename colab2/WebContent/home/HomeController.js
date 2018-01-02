'use strict';

app.controller('HomeController', [ 
        '$scope', 
        '$location',
		'$rootScope',
		'$localStorage',
		function($scope, $location, $rootScope, $localStorage) {

        	console.log("HomeController...");
        	var self = this;
        	
        	self.getCurrentUser = function() {
				console.log("Loading current user if already logged in");
				console.log("Current User : "+$rootScope.currentUser);
				if(!$rootScope.currentUser) {
					console.log("Usernot logged in");
					$rootScope.currentUser="";
					$localStorage.currentUser=$rootScope.currentUser;
				}
				return $localStorage.currentUser;
			}
        	self.getCurrentUser();
        	$location.path('/blogs');
		} ]);