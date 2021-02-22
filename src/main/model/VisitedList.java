package model;

import java.util.LinkedList;
import java.util.List;

//class to represent the list of countries visited
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
        if (!(searchCountry(countryName, date) > -1)) {
            myVisitedList.add(newCountry);
        }
    }

    //REQUIRES: countryName is present in the visited list
    //MODIFIES: this
    //EFFECTS: removes a country from the collection of visited countries
    public void removeCountry(String countryToRemove, String date) {
        myVisitedList.remove(searchCountry(countryToRemove, date));
    }

    //REQUIRES: countryName is present in the wishlist
    //EFFECTS: returns the index of the country to be searched in the wishlist
    public int searchCountry(String countryToSearch, String date) {
        int i;
        for (i = 0; i < myVisitedList.size(); i++) {
            if ((countryToSearch.equalsIgnoreCase(myVisitedList.get(i).getCountryName()))
                    && (date.equals(myVisitedList.get(i).getDateVisited()))) {
                return i;
            }
        }
        return -1;
    }
    //use a more efficient searching technique in further phases

    //EFFECTS: returns entire visited list
    public List<VisitedCountry> getMyVisitedList() {
        return myVisitedList;
    }

    //REQUIRES: dates are given in mm-yyyy format, date1 comes before date2
    //EFFECTS: returns countries visited within in a given time duration
    public List<VisitedCountry> filterDateWise(String minDate, String maxDate) {
        for (VisitedCountry visitedCountry : myVisitedList) {
            if (visitedCountry.isAfter(minDate) && visitedCountry.isBefore(maxDate)) {
                filterList.add(visitedCountry);
            }
        }
        return filterList;
    }


}
