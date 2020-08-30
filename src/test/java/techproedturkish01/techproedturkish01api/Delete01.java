package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;


import org.junit.Test;

import io.restassured.response.Response;

public class Delete01 extends TestBase {
	
	@Test
	//Silme yetkisi olmadigi icin silemedik
	public void delete01() {
		Response responseBeforeDelete = given().
				                           auth().
				                           basic("admin", "password123").
							               spec(spec01).
							               when().
							               get("/booking/5");
	   responseBeforeDelete.prettyPrint();
	   
	   Response responseAfterDelete = given().
			                                spec(spec01).
			                                auth().
					                        basic("admin", "password123").
			                                when().
			                                delete("/booking/5");
	   responseAfterDelete.prettyPrint();
	   
	   Response getresponseBeforeDelete = given().
										   auth().
							               basic("admin", "password123").
							               spec(spec01).
							               when().
							               get("/booking/5");
       getresponseBeforeDelete.prettyPrint();
	}
	
	@Test
	//Silme yetkisi olmadigi icin silemedik
	public void delete02() {
		Response responseBeforeDelete = given().
				                           spec(spec03).
							               when().
							               get("22");
	   responseBeforeDelete.prettyPrint();
	   
	   Response responseAfterDelete = given().
			                                spec(spec03).
			                                when().
			                                delete("22");
	   responseAfterDelete.prettyPrint();
	   
	   Response getresponseBeforeDelete = given().
										   spec(spec03).
							               when().
							               get("22");
       getresponseBeforeDelete.prettyPrint();
       
       responseAfterDelete.
				       then().
				       assertThat().
				       statusCode(200);
	}
	

}
