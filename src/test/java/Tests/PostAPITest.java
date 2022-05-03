package Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import BaseClass.TestBase;
import Client.RestClinet;
import Data.Users;
import Utillities.util;

public class PostAPITest extends TestBase{
	
	TestBase testbase;
	RestClinet restClinet;
	String ServiceURL;
	String ApiURL;
	String URI;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void openURI() {
		testbase = new TestBase();
		ServiceURL = prop.getProperty("serviceUrL");
		ApiURL = prop.getProperty("ApiURL");
		URI = ServiceURL + ApiURL;

	}
	
	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException {
		restClinet = new RestClinet();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json"); 
		
		//Jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Umesh", "Leader"); //Expected Users Object
		
		//Converting Java Object to JSON File
		mapper.writeValue(new File("C:\\Selenium_Workspace\\RestAssured\\src\\main\\java\\Data\\users.json"), users);
		
		//Converting JSON file to JSON String
		String usersJSONString = mapper.writeValueAsString(users);
		System.out.println(usersJSONString);
		
		httpResponse = restClinet.POST(URI, usersJSONString, headerMap);
		
		//1. Check the Status code.
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode,util.RESPONSE_STATUS_CODE_201);
		
		//2. JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		
		JSONObject jsonObject = new JSONObject(responseString);
		System.out.println("The new Response String -->>"+jsonObject);
		
		//Converting JSON String to Java Object
		Users usersResObj = mapper.readValue(responseString, Users.class);
		System.out.println(usersResObj); //Actual Users Object
		
		Assert.assertTrue(usersResObj.getName().equals(users.getName()), "Pass Name");
		Assert.assertTrue(usersResObj.getJob().equals(users.getJob()), "Pass Job");
			
	}

}
