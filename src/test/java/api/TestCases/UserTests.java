package api.TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndPoints;
import api.Payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker = new Faker();
	User userPayload = new User();
	Logger logger = LogManager.getLogger(this.getClass());
	
	@BeforeClass
	public void setupData() {
		
		logger.info("********* Data setup started **********");
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger.info("********* Data setup completed **********");
	}
	
	@Test(priority=1)
	public void createUserTest() {
		
		logger.info("********* sending post request **********");
		
		Response response = UserEndPoints.createUserRequest(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=2)
	public void getUserTest() {
		
		logger.info("********* sending get request **********");
		
		Response response = UserEndPoints.getUserRequest(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void updateUserTest() {
		
		logger.info("********* updating the data **********");
		
		//Update user using same Payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		logger.info("********* sending put request **********");
		
		Response response = UserEndPoints.updateUserRequest(userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
		//Check data after Update
		Response responseAfterUpdate = UserEndPoints.getUserRequest(userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void deleteUserTest() {
		
		logger.info("********* sending delete request **********");
		
		Response response = UserEndPoints.deleteUserRequest(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
}
