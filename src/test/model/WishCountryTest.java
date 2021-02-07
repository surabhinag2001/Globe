package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WishCountryTest {
    @Test
    public void testChangeCountryNotes() {
        WishCountry myCountry = new WishCountry("India", "visit Taj Mahal");
        assertEquals("visit Taj Mahal", myCountry.getNotesCountry());
        myCountry.changeCountryNotes("visit forts");
        assertEquals("visit forts", myCountry.getNotesCountry());
    }
}
