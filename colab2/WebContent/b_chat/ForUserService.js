'use strict';

app.factory('ForUserService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("ForUserService...");

		var BASE_URL='http://localhost:8080/collaboration'
			return {


			
			getUserById : function(id){
				console.log("--> UserService : calling 'getUserById' method.");
				return $http
				.get(BASE_URL+'/user/'+id)
				.then(function(response) {
					return response.data;
				},
				function(errResponse) {
					console.error('Error while getting user by Id...');
					return $q.reject(errResponse);
				});
			}
		};
}]);