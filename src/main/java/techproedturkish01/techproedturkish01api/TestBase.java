package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	protected RequestSpecification spec03;
	protected Map <String, String> bookingDatesMap = new HashMap <>();
	protected Map <String, Object> requestBodymap = new HashMap<>();
	protected JSONObject jsonBookingDateBody = new JSONObject();
	protected JSONObject jsonrequestBody = new JSONObject();
	
	@Before
	public void setUp01() {
		spec01 = new RequestSpecBuilder().
				setBaseUri("https://restful-booker.herokuapp.com").
				build();
		
	}
	
	@Before
	public void setUp02() {
		
		spec02 = new RequestSpecBuilder().
				setBaseUri("http://dummy.restapiexample.com/api/v1/employees")
				.build();
	}
	
	@Before
	public void setUp03() {
		
		spec03 = new RequestSpecBuilder().
				setBaseUri("https://jsonplaceholder.typicode.com/todos")
				.build();
	}
	
	protected Response createRequestBodyByJsonObjectClass() {
		
		
		jsonBookingDateBody.put("checkin", "2020-05-02");
		jsonBookingDateBody.put("checkout", "2020-05-05");
		
		
		jsonrequestBody.put("firstname", "Ali");
		jsonrequestBody.put("lastname", "Can");
		jsonrequestBody.put("totalprice", 123);
		jsonrequestBody.put("depositpaid", true);
		jsonrequestBody.put("bookingdates", jsonBookingDateBody ); // Cok onemli
		jsonrequestBody.put("additionalneeds", "Wifi");
		
		Response response = given().
				             contentType(ContentType.JSON).
				             spec(spec01).
				             auth().
				             basic("admin", "password123").
				             body(jsonrequestBody.toString()).
				             when().
				             post("/booking");
		
		return response;
		
	}
	
	protected Response createRequestBodyByMap() {
		
		
		
		bookingDatesMap.put("checkin", "2020-05-02");
		bookingDatesMap.put("checkout", "2020-05-05");
		
		
		
		requestBodymap.put("firstname", "Ferit");
		requestBodymap.put("lastname", "Aydin");
		requestBodymap.put("totalprice", 123);
		requestBodymap.put("depositpaid", true);
		requestBodymap.put("bookingdates", bookingDatesMap);
		requestBodymap.put("additionalneeds", "Wifi");
		
		Response response = given().
	              contentType(ContentType.JSON).
	              spec(spec01).
	              auth().
	              basic("admin", "password123").
	              body(requestBodymap).
	              when().
	              post("/booking");
		
		return response;
		
	}
}
