package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinitions m = new StepDefinitions();
		if(StepDefinitions.place==null)
		{
		m.add_place_payload_with("shetty", "French", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using_get_place_api("shetty", "getPlaceAPI");
		}
		}
	
@After("@AddPlace")
	public void afterScenario() throws IOException {
		StepDefinitions n = new StepDefinitions();
		n.delete_place_payload();

		}
		
	

}

