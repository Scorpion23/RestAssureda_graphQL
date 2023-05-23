package GraphQL;
import static io.restassured.RestAssured.given;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScripts {

	public static void main(String[] args) {
		
		
		String var = "1927";
		String queryseponse=
		given().log().all().header("Content-Type","application/json").body("{\"query\":\"query($episodeid:Int!){\\n  episode(episodeId: $episodeid) {\\n    id\\n    name\\n    air_date\\n    episode\\n    characters {\\n      id\\n      name\\n    }\\n    created\\n  }\\n}\\n\",\"variables\":{\"episodeid\":"+var+"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		JsonPath js = new JsonPath(queryseponse);
		String episodename = js.getString("data.episode.name");
		System.out.println("the output is " + episodename);
		
	Assert.assertEquals(episodename, "Cricket");
		
	
	//mutation
	
	String episode = "Cricket";	
	String mutationresponse =given().log().all().header("Content-Type","application/json").body("{\"query\":\"mutation($name:String!,$air_date:String!) {\\n  createEpisode(episode: {name: $name, air_date: $air_date, episode: \\\"1\\\"}) {\\n    id\\n  }\\n}\\n\",\"variables\":{\"name\":\""+episode+"\",\"air_date\":\"Jan 3oth\"}}")
	.when().post("https://rahulshettyacademy.com/gq/graphql")
	.then().extract().response().asString();
	
	JsonPath jsm = new JsonPath(mutationresponse);
	String episodeid = jsm.getString("data.createEpisode.id");// episode increments for every run
	System.out.println(episodeid);
	
	Assert.assertEquals(episodeid, "1938");
	
	}

}
