Feature: Validating place APIs
Scenario Outline: Verify if the place is successfully added while using AddPlaceAPI
Given Add place payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "get" http request
Then the API call is successful with the status code 200
And the "status" in response body is "OK"
And the "scope" in response body is "APP"
And verify place_ID created maps to "<name>" using "GetPlaceAPI"

Examples:
|name |language|address			  |
|AAA  |English |World Cross Centre|
#|BBB  |Spanish |Sea Cross Centre  |