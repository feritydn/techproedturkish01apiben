package techproedturkish01.techproedturkish01api;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequest05 {
	
	/*
	 * 
	   2)When I send a GET request to REST API URL
		 https://restful-booker.herokuapp.com/booking/5
	     Then HTTP Status Code should be 200
	     And response content type is “application/JSON”
	     And “firstname” should be “Jim”,
	     And “totalprice” should be 602
		 And “checkin” should be “2015-06-12”
	 */

	@Test
	public void get01() {
		Response response = given().
				             when()
				              .get("https://restful-booker.herokuapp.com/booking/5");
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200).
		contentType(ContentType.JSON).
		body("firstname", Matchers.equalTo("Jim")).
		body("totalprice",Matchers.equalTo(602)).
		body("bookingdates.checkin", Matchers.hasItem("2015-06-12"));
	}
}
