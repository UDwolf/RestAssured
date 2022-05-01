package Client;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import org.apache.http.Header;

public class RestClinet {
	
	public void Get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		
		//Status Code
		int StatusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code--->>"+StatusCode);
		
		//JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("JSON Response from API --->>"+responseJSON);
		
		//ALL Headers
		
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String,String> allHeadersMap = new HashMap<String, String>();
		for(Header header: headerArray) {
			allHeadersMap.put(header.getName(), header.getValue());
		}
		System.out.println("All Headers Map--->>"+allHeadersMap);
		
		Hashtable<String, String> allHeadersTable = new Hashtable<String, String>();
		for(Header header: headerArray) {
			allHeadersTable.put(header.getName(), header.getValue());
		}
		System.out.println("All Headers Table --->>"+allHeadersTable);
	}

}
