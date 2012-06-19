package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Category extends Model implements Comparable<Category>
{
	public static final String NOT_SELECTED = "notSelected";
	
	public String categoryId;
	public String categoryName;
	
	@OneToMany(fetch=FetchType.LAZY)
	public List<Task> tasks;
		
	public Category()
	{
		
	}
	
	public Category(String categoryId, String categoryName)
	{
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}


	@Override
	public int compareTo(Category c)
	{
		if (c == null)
		{
			return -1;
		}
		else
		{
			return this.categoryName.compareTo(c.categoryName);
		}
	}
}
