package com.niit.collaborationbackend.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.niit.collaborationbackend.DAO.FriendDAO;
import com.niit.collaborationbackend.DAO.UserDAO;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.User;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;


@RestController
public class FriendController {
	
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired(required=false)
	HttpSession session;
	
	public static Logger log = org.slf4j.LoggerFactory.getLogger(FriendController.class);
	
	@RequestMapping(value="/getMyFriendRequest",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequest(HttpSession session)
	{
		log.debug("FriendController ====> Starting of the getMyFriendRequest method()");

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		List<Friend> friendRequest = new ArrayList<Friend>();
		
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			friendRequest.add(friend);
			log.debug("FriendController ====> Ending of the getMyFriendRequest method()");
		}
		
		log.debug("FriendController ====> Searching friends for "+loggedInUserId);

		
		friendRequest= friendDAO.getMyFriendRequests(loggedInUserId);
		
		if(friendRequest.isEmpty())
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Currently There are no Friend Request Available.");
			friendRequest.add(friend);
			log.debug("FriendController ====> Ending of the getMyFriendRequest method()");

		}

		log.debug("FriendController ====> Ending of the getMyFriendRequest method()");

		return new ResponseEntity<List<Friend>>(friendRequest,HttpStatus.OK);
	}

	
	
	
	
	
	@RequestMapping(value="/getMySentFriendRequest",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMySentFriendRequest(HttpSession session)
	{
		log.debug("FriendController ====> Starting of the getMyFriendRequest method()");

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		List<Friend> sentFriendRequest = new ArrayList<Friend>();
		
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			sentFriendRequest.add(friend);
			log.debug("FriendController ====> Ending of the getMyFriendRequest method()");
		}
		
		sentFriendRequest= friendDAO.getMySentFriendRequest(loggedInUserId);
		
		if(sentFriendRequest.isEmpty())
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Currently you have not sent Friend Request to AnyOne");
			sentFriendRequest.add(friend);
			log.debug("FriendController ====> Ending of the getMyFriendRequest method()");

		}

		log.debug("FriendController ====> Ending of the getMyFriendRequest method()");

		return new ResponseEntity<List<Friend>>(sentFriendRequest,HttpStatus.OK);
	}

	
	@RequestMapping(value="/getMyFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends()
	{
		log.debug("FriendController ====> Starting of the getMyFriends method()");

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		List<Friend> myFriends = new ArrayList<Friend>();
		
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			myFriends.add(friend);
			
			log.debug("FriendController ====> Ending of the getMyFriends method()");

		}
		
		myFriends=friendDAO.getMyFriends(loggedInUserId);
		
		if(myFriends.isEmpty())
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Currently You Does Not Have Any Friends Added");
			myFriends.add(friend);
			
			log.debug("FriendController ====> Ending of the getMyFriends method()");

		}
		
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);

	}

	
	
	@RequestMapping(value="/sendFriendRequest/{friendUserName}",method=RequestMethod.GET,headers="Accept=*/*")
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendUserName") String friendUserName)
	{
		log.debug("FriendController ====> Starting of the sendFriendRequest method()");
		
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			log.debug("FriendController ====> Ending of the sendFriendRequest method()");

		}
		
		else
		{
			friend.setId(friendDAO.maxID());
			friend.setStatus('N');
			friend.setIsOnline('N');
			friend.setUsername(loggedInUserId);
			friend.setFriendUserName(friendUserName);
			if(friendDAO.get(loggedInUserId, friendUserName)!=null)
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("You Already sent a friend request to "+friendUserName);
				log.debug("FriendController ====> Ending of the sendFriendRequest method()");

			}
			
			else
			{
				if(friendDAO.save(friend)==false)
				{
					friend.setErrorCode("404");
					friend.setErrorMessage("Error while adding friend ,.,please try again after sometime,.,!!,.,!!,.,");
					log.debug("FriendController ====> Ending of the sendFriendRequest method()");
	
				}
				else
				{
					friend.setErrorCode("200");
					friend.setErrorMessage("Friend Request has been sent");
					log.debug("FriendController ====> Ending of the sendFriendRequest method()");
	
				}
			}
		}
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/acceptFriendRequest/{friendUserName}",method=RequestMethod.PUT)
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendUserName") String friendUserName)
	{
		log.debug("FriendController ====> Starting of the acceptFriendRequest method()");

		friend = acceptOrRejectFriendRequest(friendUserName, 'A');
		
		log.debug("FriendController ====> Ending of the acceptFriendRequest method()");

		return new ResponseEntity<Friend>(friend,HttpStatus.OK);


	}

	
	@RequestMapping(value="/rejectFriendRequest/{friendUserName}",method=RequestMethod.PUT)
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendId") String friendUserName)
	{
		log.debug("FriendController ====> Starting of the rejectFriendRequest method()");

		friend = acceptOrRejectFriendRequest(friendUserName, 'R');
		
		log.debug("FriendController ====> Ending of the rejectFriendRequest method()");

		return new ResponseEntity<Friend>(friend,HttpStatus.OK);

	}

	
	
	
	@RequestMapping("/deleteFriend/{friendUserName}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("friendUserName") String friendUserName)
	{
		Friend friend = (Friend) friendDAO.getMyFriendRequests(friendUserName);
		if(friendDAO.delete(friend)==false)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Error while deleting friend ,.,please try again after sometime,.,!!,.,!!,.,");
		}
		else
		{
			friend.setErrorCode("200");
			friend.setErrorMessage("Friend has been deleted");
		}
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	private Friend acceptOrRejectFriendRequest(String friendUserName,char status)
	{
		log.debug("FriendController ====> Starting of the acceptOrRejectFriendRequest method()");

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			log.debug("FriendController ====> Ending of the acceptOrRejectFriendRequest method()");

		}
		else
		{
			friend = friendDAO.get(friendUserName, loggedInUserId);
			friend.setStatus(status);
			friendDAO.update(friend);
			friend.setErrorCode("200");
			friend.setErrorMessage("Operation has been Successsful");
			log.debug("FriendController ====> Ending of the acceptOrRejectFriendRequest method()");

		}
		return friend;
	}
	
	@RequestMapping(value="/fetchallFriends/{friendUserName}",method=RequestMethod.GET)
	public ResponseEntity<List<User>> fetchallFriends(@PathVariable("friendUserName") String username) throws SAXException, JAXBException
	{
		ArrayList<User> selectedUsers = (ArrayList<User>) userDAO.getall(username);
	
	return new ResponseEntity<List<User>>(selectedUsers,HttpStatus.OK);
	}
}
