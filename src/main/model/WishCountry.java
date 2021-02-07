package model;

public class WishCountry {
    private String country;
    private String notesCountry;

    //EFFECTS: constructs a country object with associated name
    public WishCountry(String countryName, String notes) {
        this.country = countryName;
        this.notesCountry = notes;
    }

    //EFFECTS: returns name of the country
    public String getCountryName() {
        return country;
    }

    //EFFECTS: returns notes about the country
    public String getNotesCountry() {
        return notesCountry;
    }

    //MODIFIES: this
    //EFFECTS: change country notes
    public void changeCountryNotes(String newNotes) {
        this.notesCountry = newNotes;
    }
}
