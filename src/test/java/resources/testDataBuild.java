package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class testDataBuild {
	
	/*
	 * create Add place Customized JSON as per requirement
	 */
	 public AddPlace addPlacePayload(String name,String address, String language) {
		RestAssured.baseURI="https://rahulshettyacademy.com";

		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 123 893 567");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);	
		return p;
	}
	 
	 /*
	  * Delete place_id
	  */
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
