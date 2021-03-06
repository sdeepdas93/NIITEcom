'use strict';

app.controller('BlogController', [
		'$scope',
		'BlogService',
		'$location',
		'$rootScope',
		
		function($scope, BlogService, $location, $rootScope) {
			
			console.log("BlogController...")

			var self = this;
			self.blog = {
				blogId : '',
				blogTitle : '',
				blogReason : '',
				blogContent : '',
				blogDate : '',
				blogUserId : '',
				blogStatus : '',
				blogCountLike : '',
				errorCode : '',
				errorMessage : ''
			}
			self.blogs = [];
			
			
			 
		 self.blogComment = 
		    {
		    		blogCommentId : '',
		    		blogId : '',
		    		userId : '',
		    		userName : '',
		    		blogComment : '',
		    		blogCommentDate:'',
					errorCode : '',
					errorMessage : ''
			}		
		    
		    self.blogComments = [];
			
			
			self.getSelectedBlog = function(id) {
				console.log("-->BlogController : calling getSelectedBlog method : getting blog with id : " + id);
				BlogService.getSelectedBlog(id).then(
						
						function(d) {
							self.blog = d;
							
							self.fetchAllBlogComments(id);
							$location.path('/viewBlog');
						}, 
						function(errResponse) {
							console.error('Error while fetching Blog...');
						});
			};
			
			self.likeSelectedBlog = function(blog, id) {
				console.log("-->BlogController : calling likeBlog() method : Blog id is : "+id);
				console.log("-->BlogController", self.blog);
				
				BlogService.likeBlog(blog, id).then(
						function()
						{self.getSelectedBlog(id),
							self.fetchAllBlogs(),
							$location.path('/viewBlog')},
						
						function(errResponse) {
							console.error("Error while liking the blog...")
						});
				
				
			};
			
			/***
			 */
			
			self.approveSelectedBlog = function(blog, id) {
				console.log("-->BlogController : calling approveBlog() method : getting blog with id : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.approveBlog(blog, id).then(
						function(){
						self.getSelectedBlog(id);
						self.fetchAllBlogs();
						$location.path('/viewBlog')},
						function(errResponse) {
							console.error("Error while approving blog...")
						});
			};

			self.rejectSelectedBlog = function(blog, id) {
				console.log("-->BlogController : calling rejectBlog() method : getting blog with id : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.rejectBlog(blog, id).then(
						function(){
						self.getSelectedBlog(id),
						self.fetchAllBlogs(),
						
						$location.path('/viewBlog')},
						function(errResponse) {
							console.error("Error while rejecting blog...")
						});
			};
			
			/**/

			self.fetchAllBlogs = function() {
				console.log("--> BlogController : calling fetchAllBlogs method.");
				BlogService.fetchAllBlogs().then(
						function(d) {
							self.blogs = d;
						}, function(errResponse) {
							console.error('Error while fetching Blogs...');
						});
			};
			
			self.createBlog = function(blog) {
				console.log("--> BlogController : calling createBlog method.");
				BlogService.createBlog(blog).then(
						
						function(d) {
							
							self.blog = d;
							alert('Blog Created Successfully...')
							self.reset();
							self.fetchAllBlogs();
							$location.path('/blogs');
							
							
							
						},
						function(errResponse) {
							console.error('Error while creating blog...');
						}
						);
			};

			self.updateBlog = function(blog, id) {
				console.log("-->BlogController : calling updateBlog method.");
				BlogService.updateBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error('Error while updating blog...')
						});
			};

			self.deleteBlog = function(id) {
				console.log("-->BlogController : calling deleteBlog method.");
				BlogService.deleteBlog(id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error('Error while deleting blog...')
						});
			};
			
			self.approveBlog = function(blog, id) {
				console.log("-->BlogController : calling approveBlog() method : getting blog with id : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.approveBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error("Error while approving blog...")
						});
			};

			self.rejectBlog = function(blog, id) {
				console.log("-->BlogController : calling rejectBlog() method : getting blog with id : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.rejectBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error("Error while rejecting blog...")
						});
			};
			
			self.likeBlog = function(blog, id) {
				console.log("-->BlogController : calling likeBlog() method : Blog id is : "+id);
				console.log("-->BlogController", self.blog);
				
				BlogService.likeBlog(blog, id).then(
						self.fetchAllBlogs,$location.path('/blogs'),
						function(errResponse) {
							console.error("Error while liking the blog...")
						});
				
				
			};

			self.fetchAllBlogs();

	/*****************************************************************************/
			
			self.submit = function() {
				{
					console.log("--> BlogController : calling submit() method.", self.blog);
					self.createBlog(self.blog);
					console.log('Saving new Blog', self.blog);
					
				}
				
				
			};			
			
			self.edit = function(id) {
				console.log("id to be edited : "+id);
				for (var i = 0; i < self.blogs.length; i++) {
					if (self.blogs[i].id === id) {
						self.blog = angular.copy(self.blogs[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.blog.id === id) {
					self.reset();
				}
				self.deleteBlog(id);
			};

			self.reset = function() {
				console.log('submit a new Blog', self.blog);
				self.blog = {
						blogId : '',
						blogTitle : '',
						blogReason : '',
						blogContent : '',
						blogDate : '',
						blogUserId : '',
						blogStatus : '',
						blogCountLike : '',
						errorCode : '',
						errorMessage : ''
				};
				
				$scope.myForm.$setPristine(); // reset form...
			};
			//comment section
			self.fetchAllBlogComments = function(id)
			{
				console.log("-->BlogController : calling fetchAllBlogComments method with id : "+ id);
				BlogService.fetchAllBlogComments(id).then
				(function(d) 
				{
					self.blogComments = d;
						//calling getSelectedBlog(id) method ...
					
				},
				function(errResponse) 
				{
					console.error('Error while fetching BlogComments...');
				}
				);
			};
			
			
			
			self.createBlogComment = function(blogComment, id) {
				console.log("-->BlogController : calling 'createBlogComment' method.", self.blog);
				blogComment.blogId = id;
				console.log("-->BlogController BlogId :" +blogComment.blogId);
				BlogService.createBlogComment(blogComment).then
							(function(d) 
							{
								console.log('Current User :',$rootScope.currentUser.userId)
								self.blogComment = d;
								console.log('-->BlogController :', self.blogComment)
								self.fetchAllBlogComments(id);
								self.resetBlogComment();
							},
							function(errResponse) {
								console.error('Error while creating blogComment...');
							}
							);
			};
			
			
			
			
			self.resetBlogComment = function() 
			{
				console.log('submit a new BlogComment', self.blogComment);
				self.blogComment = {
						blogCommentId : '',
			    		blogId : '',
			    		userId : '',
			    		userName : '',
			    		blogComment : '',
			    		blogCommentDate:'',
						errorCode : '',
						errorMessage : ''
					};
				$scope.myForm.$setPristine(); // reset blogComment form...
			};
			
		} ]);