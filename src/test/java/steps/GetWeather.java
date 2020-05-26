package steps;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class GetWeather {

	RequestSpecification weatherRequest;
	Response weatherResponse;


	@Given("enabling logs")
	public void enableLogs() {
		RestAssured.baseURI= "http://api.openweathermap.org/data/2.5/weather";
		weatherRequest = RestAssured
				.given()
				.log().all();

	}

	@And("Get the city name (.*)$")
	public void getCity(String city) {

		weatherRequest =weatherRequest
				.when()
				.queryParam("q", city)
				.queryParam("appid", "c397236a177654c953b206cf4304b40f");
	}

	@When("get request is sent")
	public void getRequest() {

		weatherResponse =weatherRequest.accept(ContentType.JSON)
				.get();

	}

	@Then("the return status code is {int}")
	public void verifyStatuscode(int statusCd) {
		weatherResponse.then().statusCode(statusCd);
		if(weatherResponse.statusCode() == statusCd) {
			System.out.println("The status code "+ weatherResponse.statusCode()+" matches the expected code");

		}else {
			System.out.println("The status code "+ weatherResponse.statusCode()+" does not match the expected code");

		}

	}


	@And("print the response time")
	public void verifyResponseTime() {

		System.out.println("Response time : " + weatherResponse.getTime());

		if(weatherResponse.getTime() <= 1000) {
			System.out.println("The time taken " + weatherResponse.getTime() +" with in the expected time");

		}else {
			System.out.println("The time taken " + weatherResponse.getTime() +" is greater than expected SLA time 1000 " );
		}

	}

	@And("print the response content")
	public void printResponse() {
		weatherResponse.prettyPrint();

	}


	@And("print the max temparature, sun set time and the wind speed")
	public void printValues() {
		JsonPath jsonPath = weatherResponse.jsonPath();
		System.out.println("Maximum Temperature is: " + jsonPath.get("main.temp_max"));
		System.out.println("Sunset timee is: " + jsonPath.get("sys.sunset"));
		System.out.println("Sunset timee is: " + jsonPath.get("wind.speed"));

	}



}
