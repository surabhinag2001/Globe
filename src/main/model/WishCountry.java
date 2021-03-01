package model;

import org.json.JSONObject;
import persistence.Writable;

//class to represent a country that user wishes to visit
public class WishCountry extends Country {

    //EFFECTS: constructs a country object with associated name
    public WishCountry(String countryName, String notes) {
        super(countryName, notes);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", super.country);
        json.put("notes", super.notesCountry);
        return json;
    }
}
