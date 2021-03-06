package model;

import exceptions.CountryNotPresentInListException;
import exceptions.InvalidCountryException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//class to test functions defined in WishList class
class WishListTest {

    @Test
    public void testAddCountry() {
        WishList myWishList = new WishList();
        assertEquals(0, myWishList.getMyWishList().size());
        try {
            myWishList.addCountry("India", "visit forts");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(1, myWishList.getMyWishList().size());
        List<WishCountry> res1 = new LinkedList<>();
        WishCountry test1 = new WishCountry("India", "visit forts");
        WishCountry test2 = new WishCountry("Germany", "visit castle");
        res1.add(test1);
        assertEquals(res1.get(0).getCountryName(), myWishList.getMyWishList().get(0).getCountryName());
        assertEquals(res1.get(0).getNotesCountry(), myWishList.getMyWishList().get(0).getNotesCountry());
        try {
            myWishList.addCountry("Germany", "visit castle");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        res1.add(test2);
        assertEquals(2, myWishList.getMyWishList().size());
        assertEquals(res1.get(1).getCountryName(), myWishList.getMyWishList().get(1).getCountryName());
        assertEquals(res1.get(1).getNotesCountry(), myWishList.getMyWishList().get(1).getNotesCountry());

        //adding an invalid country
        try {
            myWishList.addCountry("Bharat", "visit castle");
        } catch (InvalidCountryException e) {
            //InvalidCountryException caught
        }
    }

    @Test
    public void testRemoveCountry() {
        WishList myWishList = new WishList();
        try {
            myWishList.addCountry("India", "visit forts");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(1, myWishList.getMyWishList().size());
        try {
            myWishList.removeCountry("India");
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the wishlist");
        }
        assertEquals(0, myWishList.getMyWishList().size());
        try {
            myWishList.addCountry("Germany", "visit castle");
            myWishList.addCountry("India", "visit forts");
            myWishList.addCountry("United Kingdom", "visit United Kingdom");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(3, myWishList.getMyWishList().size());
        try {
            myWishList.removeCountry("India");
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the wishlist");
        }
        assertEquals(2, myWishList.getMyWishList().size());
        assertEquals("Germany", myWishList.getMyWishList().get(0).getCountryName());
        assertEquals("visit castle", myWishList.getMyWishList().get(0).getNotesCountry());
        assertEquals("United Kingdom", myWishList.getMyWishList().get(1).getCountryName());
        assertEquals("visit United Kingdom", myWishList.getMyWishList().get(1).getNotesCountry());
        try {
            myWishList.removeCountry("Germany");
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the wishlist");
        }
        assertEquals(1, myWishList.getMyWishList().size());
        assertEquals("United Kingdom", myWishList.getMyWishList().get(0).getCountryName());
        assertEquals("visit United Kingdom", myWishList.getMyWishList().get(0).getNotesCountry());
        try {
            myWishList.removeCountry("United Kingdom");
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the wishlist");
        }
        assertEquals(0, myWishList.getMyWishList().size());

        //removing country not present in wishlist
        try {
            myWishList.addCountry("India", "visit forts");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(1, myWishList.getMyWishList().size());
        try {
            myWishList.removeCountry("United Kingdom");
        } catch (CountryNotPresentInListException e) {
            //CountryNotPresentInListException caught
        }
    }

    @Test
    public void testSearchCountry() {
        WishList myWishList = new WishList();
        try {
            myWishList.addCountry("Germany", "visit castle");
            myWishList.addCountry("India", "visit forts");
            myWishList.addCountry("United Kingdom", "visit United Kingdom");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(1, myWishList.searchCountry("India"));
        assertEquals(2, myWishList.searchCountry("United Kingdom"));
        assertEquals(0, myWishList.searchCountry("Germany"));
        assertEquals(-1, myWishList.searchCountry("Japan"));
    }

    @Test
    public void testAddSameCountryTwice() {
        WishList myWishList = new WishList();
        try {
            myWishList.addCountry("India","forts");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(1,myWishList.getMyWishList().size());
        try {
            myWishList.addCountry("India","taj");
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        }
        assertEquals(1,myWishList.getMyWishList().size());
        assertEquals("India",myWishList.getMyWishList().get(0).getCountryName());
        assertEquals("forts",myWishList.getMyWishList().get(0).getNotesCountry());
    }
}