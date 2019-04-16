package com.JIRARestAssuredAPI;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateJiraThroughAPI {
	Properties prop;
	
	@BeforeTest
	public void propFiles() throws IOException
	{
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("F:\\Tushar Data\\API_Testing\\rest_api_workspace\\APITesting_Udemy\\src\\test\\java\\files\\Url.properties");
		prop.load(fis);
	}
	
	@Test
		public void newCreateJira() throws IOException
		{
			//Creating the issue/defect/jira
		RestAssured.baseURI=prop.getProperty("JiraHost");
		
		Response resp = given().log().all().
						header("cookie", "JSESSIONID="+ReusableMethods.getJiraAPISessionID()).
						contentType(ContentType.JSON).
						body(Payload.createJiraPayloadData()).
						when().
						post(prop.getProperty("createIssue")).
						then().
						extract().response();
		
		String issueDetails=resp.asString();
		System.out.println(issueDetails);
		JsonPath js = new JsonPath(issueDetails);
		System.out.println("Getting Jira-ID/Key after creating the new jira====>"+js.get("key"));
	
		}
	}

