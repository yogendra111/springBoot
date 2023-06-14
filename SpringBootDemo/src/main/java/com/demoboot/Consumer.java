package com.demoboot;

public class Consumer {
	
	private int id;
	private String name;
	private String course;
	
	public int getId() {
		return id;
	}
	public Consumer setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public Consumer setName(String name) {
		this.name = name;
		return this;
	}
	public String getCourse() {
		return course;
	}
	public Consumer setCourse(String course) {
		this.course = course;
		return this;
	}
	
	@Override
	public String toString() {
		return "Consumer [id=" + id + ", name=" + name + ", course=" + course + "]";
	}
	

}
