package Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClass.TestBase;
import Client.RestClinet;
import Utillities.util;

public class GetAPITest extends TestBase {

	TestBase testbase;
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
	public void getAPITestWithoutHeader() throws ClientProtocolException, IOException {
		RestClinet restClient = new RestClinet();
		httpResponse = restClient.Get(URI);

		// Status Code
		int StatusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code--->>" + StatusCode);

		Assert.assertEquals(StatusCode, util.RESPONSE_STATUS_CODE_200, "Status code is 200");

		// JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("JSON Response from API --->>" + responseJSON);
		
		//Fetch The value from JSON String (Single Value Assertion)
		//PerPage
		String perPageValues = util.getValueByJPath(responseJSON, "/per_page");
		System.out.println("PerPage value --->>" + perPageValues);
		Assert.assertEquals(Integer.parseInt(perPageValues), 6);
		
		//Total
		String totalValues = util.getValueByJPath(responseJSON, "/total");
		System.out.println("Total value --->>" + totalValues);
		Assert.assertEquals(Integer.parseInt(totalValues), 12); 
		
		//Fetch Value from JsonArray
		String LastNameJSON = util.getValueByJPath(responseJSON, "/data[0]/last_name");
		String idJSON = util.getValueByJPath(responseJSON, "/data[0]/id");
		String avatarJSON = util.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_nameJSON = util.getValueByJPath(responseJSON, "/data[0]/first_name");

		System.out.println(idJSON+"--" +first_nameJSON+"--"+ LastNameJSON+"--" + avatarJSON);

		// ALL Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String, String> allHeadersMap = new HashMap<String, String>();
		// Hashtable<String, String> allHeadersTable = new Hashtable<String, String>();
		for (Header header : headerArray) {
			allHeadersMap.put(header.getName(), header.getValue());
		}
		System.out.println("All Headers Map--->>" + allHeadersMap);
	}
	
	@Test
	public void getAPITestWithHeader() throws ClientProtocolException, IOException {
		RestClinet restClient = new RestClinet();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		httpResponse = restClient.Get(URI, headerMap);

		// Status Code
		int StatusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code--->>" + StatusCode);

		Assert.assertEquals(StatusCode, util.RESPONSE_STATUS_CODE_200, "Status code is 200");

		// JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("JSON Response from API --->>" + responseJSON);
		
		//Fetch The value from JSON String (Single Value Assertion)
		//PerPage
		String perPageValues = util.getValueByJPath(responseJSON, "/per_page");
		System.out.println("PerPage value --->>" + perPageValues);
		Assert.assertEquals(Integer.parseInt(perPageValues), 6);
		
		//Total
		String totalValues = util.getValueByJPath(responseJSON, "/total");
		System.out.println("Total value --->>" + totalValues);
		Assert.assertEquals(Integer.parseInt(totalValues), 12); 
		
		//Fetch Value from JsonArray
		String LastNameJSON = util.getValueByJPath(responseJSON, "/data[0]/last_name");
		String idJSON = util.getValueByJPath(responseJSON, "/data[0]/id");
		String avatarJSON = util.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_nameJSON = util.getValueByJPath(responseJSON, "/data[0]/first_name");

		System.out.println(idJSON+"--" +first_nameJSON+"--"+ LastNameJSON+"--" + avatarJSON);

		// ALL Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String, String> allHeadersMap = new HashMap<String, String>();
		// Hashtable<String, String> allHeadersTable = new Hashtable<String, String>();
		for (Header header : headerArray) {
			allHeadersMap.put(header.getName(), header.getValue());
		}
		System.out.println("All Headers Map--->>" + allHeadersMap);
	}

}
