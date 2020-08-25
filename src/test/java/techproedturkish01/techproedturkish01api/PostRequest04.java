package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response; 

public class PostRequest04 extends TestBase {
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
	
	/*
	 POJO : Plain Old Java Object
	 */
	
	
	@Test
	
	public void post01() {
		Bookingdates bookingdates = new Bookingdates("2020-05-02", "2020-05-05");
		Booking booking = new Booking("Ferit", "Aydin", 123, true, bookingdates, "Wifi");
		
		Response response = given().
				              contentType(ContentType.JSON).
				              spec(spec01).
				              auth().
				              basic("admin", "password123").
				              body(booking).
				              when().
				              post("/booking");
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200);
		
		JsonPath json = response.jsonPath();
		
		// Datalar uyusmaya bilir get methodlarinini data type larini degistir.
		// ya da burada dirakt get yap 
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("booking.firstname"), booking.getFirstname());
		softAssert.assertEquals(json.getString("booking.lastname"), booking.getLastname());
		softAssert.assertEquals(json.getInt("booking.totalprice"),booking.getTotalprice());
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), booking.getDepositpaid());
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), booking.getBookingdates().getCheckin());
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),booking.getBookingdates().getCheckout());
		softAssert.assertEquals(json.getString("booking.additionalneeds"), booking.getAdditionalneeds());
		softAssert.assertAll();		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
