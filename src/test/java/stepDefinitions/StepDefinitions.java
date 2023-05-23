package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {

	RequestSpecification response;
	ResponseSpecification resspec;
	Response response1;
	TestDataBuild data = new TestDataBuild();
	static String place;
	JsonPath js;
	// Demo demo = new Demo();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String string, String string2, String string3) throws IOException {

		response = given().spec(requestSpecification()).body(data.addPlacePayload(string, string2, string3));
		System.out.println("AddPlaceAPI started");
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		APIResources resourceAPI = APIResources.valueOf(resource);

		if (method.equalsIgnoreCase("POST")) {

			response1 = response.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();
		}

		else if (method.equalsIgnoreCase("GET")) {

			response1 = response.when().get(resourceAPI.getResource()).then().spec(resspec).extract().response();

		}

		// response1 =
		// response.when().post(demo.getAddPlaceAPI()).then().spec(resspec).extract().response();
		// make resource dynamic
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response1.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void status_in_response_body_is_ok(String keyValue, String Expectedvalue) {

		String resp = response1.asString();
		js = new JsonPath(resp);

		assertEquals(getJsonpath(response1, keyValue), Expectedvalue);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using_get_place_api(String expectedname, String resource)
			throws IOException {
		// requestSpecification will fetch baseurl and query parameters
		place = getJsonpath(response1, "place_id");
		response = given().spec(requestSpecification()).queryParam("place_id", place);
		user_calls_with_http_request(resource, "GET");// important

		String actualname = getJsonpath(response1, "name");

		assertEquals(actualname, expectedname);
	}

	
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		response=  given().spec(requestSpecification()).body(data.deletePlacePayload(place));
		
		System.out.println("DeletePlaceAPI started");
	}
	
	
}
