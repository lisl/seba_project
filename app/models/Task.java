package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	public String description;
	
	@Required
	public String location;
	
	@Required
	public String payment;
	
	
	@ManyToOne
	public Category category;
	
	public void setCategoryId(String value)
	{
		category = null;
	}
	
	public String getCategoryId()
	{
		return "TODO";
	}
}
