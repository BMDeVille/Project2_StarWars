package starwar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
	public static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private DaoService ds= context.getBean("DaoService", DaoService.class);
	public static Date date = new Date();


	@Test
	public void returnUsers() {
		List<Allegiance> pt = ds.selectAllAllegiance();
		User u = new User("test11","f1","l1","test11@test.com","test11",new Timestamp(date.getTime()),"secans",pt.get(0));
		ds.insertUser(u);
		List<User> ut = ds.selectAllUser();
		assertNotNull(ut);
		assertEquals(1, ut.size());
		User u1 = ut.get(0);
		u1.setAbout("test2");
		ds.updateUser(u1);
		ds.insertUser(new User("test22","f2","l2","test22@test.com","test3",new Timestamp(date.getTime()),"secans",pt.get(1)));
		User u2 = ds.selectByUsername("test1");
		assertEquals("test2",u2.getAbout());
		assertNotNull(u2.getId());
		assertNotNull(u2);
		assertNotNull(ds.selectByFirstName("f1"));
		assertNotNull(ds.selectByLastName("l1"));
		assertNotNull(ds.selectByUsername("test1"));
		ds.deleteUser(u1);
		List<User> ut1 = ds.selectAllUser();
		assertEquals(1,ut1.size());
		ds.deleteUser(ut1.get(0));
		assertEquals(0, ds.selectAllUser().size());
	}

	@Test
	public void returnComment() {
		List<Allegiance> pt = ds.selectAllAllegiance();
		User u = new User("test1","f1","l1","test1@test.com","test1",new Timestamp(date.getTime()),"secans",pt.get(0));
		ds.insertUser(u);
		Post p = new Post("post1",new Timestamp(date.getTime()),u);
		ds.insertPost(p);
		Comment c = new Comment("Test comment", p, u);
		ds.insertComment(c);
		List<Comment> ct = ds.selectAllComment();
		assertNotNull(ct);
		assertEquals(1,ct.size());
		Comment c1 = ct.get(0);
		c1.setBody("comment2");
		ds.updateComment(c1);
		List<Comment> c2 = ds.selectAllComment();
		assertEquals("comment2",c2.get(0).getBody());
		assertNotNull(c2.get(0).getId());
		assertNotNull(c2.get(0));
		ds.deleteComment(c);
		List<Comment> c3 = ds.selectAllComment();
		assertEquals(0,c3.size());
		ds.deletePost(p);
		ds.deleteUser(u);
	}
	
	@Test
	public void returnPost() {
		List<Allegiance> at = ds.selectAllAllegiance();
		User u = new User("test1","f1","l1","test1@test.com","test1",new Timestamp(date.getTime()),"secans",at.get(0));
		ds.insertUser(u);
		Post p = new Post("post1",new Timestamp(date.getTime()),u);
		ds.insertPost(p);
		List<Post> pt = ds.selectAllPost();
		assertNotNull(pt);
		assertEquals(1,pt.size());
		Post p1 = pt.get(0);
		p1.setBody("post2");
		ds.updatePost(p1);
		List<Post>p2 = ds.selectAllPost();
		assertEquals("post2",p2.get(0).getBody());
		assertNotNull(p2.get(0).getPid());
		assertNotNull(p2.get(0));
		ds.deletePost(p1);
		List<Post> p3 = ds.selectAllPost();
		assertEquals(0,p3.size());
		ds.deleteUser(u);
	}
	
	@Test
	public void returnAllegiance() {
//		ds.insertAllegiance(new Allegiance(1, "Rebel Alliance"));
//		ds.insertAllegiance(new Allegiance(2, "Galactic Empire"));
		List<Allegiance> pt = ds.selectAllAllegiance();
		assertNotNull(pt);
		assertEquals(2,pt.size());
	}
//	@Test
//	public void returnImage() {
//		
//	}

}
