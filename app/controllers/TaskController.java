package controllers;

import models.Task;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class TaskController extends Controller
{
	public static void fillOut(Task task)
	{
		if (task == null) {
			//new task
			render();
		}
		else {
			//TODO load existing task by id
			//edit task
			render(task);
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
	
	public static void save(Task task)
	{
		JPA.em().persist(task);

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