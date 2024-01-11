package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification  req;
public RequestSpecification requestSpecification() throws IOException {
	if(req == null) {
	PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	req = new RequestSpecBuilder().setBaseUri(globalProperties("baseUri")).addQueryParam("Key","qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.addHeader("Content-Type","application/json").build();
	return req;
	}
	return req;
}

public static String globalProperties(String key) throws IOException {
	Properties prop = new Properties();
	FileInputStream fil = new FileInputStream("/Users/shishir/eclipse-workspace/APIFramework/src/test/java/resources/global.properties");
	prop.load(fil);
	return(prop.getProperty(key));
}
public String getJsonPath(Response response, String key) {
	String resp = response.toString();
	JsonPath js = new JsonPath(resp);
	return js.get(key).toString();
	
}
}
