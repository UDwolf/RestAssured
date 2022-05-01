import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Test01_GET {
	
	@Test
	public void test_01() {
		Response response = get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.asString());
		System.out.println("-----------------------------Output Pretty---------------------------------------------------------------------------");
		System.out.println(response.getBody().asPrettyString());
		System.out.println("-----------------------------Output Regular String--------------------------------------------------------------------------");
		System.out.println(response.getBody().asString());
		System.out.println("-----------------------------Status Code--------------------------------------------------------------------------");
		System.out.println(response.getStatusCode());
		System.out.println("-----------------------------Time--------------------------------------------------------------------------");
		System.out.println(response.getTime());
		System.out.println("-----------------------------Status Line--------------------------------------------------------------------------");
		System.out.println(response.getStatusLine());
		System.out.println("------------------------------header--------------------------------------------------------------------------");
		System.out.println(response.getHeader("content-type"));
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test
	public void test_02() {
		given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]", equalTo(7)).body("data.first_name",hasItems("Michael","Tobias"));
		
	}
	

}
