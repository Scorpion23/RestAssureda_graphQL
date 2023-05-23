package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name,String address,String language) {
		
		AddPlace p = new AddPlace();
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("https://rahulshettyacademy.com");
		p.setAccuracy(50);

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);

		Location l = new Location();
		l.setLat(-38.383494);      
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
		
		
	}
	
	
	public String deletePlacePayload(String placeID) {  // diffeent for differt payloads hence dynamic
		
		return "{\"place_id\":\""+placeID+"\"}";
	}
	
	
	

}
