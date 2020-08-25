package techproedturkish01.techproedturkish01api;




import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest02 extends TestBase {
	
					/*
					 * POST Scenario: 
					 * Accept type Json olsun When POST request yolladigimda 
					 * 1) https://restful-booker.herokuapp.com/booking
					 *  2) Request Body 
					 *  { "firstname":"Suleyman", 
					 *  "lastname": "Alptekin", 
					 *  "totalprice": 123, 
					 *  "depositpaid": true,
					 * "bookingdates": {
					 *  "checkin": "2020-05-02", 
					 *  "checkout": "2020-05-05" },
					 * "additionalneeds": "Wifi" } 
					 * Then 
					 * Status Code 200 olmali 
					 * Response Bodynin , Request Body ile ayni oldugunu verify ediniz.
					 */
	
	@Test
	public void post01() {
		
		
		
		Response response = createRequestBodyByJsonObjectClass(); // JSON object class kullandik iyi bir sey
		
		response.prettyPrint();
		

		response.
		then().
		assertThat().
		statusCode(200);
		
		JsonPath json = response.jsonPath();
		
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("booking.firstname"), "Ferit");
		softAssert.assertEquals(json.getString("booking.lastname"), "Aydin");
		softAssert.assertEquals(json.getInt("booking.totalprice"),123);
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), true);
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02");
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05");
		softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi");
		softAssert.assertAll();		
		
	}

}
