package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import play.data.validation.IsTrue;
import play.data.validation.Match;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Task extends Model
{
	@Required
	@MinSize(5)
	public String title;
	
	@Required
	@MinSize(10)
	public String description;
	
	@Required
	@MinSize(4)
	public String location;
	
	@Required
	@Match(value = "(\\d)+((,|\\.)(\\d){1,2}){0,1}", message = "Numeric value required")
	public String payment;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	public Category category;
	
	public void setCategoryId(String value)
	{
		category = Category.find("FROM Category AS c WHERE c.categoryId = ?", value).first();
	}
	
	public String getCategoryId()
	{
		if (category == null)
		{
			return Category.NOT_SELECTED;
		}
		else
		{
			return category.categoryId;
		}
	}
}
