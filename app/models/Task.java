package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Task extends Model{

	public String title;
	public String description;
	public String location;
	public String payment;
	
	
	@ManyToOne
	public Category category;
	
	public void setCategoryId(String value) {
		category = null;
	}
	
	public String getCategoryId() {
		return "TODO";
	}
}
