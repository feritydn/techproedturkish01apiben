package techproedturkish01.techproedturkish01api;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PutRequest02 extends TestBase {

	/*
	 * 1) Spec01 kullanarak herhangi bir datayi update ediniz 2) Update edildigini
	 * status code ve response body ile verify ediniz
	 */
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
				                 when().put("/200");
		responseAfterPut.prettyPrint();
		
		responseAfterPut.
		        then().
		        assertThat().
		        statusCode(200); // 201 de olabilir
		
		JsonPath json = responseAfterPut.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		// completed degerini verify ediniz
		softAssert.assertEquals(json.getBoolean("completed"), jsonObject.get("completed"));
		softAssert.assertEquals(json.getString("title"), jsonObject.get("title"));
		softAssert.assertEquals(json.getInt("userId"), jsonObject.get("userId"));
		softAssert.assertAll();
		
		
	}

}
