"use strict"

app.controller('ChatForumController',function(ChatForumServices,$scope){
	
	console.log("Starting ==>  ChatForumController")

	
	$scope.chatmessage = "Message from CharForumController"

	$scope.messages = [];
	
	$scope.message = "";
	
	$scope.max = 50;
	
	$scope.addMessage = function()
	{
		console.log("ChatForumController ==> Starting addMessage function()")
		
		ChatForumServices.send($scope.message);
		
		$scope.message = "";
		
		console.log("ChatForumController ==> Ending addMessage function()")

	}
	
	ChatForumServices.receive().then(null,null,function(message){
		
		console.log("ChatForumController ==> Starting ChatForumSerivec.receive function()")

		$scope.messages.push(message);
		
		console.log("ChatForumController ==> Ending ChatForumSerivec.receive function()")

	})
	
	
	
})