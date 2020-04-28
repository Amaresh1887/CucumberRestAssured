Feature: Validating place Api
@AddPlace
Scenario Outline: Verify the place is sucessfully added or not
    Given Add place payload with "<name>" "<address>" "<language>"
    When user calls "AddPlaceApi" with "post" request 
    Then the api call is success with Status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceApi"

Examples:
     |name |address |language|
     |Amaresh|Bangalore|English|
  #   |Amaresh1|Bangalore1|English1|
  
  @DeletePlace
Scenario: verify Delete place is woring or Not
    Given DeletePlace Payload
    When user calls "DeletePlaceApi" with "post" request 
    Then the api call is success with Status code 200
	And "status" in response body is "OK"