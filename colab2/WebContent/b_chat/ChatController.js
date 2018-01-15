
  app.controller("ChatController", ['$location','$scope', 'ChatService','ForUserService','$rootScope',function($location,$scope, ChatService,ForUserService,$rootScope) {
    $scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
    $scope.viewProfile = function(id) {
		console.log("-->ChatController : calling 'viewProfile' method.");
		ForUserService
					.getUserById(id)
					.then(function(d) {
						
						
						
						$rootScope.selectedUser=d;
						$location.path('/viewProfile');
					},
					function(errResponse) {
						console.error("Error while getting selected user")
					});
	};
    
    $scope.addMessage = function() {
    	ChatService.send($scope.message,$rootScope.currentUser);
    	$scope.message = "";
    };
    
    ChatService.receive().then(null, null, function(message) {
    	$scope.messages.push(message);
    });
  }]);
