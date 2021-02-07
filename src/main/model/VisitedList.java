package model;

import java.util.LinkedList;
import java.util.List;

public class VisitedList {
    private List<VisitedCountry> myVisitedList;
    private List<VisitedCountry> filterList;

    //EFFECTS: constructs an empty collection of countries I have visited
    public VisitedList() {
        myVisitedList = new LinkedList<>();
        filterList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new country to the collection of wishlist countries
    public void addCountry(String countryName, String notes, String date) {
        VisitedCountry newCountry = new VisitedCountry(countryName, notes, date);
        myVisitedList.add(newCountry);
    }

    //REQUIRES: countryName is present in the visited list
    //MODIFIES: this
    //EFFECTS: removes a country from the collection of visited countries
    public void removeCountry(String countryToRemove) {
        myVisitedList.remove(searchCountry(countryToRemove));
    }

    //REQUIRES: countryName is present in the wishlist
    //EFFECTS: returns the index of the country to be searched in the wishlist
    public int searchCountry(String countryToSearch) {
        int i;
        for (i = 0; i < myVisitedList.size(); i++) {
            if (countryToSearch.equalsIgnoreCase(myVisitedList.get(i).getVisitedCountryName())) {
                break;
            }
        }
        return i;
    }
    //use a more efficient searching technique in further phases

    //EFFECTS: returns entire visited list
    public List<VisitedCountry> getMyVisitedList() {
        return myVisitedList;
    }

    //REQUIRES: dates are given in mm-yyyy format, date1 comes before date2
    //EFFECTS: returns countries visited within in a given time duration
    public List<VisitedCountry> filterDateWise(String minDate, String maxDate) {
        for (int i = 0; i < myVisitedList.size(); i++) {
            if ((myVisitedList.get(i)).isAfter(minDate) && myVisitedList.get(i).isBefore(maxDate)) {
                filterList.add(myVisitedList.get(i));
            }
        }
        return filterList;
    }


}
