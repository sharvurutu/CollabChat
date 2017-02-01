var app = angular.module('myapp',['ngRoute','ngCookies']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/',{
		templateUrl:'c_user/home.html'
	})

	.when('/upload',{
		templateUrl:'c_user/FileUpload.html'
	})

	
	.when('/home',{
		redirectTo:'/'
	})
	
	
	.when('/selectReject',{
	templateUrl:'c_admin/NewFile.html'
	})

	
	.when('/adminhome',{
	templateUrl:'c_admin/adminNavbar.html'
	})

	
	.when('/alljobapplication',{
	templateUrl:'c_admin/alljobapplication.html'
	})

	
		.when('/allusers',{
		templateUrl:'c_admin/allusers.html'
	})

	
	.when('/login',{
		templateUrl:'c_user/login.html',
			controller:'UserController'
	})

	.when('/register',{
		templateUrl:'c_user/register.html'
	})

	
	/*.when('/logout',{
			
			templateUrl:'c_user/register.html',
			controller:'UserController'

		})

*/	
	.when('/blogs',{
		templateUrl:'c_blog/blogs.html'
	})
	
	
	
	.when('/createblog',{
		templateUrl:'c_blog/createBlog.html',
			controller:'BlogController'

	})

	
	.when('/getSelectedBlog',{
		templateUrl:'c_blog/getSelectedBlog.html',
			controller:'BlogController'

	})

	

		.when('/createjob',{
		templateUrl:'c_job/createJob.html',
			controller:'JobController'

	})

	
	.when('/myappliedjobs',{
		templateUrl:'c_job/myappliedjobs.html',
			controller:'JobController'

	})

	
		.when('/jobHome',{
		templateUrl:'c_job/jobHome.html',
		controller:'JobController'
	})
	
		.when('/openJobs',{
		templateUrl:'c_job/openJobs.html'
	})
	
		.when('/eventhome',{
		templateUrl:'c_event/eventHome.html'
			
	})
	
		.when('/createevent',{
		templateUrl:'c_event/createEvent.html'
			
	})
	
	
	
	.when('/grouphome',{
		templateUrl:'c_forum/groupHome.html'
			
	})
	
		.when('/createforum',{
		templateUrl:'c_forum/createForum.html'
			
	})
	
	.when('/allfriends',{
		templateUrl:'c_friend/allfriends.html',
			controller:'FriendController'
			
	})

	.when('/viewfriendrequest',{
		templateUrl:'c_friend/viewfriendrequest.html'
			
	})
	
		.when('/myFriends',{
		templateUrl:'c_friend/myfriends.html'
			
	})

	
	
	.when('/chat',{
		templateUrl:'c_chat/chat.html'
			
	})
	
	
	
	
	
	
		/*.when('/login',{
			
			templateUrl:'login.html',
			controller:'LoginController'

		})
*/
		
	

})
