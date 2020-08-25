package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class PostRequest01 extends TestBase {

	/*
	 * Post Request olusturmak icin gerekenler 1) End point sart 2) Request body
	 * sart 3) Authorization sart 4) Accept Type istege bagli 5) Content Type istege
	 * bagli
	 * 
	 * Get ile Post requestlerin farki nedir? 1) get requesti data cekmek icin
	 * kullaniriz. SQL deki select gibidir auto gerekir ve sadece endpoint ile is
	 * yapilabilir
	 * 
	 * 2) Post data olusturmak icin olusturmak icin kullanir. Endpoit ve data lazim
	 * Bunun disinda auto ve benzeri durumla da istenilebilir.
	 * 
	 * FARKLAR 1) Get icin sadece EndPoint yeterlidir ama Post icin endPoint in
	 * yaninda request body de sarttir.
	 * 
	 * NOTE: API developer lar olusturuacak datanin bazi bolumlerinin bos
	 * birakilmamasina gerektigini karar vermislerse post request olusturulurken
	 * kesinlikle o kisimlara deger verilerek POST REQUEST olusturulmalidir. Eger
	 * buna dikkat etmezsek 400 bad request status code alirsiniz
	 * 
	 * 
	 * NOTE: API developer lar olusturuacak datanin bazi bolumlerinin tekrarli
	 * olmamasina karar vermislerse post request olusturulurken kesinlikle o
	 * kisimlara tekrarli deger verilmeyerek POST REQUEST olusturulmalidir. Eger
	 * buna dikkat etmezsek 400 bad request status code alirsiniz
	 */

	@Test
	public void post01() {

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
		
		
		
		// 1. Yol iyi degil
		String jsonRequestBody = "{\n" + 
									"\"firstname\": \"Ferit\",\n" + 
									"\"lastname\": \"Aydin\",\n" + 
									"\"totalprice\": 123,\n" + 
									"\"depositpaid\": true,\n" + 
									"\"bookingdates\": {\n" + 
									"\"checkin\": \"2020-05-02\",\n" + 
									"\"checkout\": \"2020-05-05\"\n" + 
									"},\n" + 
									"\"additionalneeds\": \"Wifi\"\n" + 
									"}";

		Response response = given().
				              contentType(ContentType.JSON).
				              spec(spec01).
				              auth().
				              basic("admin", "password123").
				              body(jsonRequestBody).
				              when().
				              post("/booking");
		response.prettyPrint();


		response.
		then().
		assertThat().
		statusCode(200);
		
		//jsonPath kullanarak assertion yapalim
		
		JsonPath json = response.jsonPath();
		
		
		SoftAssert softAssert = new SoftAssert();
		// firstname assertion 
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
