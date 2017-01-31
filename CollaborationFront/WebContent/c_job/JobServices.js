"use stict"

app.factory('JobServices',['$http','$q','$rootScope',function($http,$q,$rootScope)
                          {
	
	console.log("Starting ==>  JobServices")

								var BaseURL='http://localhost:8080/collaborationback'
								return{
											
									
											fetchAllJobs : function(){
												console.log("JobServices ==> Starting fetchAllJobs function()")

												return $http.get(BaseURL+'/getOpenJobs').then
												(
														function(Response){
															console.log("JobServices ==> Ending fetchAllJobs function()")

															return Response.data;
														},
														null
												)
											},
											
											
											fetchAllJobApplications : function(){
												console.log("JobServices ==> Starting fetchAllJobApplications function()")

												return $http.get(BaseURL+'/allJobApplications').then
												(
														function(Response){
															console.log("JobServices ==> Ending fetchAllJobApplications function()")

															return Response.data;
														},
														null
												)
											},
											
											createJob : function(job){
												console.log("JobServices ==> Starting createJob function()")

												return $http.post(BaseURL+'/postJob/',job).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending createJob function()")

															return Response.data;
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending createJob function()")

															return $q.reject(errResponse)
														}
												)
											},
											
											jobById : function(id){
												console.log("JobServices ==> Starting jobById function()")

												return $http.get(BaseURL+'/jobById/'+id).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending jobById function()")

															return Response.data
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending jobById function()")

															$q.reject(errResponse)
														}
												)
											},
											
											updateJob : function(job){
												console.log("JobServices ==> Starting updateJob function()")

												return $http.put(BaseURL+'/updateuser',job).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending updateJob function()")

															return Response.data;
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending updateJob function()")

															return $q.reject(errResponse)
															
														}
												)
											},
											
											
											applyForJob : function(jobId)
											{
												console.log("JobServices ==> Starting applyForJob function()")

												return $http.post(BaseURL	+'/applyForJob/'+jobId).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending applyForJob function()")

															return Response.data;
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending applyForJob function()")

															console.error("Error While Applying For Job")
															return $q.reject(errResponse)
														}
												)
											},
											
											
											
											selectJobApplication : function(username,jobid,reason)
											{
												console.log("JobServices ==> Starting selectJobApplication function()")
												return $http.put(BaseURL+'/selectJobApplication/'+username+'/'+jobid+'/'+reason).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending selectJobApplication function()")

															return Response.data
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending selectJobApplication function()")

															console.error("Error While Selecting Job Application")
															return $q.reject(errResponse)
														}

												)
												


											},
											
											
											rejectJobApplication : function(username,jobid,reason)
											{
												console.log("JobServices ==> Starting selectJobApplication function()")
												return $http.put(BaseURL+'/rejectJobApplication/'+username+'/'+jobid+'/'+reason).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending selectJobApplication function()")

															return Response.data
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending selectJobApplication function()")

															console.error("Error While Selecting Job Application")
															return $q.reject(errResponse)
														}

												)
												


											},
											
											
											callForInterview : function(username,jobid,reason)
											{
												console.log("JobServices ==> Starting selectJobApplication function()")
												return $http.put(BaseURL+'/callForInterview/'+username+'/'+jobid+'/'+reason).then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending callForInterview function()")

															return Response.data
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending callForInterview function()")

															console.error("Error While Selecting Job Application")
															return $q.reject(errResponse)
														}

												)
												


											},
											
											
											myAppliedJobs : function()
											{
												console.log("JobServices ==> Starting myAppliedJobs function()")
												return $http.get(BaseURL+'/myAppliedJobs').then
												(
														function(Response)
														{
															console.log("JobServices ==> Ending myAppliedJobs function()")

															return Response.data
														},
														function(errResponse)
														{
															console.log("JobServices ==> Ending myAppliedJobs function()")

															console.error("Error While getting my selected job")
															return $q.reject(errResponse)
														}

												)

											}
											
											
											
											


											
											
											
											
											
									  }
                          }])
