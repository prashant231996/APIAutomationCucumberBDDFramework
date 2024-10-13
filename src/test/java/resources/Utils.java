package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public String Base_Url="https://rahulshettyacademy.com";
	public static Properties prop;
	
	public RequestSpecification requestSpecifications() throws FileNotFoundException
	{
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("Logging.txt"));
		
		req=new RequestSpecBuilder().setBaseUri(readProperty("Base_Url")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return req;
		}
		return req;
	}
	
	public static String readProperty(String key)
	{
		try
		{
			File propFilePath=new File("src/test/resources/config.properties");
			FileInputStream fis=new FileInputStream(propFilePath);
			prop=new Properties();
			prop.load(fis);
			return prop.getProperty(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String getJsonPath(Response res, String key)
	{
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		return js.get(key).toString();
	}
	
	

}
