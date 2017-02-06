package com.niit.collaborationbackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.FriendDAO;
import com.niit.collaborationbackend.DAO.UserDAO;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.User;

@RestController
public class UserController {

	@Autowired
	User user;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired(required=false)
	HttpSession session;
	
	public static Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	// Get List of all users
	// http://localhost:8080/collaboration/allusers
	
	//headers="Accept=application/json"
	@RequestMapping(value="/helloworld",method=RequestMethod.GET)
	
	public String Hello()
	{
		log.debug("UserController ====> Starting of the Hello method()");

		log.debug("UserController ====> Ending of the Hello method()");

		return "Hello";

	}
	
	
	@RequestMapping(value="/allUsers",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		
		log.debug("UserController ====> Starting of the getAllUser method()");

		List<User> users = userDAO.list();

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No User are available");
			users.add(user);
			log.debug("UserController ====> Ending of the getAllUser method()");

		}

		// errorcode :200 :404
		// errormessage Success :Not Found
		log.debug("UserController ====> Ending of the getAllUser method()");

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value="/getAllFriend",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllFriend() {
		
		log.debug("UserController ====> Starting of the getAllFriend method()");
		
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		System.out.println("Calling getAllFriend() with "+loggedInUserId);

		List<User> users = userDAO.getall(loggedInUserId);

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No User are available");
			users.add(user);
			log.debug("UserController ====> Ending of the getAllFriend method()");

		}

		// errorcode :200 :404
		// errormessage Success :Not Found
		log.debug("UserController ====> Ending of the getAllUser method()");

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	
	// Get User by Id
	// http://localhost:8080/collaboration/allusers/id
	
	@RequestMapping(value="/userById/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") String userEmailId) {
		
		log.debug("UserController ====> Starting of the getUserById method()");
		
		user = userDAO.get(userEmailId);

		if (user == null) {
			user = new User();// to avoid NullPointerException
			user.setErrorCode("404");
			user.setErrorMessage("User Not Found with ID  " + userEmailId);
			
			log.debug("UserController ====> Ending of the getUserById method()");

		}

		log.debug("UserController ====> Ending of the getUserById method()");

		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	// http://localhost:8080/collaboration/allusers/id/password
	// Instead of Request mapping POST method we can use
	// @PostMapping("authenticate") also
	// sending values from request body--- {"emailId":"abc", "password":"xyz"}
	
/*	@RequestMapping(value = "/authenticate/{emailId}/{password}", method = RequestMethod.POST)
*/
	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user) {
		
		log.debug("UserController ====> Starting of the authenticate method()");

		user = userDAO.IsValidUser(user.getUsername(), user.getPassword());

		if (user == null) {
			user = new User();// to avoid NullPointerException
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials,., Please Try Again");
			
			log.debug("UserController ====> Ending of the authenticate method()");

		}
		else
		{
			user.setErrorCode("200");
			user.setErrorMessage("You are SuccessFully Logged In,.,!!,.,.");
			user.setIsOnline('Y');
			session.setAttribute("loggedInUserId", user.getUsername());
			
			log.debug("UserController ====> Login in with Username:-  "+session.getAttribute("loggedInUserId"));

			
			session.setAttribute("loggedInUserRole", user.getRole());
			userDAO.setOnline(user.getUsername());
			friendDAO.setOnline(user.getUsername());
			
			log.debug("UserController ====> Ending of the authenticate method()");

		}
	

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/registerUser/", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		
		log.debug("UserController ====> Starting of the authenticate method()");

		if(userDAO.get(user.getEmailId())==null)
		{
			user.setIsOnline('N');
			user.setStatus('N');
		
		if (userDAO.save(user)==false) {
			user.setErrorCode("404");
			user.setErrorMessage("Registration Not Successful.,., Please try Again,.,!!,.,!!");
			
			log.debug("UserController ====> Ending of the authenticate method()");
			
		} else {
			user.setErrorCode("200");
			user.setErrorMessage("Thankyou for Registration !!..!!");
			
			log.debug("UserController ====> Ending of the authenticate method()");

		}
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/UpdateUser/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		log.debug("UserController ====> Starting of the updateUser method()");

		if (userDAO.update(user)==false) {
			user.setErrorCode("404");
			user.setErrorMessage("Update Not Successful.,., Please try Again,.,!!,.,!!");
			log.debug("UserController ====> Ending of the updateUser method()");

		} else {
			user.setErrorCode("200");
			user.setErrorMessage("Thankyou Update is Successful !!..!!");
			log.debug("UserController ====> Ending of the updateUser method()");

		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@RequestMapping(value="/makeAdmin/{username}",method=RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("username") String username)
	{
		log.debug("UserController ====> Starting of the makeAdmin method()");

		user = userDAO.get(username);
		if(user==null){
			user= new User();
			user.setErrorCode("404");
			user.setErrorMessage("User Does Not present with the UserID:-  "+username);
			
			log.debug("UserController ====> Ending of the makeAdmin method()");

		}
		else{
			user.setRole("admin");
			user.setStatus('A');
			userDAO.update(user);
			user.setErrorCode("200");
			user.setErrorMessage("User :- "+username+" Role has been successfully updated");
			
			log.debug("UserController ====> Ending of the makeAdmin method()");

		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<User> logout()
	{
		log.debug("UserController ====> Starting of the logout method()");
		
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		System.out.println(loggedInUserId);
		user.setIsOnline('N');
		userDAO.setOffline(loggedInUserId);
		friendDAO.setOffline(loggedInUserId);
		session.invalidate();
		user.setErrorCode("200");
		user.setErrorMessage("You Have Been Successfully Logged Out");
		
		log.debug("UserController ====> Ending of the logout method()");

		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	

}
