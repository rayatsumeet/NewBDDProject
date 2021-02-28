package request;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import Basesetup.Base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class Post extends Basesetup.Base {
	
	
	public static HashMap  data = new HashMap();
	public static String id;
	
	@BeforeClass
	void Data()
	{
		System.out.println("insidedata");
	data.put("name", "morpheus");
	data.put("job", "Leader");
	
	
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api/users";
		
	}
	@Test(priority = 1)
	
	void checkpost()
	{
		try {
			logger.info("starting post request");
			
			 id= 	given()
				.header("Content-Type","application/json")
				.body(data)
				.when()
				.post()
				.then()
				.body("name", is("morpheus"))
				.body("job", is("Leader"))
				.contentType(ContentType.JSON)
				.time(lessThan(5000l))
			.statusCode(201)
		
				.extract().path("id");
			 
			 System.out.println(id); 
			 
		} catch (Exception e) {
		e.printStackTrace();
		}

		
		
		
	}
	
	@Test(priority = 2)
	public static  String  returnid() {
		
	System.out.println(id);
return id;
	}
	
	
	
	
	@AfterClass
	void teardown()
	{
		logger.info("post request complete");
	}

}
