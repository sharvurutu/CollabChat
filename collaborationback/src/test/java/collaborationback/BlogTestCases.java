package collaborationback;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.DAO.BlogDAO;
import com.niit.collaborationbackend.model.Blog;

import junit.framework.Assert;
import oracle.sql.DATE;

public class BlogTestCases {

	@Autowired
	static Blog blog;

	@Autowired
	static BlogDAO blogDAO;

	@Autowired
	static AnnotationConfigApplicationContext context;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
	}

	//@Test
	public void CreateBlogTestCase() {
		//blog.setId(2);
		blog.setEmailId("ankur2.baghel92@gmail.com");
		blog.setDescription("First Blog");
		blog.setReason("NO");
		Timestamp t = new Timestamp(System.currentTimeMillis());
		blog.setDate_Time(t);
		blog.setStatus('N');
		blog.setTittle("First Blog");
		boolean status = blogDAO.save(blog);
		Assert.assertEquals("CreateBlogTestCase", true, status);

	}
/*
	//@Test
	public void GetblogTestCase() {
		//Blog blog = blogDAO.get(100);
		System.out.println(blog.getDescription());
		Assert.assertEquals("Get One blog Test Case", null, blogDAO.get("2"));
	}
*/	 
	 
	// @Test
	 public void UpdateBlogTestCase(){
		 	//blog.setId("2");
			blog.setEmailId("ankur2.baghel92@gmail.com");
			blog.setDescription("Second Blog");
			blog.setReason("NO");
			Timestamp t = new Timestamp(System.currentTimeMillis());
			blog.setDate_Time(t);
			blog.setStatus('N');
			blog.setTittle("First Blog");
			boolean status = blogDAO.update(blog);
			Assert.assertEquals("UpdateBlogTestCase", true, status);

	 }

	/*
	 * @Test public void test() { fail("Not yet implemented"); }
	 */
	 
	 
	 @Test
		public void GetblogByIdTestCase() {
			Blog blog = blogDAO.getById(100);
			System.out.println(blog.getDescription());
			//Assert.assertEquals("Get One blog Test Case", null, blogDAO.get("2"));
		}
	 
	 
}
