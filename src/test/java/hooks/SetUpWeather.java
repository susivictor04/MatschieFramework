package hooks;
import java.io.IOException;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class SetUpWeather {
	@Before
	public void setUpGetWeather() throws  IOException{ 

		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";

	}

	@After
	public void tearDown(){ 
	}
}



