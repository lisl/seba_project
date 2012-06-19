package controllers;

import java.util.List;

import models.InitData;
import models.Category;
import models.Task;
import play.data.validation.Error;
import play.data.validation.Valid;
import play.mvc.Controller;

public class TaskController extends Controller
{
	public static void fillOut(Task task)
	{
		List<Category> categories = Category.findAllOrdered();
		
		if (task == null) {
			//new task
			render(categories);
		}
		else {
			//edit task
			render(categories, task);
		}
	}
	
	public static void submit(@Valid Task task)
	{
		if (task.category == null)
		{
			//validation.errors().add(new Error("no-category", "No category selected", new String[]{}));
		}
		
		if(validation.hasErrors())
		{
			List<Category> categories = Category.findAllOrdered();
            render("@fillOut", categories, task);
        }
		
		//redirect to the page preview
		view(task, true);
	}
	
	public static void view(Task task, boolean isPreview)
	{
		render(task, isPreview);
	}
	
	public static void save(Task task)
	{
		task.category.save();
		task.save();

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