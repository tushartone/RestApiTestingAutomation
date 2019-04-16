package com.JIRARestAssuredAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class AddCommentThroughAPI {
	
	public Properties prop;
	@BeforeTest
	public void getProperties() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("F:\\Tushar Data\\API_Testing\\rest_api_workspace\\APITesting_Udemy\\src\\test\\java\\files\\Url.properties");
		 prop.load(fis);
		 
	}

	@Test
	public void addCommentToIssue() throws IOException
	{
		RestAssured.baseURI=prop.getProperty("JiraHost");
		
		Response resp =given().log().all().
		header("cookie", "JSESSIONID="+ReusableMethods.getJiraAPISessionID()).
		contentType(ContentType.JSON).
		body(Payload.createCommentPayloadData()).
		when().
		post(prop.getProperty("addComment")).
		then().statusCode(201).extract().response();
		
		String commentdata=resp.asString();
		JsonPath js = new JsonPath(commentdata);
		String commentID = js.get("id");
		System.out.println("New Creadted comment id :"+commentID);
		if(commentID!=null) {
		System.out.println("Successfully added the comment");
		}
		else
		{
			System.out.println("Incorrect id");
		}
		
		
	}
}
