package request;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.client.protocol.ResponseContentEncoding;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class VerifyList {
	
	
	@Test(priority = 1)
	public void verifycodes()
	{
		
		given()
		.when()
		.get()
		.then()
		.statusCode(200)
		.time(lessThan(4000l))
		.header("Content-Type","application/json");
		
		
	}
	
	
	@Test(priority = 2)
	public void verifydata()
	{
		
	Response r =	given()
		.when()
		.get()
		.then()
	.body(".data[2].name", equalTo("true red "))
	.extract().response();
	
	String bodyString= r.getBody().asString();
	
	Assert.assertTrue(bodyString.contains("To keep ReqRes free, contributions towards "));
		
	}
	
	
	
	
	

}
