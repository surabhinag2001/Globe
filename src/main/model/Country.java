package model;

//abstract class to represents a country having name and notes
public abstract class Country {
    protected String country;
    protected String notesCountry;

    protected Country(String name, String notes) {
        this.country = name;
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
