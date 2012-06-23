package controllers;

import java.util.List;

import models.Category;
import models.Task;
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
		if (task.categoryId.equals(Category.NOT_SELECTED))
		{
			validation.addError("task.categoryId", "Choose a category");
		}
		
		if(validation.hasErrors())
		{
			List<Category> categories = Category.findAllOrdered();
            render("@fillOut", categories, task);
        }
		
		view(task, Category.getByCategoryId(task.categoryId), true, false);
	}
	
	public static void view(Task task, Category category, boolean isPreview, boolean justPosted)
	{
		render(task, category, isPreview, justPosted);
	}
	
	public static void save(@Valid Task task)
	{
		Category category = Category.getByCategoryId(task.categoryId);
		task.category = category;
		task.save();

		view(task, task.category, false, true);
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
		List<Task> tasksOfCurrentCategory = selectedCategory == null ? (List) Task.findAll() : Task.getTasksByCategory(selectedCategory);
		
		render(allCategories, tasksOfCurrentCategory, selectedCategoryId);
	}
}