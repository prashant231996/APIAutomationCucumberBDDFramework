Feature: Validation Place API's

@AddPlaceAPI
Scenario Outline: Verify if place is being added successfully using AddPlace API

     Given Add Place Payload with "<name>" "<language>" "<address>"
     When user calls "AddPlaceAPI" with "Post" http request
     Then the API call got success with status code 200
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And verify place_Id created maps to "<name>" using "GetPlaceAPI"
Examples:
	|name    |language|address                |
	|Prashant| Marathi| Shahapur, Ichalkaranji|
	#|Jyoti   | Kannada| Pipmle Gurav, Pune    |


@UpdatePlaceAPI
Scenario Outline: Verify if Update Functionality is working

        Given UpgdatePlace Payload with adress as "<address>"
        When user calls "UpdatePlaceAPI" with "Put" http request
        Then the API call got success with status code 200
        And "msg" in response body is "Address successfully updated"
        
Examples:
|address	         |
|Pimple Gurav, Pune|
      
@DeletePlaceAPI        	
Scenario: Verify if Delete Place Functionality is working 

      Given DeletePlace Payload
      When user calls "DeletePlaceAPI" with "Delete" http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"
      
   