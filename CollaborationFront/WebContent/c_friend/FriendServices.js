'use strict'

app.factory('FriendServices',['$http','$q','$rootScope',function($http,$q,$rootScope){

	console.log("Starting ==>  FriendService")

	
	var BaseURL='http://localhost:8080/collaborationback'

		return{
			
		//To send Friend Request to a Friend
		sendFriendRequest:function(friendUserName)
		{
			
			console.log("FriendService ==> Starting sendFriendRequest function()")
			
			return $http.get(BaseURL+'/sendFriendRequest/'+friendUserName).
			then(
				function(Response)
				{
					console.log("FriendService ==> Ending sendFriendRequest function()")

					return Response.data;
				},
				function(errResponse)
				{
					console.log("FriendService ==> Ending sendFriendRequest function()")

					return $q.reject(errResponse);
				}
			);
		},
		
		
		
		//To Accept Friend Request
		acceptFriendRequest:function(friendUserName){
			
			console.log("FriendService ==> Starting acceptFriendRequest function()")

			return $http.put(BaseURL+'/acceptFriendRequest/'+friendUserName).then
			(
					function(Response)
					{
						console.log("FriendService ==> Ending acceptFriendRequest function()")

						return Response.data;
					},
					function(errResponse)
					{
						console.log("FriendService ==> Ending acceptFriendRequest function()")

						return $q.reject(errResponse);
					}
			)
		},
		
		
		//To Reject Friend Request
		rejectFriendRequest:function(friendUserName){
			console.log("FriendService ==> Starting rejectFriendRequest function()")

			return $http.put(BaseURL+'/rejectFriendRequest/'+friendUserName).then
			(

					function(Response)
					{
						console.log("FriendService ==> Ending rejectFriendRequest function()")

						return Response.data;
					},
					function(errResponse)
					{
						console.log("FriendService ==> Ending rejectFriendRequest function()")

						return $q.reject(errResponse);
					}
			)
		},
		
		
		//To get My Friend Request
		getMyFriendRequest:function()
		{
			console.log("FriendService ==> Starting getMyFriendRequest function()")

			return $http.get(BaseURL+'/getMyFriendRequest').then
			(
					function(Response)
					{
						console.log("FriendService ==> Ending getMyFriendRequest function()")

						return Response.data;
					},
					null
			)
		},
		
		
		
		//To get My Sent Friend Request
		getMySentFriendRequest:function()
		{
			console.log("FriendService ==> Starting getMySentFriendRequest function()")

			return $http.get(BaseURL+'/getMySentFriendRequest').then
			(
					function(Response)
					{
						console.log("FriendService ==> Ending getMySentFriendRequest function()")

						return Response.data;
					},
					null
			)
		},
		
		
		
		//To get List of My Friends
		getMyFriends:function()
		{
			console.log("FriendService ==> Starting acceptFriendRequest function()")

			return $http.get(BaseURL+'/getMyFriends').then
			(
					function(Response)
					{
						return Response.data;
					},
					null
			)
			
		}
	
		}
}
]
)














