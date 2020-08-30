package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.response.Response;
import utilities.JsonUtil;
import static io.restassured.RestAssured.*;

public class ObjectMapperClassWithPojo extends TestBase {

	@Test
	public void javaToJson() {
		Bookingdates bookingdates = new Bookingdates("2020-11-03","2020-11-08");
		
		// Bookingdates java objectini json a cevirdik ===> Serialization
		String jsonFromPojo = JsonUtil.convertjavaToJson(bookingdates);
		System.out.println(jsonFromPojo);
	}
	
	@Test
	
	public void jsonToJava() {
		Response response = given().
	              spec(spec01).
	              when().
	              get("booking/3");
		response.prettyPrint();
		
		//API dan gelen Json Data'yi classa cevirdik
		
		Booking jsonToPojoApi = JsonUtil.convertJsonTojava(response.asString(), Booking.class);
		System.out.println(jsonToPojoApi);
		
		Bookingdates bookingdates = new Bookingdates("2015-06-07", "2020-08-10");
		Booking booking = new Booking("Susan", "Jones", 277, true, bookingdates, "Breakfast");
		
		
		response.
		      then().
		      assertThat().
		      statusCode(200);
		assertEquals(booking.getBookingdates().getCheckin(),jsonToPojoApi.getBookingdates().getCheckin());
		assertEquals(booking.getBookingdates().getCheckout(),jsonToPojoApi.getBookingdates().getCheckout());
		
	}
}
