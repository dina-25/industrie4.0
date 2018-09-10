package de.se_rwth.industry40.interaction_api;

import static spark.Spark.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import de.se_rwth.industry40.interaction_api.model.Order;
import de.se_rwth.industry40.interaction_api.model.User;

public class InteractionAPI 
{
	private static Gson gson;
	private static SessionFactory sessionFactory;
	
    public static void main(String[] args) throws Exception {
    	//Initialization    	
		sessionFactory = new Configuration().configure().buildSessionFactory();
    	
    	//Create our gson object and configure it to ignore all fileds that dont have the Expose annotation
    	GsonBuilder gsonBuilder = new GsonBuilder();
    	gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
    	
    	//Set the port to 80
    	port(80);
    	
    	//For examples how to use spark: http://sparkjava.com/documentation#getting-started
    	
    	//Register Endpoints
        registerUserEndpoints();
        registerOrderEndpoints();
    }
    
    public static void registerUserEndpoints(){
    	post("/users", (req, res) -> {
    		res.type("application/json");
    		
    		try{
    			//Transform the request body to a JsonObject
    			User user = gson.fromJson(req.body(), User.class);
    			
    			//Save the user to the database
        		Session session = sessionFactory.openSession();
        		session.beginTransaction();
        		session.save(user);
        		session.getTransaction().commit();
        		session.close();
    			
        		//set the response code to 200
        		res.status(200);
    		}catch(JsonSyntaxException exception){
    			res.status(400);
    			
    			JsonObject json= new JsonObject();
    			json.addProperty("error", "The json data is not in an expected format.");
    			return json;
    		}
    		
    		return "";
    	});
    	
        get("/users/:id", (req ,res) -> { 
        	res.type("application/json");
        	
    		//Load the user from the database
        	Session session = sessionFactory.openSession();
    		session = sessionFactory.openSession();
    		session.beginTransaction();
    		User user = (User) session.get(User.class, new Long(req.params(":id")));
    		session.getTransaction().commit();
    		
    		String result = "";
    		if(user != null){
    			result = gson.toJson(user);
    		}else{
    			res.status(404);
    		}
    			    		
    		session.close();
       		
    		return result;
        });
    	    	
        
        get("/users", (req ,res) -> {    
        	res.type("application/json");
        	
    		//Load the users from the database
        	Session session = sessionFactory.openSession();
    		session = sessionFactory.openSession();
    		session.beginTransaction();
    		List<User> userList = session.createQuery("from User").list();
    		
    		session.getTransaction().commit();
    		
    		String result = gson.toJson(userList);
    		session.close();
    		
        	return result;
        });
    }

    public static void registerOrderEndpoints(){

    }
}
