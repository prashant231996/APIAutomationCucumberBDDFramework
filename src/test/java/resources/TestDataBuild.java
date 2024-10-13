package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Book;
import pojo.Location;

public class TestDataBuild {
	
	   Book book=new Book();
	   Location loc=new Location();
	   
	public Book addPlacePayload(String name, String language, String address)
	{
		   loc.setLat(-38.839);
		   loc.setLng(33.646);
		   book.setLocation(loc);
		   book.setAccuracy(50);
		   book.setName(name);
		   book.setPhone_number("3363636");
		   book.setAddress(address);
		   List<String>types=new ArrayList<String>();
		   types.add("shoe park");
		   types.add("shop");
		   book.setTypes(types);
		   book.setWebsite("https://google.com");
		   book.setLanguage(language);
		   return book;
	}
	
	public String getDeletePayLoad(String placeId)
	{
		return "{\r\n" + 
		"    \"place_id\":\""+placeId+"\"\r\n" + 
		"}";
	}
	
	public String getUpdatePlacePayload(String placeId,String address)
	{
		return "{\r\n" + 
		"\"place_id\":\""+placeId+"\",\r\n" + 
		"\"address\":\""+address+"\",\r\n" + 
		"\"key\":\"qaclick123\"\r\n" + 
		"}";
	}

}
