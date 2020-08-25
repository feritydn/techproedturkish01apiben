package techproedturkish01.techproedturkish01api;

import org.junit.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequest01 {
	
//Rest-Assured kullanarak API Testing yapacagiz	
	
	@Test
	public void getMethod01() {
		
		given().
		when().
		   get("https://restful-booker.herokuapp.com/booking").
		then().
		   assertThat().
		   statusCode(200);		
	}
	
	//GET ile aldigim data'yi console'da gormek istiyotum
	@Test
	public void getMethod02() {		
		Response response = given().
		                    when().
		                    get("https://restful-booker.herokuapp.com/booking/5");
		//Response body'i console'a yazdirmak icin response.prettyPrint(); kullanilir.
		response.prettyPrint();	
		
		//statuscode'u console'da gormek icin;  
		System.out.println("Status code: " + response.getStatusCode());
		
		//statusline'i console'da gormek icin;
		System.out.println("Status line: " + response.getStatusLine());
		
		//Response body'deki data'nin content(icerik) type
		System.out.println("Content Type 1. Yol: " + response.getContentType());
		// 2. yol
		System.out.println("Content Type 2. Yol: " + response.getHeader("Content-Type"));
		
		// Headers daki tüm bilgileri almak için
		System.out.println(response.getHeaders());
		
		//Headers den istenen spesific bir bilgiyi alalım
		System.out.println(response.getHeader("Date"));
		
		// Assertion yapalım
		
		//1) Status code 200
		// AssertThat kullanirsaniz hard assertion yapiyorsunuz demektir
		// yani ilk hatada code execution durur ve hata raporu verilir.
		// Ilk hatadan sonraki kodlar calistirilmaz.
		response.
		then().
		assertThat().
		statusLine("HTTP/1.1 200 OK").
		contentType("application/json; charset=utf-8").
		statusCode(200);
		 // http request method  bes tanedir postman da kullandik
		
		
	}
	
}

