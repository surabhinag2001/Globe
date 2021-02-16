package model;

import java.util.LinkedList;
import java.util.List;

//class to represent the list of countries user wishes to visit
public class WishList {

    private List<WishCountry> myWishList;

    //EFFECTS: constructs an empty collection of countries I wish to visit
    public WishList() {
        myWishList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new country to the collection of wishlist countries
    public void addCountry(String countryName, String notes) {
        WishCountry newCountry = new WishCountry(countryName, notes);
        myWishList.add(newCountry);
    }

    //REQUIRES: countryName is present in the wishlist
    //MODIFIES: this
    //EFFECTS: removes a country from the collection of wishlist countries
    public void removeCountry(String countryToRemove) {
        myWishList.remove(searchCountry(countryToRemove));
    }


    //REQUIRES: countryName is present in the wishlist
    //EFFECTS: returns the index of the country to be searched in the wishlist
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
}
