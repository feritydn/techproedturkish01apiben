package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequest09 extends TestBase {
	
	@Test
	public void get01() {
		Response response = given().
				            spec(spec02).
				            when().
				            get();
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		
		// Tum emloyee isimlerini console yazdiriniz
		
		System.out.println(json.getList("data.employee_name"));
		
		// Ikinci iscinin ismini Garrett Winters oldugunun verify (soft assertion)  ediniz
		assertEquals("isim istenen gibi degil", "Garrett Winters", json.getString("data[1].employee_name"));
		/*
		 * Soft Assertion olusturmak icin 3 adim atilir
		 * 1) SoftAssert class'indan bir obje (softAssert) olustur
		 * 2) Objeyi kullanarak assertion yap
		 * 3) softAssert.assertAll(). yapmazsak surekli pass aliriz
		 */
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("data[1].employee_name"), "Garrett Winters", "isim istenen gibi degil");
		
		
		// Iscilerin arasindan "Herrod Chandler" oldugunu verify ediniz
		
		softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"),"Aradiginiz isme ulasilamadi");
		
		// 24 tane isci oldugunu verifay ediniz
		softAssert.assertSame(json.getList("data.id").size(), 24, "24 isci yok");
		
		// 7. iscinin maasinin 137500 oldugunu verify ediniz
		softAssert.assertEquals(json.getString("data[6].employee_salary"), "137500", "maas esit degil");
		
		softAssert.assertAll();
	}

}
