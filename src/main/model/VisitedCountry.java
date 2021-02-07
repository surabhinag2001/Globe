package model;

public class VisitedCountry {
    private String country;
    private String notesCountry;
    private String dateVisited; //stores date in mm-yyyy format
    //add a way to store images in future phases

    //EFFECTS: constructs a country object with associated name
    public VisitedCountry(String countryName, String notes, String date) {
        this.country = countryName;
        this.notesCountry = notes;
        this.dateVisited = date;
    }

    //EFFECTS: returns name of the country
    public String getVisitedCountryName() {
        return country;
    }

    //EFFECTS: returns notes about the country
    public String getNotesVisitedCountry() {
        return notesCountry;
    }

    //EFFECTS: returns date when country was visited
    public String getDateVisited() {
        return dateVisited;
    }

    //EFFECTS: returns month when country was visited
    public int getMonthVisited() {
        int month = Integer.parseInt(dateVisited.substring(0, 2));
        return month;
    }

    //EFFECTS: returns year when country was visited
    public int getYearVisited() {
        int year = Integer.parseInt(dateVisited.substring(3));
        return year;
    }

    //MODIFIES: this
    //EFFECTS: change country notes
    public void changeVisitedCountryNotes(String newNotes) {
        this.notesCountry = newNotes;
    }

    //MODIFIES: this
    //EFFECTS: change visit date
    public void changeDateVisited(String newDate) {
        this.dateVisited = newDate;
    }

    public boolean isAfter(String minDate) {
        int minMonth = Integer.parseInt(minDate.substring(0, 2));
        int maxMonth = Integer.parseInt(dateVisited.substring(0, 2));
        int minYear = Integer.parseInt(minDate.substring(3));
        int maxYear = Integer.parseInt(dateVisited.substring(3));
        if (minYear < maxYear) {
            return true;
        }
        if ((minYear == maxYear) && (minMonth <= maxMonth)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBefore(String maxDate) {
        int minMonth = Integer.parseInt(dateVisited.substring(0, 2));
        int maxMonth = Integer.parseInt(maxDate.substring(0, 2));
        int minYear = Integer.parseInt(dateVisited.substring(3));
        int maxYear = Integer.parseInt(maxDate.substring(3));
        if (minYear < maxYear) {
            return true;
        }
        if ((minYear == maxYear) && (minMonth <= maxMonth)) {
            return true;
        } else {
            return false;
        }
    }
}
