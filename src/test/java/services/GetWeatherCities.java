package services;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetWeatherCities {

	@DataProvider(name="Cities", parallel=true)
	public String[] getcities(){
		String[] data = new String[3];
		data[0]="chennai";
		data[1]="London";
		data[2]="washington";
		return data;
	}

	@Test(dataProvider="Cities")
	public void getWeathercity(String cityName ) {

		RestAssured.baseURI= "http://api.openweathermap.org/data/2.5/weather";

		Response response = RestAssured
				.given()
				.log().all()
				.queryParam("q", "new york")
				.queryParam("appid", "c397236a177654c953b206cf4304b40f")
				.accept(ContentType.JSON)
				.get();

		response.prettyPrint();
		

		JsonPath jsonPath = response.jsonPath();
		System.out.println("Maximum Temperature is: " + jsonPath.get("main.temp_max"));
		System.out.println("Sunset timee is: " + jsonPath.get("sys.sunset"));
		System.out.println("Sunset timee is: " + jsonPath.get("wind.speed"));


	}

}
