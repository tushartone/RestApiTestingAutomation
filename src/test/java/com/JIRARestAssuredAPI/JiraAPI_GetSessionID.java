// Here we are getting the Autentication Jira session ID

package com.JIRARestAssuredAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAPI_GetSessionID {

	Properties prop;
	@BeforeTest
	public void getData() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("F:\\Tushar Data\\API_Testing\\rest_api_workspace\\APITesting_Udemy\\src\\test\\java\\files\\Url.properties");
		prop.load(fis);
	}
	
	@Test
	public void jiraAPISession()
	{
		
		RestAssured.baseURI=prop.getProperty("JiraHost");
		
		Response resp = given().
							contentType(ContentType.JSON).
							body("{ \r\n" + 
									"	\"username\": \"tushartone\",\r\n" + 
									"	\"password\": \"Tushar@1993\"\r\n" + 
									"}").
							when().
							post(prop.getProperty("postURL")).
							then().statusCode(200).
							extract().response();
		String respData=resp.asString();
		
		JsonPath jsonPath = new JsonPath(respData);
		String sessionID = jsonPath.get("session.value");
		System.out.println("Session id is"+sessionID);
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
