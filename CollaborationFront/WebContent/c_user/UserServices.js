"use strict"

app.factory('UserServices',['$http','$q','$rootScope',function($http,$q,$rootScope){

	console.log("Starting ==>  UserServices")

	var BaseURL='http://localhost:8080/collaborationback'
	
		return{
		
		//start of fetchAllUsers
		fetchAllUsers:function(){
			console.log("UserServices ==> Starting fetchAllUsers function()")

			return $http.get(BaseURL+'/allUsers')
			.then(
					function(Response){
						console.log("UserServices ==> Ending fetchAllUsers function()")

						return Response.data;
					},
					null
				)
		},//end of fetchAllUsers:function()
	

		
		//start of createUser function()
		createUser:function(user){
			console.log("UserServices ==> Starting createUser function()")

		return $http.post(BaseURL+'/registerUser/',user)
		.then(
				function(Response){
					console.log("UserServices ==> Ending createUser function()")

					return Response.data;
				},
				function(errResponse){
					console.log("UserServices ==> Ending createUser function()")

					return $q.reject(errResponse);
				}
				
		);
	},//end of createUser function()
	
	
	
	
	//start of updateUser function()
	updateUser:function(user){
		console.log("UserServices ==> Starting updateUser function()")

		return $http.put(BaseURL+'/updateUser/'+user)
		.then(
			function(Response)
			{
				console.log("UserServices ==> Ending updateUser function()")

				return Response.data
			},
			function(errResponse)
			{
				console.log("UserServices ==> Ending updateUser function()")

				return $q.reject(errResponse);
			}
		);
	},//end of updateUser function()
	
	
	
	
	
	//start of accept function()
	accept:function(id){
		console.log("UserServices ==> Starting accept function()")

		return $http.get(BaseURL+'/userById/'+id)
		.then(
				function(Response)
				{
					console.log("UserServices ==> Ending accept function()")

					return Response.data;
				},
				function(errResponse)
				{
					console.log("UserServices ==> Ending accept function()")

					return $q.reject(errResponse);
				}
		);

	},//end of accept function()
	
	
	
	
	//start of the authenticate function()
	authenticate:function(user){
		console.log("UserServices ==> Starting authenticate function()")

		return $http.post(BaseURL+'/authenticate/',user)
		.then(
				function(Response){
					console.log("UserServices ==> Ending authenticate function()")

					return Response.data;
				},
				null
				/*function(errResponse)
				{
					return $q.reject(errResponse);
				}*/
		);
	}, //end of authenticate function()
	
	
	makeAdmin : function(username){
		console.log("UserServices ==> Starting makeAdmin function()")

		return $http.put(BaseURL+"/makeAdmin/"+username).then
		(
				function(Response)
				{
					
					console.log("UserServices ==> Ending makeAdmin function()")

					return Response.data;
				},
				null
		)
	},
	
	logout : function()
	{
		console.log("UserServices ==> Starting logout function()")

		return $http.get(BaseURL+'/logout').then
		(
				function(Response)
				{
					console.log("UserServices ==> Ending logout function()")

					return Response.data;
				},
				null
		)
	},
	
	
	
	//start of myProfile function()
	myProfile : function(){
		console.log("UserServices ==> Starting myProfile function()")

		return $http.get(BasURL+'/myprofile').then
		(
				function(Response)
				{
					console.log("UserServices ==> Ending myProfile function()")

					return Response.data;
				},
				null
		)
		
	},//end of myProfile function()
	
	fileUpload : function(uploadFile)
	{
		return $http.get(BaseURL+'/upload/'+uploadFile).then
		(
			function(Response)
			{
				return Response.data
			},
			null
		)
	}
	
	
	
	}
}])