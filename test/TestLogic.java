import java.util.Date;
import java.util.List;

import models.Category;
import models.Task;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class TestLogic extends UnitTest
{
	@Before
	public void setUpData()
	{
		Fixtures.deleteAllModels();
		Fixtures.loadModels("data.yml");
	}

	@Test
	public void checkInitData() 
	{
		assertEquals(6, Category.count());
		assertNotNull(Category.getByCategoryId("delivery"));
	}
	
	@Test
	public void createTask()
	{
		Task t = new Task();
		t.category = Category.getByCategoryId("shopping");
		t.creationDate = new Date();
		t.description = "Description";
		t.location = "Location";
		t.payment = "30";
		t.title = "Title";
		t.save();
		
		assertNotNull(Task.findById(t.id));
	}
	
	@Test
	public void findInCategory()
	{
		List<Task> tasks = Task.getTasksByCategory(Category.getByCategoryId("delivery"));
		
		assertEquals(3, tasks.size());
	}
}
