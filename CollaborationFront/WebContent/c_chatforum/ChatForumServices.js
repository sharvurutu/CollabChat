"use strict"

app.factory('ChatForumServices',function($q,$timeout){
	
	console.log("Starting ==>  ChatForumServices")
	
	var service = {};
	
	var listner = $q.defer();
	
	var socket = {
					client : null,
					stomp : null
				}
	
	var messageIds = [];
	
	service.reConnectTime = 30000;
	
//	service.socketURL = '/collaborationback/chat_forum';
	
	service.socketURL ='http://localhost:8080/collaborationback/chat_forum';
	
	service.chatTopic = '/topic/message';
	
	service.chatBroker = '/app/chat_forum';
	
	service.receive = function()
	{
		console.log("ChatForumServices ==> Starting receive function()")

		console.log("ChatForumServices ==> Ending receive function()")

		return listner.promise;
	}
	
	service.send = function(message)
	{
		console.log("ChatForumServices ==> Starting send function()")

		var id = Math.floor(Math.random()*10000000);
		
		socket.stomp.send(service.chatBroker,{priority : 9},JSON.stringify({
			message : message,
			id : id
		}));
		
		messageIds.push(id);
		
		console.log("ChatForumServices ==> Ending send function()")

	}
	
	var reconnect = function()
	{
		console.log("ChatForumServices ==> Starting reconnect function()")

		$timeout(function(){
			
			initialize();
			
			this.reConnectTime;
	
		console.log("ChatForumServices ==> Ending reconnect function()")
	
		})
	}

	
	var getMessage = function(data)
	{
		console.log("ChatForumServices ==> Starting getMessage function()")

		var message = JSON.parse(data);
		
		var out = {};
		
		out.message = message.message;
		
		out.time = new Date(message.time);
		
		console.log("ChatForumServices ==> Ending getMessage function()")

		
		return out;
	}
	
	
	
	var startListener = function(){
		
		console.log("ChatForumServices ==> Starting startListner function()")
		
		socket.stomp.subscribe(service.chatTopic,function(data){
			
			listner.notify(getMessage(data.body));
			
		console.log("ChatForumServices ==> Ending startListner function()")

			
		})
		
	}
	
	
	var initialize = function()
	{
		console.log("ChatForumServices ==> Starting initialize function()")

		socket.client = new SockJS(service.socketURL);
		
		socket.stomp = Stomp.over(socket.client);
		
		socket.stomp.connect({},startListener);
		
		socket.stomp.onclose = reconnect;
		
		console.log("ChatForumServices ==> Ending initialize function()")

	}
	
	initialize();
	return service;
	
	
})