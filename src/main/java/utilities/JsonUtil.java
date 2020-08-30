package utilities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper mapper;
	
	static {
		
		mapper = new ObjectMapper();
	}
	
	
	// Java b=objectini Json a ceviren method ==> Serialization
	
	public static String convertjavaToJson(Object object) {
		String jsonResult= "";
		try {
			jsonResult = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			System.out.println("Java objesini Json'a cevirirken exception olustu"+ e.getMessage());
			
		} catch (JsonMappingException e) {
			
			System.out.println("Java objesini Json'a cevirirken exception olustu"+ e.getMessage());
		} catch (IOException e) {
			
			System.out.println("Java objesini Json'a cevirirken exception olustu"+ e.getMessage());
		}
		
		return jsonResult;
	}
	
	
	// Json Formatindaki data'yi java objectine ceviren method ===> De-Serialization
	
	public static <T> T convertJsonTojava(String json, Class<T> cls) {
		// Bu method result hangi data tipinde ise onu dondurur. Adi da jenerik methodtur.
		T javaResult = null; 
		try {
			javaResult = mapper.readValue(json, cls);
		} catch (JsonParseException e) {
			System.out.println("Json'i java'yaa cevirirken exception olustu"+ e.getMessage());

		} catch (JsonMappingException e) {
			System.out.println("Json'i java'yaa cevirirken exception olustu"+ e.getMessage());
		} catch (IOException e) {
			System.out.println("Json'i java'yaa cevirirken exception olustu"+ e.getMessage());
		}
		return javaResult;
	}
	
	
	
	
	
	
	
}
