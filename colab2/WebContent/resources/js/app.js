var app = angular.module('myApp', [ 'ngRoute', 'ngCookies', 'ngStorage']);		//we need to include 'ngRoute' and 'ngCookies' as dependency in the application module.

app.config(function($routeProvider) {		//We can use config() block to inject only providers and constants in our AngularJS application.
										//The providers basically create new instances, but only once for each provider.
	$routeProvider				//With the $routeProvider we can define what page to display when a user clicks a link.

	/**
	 * Home page mapping
	 */
	.when('/', {							//when(path, route) where path is string type and route is object type
		templateUrl : 'home/home.html',			//'templateUrl' is used to specify the path of the view file that will load 
		controller : 'HomeController as ctrl'		//'controller' is used to specify the particular controller for this 'path' or view.
	})

		



	
	

	
	/*for user edited 12/06/17*/
.when('/login', {
		templateUrl : 'user/login.html',
		controller : 'UserController as ctrl'
	})
	
.when('/editprofile', {
		templateUrl : 'user/editprofile.html',
		controller : 'UserController as ctrl'
	})	
		
.when('/register', {
		templateUrl : 'user/register.html',
		controller : 'UserController as ctrl'
	})
	
	
.when('/viewProfile', {
	templateUrl : 'user/viewProfile.html',
	controller : 'UserController as ctrl'
	})
	
.when('/users', {
templateUrl : 'user/userList.html',
controller : 'UserController as ctrl'
})
	
	/*blog edited on 12/07/2017*/
.when('/blogs', {
		templateUrl : 'blog/bloglist.html',
		controller : 'BlogController as ctrl'
	})
	
.when('/viewBlog', {
		templateUrl : 'blog/viewBlog.html',
		controller : 'BlogController as ctrl'
	})	
	
.when('/forums', {
		templateUrl : 'forum/forumList.html',
		controller : 'ForumController as ctrl'
	})
.when('/viewForum', {
		templateUrl : 'forum/viewForum.html',
		controller : 'ForumController as ctrl'
	})
	
	
	.when('/jobs', {
		templateUrl : 'job/jobList.html',
		controller : 'JobController as ctrl'
	})
	
	.when('/applicants', {
		templateUrl : 'job/applicantsList.html',
		controller : 'JobController as ctrl'
	})
	
	

.when('/file', {
		templateUrl : 'f_file/file_upload.html',
		controller : 'FileUploadController as ctrl'
	})
	
	
.when('/chat', {
		templateUrl : 'b_chat/chat.html',
		controller : 'ChatController'
	})
	
	//forum 28/12/2017

	
	/**
	 * If anything goes wrong then this mapping will handle the request...
	 */

	.otherwise({			//If none of the above link has been clicked, then 'otherwise' method get called.
		redirectTo : '/'		// otherwise method redirects to '/' path if path given in wrong way
	});
});

app.run(function($rootScope, $location, $cookieStore, $http) {		//run() block gives us facility to inject any instance and constants in our application.
	console.log("--> app : entered app.run");

	$rootScope.$on('$locationChangeStart', function(event, next, current) {		//The $locationChangeStart event can be used to prevent a location change going forward.
		console.log("--> $rootScope.$on <--");
		// redirect to login page if try to access any other page rather than the restricted pages
		var restrictedPage = $.inArray($location.path(), [ '/', 
		                                                   '/login', 
		                                                   '/logout', 
		                                                   '/register',

		                                                   	'/blogs', 
		                                                   	'/viewBlog',
		                                                   	'/chat',
		                                                   	
		                                                 
		                                                   '/about', 
		                                                   '/list_event',
		                                                   '/view_event', 
		                                                   '/list_forum', 
		                                                   '/view_forum', 
		                                                   '/search_job', 
		                                                   '/view_job_details',
		                                                   '/chat',
		                                                   '/myprofile']) === -1;

		console.log("restrictedPage : " + restrictedPage);
		var loggedIn = $rootScope.currentUser.userId;		//taking currentUser.id in $rootScope as 'loggedIn' so that we can use it throughout the session. 

		console.log("loggedIn : " + loggedIn);
		if (restrictedPage && !loggedIn) {
			console.log("Navigating to login page.");
			$location.path('/login');
		}
	});


	// keep user logged in after page refresh...
	
	 $rootScope.currentUser = $cookieStore.get('currentUser');
	 
});   
