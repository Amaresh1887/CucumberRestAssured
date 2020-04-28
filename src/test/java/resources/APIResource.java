package resources;

public enum APIResource {

	/*
	 * Create Enum to set and get APIResource type as per Requirement
	 */
	AddPlaceApi("/maps/api/place/add/json"),
	DeletePlaceApi("/maps/api/place/delete/json"),
	getPlaceApi("/maps/api/place/get/json");
	 private String resource;
	APIResource (String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
