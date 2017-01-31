"use strict"

app.controller('EventController',['EventServices','$http','$location','$scope','$rootScope',function(EventServices,$http,$location,$scope,$rootScope){
	$scope.message = "Message from EventController"

	var self = this;
	
	self.event = {Id : '', name : '', Date_Time : '', venue : '', description:''};
	
	self.event = [];
	
	self.fetchAllEvents = function()
	{
		EventServices.fetchAllEvents().then
		(
				function(d)
				{
					self.events = d;
				},
				
				function(d)
				{
					console.log("Error while whiel Fetching all the Events")
				}
		)
	},
	
	
	
	self.createEvent  = function(event)
	{
		EventServices.createEvent(event).then
		(
				function()
				{
					alert("ThankYou Event has been Created SuccessFully ,.,!!,.,!!,.,");
				},
				
				function(errResponse)
				{
					console.log("Errro while Creating Events ,.Please try again after some time,.,!!,.,!!,.,.,");
				}
		)
		
	},
	
	
	self.updateEvent = function(event)
	{
		EventServices.updateEvent(event).then
		(
				function(d)
				{
					alert("Thank you! Event has been successfully updated ,.,!!,.,!!,.,");
				},
				
				function(errResponse)
				{
					console.log("Error While updating Event,., Please try again after some time .,.,!!,.,.!!,.,")
				}
			
		)
		
	},
	
	
	self.eventById = function(id)
	{
		EventServices.eventById(id).then
		(
				function(d)
				{
					self.event = d;
				},
				function(errResponse)
				{
					console.log("Error while fetching the event,.,please try again after sometime,.,!!,.,!!,.,")
				}
		)
	}
	
	
	self.submit =function()
	{
		self.createEvent(self.event);
		self.reset();
	};
	
	
	self.reset = function()
	{
	self.event={
			
			Id :'',
			name :'',
			Date_Time : '',
			venue : '',
			description :''
	}
	};
	
	
	
}])















