package techproedturkish01.techproedturkish01api;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.junit.Test;

import io.restassured.response.Response;
import utilities.JsonUtil;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class ObjectMapperTestWithMap extends TestBase {
	
	@Test
	public void javaToJson() {
		HashMap <Integer, String> map = new HashMap<>();
		
		map.put(101, "Ali");
		map.put(102, "Can");
		map.put(103, "Remziye");
		// Bookingdates java map objectini json a cevirdik ===> Serialization
		String jsonFronMap = JsonUtil.convertjavaToJson(map);
		System.out.println(jsonFronMap);
		// {"101":"Ali","102":"Can","103":"Remziye"}
	}

	@Test
	
	public void jsonToJava() {
		Response response = given().
				              spec(spec01).
				              when().
				              get("booking/3");
		response.prettyPrint();
		
		// API dan gelen json formatindan gelen datayi map a cevirdim. 
		// === De serialization
		Map <String, Object> jsonToMapApi =  JsonUtil.convertJsonTojava(response.asString(), Map.class);
		System.out.println(jsonToMapApi);
		
		/*
		 1) Api den gelen Json formatindaki datayi map'e cevirdim.
		 2) TestCase de bana verilen datayi map e cevirecegim.
		 3) Bu iki map'daki datalari karsilastirarak verification yapacagim
		 
		 */
		
		Map <String, Object> jsonToMapTestCase = new HashMap<>();
		jsonToMapTestCase.put("firstname", "Sally");
		jsonToMapTestCase.put("lastname", "Brown");
		jsonToMapTestCase.put("totalprice", 368);
		jsonToMapTestCase.put("depositpaid", true);
		
		response.
		       then().
		       assertThat().
		       statusCode(200);
		assertEquals(jsonToMapTestCase.get("firstname"),jsonToMapApi.get("firstname"));
		assertEquals(jsonToMapTestCase.get("lastname"),jsonToMapApi.get("lastname"));
		assertEquals(jsonToMapTestCase.get("totalprice"),jsonToMapApi.get("totalprice"));
		assertEquals(jsonToMapTestCase.get("depositpaid"),jsonToMapApi.get("depositpaid"));
	}
}
