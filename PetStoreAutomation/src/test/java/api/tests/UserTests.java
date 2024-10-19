package api.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
    User userPayload;
    
	@BeforeClass
	public void setupData() {
		
			faker=new Faker();
			userPayload=new User();
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setUsername(faker.name().username());

			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			userPayload.setPassword(faker.internet().password(5,10));
			userPayload.setPhone(faker.phoneNumber().cellPhone());

			
		}
	
	@Test(priority=1)
	public void testPostUser() {
		
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all();
		//response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 302);
		
	//Assert.assertEquals(response.getStatusCode(), 200);
		//assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=2)
	public void testGetUserByName() {
		
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
	//	response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 302);
		//Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
}
