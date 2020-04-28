package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResource;
import resources.testDataBuild;
import resources.utils;

public class stepDefination extends utils {
	Response response;
	RequestSpecification reqs;
	ResponseSpecification ress;
	JsonPath js;
	static String place_id;
	testDataBuild tdb= new testDataBuild();
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
	   
		     reqs= given().spec(requestSpecification()).body(tdb.addPlacePayload(name,address,language));
	}

	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String resource, String httpmethod) {
		
		APIResource apir= APIResource.valueOf(resource);
		System.out.println(apir.getResource());
		if(httpmethod.equalsIgnoreCase("POST")) {
     		response=reqs.when().post(apir.getResource()).
		 then().spec(responseSpecification()).extract().response();
		}
		if(httpmethod.equalsIgnoreCase("GET")) {
     		response=reqs.when().get(apir.getResource()).
		 then().spec(responseSpecification()).extract().response();
		}
		
	}

	@Then("the api call is success with Status code {int}")
	public void the_api_call_is_success_with_Status_code(Integer int1) {
	   assertEquals(response.statusCode(),200);
	  
	}

	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Evalue) {
		
		assertEquals(getJsonPath(response, key).toString(),Evalue);
		
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String Ename, String resource) throws IOException {
		place_id=getJsonPath(response, "place_id");
		reqs=given().spec(requestSpecification()).param("place_id", place_id);
		user_calls_with_request(resource, "GET");
	
		assertEquals(getJsonPath(response, "name").toString(),Ename);
	   
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		
	   reqs=given().spec(requestSpecification()).body(tdb.deletePlacePayload(place_id));
	   System.out.println("success");
	   
	}
	
}
