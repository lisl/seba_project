package models;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Categories
{
	private static Map<String, Category> builtInCategories;
	
	static
	{
		builtInCategories = new HashMap<String, Category>();
		
		putBuiltInCategory(new Category("household", "Household"));
		putBuiltInCategory(new Category("handyman", "Handyman"));
	}
	
	private final static void putBuiltInCategory(Category c)
	{
		c.save();
		builtInCategories.put(c.categoryId, c);
	}
	
	public final static List<Category> getCategories()
	{
		List<Category> categories = new LinkedList<Category>();
		categories.addAll(builtInCategories.values());
		Collections.sort(categories);
		
		return categories;
	}
	
	public final static Category getCategoryById(String categoryId)
	{
		return builtInCategories.get(categoryId);
	}
}
