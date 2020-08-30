package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class GetRequest11 extends TestBase {
	
	/*
	 GSON : 1) GSON, Json formatindai datalari Java objectlerine donusturur.(De-Serialization)
	        2) Java objectlerini Json formatindaki datalara donusturur. (Serialization)
	        
	 Note: GSON ile ayni isi yapan ObjectMapper class'da var.       
	        
	 */
	
	@Test
	public void get01() {
		
		Response response = given(). 
				                spec(spec03).
				                when().
				                get("/2");
		response.prettyPrint();
		
	   HashMap <String,String> map = response.as(HashMap.class); // De = serilization
	   System.out.println(map);
	   
	   System.out.println(map.keySet());
	   
	   System.out.println(map.values());
	   
	   // completed key sinin degerinin false oldugunu "verify" ediniz.
	   SoftAssert softAssert =  new SoftAssert();
	   softAssert.assertEquals(map.get("completed"),false, "completed istenilen gibi degil");
	   
	   // intler double olabilir boylece fail alabilirsin.
	   softAssert.assertEquals(map.get("userId"),1, "User Id istenilen gibi degil");
	   softAssert.assertEquals(map.get("id"),2, "Id istenilen gibi degil");
	   softAssert.assertEquals(map.get("title"), "quis ut nam facilis et officia qui", "Title istenilen gibi degil");
	   
	   
	   softAssert.assertAll();
	   
	  Gson gson = new Gson();
	  String jsonFromMap = gson.toJson(map);
	  System.out.println(jsonFromMap);
	}

}
