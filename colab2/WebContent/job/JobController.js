'use strict'

app.controller('JobController', ['JobService', '$scope', '$location', '$rootScope',
	function(JobService, $scope, $location, $rootScope) {
		console.log('JobController...');

		var self = this;
		self.job = {
				jobId : '', 
				jobProfile : '', 
				jobDescription : '', 
				jobQualification : '',
				jobStatus : '', 
				jobPostDate : '',
					errorCode: '',
					errorMessage: '',
		};

		self.jobs = [];
		
		self.jobApplication = {
				
				jobApplicationId : '',
				userId : '',
				jobId : '',
				jobApplicationStatus : '',
				errorCode: '',
				errorMessage: ''
		};
		
		self.jobApplications = [];
		
			

		self.listJobs = function() {
			console.log("-->JobController : calling 'listJobs' method.");
			JobService
						.listJobs()
						.then(function(d) {
							console.log("jobs :"+d[0]);
							self.jobs = d;
						},
						function(errResponse) {
							console.error("Error while getting job list.")
						});
		};		
		self.listJobs();
		

		
		self.createJob = function(job) {
			console.log("-->JobController : calling 'createJob' method.");
			JobService
						.createJob(job)
						.then(function(d) {
							self.job = d;
							alert('Post job?');
							self.reset();
							self.listJobs();
							$location.path('/jobs');
						},
						function(errResponse) {
							console.error('Error while posting new Job...');
						});
		};
		
		self.getJob = function(jobId) {
			console.log("-->JobController : calling 'getJob' method with jobId : "+jobId);
			JobService
						.getJob(jobId)
						.then(function(d) {
							self.job = d;
							$location.path('/view_job');
						},
						function(errResponse) {
							console.error('Error while fetching job details...')
						});
		};
		
		
		self.listVacantJobs = function() {
			console.log("-->JobController : calling 'listVacantJobs' method.");
			JobService
						.listVacantJobs()
						.then(self.listJobs,
						function(errResponse) {
							console.log("Error while getting list of vacant jobs.");
						});
		};
		
		
		self.applyForJob = function(job,jobId) {
			console.log("-->JobController : calling 'applyForJob' method with jobId:"+jobId);
			JobService
						.applyForJob(job,jobId)
						.then(function(d) {
							self.jobApplication = d;
							alert("You have successfully applied for the job...");
							self.listJobs();
							console.log("-->JobController : ", self.jobApplication);
							console.log("-->JobController : ", self.job);
							$location.path('/jobs');
							
						},
						function(errResponse) {
							console.error('Error while applying for job...')
						});

		};
		
		self.apply = function(job,jobId) {
			console.log("-->JobController : calling 'apply()' method.", self.job);
			self.applyForJob(job,jobId);
			console.log('Job applied successfully...', job);
		};
		
		self.submit = function() {
			{
				console.log("-->JobController : calling 'submit()' method.", self.job);
				self.createJob(self.job);
				console.log('Saving new Job', self.job);
			}
			self.reset();
		};
		self.reset = function() {
			console.log('submit a new job', self.job);
			self.job = {
					
					jobId : '', 
					jobProfile : '', 
					jobDescription : '', 
					jobQualification : '',
					jobStatus : '', 
					jobPostDate : '',
					errorCode: '',
					errorMessage: ''
			};
			$scope.myForm.$setPristine();	//reset form...
		};

	}]);