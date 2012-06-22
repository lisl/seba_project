package controllers;

import models.Category;
import play.mvc.Controller;

public class Application extends Controller
{
	public static void index()
	{
		TaskController.showAll(Category.NOT_SELECTED);
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