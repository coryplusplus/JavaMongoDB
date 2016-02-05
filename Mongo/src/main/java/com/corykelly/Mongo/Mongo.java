package com.corykelly.Mongo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class Mongo {
	private Datastore datastore;
	private final Morphia morphia = new Morphia();
	private String mHost = "localhost";
	private String mDatabaseName ="test";

	public Mongo(ArrayList<String> classPaths)  {
		initialize(classPaths);
	}

	private void readProperties() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(ClassLoader.getSystemResource("config.properties").getFile());

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			mHost = prop.getProperty("dbhost");
			mDatabaseName = prop.getProperty("dbname");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Datastore getDatastore()
	{
		return datastore;
	}
	private void initialize(ArrayList<String> classPaths)  {
		readProperties();
		// create the Datastore connecting to the default port on the local host
		datastore = morphia.createDatastore(new MongoClient(mHost), mDatabaseName);
		datastore.ensureIndexes();

	}
	
	public void save(Object o)
	{
		datastore.save(o);
	}

}
