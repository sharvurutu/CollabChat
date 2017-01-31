"use strict"

app.controller('JobController',['JobServices','$http','$rootScope','$location','$scope',function(JobServices,$http,$location,$scope,$rootScope)
{
	$scope.message = "Message from JobController"
		
		console.log("Starting ==>  JobController")


	var self = this;
	
	self.job = {Id : '', tittle : '', qualification : '', description : '', status : '', date : ''};
	
	self.jobs = [];
	
	self.jobapplication = {Id:'',username :'',jobId:'',date_Applied:'',status:'',remarks:''}
	
	self.jobapplications = [] 
	
	self.myJobapplications = [];
	
	
	var currentUser = $rootScope.currentUser;

	
	self.fetchAllJobs = function()
	{
		console.log("JobController ==> Starting fetchAllJobs function()")

		JobServices.fetchAllJobs().then
		(
				function(d)
				{
					console.log("JobController ==> Ending fetchAllJobs function()")

					self.jobs=d;
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending fetchAllJobs function()")

					console.log("Error While Fetching all Jobs !!,.,!!,.,.")
				}
		)
	},
	
	
	self.fetchAllJobApplications = function()
	{
		console.log("JobController ==> Starting fetchAllJobs function()")

		JobServices.fetchAllJobApplications().then
		(
				function(d)
				{
					console.log("JobController ==> Ending fetchAllJobs function()")

					self.jobapplications=d;
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending fetchAllJobs function()")

					console.log("Error While Fetching all Jobs !!,.,!!,.,.")
				}
		)
	}
	
	self.fetchAllJobs();
	
	self.fetchAllJobApplications();
	
	
	function applyForJob(jobId)
	{
		console.log("JobController ==> Starting applyForJob function()")
		console.log("Logged In User Id:-  "+$rootScope.currentUser)
		var currentUser = $rootScope.currentUser;

	//	var currentUserId = $rootScope.currentUser.emailId
	//	console.log("Logged In User Id:-  "+$rootScope.currentUser.emailId)
		if (typeof currentUser == 'undefined')
			{
				alert("Please login to apply for the Job")
				//console.log("User Not Logged In So Not Able To Apply  ---->")
									console.log("JobController ==> Ending applyForJob function()")

				return;
			}
		
		JobServices.applyForJob(jobId).then
		(
				function(d)
				{
					console.log("JobController ==> Ending applyForJob function()")

					self.job = d
					alert(self.job.errorMessage)
				}
		)
	}
	
	
	self.applyForJob = applyForJob
	
	self.jobByID = function(id)
	{
		console.log("JobController ==> Starting applyForJob function()")

			JobServices.jobById(id).then
			(
					function(d)
					{
						console.log("JobController ==> Ending applyForJob function()")

						self.job = d;
					},
					function(errRespose)
					{
						console.log("JobController ==> Ending applyForJob function()")

						console.log("Error While Fetching Jon ,.,!!,.,!!,.,")
					}
			)
	},
	
	
	self.createJob = function(job)
	{
		console.log("JobController ==> Starting createJob function()")

		JobServices.createJob(job).then
		(
				function(d)
				{
					console.log("JobController ==> Ending createJob function()")

					alert("Thankyou Job has been Posted Successfull")
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending createJob function()")

					console.log("Error while posting job,, please try again after sometime ,.,!!,.,!!,.,")
				}
		)
	}
	
	
	self.updateJob = function(job)
	{
		console.log("JobController ==> Starting updateJob function()")

		JobServices.updateJob(job).then
		(
				function(d)
				{
					console.log("JobController ==> Ending updateJob function()")

					alert("Thankyou Job has been udpate Successfully")
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending updateJob function()")

					console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
				}
		)
	}
	
	
	self.selectJobApplication = function(username,jobid,reason)
	{
		console.log("JobController ==> Starting selectJobApplication function()")

		console.log("JobController ==> Calling selectJobApplication with "+username+"  "+jobid+"  "+reason)

		JobServices.selectJobApplication(username,jobid,reason).then
		(
				function(d)
				{
					self.jobapplication = d;
					console.log("JobController ==> Ending selectJobApplication function()")
					alert(self.jobapplication.errorMessage)
					self.fetchAllJobApplications()
					$location.path('/alljobapplication')
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending selectJobApplication function()")

					console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
				}
		)

	}
		
	self.rejectJobApplication = function(username,jobid,reason)
	{
		console.log("JobController ==> Starting rejectJobApplication function()")

		console.log("JobController ==> Calling rejectJobApplication with "+username+"  "+jobid+"  "+reason)

		JobServices.rejectJobApplication(username,jobid,reason).then
		(
				function(d)
				{
					self.jobapplication = d;
					console.log("JobController ==> Ending rejectJobApplication function()")
					alert(self.jobapplication.errorMessage)
					self.fetchAllJobApplications()
					//$location.path('/adminhome')
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending rejectJobApplication function()")

					console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
				}
		)

	},
	
	self.callForInterview = function(username,jobid,reason)
	{
		console.log("JobController ==> Starting callForInterview function()")

		console.log("JobController ==> Calling callForInterview with "+username+"  "+jobid+"  "+reason)

		JobServices.callForInterview(username,jobid,reason).then
		(
				function(d)
				{
					self.jobapplication = d;
					console.log("JobController ==> Ending callForInterview function()")
					alert(self.jobapplication.errorMessage)
					self.fetchAllJobApplications()
					//$location.path('/adminhome')
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending callForInterview function()")

					console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
				}
		)

	},
	
	
	
	self.myAppliedJobs = function()
	{
		console.log("JobController ==> Starting myAppliedJobs function()")

	//	console.log("JobController ==> Calling myAppliedJobs with "+username+"  "+jobid+"  "+reason)
		
		JobServices.myAppliedJobs().then
		(
				function(d)
				{
					self.myJobapplications = d;
					console.log("JobController ==> Ending myAppliedJobs function()")
					//alert(self.jobapplication.errorMessage)
					//self.fetchAllJobApplications()
					//$location.path('/adminhome')
				},
				function(errResponse)
				{
					console.log("JobController ==> Ending myAppliedJobs function()")

					console.log("Error While fetching myAppliedJobs,.,Please try again after sometime,.,!!,.,!!,.,")
				}
		)

	}

	self.myAppliedJobs();
	
	
	self.submit =function()
	{
		console.log("JobController ==> Starting submit function()")

		self.createJob(self.job);
		self.reset();
		console.log("JobController ==> Ending submit function()")

	};
	
	
	self.reset = function()
	{
		console.log("JobController ==> Starting reset function()")

	self.job={
			
			Id :'',
			tittle :'',
			qualification : '',
			description :''
	}
		console.log("JobController ==> Ending reset function()")

	};
	
}])

