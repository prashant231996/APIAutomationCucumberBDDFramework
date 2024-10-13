package resources;

public enum EndPoints {
	
	//ENUM is a special classs which contains set of variables/constants/methods
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resources;

	EndPoints(String resources) {
		this.resources=resources;	
	}
	
	public String getResources()
	{
		return this.resources;
	}

}
