package request;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;
import com.google.gson.JsonObject;
import com.sun.xml.xsom.impl.scd.Iterators.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import Basesetup.Base;
import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class grtreq1 extends Base{
	public HashMap Map = new HashMap();
	@BeforeClass
	public void checkData()
	{
		Map.put("email", "eve.holt@reqres.in");
		Map.put("password", "pistol");
		
		RestAssured.baseURI="https://reqres.in/api";
		RestAssured.basePath="/register";
		
		
		
	}
	
	@Test
	void Testdata()
	{
		
		logger.info("inside test data of getreq1");
	Response response= 	given()
		.header("Content-Type","application/json")
		.body(Map)
		.when()
		.post()
		.then()
		.time(lessThan(4000l))
		.statusCode(200)
		.body("id", equalTo(4))
		.extract().response();
	

String bodyString= 	response.getBody().asString();

		Assert.assertTrue(bodyString.contains("id"));    
		
	}
	

}
