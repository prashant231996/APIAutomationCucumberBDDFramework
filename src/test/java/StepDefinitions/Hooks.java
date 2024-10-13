package StepDefinitions;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws FileNotFoundException
	{
		//Execute this code only if place id is null, 
		//Write a code that will give you place id
		StepDefination stepDef=new StepDefination();
		if(StepDefination.place_id==null)
		{
		stepDef.add_place_payload_with("Prashant", "Marathi", "Yadrav");
		stepDef.user_calls_with_http_request("AddPlaceAPI", "post");
		stepDef.verify_place_id_created_maps_to_using("Prashant", "GetPlaceAPI");
		}
		
	}

}
