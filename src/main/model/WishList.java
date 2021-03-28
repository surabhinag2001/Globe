package model;

import exceptions.CountryAlreadyPresentException;
import exceptions.CountryNotPresentInListException;
import exceptions.InvalidCountryException;
import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;

//class to represent the list of countries user wishes to visit
public class WishList {

    private final List<WishCountry> myWishList;

    //EFFECTS: constructs an empty collection of countries I wish to visit
    public WishList() {
        myWishList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new country to the collection of wishlist countries
    public void addCountry(String countryName, String notes) throws InvalidCountryException,
            CountryAlreadyPresentException {
        AllCountries obj = new AllCountries();
        boolean isCountryValid = obj.getAllCountries().stream().anyMatch(countryName::equalsIgnoreCase);
        if (!isCountryValid) {
            throw new InvalidCountryException();
        }
        WishCountry newCountry = new WishCountry(countryName, notes);
        //checking if the country has been added to the wishlist before
        if (!(searchCountry(countryName) > -1)) {
            myWishList.add(newCountry);
        } else {
            throw new CountryAlreadyPresentException();
        }
    }
    


    //MODIFIES: this
    //EFFECTS: removes a country from the collection of wishlist countries
    //         if countryName and corresponding date is  not present in wishlist countries
    //         throws CountryNotPresentInListException
    public void removeCountry(String countryToRemove) throws CountryNotPresentInListException {
        int n = searchCountry(countryToRemove);
        if (n < 0) {
            throw new CountryNotPresentInListException();
        }
        myWishList.remove(searchCountry(countryToRemove));
    }

    //REQUIRED: n is within bounds
    //MODIFIES: this
    //EFFECTS: removes a country from the collection of wishlist countries at index n if present
    public void removeCountryByIndex(int n) {
        if (n >= 0 && n <= myWishList.size()) {
            myWishList.remove(n);
        }
    }


    //EFFECTS: returns the index of the country to be searched in the wishlist
    //         if country not present, returns -1
    public int searchCountry(String countryToSearch) {
        int i;
        for (i = 0; i < myWishList.size(); i++) {
            if (countryToSearch.equalsIgnoreCase(myWishList.get(i).getCountryName())) {
                return i;
            }
        }
        return -1;
    } //use a more efficient searching technique in further phases

    //EFFECTS: returns entire wishlist
    public List<WishCountry> getMyWishList() {
        return myWishList;
    }

    // EFFECTS: returns wishlist countries as a JSON array
    public JSONArray wishlistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WishCountry w : myWishList) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
