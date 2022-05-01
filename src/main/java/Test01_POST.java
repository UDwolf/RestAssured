import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Test01_POST {
	
	@Test
	public void test01_post() {
		/*Map<String, Object> map =new HashMap<String, Object>();
		
		map.put("name","bord1");
		
		System.out.println(map);*/
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Borad2");
		request.put("key", "243f2608843777ec273d1ae7442d1a7e");
		request.put("token", "7f556f26429099885cecc9437877bb4edba49198be77d6ed9fe6bbef44429ee0");
		
		//System.out.println(request.toJSONString());
		
		/*given().body(request.toJSONString())
		.when().post("https://api.trello.com/1/boards/")
		.then().statusCode(200);*/
		
	}

}
