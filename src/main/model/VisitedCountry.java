package model;

import org.json.JSONObject;

import java.time.LocalDate;


//class to represent a country that has been visited
public class VisitedCountry extends Country {
    private LocalDate dateVisited; //stores date in yyyy-mm-dd format
    //add a way to store images in future phases

    //EFFECTS: constructs a country object with associated name
    public VisitedCountry(String countryName, String notes, LocalDate date) {
        super(countryName, notes);
        this.dateVisited = date;
    }

    //EFFECTS: returns date when country was visited
    public LocalDate getDateVisited() {
        return dateVisited;
    }


    //MODIFIES: this
    //EFFECTS: change visit date
    public void changeDateVisited(LocalDate newDate) {
        this.dateVisited = newDate;
    }


    //EFFECT : returns true if this date is after min date
    public boolean isAfter(LocalDate minDate) {
        return dateVisited.isAfter(minDate);
    }


    //EFFECT : returns true if this date is before max date
    public boolean isBefore(LocalDate maxDate) {
        return dateVisited.isBefore(maxDate);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", super.country);
        json.put("notes", super.notesCountry);
        json.put("date",dateVisited);
        return json;
    }
}
