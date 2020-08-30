package techproedturkish01.techproedturkish01api;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class GetRequest12 extends TestBase{

	@Test
	public void get01() {
		
		Response response = given().
							spec(spec03).
								when().
							get();
		response.prettyPrint();
		
		List<Map<String, Object>> listOfMaps = response.as(ArrayList.class);
		
		System.out.println(listOfMaps);
		System.out.println(listOfMaps.get(0));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(listOfMaps.size()==200, "Id sayisi 200 degil");
		// 121. elemanin comleter degerinin true oldugunu verify ediniz
		softAssert.assertEquals(listOfMaps.get(120).get("completed"), true,"Istenen gibi degil");
		softAssert.assertEquals(listOfMaps.get(listOfMaps.size()-2).get("title"), "numquam repellendus a magnam");
		softAssert.assertAll();
		
		// java objectlerini JSon formatina cevirme. 
		// Aslinda biz bunu cok kullanmayiz.
		
		Gson gson = new Gson();
		String jsonFromList = gson.toJson(listOfMaps);
		System.out.println(jsonFromList);
		
		
	
	}
}