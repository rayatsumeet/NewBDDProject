package request;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import Basesetup.Base;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class update extends Base {
	
	String idString= Post.returnid();
	

	public HashMap me = new HashMap();
	
	@BeforeClass
	void datapreapare()
	{
	logger.info("setting update information");
	System.out.println(idString);
	
	me.put("name", "morpheus");
	me.put("job", "zion resident");
	
	RestAssured.baseURI= "https://reqres.in/api/users/"+idString;
	
	
		
	}
	
	
	@Test
	void update()
	
	{
		logger.info("updating information");
	Response response=	given()
		.body(me)
		.header("Content-Type", "application/json")
		.when()
		.put()
		.then()
		.statusCode(200)
		.body("job", equalTo("zion resident"))
		.time(lessThan(2000l))
		.extract().response();
		
	String reString=	response.getBody().asString();
		System.out.println(reString);
	}
	

}
