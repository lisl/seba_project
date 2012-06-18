package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Category extends Model {

	@Id
	public String categoryId;
	public String categoryName;
	
	@OneToMany
	public List<Task> tasks;
}
