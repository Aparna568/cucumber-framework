package stepDefinitions;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitions extends Utils {
	RequestSpecification reqspec;
	 RequestSpecification res;
	 Response response ;
	 String placeId;
			@Given("Add place payload with {string} {string} {string}")
			public void add_place_payload_with(String name, String language, String address) throws IOException {
			    // Write code here that turns the phrase above into concrete actions
		TestDataBuild data = new TestDataBuild();
		  reqspec= given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

	}
		@When("user calls {string} with {string} http request")
		public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceString = APIResources.valueOf(resource);
		System.out.println(resourceString.getResources());
		if(method.equalsIgnoreCase("post")) {
		 response = reqspec.when().post(resourceString.getResources());	
		 }
		else if(method.equalsIgnoreCase("get")) {
			 response = reqspec.when().get(resourceString.getResources());	
			 }
		if(method.equalsIgnoreCase("delete")) {
			 response = reqspec.when().delete(resourceString.getResources());	
			 }
		}
		
	@Then("the API call is successful with the status code {int}")
	public void the_api_call_is_successful_with_the_status_code(Integer int1) {
		response.then().extract().response();
		assertEquals(200, response.getStatusCode());

	}
	@Then("the {string} in response body is {string}")
	public void the_in_response_body_is(String keyValue,String expectedValue) {
		String responseString = response.asString();
		System.out.println((expectedValue));
		assertEquals(getJsonPath(response, keyValue),expectedValue);
	}


	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String string , String resource ) throws IOException {
		 placeId = getJsonPath(response,"place_id");
	    reqspec=given().spec(requestSpecification()).queryParam("place_id",placeId);
	    user_calls_with_http_request(resource,"Get");	
	}
}
