"use strict"
app
		.controller(
				'BlogController',
				[
						'BlogServices',
						'$http',
						'$location',
						'$rootScope',
						'$scope',
						function(BlogServices, $http, $location, $rootScope,
								$scope) {

							$scope.message = "Message from BlogController"

							var self = this;

							self.blog = {
								Id : '',
								tittle : '',
								emailId : '',
								Date_Time : '',
								status : '',
								reason : '',
								description : ''
							};

							self.blogComment = {
									Id : "",
									blogId : "",
									username : "",
									userComment :""
							}
							
							self.blogComments = [];
							
							self.blogs = [];

									// start of the fetchAllBlogs function()
									self.fetchAllBlogs = function() {
										BlogServices
												.fetchAllBlogs()
												.then(

														function(d) {
															self.blogs = d;

														},
														function(errResponse) {
															console
																	.log("Error while fetching all the Blogs")
														})
									},// end of the fetchAllBlogs function()

									self.fetchAllBlogs();

									
									
									
									// start of the createBlog function()
									self.createBlog = function(blog) {
										// console.log(self.blog.Id)

										console
												.log("BlogController=====>Starting createBlog function()=====>")

										BlogServices
												.createBlog(blog)
												.then(

														function(d) {

															self.blog = d;
															alert(self.blog.errorMessage)
															$location
																	.path('/blogs')
															console
																	.log("BlogController=====>Ending createBlog function()=====>")

														},
														function(errResponse) {
															alert("Error while Creating Blog,,., Please try Again after sometime,.,.. ")
															console
																	.log("Error while Creating Blog,,., Please try Again after sometime,.,.. ")
															console
																	.log("BlogController=====>Ending createBlog function()=====>")

														})
									},// end of the createBlog function()

								
									
									
									
									
									
									
									
									
									
									self.updateBlog = function(blog) {
										BLogServices
												.updateBlog(blog)
												.then(
														function(d) {
															alert("ThankYou!! BLog has been update Successfully,.,!!,.,!!,.,")
															$location
																	.path('/getSelectedBlog')

														},

														function(errResponse) {
															console
																	.log("Error while updating Blog,.,Please try again after sometime,.,!!,.,!!,.,")
														})

									}

							// start of the getBlog function()
									self.getBlogById = function(id) {
										console.log("BlogController=====>Starting getBlogbyId function()=====>")
										console.log(self.blog.id)
										BlogServices
												.getBlogbyId(id)
												.then(
														function(d) {
															self.blog = d;
															/*console
																	.log(self.blog.description)
															console
																	.log(self.blog.tittle)
*/
															console
															.log("BlogController=====>Ending getBlogbyId function()=====>")

															$location
																	.path('/getSelectedBlog')
														},
														function(errResponse) {
															console
																	.log("Error while fetching the blog")
																	console
																	.log("BlogController=====>Ending getBlogbyId function()=====>")

														}

												)

									},// end of the getBlogFunction()

									self.postBlogComment = function(blogId,comment) {
										console.log("BlogController=====>Starting postBlogComment function()=====>")
										console.log("BlogController=====>Calling postBlogComment function() with blogID==>"+blogId+" comment==>"+comment)

										BlogServices
												.postBlogComment(blogId,comment)
												.then(
														function(d) {
															self.blogComment = d;
															
															console
															.log("BlogController=====>Ending postBlogComment function()=====>")

																											},
														function(errResponse) {
															console
																	.log("Error while fetching the blog")
																	console
																	.log("BlogController=====>Ending postBlogComment function()=====>")

														}

												)

									},// end of the postBlogComment()

									
									self.allBlogComments = function() {
										console.log("BlogController=====>Calling blogComments function() with BLOGID=====>")

										BlogServices
												.allBlogComments()
												.then(

														function(d) {
															self.blogComments = d;

														},
														function(errResponse) {
															console
																	.log("Error while fetching all the Blogs")
														})
									},// end of the allBlogComments function()

									/*self.blogComments = function(blogId) {
										console
										.log("BlogController=====>Calling blogComments function() with BLOGID=====>"+blogId)
										$rootScope.showcomments="true"
										BlogServices
												.blogComments(blogId)
												.then(

														function(d) {
															self.blogComments = d;

														},
														function(errResponse) {
															console
																	.log("Error while fetching all the Blogs")
														})
									}
*/									
									self.allBlogComments();

									
									self.submit = function() {
										self.createBlog(self.blog);
										self.reset();

									};

							self.reset = function() {
								self.blog = {
									tittle : '',
									description : ''
								}
							};

						} ])