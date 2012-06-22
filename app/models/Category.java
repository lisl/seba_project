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
	
	public static List<Category> findAllOrdered()
	{
		return Category.find("FROM Category AS c ORDER BY c.categoryName").fetch();
	}
	
	public static Category getByCategoryId(String selectedCategoryId)
	{
		return Category.find("FROM Category AS c WHERE c.categoryId = ?", selectedCategoryId).first();
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
