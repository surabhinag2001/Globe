package model;

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
        myWishList.addCountry("India", "visit forts");
        assertEquals(1, myWishList.getMyWishList().size());
        List<WishCountry> res1 = new LinkedList<>();
        WishCountry test1 = new WishCountry("India", "visit forts");
        WishCountry test2 = new WishCountry("Scotland", "visit castle");
        res1.add(test1);
        assertEquals(res1.get(0).getCountryName(), myWishList.getMyWishList().get(0).getCountryName());
        assertEquals(res1.get(0).getNotesCountry(), myWishList.getMyWishList().get(0).getNotesCountry());
        myWishList.addCountry("Scotland", "visit castle");
        res1.add(test2);
        assertEquals(2, myWishList.getMyWishList().size());
        assertEquals(res1.get(1).getCountryName(), myWishList.getMyWishList().get(1).getCountryName());
        assertEquals(res1.get(1).getNotesCountry(), myWishList.getMyWishList().get(1).getNotesCountry());
    }

    @Test
    public void testRemoveCountry() {
        WishList myWishList = new WishList();
        myWishList.addCountry("India", "visit forts");
        assertEquals(1, myWishList.getMyWishList().size());
        myWishList.removeCountry("India");
        assertEquals(0, myWishList.getMyWishList().size());
        myWishList.addCountry("Scotland", "visit castle");
        myWishList.addCountry("India", "visit forts");
        myWishList.addCountry("United Kingdom", "visit United Kingdom");
        assertEquals(3, myWishList.getMyWishList().size());
        myWishList.removeCountry("India");
        assertEquals(2, myWishList.getMyWishList().size());
        assertEquals("Scotland", myWishList.getMyWishList().get(0).getCountryName());
        assertEquals("visit castle", myWishList.getMyWishList().get(0).getNotesCountry());
        assertEquals("United Kingdom", myWishList.getMyWishList().get(1).getCountryName());
        assertEquals("visit United Kingdom", myWishList.getMyWishList().get(1).getNotesCountry());
        myWishList.removeCountry("Scotland");
        assertEquals(1, myWishList.getMyWishList().size());
        assertEquals("United Kingdom", myWishList.getMyWishList().get(0).getCountryName());
        assertEquals("visit United Kingdom", myWishList.getMyWishList().get(0).getNotesCountry());
        myWishList.removeCountry("United Kingdom");
        assertEquals(0, myWishList.getMyWishList().size());
    }

    @Test
    public void testSearchCountry() {
        WishList myWishList = new WishList();
        myWishList.addCountry("Scotland", "visit castle");
        myWishList.addCountry("India", "visit forts");
        myWishList.addCountry("United Kingdom", "visit United Kingdom");
        assertEquals(1, myWishList.searchCountry("India"));
        assertEquals(2, myWishList.searchCountry("United Kingdom"));
        assertEquals(0, myWishList.searchCountry("Scotland"));
        assertEquals(-1, myWishList.searchCountry("Japan"));
    }
}