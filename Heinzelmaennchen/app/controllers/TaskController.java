package controllers;

import models.Task;
import play.mvc.Controller;

public class TaskController extends Controller
{
	public static void fillOut(String taskId)
	{
		if (taskId == null) {
			//new task
			render();
		}
		else {
			//TODO load existing task by id
			//edit task
			render();
		}
	}
	
	public static void submit(Task task)
	{
		//redirect to the page preview
		preview(task);
	}
	
	public static void preview(Task task)
	{
		render(task);
	}
	
	public static void save(String taskId)
	{
		//redirect to this task's category
		showAll("TODO");
	}
	
	public static void showAll(String categoryId)
	{
		if (categoryId == null)
		{
			categoryId = "allCategories";
		}
		
		render(categoryId);
	}
}