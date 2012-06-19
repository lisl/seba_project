package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Category extends Model
{
	@Id
	public String categoryId;
	public String categoryName;
	
	@OneToMany
	public List<Task> tasks;
	
	public Category()
	{
		
	}
	
	public Category(String categoryId, String categoryName)
	{
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public final static List<Category> getCategories()
	{
		List<Category> categories = new LinkedList<Category>();
		
		categories.add(new Category("household", "Household"));
		categories.add(new Category("handyman", "Handyman"));
		
		return categories;
	}
}
