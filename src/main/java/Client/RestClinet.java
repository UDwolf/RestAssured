package Client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import org.apache.http.Header;

public class RestClinet {

	// 1. GET Method Without Headers:
	public CloseableHttpResponse Get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		return httpResponse;
	}

	// 2. GET Method With Headers:
	public CloseableHttpResponse Get(String url, HashMap<String, String> HeaderMap)throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		for (Map.Entry<String, String> entry : HeaderMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		return httpResponse;
	}
	
	// 3. POST Method
	public CloseableHttpResponse POST(String url, String entityString, HashMap<String, String> HeaderMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); //Http Post Request
		httpPost.setEntity(new StringEntity(entityString)); //Payload
		
		//for Header
		for(Map.Entry<String, String> entry: HeaderMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
	}

}
