package Tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClass.TestBase;
import Client.RestClinet;

public class GetAPITest extends TestBase {

	TestBase testbase;
	String ServiceURL;
	String ApiURL;
	String URI;
	
	@BeforeMethod
	public void openURI(){
		testbase = new TestBase();
		ServiceURL = prop.getProperty("serviceUrL");
		ApiURL = prop.getProperty("ApiURL");
		URI = ServiceURL + ApiURL;
		
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		RestClinet restClient = new RestClinet();
		restClient.Get(URI);
	}

	
}
