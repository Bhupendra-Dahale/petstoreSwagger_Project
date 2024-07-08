package api.EndPoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java 
//Created to perform Create, Read, Update, Delete requests to the User services

public class UserEndPoints {

	public static Response createUserRequest(User payload) {
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
							.when()
								.post(Routes.create_user_url);
		return response;
	}
	
	public static Response getUserRequest(String userName) {
		
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username",userName)
							.when()
								.get(Routes.get_user_url);
		return response;
	}
	
	public static Response updateUserRequest(String userName, User payload) {
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.pathParam("username",userName)
								.body(payload)
							.when()
								.post(Routes.update_user_url);
		return response;
	}
	
	public static Response deleteUserRequest(String userName) {
		
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username",userName)
							.when()
								.get(Routes.delete_user_url);
		return response;
	}
}
