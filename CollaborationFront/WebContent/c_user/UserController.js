/*"use strict"
*//*
app.controller('UserController',['$scope','UserServices','$location','$rootScope','$cookieStore','$http',
                                 function($scope, $UserServices, $location, $rootScope, $cookieStore, $http){
	*/
/*app.controller('UserController',function($scope){

	var self=this;

	
	self.user = {fname : '', lname : '', emailId : '', password : '', mobile : '', role : '', isOnline : '', status : '', errorCode : '',
			 errorMessage : '', gender : '', reason: ''	};
	

$scope.message="Message From User Contoller"
	
	
	self.reset = function(){
	self.user = {
	fname : '',
	lname : '',
	emailId : '',
	password : '',
	mobile : '',
	role : '',
	isOnline : '',
	status : '',
	errorCode : '',
    errorMessage : '',
    gender : '',
	reason: ''
	}

}
}
);*/
	

app.controller('UserController',['$scope','UserServices','$location','$rootScope','$http',
                                 function($scope, UserServices, $location, $rootScope, $http){
	$scope.message="Message From User Contoller"
		console.log("Starting ==>  UserController")

										var self =this;
									
										self.user = {username:'',fname : '', lname : '', emailId : '', password : '', conPassword : '', mobile : '', role : '', isOnline : '', status : '', errorCode : '',
													 errorMessage : '', gender : '', reason : ''	};
										//console.log(self.user.gender)
										
										self.users = [];
										
										
										//Start of fetchAllUsers function()
										self.fetchAllUsers = function(){	
											console.log("UserController ==> Starting fetchAllUsers function()")
											
											UserServices.fetchAllUsers().then
											    (
														function(d){
															self.users=d;
															console.log("UserController ==> Ending fetchAllUsers function()")
														},
														function(errResponse)
														{
															console.error("Error  While Getting all the Users detials,.,.,.")
															console.log("UserController ==> Ending fetchAllUsers function()")

														}
												)
										};//end of fetchAllUsers function()
									
										
										
										
										//start of createUser function()
										self.createUser = function(user){
											console.log("UserController ==> Starting createUser function()")
											UserServices.createUser(user).then
											(
													function(d)
													{
														alert("ThankYou For Registration,.,,,,")
														console.log("UserController ==> Ending createUser function()")
														//location
													},
													function(errResponse)
													{
														console.error("Error While Registration,,., Please try again after some time,.,")
														console.log("UserController ==> Ending createUser function()")

													}
											)
										};//end of createUser function()
										
										
										
										self.authenticate = function(user)
										{
											console.log("UserController ==> Starting createUser function()")
											UserServices.authenticate(user).then
											(
													function(d)
													{
														self.user = d;
														if(self.user.errorCode =="404")
															{
																alert(self.user.errorMessage)
																self.username=''
																self.password=''
																	console.log("UserController ==> Ending createUser function()")
																
															}
														else
															{
																$rootScope.currentUser = self.user
																console.log("Logging in with Email :- "+$rootScope.currentUser.emailId)
																$rootScope.IsLoggedIn="true"
																if($rootScope.currentUser.role==='admin')
																	{
																			$rootScope.isAdmin="true"
																			console.log("UserController ==> Login as "+$rootScope.currentUser.role)
																			console.log("UserController ==> Ending createUser function()")
																			$location.path('/adminhome')
																	}
																else
																	{
																			$rootScope.showProfile="true"
																			console.log("UserController ==> Login as "+$rootScope.currentUser.role)
																			console.log("UserController ==> Ending createUser function()")
																			$location.path('/blogs')	
																	}
															}
													}
											)
										}
										
										
										
										
										self.makeAdmin = function(username){
											console.log("UserController ==> Starting makeAdmin function()")
											console.log("UserController ==> Calling MakeAdmin Function with username "+username)
											UserServices.makeAdmin(username).then
											(
													function(d)
													{
														self.user =  d;
														console.log("UserController ==> Ending makeAdmin function()")
														alert(self.user.errorMessage)
														$location.path('/allusers')
													}											)
										}
										
										
										
										
										self.logoutUser = function()
										{
											UserServices.logout().then
											(
													function(d)
													{	
														$rootScope.IsLoggedIn="false"

															self.user = d;
															alert(self.user.errorMessage)
															$location.path('/login')
													},
													null
											)
										}
										
										
										//start of myProfile function()
										self.myProfile = function(){
											UserServices.myProfile().then
											(
													function(d)
													{
														self.user=d;
													},
													function(errResponse)
													{
														console.error("Error While Fetching Profile,.,..,.")
													}
											)
										}//end of myProfile function()
										
										
										
										
										
										//start of submit function()
										self.submit = function() {
											{
											self.createUser(self.user);
											}
											self.reset();

										};//end of submit function()
										
										
										self.login = function()
										{
											self.authenticate(self.user)
										}
										
										self.logout = function()
										{
											self.logoutUser()
										}
										
										
										self.fileUpload=function()
										{
											
													$location.path('/blogs')
										}
										
										
										self.fetchAllUsers();
										
										//start of reset function
										self.reset = function(){
											self.user = {
											fname : '',
											lname : '',
											emailId : '',
											password : '',
											mobile : '',
											role : '',
											isOnline : '',
											status : '',
											errorCode : '',
										    errorMessage : '',
										    gender : '',
											reason: ''
											}
										};//end of reset funtion()
											
}])