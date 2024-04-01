package com.kcrypt.db;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoStorage {
	final MongoCollection<Document> userCollection;
	final MongoClient client;

	public MongoStorage() {
		// new client connection
		// db setup
		// collections setup

		client = new MongoClient("localhost", 27017);

		MongoDatabase db = client.getDatabase("kcrypt");

		userCollection = db.getCollection("Users");
	}

	// create new user

	@Override
	protected void finalize() throws Throwable {
		client.close();
	}

}
