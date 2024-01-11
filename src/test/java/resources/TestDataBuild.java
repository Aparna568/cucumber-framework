package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GeoLocation;
import pojo.Location;

public class TestDataBuild {

	public GeoLocation addPlacePayload(String name, String language, String address) {
		
		 GeoLocation g = new GeoLocation();
		 Location l = new Location();
		 l.setLat(-38.383494);
		 l.setLng(33.427362);
		 g.setLocation(l);	
		 g.setAccuracy(50);
		 g.setName(name);
		 g.setPhone_number("(+91) 983 893 3937");
		 g.setAddress(address);
		 List<String> myList = new ArrayList<String>();
		 myList.add("Shoe Park");
		 myList.add("Shop");
		 g.setTypes(myList);
		 g.setWebsite("http://google.com");
		 g.setLanguage(language);
		 return(g);
	}
}
