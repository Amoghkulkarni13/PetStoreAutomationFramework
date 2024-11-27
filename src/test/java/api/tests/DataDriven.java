package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Userendpoints;
import api.payload.User;
import api.uitilites.Dataprovider;
import io.restassured.response.Response;

public class DataDriven {
	

	@Test(priority=1, dataProvider="Data", dataProviderClass=Dataprovider.class )
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=Userendpoints.createuser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataprovider.class)
	public void testDeleteUserByName(String userName)
	{
			Response response=Userendpoints.deleteuser(userName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}
	

}
