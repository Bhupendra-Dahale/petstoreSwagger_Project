package api.EndPoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java 
//Created to perform Create, Read, Update, Delete requests to the User services

public class UserEndPointsProperties {

	// method created for getting URL's from properties file
	static ResourceBundle getRoutes() {
		
		ResourceBundle rb = ResourceBundle.getBundle("routes");	// Load properties file  // name of the properties file
		return rb;
	}
	
	public static Response createUserRequest(User payload) {
		
		String create_url = getRoutes().getString("create_user_url");
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
							.when()
								.post(create_url);
		return response;
	}
	
	public static Response getUserRequest(String userName) {
		
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username",userName)
							.when()
								.get(getRoutes().getString("get_user_url"));
		return response;
	}
	
	public static Response updateUserRequest(String userName, User payload) {
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("username",userName)
								.body(payload)
							.when()
								.post(getRoutes().getString("update_user_url"));
		return response;
	}
	
	public static Response deleteUserRequest(String userName) {
		
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username",userName)
							.when()
								.get(getRoutes().getString("delete_user_url"));
		return response;
	}
}
