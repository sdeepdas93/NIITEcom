'use strict';

app.factory('JobService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("JobService...");

		var BASE_URL='http://localhost:8080/collaboration'
			return {

			listJobs : function() {
				console.log("-->JobService : calling 'listJobs' method.");
				return $http
							.get(BASE_URL+'/jobs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting Job list...');
								return $q.reject(errResponse);
							});
			},
			
			createJob : function(job) {
				console.log("-->JobService : calling 'createJob' method.");
				return $http
							.post(BASE_URL+'/job/', job)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new Job...');
								return $q.reject(errResponse);
							});
			},
			
			updateJob : function(job, id) {
				console.log("-->JobService : calling 'updateJob' method.");
				return $http
							.put(BASE_URL+'/job/'+id)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("Error while updating job.");
								return $q.reject(errResponse);
							});
			},

			getJob : function(jobId) {
				console.log("-->JobService : calling 'getJob' method with jobId : "+jobId);
				return $http
							.get(BASE_URL+'/jobs/'+jobId)
							.then(function(response) {
								$rootScope.selectedJob = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting job details');
								return $q.reject(errResponse);
							});
			},
			
			listJobApplications : function(id) {
				console.log("-->JobService : calling 'listJobApplications' method");
				return $http
							.get(BASE_URL+'/allApplications/'+id)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting JobApplication list...');
								return $q.reject(errResponse);
							});
			},

			getMyAppliedJobs : function() {
				console.log("-->JobService : calling 'getMyAppliedJobs' method");
				return $http
							.get(BASE_URL+'/appliedJobs')
							.then(function(response) {
								$rootScope.getAppliedJob = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting all applied jobs...');
								return $q.reject(errResponse);
							});
			},

			callForInterview : function(jobApplication, userId, jobId) {
				console.log("-->JobService : calling 'callForInterview' method with userId : "+userId+" jobId : "+jobId);
				return $http
							.put(BASE_URL+'/callForInterview/'+userId+'/'+jobId)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while updating Job Application...');
								return $q.reject(errResponse);
							});
			},

			rejectJobApplication : function(jobApplication, userId, jobId) {
				console.log("-->JobService : calling 'rejectJobApplication' method with userId : "+userId+" jobId : "+jobId);
				return $http
							.put(BASE_URL+'/rejectJobApplication/'+userId+'/'+jobId)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while updating Job Application...');
								return $q.reject(errResponse);
							});
			},
			
			listVacantJobs : function() {
				console.log("-->JobService : calling 'listVacantJobs' method.");
				return $http
							.get(BASE_URL+'/listVacantJobs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("Error while getting all vacant jobs.");
								return $q.reject(errResponse);
							});
			},

			applyForJob : function(job,jobId) {
				console.log("-->JobService : calling 'applyForJob' method with jobId:"+jobId);
				return $http
							.post(BASE_URL+'/jobApplication/'+jobId,job)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while applying for Job...');
								return $q.reject(errResponse);
							});
			},
			
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