package api.tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	public Logger logger;
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********** Creating user  ***************");
		Response response=Userendpoints.createuser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**********User is creatged  ***************");
			
	}
	
	@Test(priority=2)
	
	public void testgetuser() {
		logger.info("********** Reading User Info ***************");
		
		Response res= Userendpoints.getuser(userPayload.getUsername());
		res.then().log().all();
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("**********User info  is displayed ***************");
		
			  
			  
		  }
	
	
	@Test(priority=3)
	public void testupdateuser() {
		logger.info("********** Updating User ***************");
		
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
	Response res=	Userendpoints.updateuser(userPayload.getUsername(), userPayload);
		
	
	   Assert.assertEquals(res.getStatusCode(), 200);
	   
	   logger.info("********** User updated ***************");
	   
	   Response responseAfterupdate=Userendpoints.getuser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
			
	}
	
	
	@Test(priority=4)
	public void testdeleteuser() {
		
		logger.info("**********   Deleting User  ***************");
		
		
Response res=Userendpoints.deleteuser(userPayload.getUsername());
Assert.assertEquals(res.getStatusCode(), 200);

logger.info("********** User deleted ***************");
	}

}

