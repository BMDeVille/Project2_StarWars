package starwar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.p2.dao.DaoService;
import com.p2.models.Allegiance;
import com.p2.models.Comment;
import com.p2.models.Post;
import com.p2.models.User;

@Component
public class StarwarTest {
	@Autowired
	private DaoService ds;
	public static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Before
	public void before() {
		Date date = new Date();
		Allegiance al = new Allegiance();
		al.setAllegiance("a");
		User u = new User("test4","l1","f1","test4@test4.com","test1",new Timestamp(date.getTime()),"secans",al);
		Post p = new Post("post1",new Timestamp(date.getTime()));
		Comment c = new Comment("test comment");
		//Image i = new Image();
		context.getBean("DaoService", DaoService.class);
		ds.insertUser(u);
		ds.insertPost(p);
		ds.insertComment(c);
	}
	
	@Test
	public void returnUsers() {
		List<User> ut = ds.selectAllUser();
		assertNotNull(ut);
		assertEquals(1,ut.size());
		User u1 = ut.get(0);
		u1.setAbout("test2");
		ds.updateUser(u1);
		List<User> u2 = ds.selectAllUser();
		assertEquals("test2",u2.get(0).getAbout());
		assertNotNull(u2.get(0).getId());
		assertNotNull(u2.get(0));
		assertNotNull(ds.selectByFirstName("f1"));
		assertNotNull(ds.selectByLastName("l1"));
		assertNotNull(ds.selectByUsername("test1"));
		ds.deleteUser(u1);
		List<User> ut1 = ds.selectAllUser();
		assertEquals(0,ut1.size());

	}
	
	@Test
	public void returnComment() {
		List<Comment> ct = ds.selectAllComment();
		assertNotNull(ct);
		assertEquals(1,ct.size());
		Comment c = ct.get(0);
		c.setBody("comment2");
		ds.insertComment(c);
		List<Comment> c2 = ds.selectAllComment();
		assertEquals("comment2",c2.get(0).getBody());
		assertNotNull(c2.get(0).getId());
		assertNotNull(c2.get(0));
		ds.deleteComment(c);
		List<Comment> c3 = ds.selectAllComment();
		assertEquals(0,c3.size());
		
	}
	
	@Test
	public void returnPost() {
		List<Post> pt = ds.selectAllPost();
		assertNotNull(pt);
		assertEquals(1,pt.size());
		Post p1 = pt.get(0);
		p1.setBody("post2");
		ds.insertPost(p1);
		List<Post>p2 = ds.selectAllPost();
		assertEquals("post2",p2.get(0).getBody());
		assertNotNull(p2.get(0).getId());
		assertNotNull(p2.get(0));
		ds.deletePost(p1);
		List<Post> p3 = ds.selectAllPost();
		assertEquals(0,p3.size());
	}
//	@Test
//	public void returnImage() {
//		
//	}

}
