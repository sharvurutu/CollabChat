'use strict'

app.controller('FriendController',['UserServices','FriendServices','$rootScope','$scope','$location',
                               function(UserServices,FriendServices,$rootScope,$scope,$location){
	
	$scope.friendmessage="Message From Friend Controller"
		
		console.log("Starting ==>  FriendController")

		
		var self = this;
		
		self.friend = {Id:'',username : '',friendUserName : '',status : '',IsOnline : '' };
		
		self.friends = [];
		
		self.myFriends = [];

		

		
		self.user = {username:'',fname : '', lname : '', emailId : '', password : '', conPassword : '', mobile : '', role : '', isOnline : '', status : '', errorCode : '',
				 errorMessage : '', gender : '', reason : ''	};
	
	    self.users = [];
	    
	    
	    
	    
	    
	    self.getAllFriend = function(){
			console.log("FriendController ==> Starting getAllFriend function()")
			UserServices.getAllFriend().then
			    (
						function(d){
							self.users=d;
							console.log("FriendController ==> Ending getAllFriend function()")

						},
						function(errResponse)
						{
							console.error("Error  While Getting all the Users detials,.,.,.")
							console.log("FriendController ==> Ending getAllFriend function()")

						}
				)
		};


	    

	    
	   		
		
		
		

		 self.sendFriendRequest = function (friendUserName){

			console.log("FriendController ==> Starting sendFriendRequest function()")

			console.log(friendUserName)

			FriendServices.sendFriendRequest(friendUserName).then
			(
					function(d)
					{
						self.friend=d;
						alert(self.friend.errorMessage)
						console.log("FriendController ==> Ending sendFriendRequest function()")
						self.getAllFriend();

					},
					function(errResponse)
					{
						console.error("Error  While Sending Friend Request")
						console.log("FriendController ==> Ending sendFriendRequest function()")

					}
			) 
			
				
		},
		

		
		
		self.send = function(friendUserName)
		{
			console.log(friendUserName)
			console.log(self.user.username)
									console.log("FriendController ==> Starting sendFriendRequest under send function()")

			self.sendFriendRequest(friendUserName)
									console.log("FriendController ==> Starting getAllFriend under send function()")
									
						self.getAllFriend();

			$location.path('/myFriends')
	

			
		}
		
		self.acceptFriendRequest = function(friendUsername)
		{
			console.log("FriendController ==> Starting acceptFriendRequest function()")
		console.log("FriendController ==> calling acceptFriendRequest fucntion with username "+friendUsername)

			FriendServices.acceptFriendRequest(friendUsername).then
			(					
					function(d)
					{
						self.friend=d;
						
						$rootScope.friendRequestAccepted = "true";

						console.log("FriendController ==> Ending acceptFriendRequest function()")

						alert(self.friend.errorMessage)
						
						$location.path('/viewfriendrequest')
					},
					
					function(errResponse)
					{
						
						console.error("Error  While Accepting Friend Request")
						console.log("FriendController ==> Ending acceptFriendRequest function()")

					}
					
			)
		}
		
		

	self.getMyFriendRequest = function()
	{
		console.log("FriendController ==> Starting getMyFriendRequest function()")
	//	console.log("LoginUserName:-  "+loggedInUsername)
		FriendServices.getMyFriendRequest().then
		(
				function(d)
				{
					if(d!=null)
						{
							self.friends=d;
							console.log("FriendController ==> Ending getMyFriendRequest function()")
						}
					else
						$scope.empty = "yes"
				},
				null
		)


	}
	
	self.getMyFriendRequest()
	
	self.getMyFriends = function()
	{
		console.log("FriendController ==> Starting getMyFriends function()")

		FriendServices.getMyFriends().then
		(
			function(d)
			{
				self.myFriends = d;
				
				console.log("FriendController ==> Ending getMyFriends function()")

			},
			
			null
		)
	}
	
	self.getMyFriends();
	
	self.getAllFriend();


	/*self.view = function()
	{
		console.log("FriendController ==> Starting view function()")
		self.getMyFriendRequest()

		$location.path('/viewfriendrequest')

	}
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
}])