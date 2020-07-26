package bookstoreProject;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Category;

public class TestCategoryDao {


	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private static CategoryDAO categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		entityManager=entityManagerFactory.createEntityManager();
		categoryDao=new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat=new Category("OOOOO");
		Category created=categoryDao.create(newCat);
		assertNotNull(created);
	}
	
	@Test
	public void testUpdateCategory() {
		Integer catId=33;
		Category cat=new Category(catId,"tuntun");
		categoryDao.update(cat);
	}
	
	@Test
	public void testGetCategory(){
		Category cat=categoryDao.get(32);
		System.out.println(cat.getName());
		assertNotNull(cat);
	}
	
	@Test
	public void testDeleteCategory(){
		Category cat=categoryDao.get(34);
		categoryDao.delete(cat.getCategoryId());
	}

	@Test
	public void testListCategory(){
		List<Category> listCat=categoryDao.listAll();
		
		for(Category cat:listCat){
			System.out.println(cat.getName());
		}
		
		assertNotNull(listCat);
	}
}
