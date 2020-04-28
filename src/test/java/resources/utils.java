package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class utils {
	public static RequestSpecification rs;
	public static ResponseSpecification ress;
	/*
	 * Create Request Specification for Global Use
	 */
	public RequestSpecification requestSpecification() throws IOException {
		
		if(rs==null) {
		PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
		 rs= new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUri"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		 return rs;
		}
		 return rs;
		
		
	}
	
	/*
	 * To Return Global Values from properties File
	 */
	public static String getGlobalValues(String key) throws IOException {
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream("F:\\Testing\\AutomationDemo\\src\\test\\java\\resources\\global.properties");
	    prop.load(fis);
	    return prop.getProperty(key);
	   
	    
	}
	
	/*
	 * Create Response Specification for Global Use
	 */
	public ResponseSpecification responseSpecification() {
		ress= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return ress;
	}
	/*
	 * Get Key value from JSON
	 */
	
	public String getJsonPath(Response response,String key) {
		
		JsonPath js= new JsonPath(response.asString());
		return js.get(key).toString();
		
	}

}
