package request;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utility.XlUtils;
public class postwithexcel {

	
	public static HashMap h = new HashMap();
	
	@Test(dataProvider = "datap")
	public void setdata(String ename,String ejob)
	{
		h.put("name", ename);
		h.put("job", ejob);
		
		Response response=		given()
		.header("Content-Type","application/json")
		.body(h)
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.statusCode(201)
		.time(lessThan(3000l))
		.log().all()
		.extract().response();
		
		Assert.assertTrue(response.getBody().asString()!=null);
		String jsondataString= response.getBody().asString();
		Assert.assertTrue(jsondataString.contains("id"));
		
	}
	
	@DataProvider(parallel = true,name = "datap")
	public Object[][] data() throws IOException
	{
		
		 String path = System.getProperty("user.dir") +"/data.xlsx";
		 
		 int rowcount = XlUtils.getRowCount(path, "Sheet1");
		 int colcount = XlUtils.getCellCount(path, "Sheet1", 1);
			//String data[][] = {{"shyam","20660","22"},{"ghanshyam","206099","32"},{"rajvada","250000","22"}};
		 
		 Object data[][] = new Object [rowcount][colcount];
		 
		 for(int i = 1;i<=rowcount;i++)
		 {
			 for(int j=0; j<colcount;j++)
			 {
				 
			data[i-1][j]	= XlUtils.getCellData(path, "Sheet1", i, j) ;
			 }
				
			 }
		 return(data);
	}
	
}
