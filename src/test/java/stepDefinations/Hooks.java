package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void runBeforeDeletePlace() throws IOException {
		//This piece of code will run if place id is null
		
		stepDefination sdf= new stepDefination();
		if(stepDefination.place_id==null)
		{
		sdf.add_place_payload_with("Amaresh", "Bangalore", "Odia");
		sdf.user_calls_with_request("AddPlaceApi","POST");
		sdf.verify_place_id_created_maps_to_using("Amaresh", "getPlaceApi");
		}
	}

}
