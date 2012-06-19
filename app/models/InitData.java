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
	}
	
	private static void initCategories()
	{
		new Category("household", "Household").save();
		new Category("handyman", "Handyman").save();
	}
}
