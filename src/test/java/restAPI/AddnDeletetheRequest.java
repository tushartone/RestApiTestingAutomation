//Create the place id and delete the newly created place id

package restAPI;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddnDeletetheRequest {
	Properties prop;
	@BeforeTest
	public void getData() throws IOException
	{
		 prop = new Properties();
		FileInputStream fis = new FileInputStream("F:\\Tushar Data\\API_Testing\\rest_api_workspace\\APITesting_Udemy\\src\\test\\java\\files\\Url.properties");
		prop.load(fis);
	}
	
	@Test
	public void addNdeleteID()
	{
		
		//Task1:Grab the response
		RestAssured.baseURI=prop.getProperty("host");
		Response resp = given().
		body(Payload.payloadData()).
		when().contentType(ContentType.JSON).
		post(prop.getProperty("postData")).
		then().log().all().assertThat().statusCode(201).and().
		extract().response();
		
		String responseString = resp.asString();
		System.out.println("Rsponse is :"+responseString);
		
		//Task2:getting the ID
		//To convert the response into the JSON
		JsonPath jsonPath = new JsonPath(responseString);
		String jsonId = jsonPath.get("id");
		System.out.println("New created id is "+jsonId);
		
		//Task2:Place this test id in delete request .i.e DELETE request
		Response resp1 = given().
			//body("{\"id\":\""+jsonId+"\"}").
			body(jsonId).
			when().contentType(ContentType.JSON).
			delete("/posts");
		
		System.out.println("Status code "+resp1.getStatusCode());
		
		
		
		
		
	}	

}
