"use strict"

app.factory('BlogServices', ['$http', '$rootScope', '$q',function($http,$rootScope,$q){

	var BaseURL='http://localhost:8080/collaborationback/'
	
	return{
		
		//start of fetchAllBlogs function()
		fetchAllBlogs:function(){
			return $http.get(BaseURL+'/allBlogs').then(
					function(Response){
						return Response.data;
					},
					function(errResponse){
						return $q.reject(errResponse);
					}
			)
		},//end of fetchAllBlogs function()
		
		
		
		//start of createBlog function()
		createBlog:function(blog){
			console.log(blog.Id)

			console.log("BlogServices=====>Starting createBlog function()=====>")

			return $http.post(BaseURL+'/saveBlog/',blog).then(
				function(Response){
					return Response.data;
					console.log("BlogServices=====>Ending createBlog function()=====>")

				},
				function(errResponse){
					$q.reject(errResponse);
					console.log("BlogServices=====>Ending createBlog function()=====>")

				}
			)
		},//end of createBlog function()
		
		
		updateBlog : function(blog)
		{
			return $http.put(BaseUrl+'/udpateBlog',event).then
			(
					function(Response)
					{
						return Response.data;
					},
					function(errReponse)
					{
						return $q.reject(errResponse)
					}
					
			)
		},
		
		
		
		
		//start of getBlog function()
		getBlogbyId:function(id){
			console.log("BlogServices=====>Starting getBlogbyId function()=====>")

			return $http.get(BaseURL+'/blogById/'+id).then(
					function(Response){
						console.log("BlogServices=====>Ending getBlogbyId function()=====>")
						return Response.data;
					},
					function(errResponse)
					{
						console.log("BlogServices=====>Ending getBlogbyId function()=====>")
						$q.reject(errResponse)
					}
					
			)
		},//end of getBlog function()
		
		
	}
}])


