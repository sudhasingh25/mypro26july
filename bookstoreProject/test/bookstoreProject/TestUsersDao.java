package bookstoreProject;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class TestUsersDao {

	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private static UserDAO userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		entityManager=entityManagerFactory.createEntityManager();
		userDao=new UserDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void testLoginSuccess() {
		String email="rab123@gmail.com";
		String password="rab123";
		
		boolean check=userDao.checkLogin(email, password);
		assertTrue(check);
	}
	
	@Test
	public void testLoginFail() {
		String email="rab12345@gmail.com";
		String password="rab12345";
		
		boolean check=userDao.checkLogin(email, password);
		assertFalse(check);
	}

	@Test
	public void testCreateUser() {
		Users user=new Users();
		user.setEmail("yakku123@gmail.com");
		user.setFullName("yakku");
		user.setPassword("yakku123");
		
		Users newuser=userDao.create(user);
		assertTrue(newuser.getUserId()>0);
	}
	
	@Test
	public void testUpdateUser(){
		Users user=new Users();
		Integer userId=53;
		String mail="nanu123@gmail.com";
		String name="nanu punnu";
		String password="nanu123";
		
		Users updated=new Users(userId,mail,name,password);
		updated=userDao.update(updated);
		assertEquals(name,updated.getFullName() );
	}

	@Test
	public void testFindByEmail(){
		String email="mitali123@gmail.com";
		Users users=userDao.findByEmail(email);
		if(users!=null)
			System.out.println(users.getEmail());
		assertNotNull(users);
	}
	
	
}
