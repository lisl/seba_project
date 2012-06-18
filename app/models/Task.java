package models;

import javax.persistence.ManyToOne;

public class Task {

	public String taskId;
	
	//@ManyToOne
	public Category category;
	
	public String title;
	public String description;
	public String location;
	public String payment;
	
	public void setCategoryId(String value) {
		category = null;
	}
	
	public String getCategoryId() {
		return "TODO";
	}
}
