package techproedturkish01.techproedturkish01api;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest07 extends TestBase{
/*
 Among the data there are someones whose first name is “Susan”
 */
	
	@Test
	
	public void get01() {
		Response response = given().
				spec(spec01).
				  get("/booking?firstname=Susan");
		response.prettyPrint();
		
		assertTrue(response.getBody().asString().contains("bookingid"));
		
	}
	
	
@Test
	
	public void get02() {
	   
	    //spec01.queryParam("firstname", "Susan");
	    //spec01.queryParam("depositpaid", true);
		spec01.queryParams("firstname", "Susan",
				           "depositpaid", true);
	    Response response = given().
				spec(spec01).
				  get("/booking");
		response.prettyPrint();
		
		assertTrue(response.getBody().asString().contains("bookingid"));
		
	}
	
}
