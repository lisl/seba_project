package controllers;

import play.mvc.Controller;

public class Application extends Controller
{

	public static void postATask()
	{
		render();
	}

	public static void allTasks(String categoryId)
	{
		if (categoryId == null)
		{
			categoryId = "allCategories";
		}
		
		render(categoryId);
	}
	
	public static void about()
	{
		render();
	}
	
	public static void contactUs()
	{
		render();
	}
}