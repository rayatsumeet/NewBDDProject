package request;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.client.protocol.ResponseContentEncoding;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class VerifyList {
	
	
	@BeforeClass
	public void  seturl() {
		// TODO Auto-generated method stub

		
		RestAssured.baseURI="https://reqres.in/" ;
		RestAssured.basePath="api/unknown";
		
		
		}
	
	
	@Test(priority = 1)
	public void verifycodes()
	{
		
		given()
		.when()
		.get()
		.then()
		.statusCode(200)
		.time(lessThan(4000l))
		.header("Content-Type","application/json; charset=utf-8");
		
		
	}
	
	
	@Test(priority = 2)
	public void verifydata()
	{
		
	Response r =	given()
		.when()
		.get()
		.then()
	
	.extract().response();
	
	String bodyString= r.getBody().asString();
	Assert.assertEquals(r.jsonPath().get("data[2].name"), "true red");	
	Assert.assertTrue(bodyString.contains("To keep ReqRes free, contributions towards"));
		
	}
	
	
	
	
	

}
