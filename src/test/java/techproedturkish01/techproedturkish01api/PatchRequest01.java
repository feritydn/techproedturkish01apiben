package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PatchRequest01 extends TestBase{
	
	@Test
	public void patch01() {
		Response responseBeforePatch = given().
							             spec(spec03).
							             when().
							             get("/199");
		responseBeforePatch.prettyPrint();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "Ali");
		
		Response responseAfterPatch = given().
				                        contentType(ContentType.JSON).
				                        spec(spec03).
				                        body(jsonObject.toString()).
				                        when().
				                        patch("/199");
		responseAfterPatch.prettyPrint();
		
		responseAfterPatch.
						then().
						assertThat().
						statusCode(200);
		// Title hard assert yapalim
		JsonPath json = responseAfterPatch.jsonPath();
		assertEquals("Isim beklenen gibi degil",jsonObject.getString("title"),json.get("title"));
		
		// Soft assert yapalim
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("title"), jsonObject.getString("title") );
		softAssert.assertAll();
	}

}
