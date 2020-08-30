package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest03 extends TestBase {
	
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
		Response response = createRequestBodyByMap();
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200);
		
		JsonPath json = response.jsonPath();
		
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("booking.firstname"), requestBodymap.get("firstname"));
		softAssert.assertEquals(json.getString("booking.lastname"), requestBodymap.get("lastname"));
		softAssert.assertEquals(json.getInt("booking.totalprice"),requestBodymap.get("totalprice"));
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), requestBodymap.get("depositpaid"));
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),bookingDatesMap.get("checkin") );
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),bookingDatesMap.get("checkout") );
		softAssert.assertEquals(json.getString("booking.additionalneeds"),requestBodymap.get("additionalneeds") );
		softAssert.assertAll();		
		
		
	}
}