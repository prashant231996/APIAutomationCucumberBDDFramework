package StepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.EndToEndQuoteFragmentEscaper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Book;
import pojo.Location;
import resources.EndPoints;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{

	   
	   RequestSpecification  requestSpec;
	   ResponseSpecification responseSpec;
	   Response res;
	   TestDataBuild testData=new TestDataBuild();
	   EndPoints endPoints;
	   static String place_id;
	
	   @Given("Add Place Payload with {string} {string} {string}")
	   public void add_place_payload_with(String name, String language, String address) throws FileNotFoundException {
		   requestSpec=given().spec(requestSpecifications()).body(testData.addPlacePayload(name, language, address));
	   }
	   @When("user calls {string} with {string} http request")
	   public void user_calls_with_http_request(String resources, String httpMethod) {
		   //Endpoints.valueOf() will invoke the constructor of Endpoints class.
		   endPoints=EndPoints.valueOf(resources);
		if(httpMethod.equalsIgnoreCase("post"))
		{
		res=requestSpec.when().
				   post(endPoints.getResources());
		}
		else if(httpMethod.equalsIgnoreCase("get"))
		{
			res=requestSpec.when().
					   get(endPoints.getResources());
		}
		else if(httpMethod.equalsIgnoreCase("put"))
		{
			res=requestSpec.when().
					put(endPoints.getResources());
		}
		else if(httpMethod.equalsIgnoreCase("delete"))
		{
			res=requestSpec.when().
					delete(endPoints.getResources());
		}
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		  //Validating response 
		   Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String attrName, String expectedVal) {
		 //Validating Scope 
		   Assert.assertEquals(res.jsonPath().get(attrName).toString(),expectedVal);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String extepectedName, String resources) throws FileNotFoundException { 
		place_id=getJsonPath(res, "place_id");
		requestSpec=given().spec(requestSpecifications()).queryParam("place_id", place_id);
		user_calls_with_http_request(resources, "GET"); 
		String actualName=getJsonPath(res, "name");
		//Asserting name present in responce is equal to name passed in add body
		Assert.assertEquals(actualName, extepectedName);   
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws FileNotFoundException {
		requestSpec=given().spec(requestSpecifications()).body(testData.getDeletePayLoad(place_id));
	}
	
	@Given("UpgdatePlace Payload with adress as {string}")
	public void upgdate_place_payload_with_adress_as(String address) throws FileNotFoundException {
		requestSpec=given().spec(requestSpecifications()).body(testData.getUpdatePlacePayload(place_id,address));
	}


}
