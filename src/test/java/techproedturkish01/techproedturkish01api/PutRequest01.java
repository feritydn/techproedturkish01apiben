package techproedturkish01.techproedturkish01api;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class PutRequest01 extends TestBase {

	@Test
	public void put01() {
		
		Response response = given().
				            	spec(spec03).
				            	when().
				            	get("/200");
		response.prettyPrint();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "Ferit");
		jsonObject.put("userId", 10);
		jsonObject.put("completed", false);
		
		Response responseAfterPut = given().
				                     contentType(ContentType.JSON).
				                     spec(spec03).
				                     body(jsonObject.toString()).
				                     when().
				                     put("/200");
		responseAfterPut.prettyPrint();
	}

}
