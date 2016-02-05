package com.corykelly.Mongo;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class TestRunner {
	

	@Id
	private ObjectId id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		ArrayList<String> classPaths = new ArrayList<>();
		classPaths.add(TestRunner.class.getName());
		Mongo db = new Mongo(classPaths,"localhost","test");

		
		TestRunner runner = new TestRunner();
		runner.setName("Paul");
		db.save(runner);
		
	}
}
