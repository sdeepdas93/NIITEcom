'use strict';

app.controller('UserController', [
		'$http',
		'$cookieStore',
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		'$localStorage',
		'$window',
		function($http, $cookieStore, $scope, UserService, $location, $rootScope, $localStorage, $window) {
			console.log("UserController....")
			var self = this;
			self.user = {
				errorCode: '',
				errorMessage: '',
				userId: '',
				userPassword: '',
				userName: '',
				userRole: '',
				userGender: '',
				userDob: '',
				userEmail: '',
				userContactNumber: '',
				userAddress: '',
				userStatus: '',
				userIsOnline: '',
				userImage: '',
				userDescription: ''
				
			};
			self.users = [];

			self.fetchAllUsers = function() {
				console.log("--> UserController : calling fetchAllUsers method.");
				UserService.fetchAllUsers().then(function (d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users...');
				});
			};

			self.createUser = function(user) {
				console.log("--> UserController : calling createUser method.");
				UserService.createUser(user).then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while creating User...');
						});
			};
			
			
			self.getUserById=function(id){
				console.log("My method getCurrentUser starting"+id);
				UserService.getUserById(id).then(function(d){
						self.user = d;
			}, function(errResponse) {
				console.error('Error while fetching User by id...');
			}
						
				
				);
			};
			
			self.updateUser = function(user, id) {
				console.log("--> UserController : calling updateUser method.");
				
				user.userRole=$rootScope.currentUser.userRole;
				user.userStatus=$rootScope.currentUser.userStatus;
				user.userIsOnline=$rootScope.currentUser.userIsOnline;
				UserService.updateUser(user, id).then(function(d) {
					self.user = d;
					$rootScope.currentUser=d;
					$location.path('/');
					}, function(errResponse) {
						console.error('--> UserController : Error while updating User...');
					});
			};

			self.authenticate = function(user) {
				console.log("--> UserController : calling authenticate method.");
				UserService.authenticate(user).then(function(d) {
					self.user = d;
					console.log("user.errorCode : "+self.user.errorCode);
					if(self.user.errorCode == "404") {
						alert("Invalid Credentials. Please try again.")
						
						self.user.userId = "";
						self.user.userPassword = "";
					} else {
						console.log("Valid Credentials. Navigating to home page.");
						$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
						$rootScope.currentUser=d;
						$cookieStore.put('currentUser', $rootScope.currentUser);
						$localStorage.newCurrentUser=$rootScope.currentUser;
						console.log("*****Local Storage"+$localStorage.newCurrentUser.userName);
						console.log("*****Root scope Storage"+$rootScope.currentUser.userName);
						$location.path('/');
					}
				}, 
				function(errResponse) {
					console.error('Error while authenticate User...');
				});
			};
			
			self.logout = function() {
				console.log("--> UserController : calling logout method.");
				UserService.logout();
				$rootScope.currentUser = {};
				$cookieStore.remove('currentUser');
				//consol: $localStorage.currentUser.remove is not a function 
				$localStorage.newCurrentUser.remove('currentUser');
				
				console.log("-->UserController : User Logged out.");
				
				$window.location.reload();
				$location.path('/');
			}

			self.deleteUser = function(id) {
				console.log("--> UserController : calling deleteUser function.");
				UserService.deleteUser(id).then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting User...');
						});
			};

			self.fetchAllUsers();

			self.login = function() {
				{
					console.log('login validation ??????????', self.user);
					self.authenticate(self.user);
				}
			};

			self.register = function() {
				{
					console.log("--> UserController : calling register() method.", self.user);
					self.createUser(self.user);
					console.log('Saving new user...');
				}
				$location.path('/login');
				self.reset();
			};
			
			self.reset = function() {
				self.user = {
						errorCode: '',
						errorMessage: '',
						userId: '',
						userPassword: '',
						userName: '',
						userRole: '',
						userGender: '',
						userDob: '',
						userEmail: '',
						userContactNumber: '',
						userAddress: '',
						userStatus: '',
						userIsOnline: '',
						userImage: '',
						userDescription: ''
				};
				$scope.myForm.$setPristine();
			};

		} ]);