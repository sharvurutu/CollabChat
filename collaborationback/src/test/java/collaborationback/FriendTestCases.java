package collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.DAO.FriendDAO;
import com.niit.collaborationbackend.model.Friend;

public class FriendTestCases {

	@Autowired
	static Friend friend;

	@Autowired
	static FriendDAO friendDAO;

	@Autowired
	static AnnotationConfigApplicationContext context;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		friendDAO = (FriendDAO) context.getBean("friendDAO");
		friend = (Friend) context.getBean("friend");
	}


	@Test
	public void sendFriendRequest()
	{
		
	}
	
}
