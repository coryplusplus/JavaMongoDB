package com.corykelly.Mongo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class Mongo {
	private Datastore datastore;
	private final Morphia morphia = new Morphia();
	private String mHost = "localhost";
	private String mDatabaseName ="test";

	public Mongo(ArrayList<String> classPaths, String host, String databaseName)  {
		this.mHost = host;
		this.mDatabaseName = databaseName;
		initialize(classPaths);
	}


	public Datastore getDatastore()
	{
		return datastore;
	}
	private void initialize(ArrayList<String> classPaths)  {
		// create the Datastore connecting to the default port on the local host
		datastore = morphia.createDatastore(new MongoClient(mHost), mDatabaseName);
		datastore.ensureIndexes();

	}
	
	public void save(Object o)
	{
		datastore.save(o);
	}

}
