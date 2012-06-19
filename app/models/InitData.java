package models;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InitData
{
	private static boolean initDone = false;
	
	public synchronized static void initIfNotDoneYet()
	{
		if (!initDone)
		{
			init();
			initDone = true;
		}
	}
	
	private static void init()
	{
		initCategories();
		initTasks();
	}
	
	private static void initCategories()
	{
		new Category("household", "Household").save();
		new Category("handyman", "Handyman").save();
		new Category("fixComputers", "Fix Computers").save();
		new Category("officeHelp", "Office Help").save();
		new Category("shopping", "Shopping").save();
		new Category("delivery", "Delivery").save();
	}
	
	private static void initTasks()
	{
		Task t = new Task();
		t.title = "Pick up laundry";
		t.description = "Please pick up my laundry this afternoon.";
		t.category = Category.findAllOrdered().get(0);
		t.location = "Berlin";
		t.payment = "30";
		t.save();
	}
}
