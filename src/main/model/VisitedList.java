package model;

import exceptions.*;
import org.json.JSONArray;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

//class to represent the list of countries visited
public class VisitedList {
    private final List<VisitedCountry> myVisitedList;
    private final List<VisitedCountry> filterList;

    //EFFECTS: constructs an empty collection of countries I have visited
    public VisitedList() {
        myVisitedList = new LinkedList<>();
        filterList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new country to the collection of wishlist countries
    public void addCountry(String countryName, String notes, LocalDate date) throws InvalidCountryException,
            FutureDateException, CountryAlreadyPresentException {
        AllCountries obj = new AllCountries();
        boolean isCountryValid = obj.getAllCountries().stream().anyMatch(countryName::equalsIgnoreCase);
        if (!isCountryValid) {
            throw new InvalidCountryException();
        }
        LocalDate now = LocalDate.now();

        if (date.isAfter(now)) {
            throw new FutureDateException();
        }
        VisitedCountry newCountry = new VisitedCountry(countryName, notes, date);
        if (!(searchCountry(countryName, date) > -1)) {
            myVisitedList.add(newCountry);
        } else {
            throw new CountryAlreadyPresentException();
        }
    }


    //MODIFIES: this
    //EFFECTS: removes a country from the collection of visited countries
    //         if countryName and corresponding date is  not present in the list of visited countries
    //         throws CountryNotPresentInListException
    public void removeCountry(String countryToRemove, LocalDate date) throws CountryNotPresentInListException {
        int n = searchCountry(countryToRemove, date);
        if (n < 0) {
            throw new CountryNotPresentInListException();
        }
        myVisitedList.remove(n);
    }

    //TODO: add test for this method
    //MODIFIES: this
    //EFFECTS: removes a country from the collection of visited countries at index n if within bounds
    public void removeVisitByIndex(int n) {
        if (n >= 0 && n <= myVisitedList.size()) {
            myVisitedList.remove(n);
        }
    }



    //EFFECTS: returns the index of the country to be searched in the wishlist
    //         if country not present, returns -1
    public int searchCountry(String countryToSearch, LocalDate date) {
        int i;
        for (i = 0; i < myVisitedList.size(); i++) {
            if ((countryToSearch.equalsIgnoreCase(myVisitedList.get(i).getCountryName()))
                    && (date.equals(myVisitedList.get(i).getDateVisited()))) {
                return i;
            }
        }
        return -1;
    }


    //EFFECTS: returns entire visited list
    public List<VisitedCountry> getMyVisitedList() {
        return myVisitedList;
    }

    //REQUIRES: dates are given in yyyy-mm-dd format
    //EFFECTS: returns countries visited within in a given time duration
    //        if minDate is after maxDate MaxDateBeforeMinDateException is thrown
    public List<VisitedCountry> filterDateWise(
            LocalDate minDate, LocalDate maxDate) throws MaxDateBeforeMinDateException {
        if (!maxDate.isAfter(minDate)) {
            throw new MaxDateBeforeMinDateException();
        }
        for (VisitedCountry visitedCountry : myVisitedList) {
            if (visitedCountry.isAfter(minDate) && visitedCountry.isBefore(maxDate)) {
                filterList.add(visitedCountry);
            }
        }
        return filterList;
    }

    // EFFECTS: returns wishlist countries as a JSON array
    public JSONArray visitedListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (VisitedCountry v : myVisitedList) {
            jsonArray.put(v.toJson());
        }

        return jsonArray;
    }


}
