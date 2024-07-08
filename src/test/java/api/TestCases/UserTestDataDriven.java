package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.EndPoints.UserEndPoints;
import api.Payloads.User;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDataDriven {

	User userPayload = new User();
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass = DataProviders.class)
	public void createUserTest(String userId, String userName, String fname, String lname, String email, String pwd, String ph) {
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUserRequest(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	

	
	@Test(priority=4, dataProvider="UserNames", dataProviderClass = DataProviders.class)
	public void deleteUserTest(String username) {
		Response response = UserEndPoints.deleteUserRequest(username);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
}
