package controllers;

import play.mvc.Controller;

public class Application extends Controller
{
	public static void index()
	{
		//redirect
		TaskController.showAll("allCategories");
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