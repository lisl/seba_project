package controllers;

import java.util.List;

import models.Category;
import models.Task;
import play.data.validation.Valid;
import play.mvc.Controller;

public class TaskController extends Controller
{
	public static void create()
	{
		fillOut(null, null);
	}
	
	public static void edit(Task task, String categoryId)
	{
		fillOut(task, categoryId);
	}
	
	public static void fillOut(Task task, String categoryId)
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
			render(categories, task, categoryId);
		}
	}
	
	public static void submit(@Valid Task task, String categoryId)
	{
		if (categoryId.equals("notSelected"))
		{
			validation.addError("categoryId", "Please choose a category");
		}
		
		if (validation.hasErrors())
		{
			validation.keep();
			fillOut(task, categoryId);
        }
		
		preview(task, categoryId);
	}
	
	public static void save(@Valid Task task, Category category)
	{
		category.addTask(task);

		viewExisting(task, true);
	}
	
	public static void preview(Task task, String categoryId)
	{
		Category category = Category.getByCategoryId(categoryId);
		view(task, category, true, false);
	}
	
	public static void viewExisting(Task task, boolean justPosted)
	{
		render(task, task.category, false, justPosted);
	}
	
	public static void view(Task task, Category category, boolean isPreview, boolean justPosted)
	{
		render(task, category, isPreview, justPosted);
	}
	
	public static void showAll(String selectedCategoryId)
	{
		Category selectedCategory;
		
		if (selectedCategoryId == null || selectedCategoryId.isEmpty() || selectedCategoryId.equals(Category.NOT_SELECTED))
		{
			selectedCategoryId = Category.NOT_SELECTED;
			selectedCategory = null;
		}
		else
		{
			selectedCategory = Category.getByCategoryId(selectedCategoryId);
		}
		
		List<Category> allCategories = Category.findAllOrdered();
		List<Task> tasksOfCurrentCategory = selectedCategory == null ? (List) Task.findAll() : selectedCategory.tasks;
		
		render(allCategories, selectedCategoryId, tasksOfCurrentCategory);
	}
}