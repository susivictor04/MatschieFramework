Feature: Get weather for different city

  Scenario Outline: Get weather report
  	Given enabling logs 
	And Get the city name <city> 
	When get request is sent
	Then the return status code is 200
	And print the response time
	And print the response content
	And print the max temparature, sun set time and the wind speed
	Examples:
	|city|
	|delhi|
	|chennai|
	|Washington|
	|New York|
	|London|	