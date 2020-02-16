package teachers;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.MalformedJsonException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;

import java.util.ArrayList;
import java.util.List;

public class MongoDAO {
	/*	External Archives
	 * 	bson-3.8.2.jar
	 * 	gson-2.2.2.jar
	 * 	mongodb-driver-3.11.0-.jar
	 * 	mongodb-driver-core-3.11.0-.jar
	 * 	mongo-java-driver-3.12.1.jar
	 */
	
	private MongoClient server;
	private MongoDatabase db;
	private  MongoCollection<Document> collection;
	
	public MongoDAO(String uriName, String dbName, String collectionName) {
		MongoClientURI uri = new MongoClientURI(uriName);
		server = new MongoClient(uri);
		db = server.getDatabase(dbName);
		collection = db.getCollection(collectionName);
	}
	
	public  void printCollection() {
		FindIterable<Document> cursor = collection.find();
		for(Document doc : cursor) {
			System.out.println(doc.toJson());
		}
	}

	public  List<Teacher> get() throws MalformedJsonException {
		List< Teacher> profesores = new ArrayList<Teacher>();
		try {
			Gson gson = new Gson();        
	    	FindIterable<Document> cursor = collection.find();
			for (Document doc : cursor) {
				profesores.add( (Teacher) gson.fromJson(doc.toJson(), Teacher.class));
				}
			}
			catch(MongoException e) {
				System.out.println("Mongo Fatal error = "+ e.getMessage()); 
			}
			return profesores;
	}

	public  Teacher getById(String id) throws MalformedJsonException {
		 Teacher profesor = new Teacher();
		try {
			Gson gson = new Gson();        
	    	Document searchById = new Document();
			searchById.put("_id", new ObjectId(id));
	    	FindIterable<Document> cursor = collection.find(searchById);
			for (Document doc : cursor) {
				profesor = (Teacher) gson.fromJson(doc.toJson(), Teacher.class);
				}
			}
			catch(MongoException e) {
				System.out.println("Mongo Fatal error = "+ e.getMessage()); 
			}
			return profesor;
	}

	public  Boolean insert(Teacher profesor) throws MalformedJsonException {
		Gson gson = new Gson(); 
		Document docProfesor = parse(gson.toJson(profesor));
		try {
			collection.insertOne(docProfesor);
			}
			catch(MongoException e) {
				System.out.println("Mongo Fatal error = "+ e.getMessage()); 
				return false;
			}
		return true;	
	}
	
	public  void drop() {
		if (collection.count() > 0) {
			System.out.println("(00) MongoDao collection.drop() documents = "+collection.count());
			collection.drop();
			}
		else {
			System.out.println("(00) MongoDao is not collection yet");
			}
	}

	public  Boolean update(Teacher profesor) throws MalformedJsonException {
		Gson gson = new Gson(); 
		Document docProfesor = parse(gson.toJson(profesor));
		Document searchById = new Document();
		searchById.put("_id", new ObjectId(profesor.get_id()));
    	final UpdateResult updateResult;
 	    try {
 	    	updateResult = collection.replaceOne(searchById,docProfesor);
	    }
		catch(MongoException e) {
			System.out.println("Mongo Fatal error = "+ e.getMessage()); 
			return false; 
		}
	    if (updateResult.getModifiedCount() > 0 ){
			return true;	}
	    else {
	    	return false;
	    }
	}
	
	public  Boolean delete(Teacher profesor) throws MalformedJsonException {
		Document searchById = new Document();
		searchById.put("_id", new ObjectId(profesor.get_id()));
    	final DeleteResult deleteResult;
		try {
	    	deleteResult=collection.deleteOne(searchById);
			}
			catch(MongoException e) {
				System.out.println("Mongo Fatal error = "+ e.getMessage()); 
				return false;
			}
			if (deleteResult.getDeletedCount() > 0 ){
				return true;	}
		    else {
		    	return false;
		    }
		}
	
	private  Document parse(String source) throws MalformedJsonException {
		try {
		    return Document.parse(source);
		  } catch (JsonIOException | org.bson.json.JsonParseException o_O) {
		    throw new MalformedJsonException("JSON error", o_O);
		  }
		}
}