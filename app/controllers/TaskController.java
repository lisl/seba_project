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
		
		if (task == null)
		{
			//new task
			render(categories);
		}
		else
		{
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
		
		view(task, true, false);
	}
	
	public static void view(Task task, boolean isPreview, boolean justPosted)
	{
		render(task, isPreview, justPosted);
	}
	
	public static void save(Task task)
	{
		//TODO: BUG 43897498754789
		task.category.save();
		//task.category.merge();
		task.save();

		view(task, false, true);
	}
	
	public static void showAll(String selectedCategoryId)
	{
		Category selectedCategory;
		
		if (selectedCategoryId == null || selectedCategoryId.isEmpty())
		{
			selectedCategoryId = Category.NOT_SELECTED;
			selectedCategory = null;
		}
		else
		{
			selectedCategory = Category.find("FROM Category AS c WHERE c.categoryId = ?", selectedCategoryId).first();
		}
		
		List<Category> allCategories = Category.findAllOrdered();
		List<Task> tasksOfCurrentCategory = selectedCategory == null ? (List) Task.findAll() : selectedCategory.tasks;
		
		render(allCategories, tasksOfCurrentCategory, selectedCategoryId);
	}
}