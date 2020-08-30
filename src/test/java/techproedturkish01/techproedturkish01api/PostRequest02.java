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
		softAssert.assertEquals(json.getString("booking.firstname"), jsonrequestBody.getString("firstname"));
		softAssert.assertEquals(json.getString("booking.lastname"), jsonrequestBody.getString("lastname"));
		softAssert.assertEquals(json.getInt("booking.totalprice"),jsonrequestBody.getInt("totalprice"));
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), jsonrequestBody.getBoolean("depositpaid"));
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), jsonBookingDateBody.getString("checkin"));
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), jsonBookingDateBody.getString("checkout"));
		softAssert.assertEquals(json.getString("booking.additionalneeds"), jsonrequestBody.getString("additionalneeds"));
		softAssert.assertAll();		
		
	}

}
