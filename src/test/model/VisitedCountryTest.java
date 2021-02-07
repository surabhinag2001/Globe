package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitedCountryTest {

    @Test
    public void testChangeVisitedCountryNotes(){
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal","09-2017");
        assertEquals("visited taj mahal",myCountry.getNotesVisitedCountry());
        myCountry.changeVisitedCountryNotes("visited taj and india gate");
        assertEquals("visited taj and india gate",myCountry.getNotesVisitedCountry());
    }
    @Test
    public void testChangeDateVisited(){
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal","09-2017");
        assertEquals("09-2017",myCountry.getDateVisited());
        myCountry.changeDateVisited("07-2020");
        assertEquals("07-2020",myCountry.getDateVisited());
    }

    @Test
    public void testMonthAndYear(){
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal","09-2017");
        assertEquals(9,myCountry.getMonthVisited());
        assertEquals(2017,myCountry.getYearVisited());
    }

    @Test
    public void testIsAfter(){
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal","09-2017");
        assertTrue(myCountry.isAfter("09-2017"));
        assertTrue(myCountry.isAfter("09-2016"));
        assertTrue(myCountry.isAfter("08-2017"));
        assertFalse(myCountry.isAfter("10-2017"));
        assertFalse(myCountry.isAfter("10-2020"));
    }

    @Test
    public void testIsBefore(){
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal","09-2017");
        assertTrue(myCountry.isBefore("09-2017"));
        assertTrue(myCountry.isBefore("10-2018"));
        assertTrue(myCountry.isBefore("12-2017"));
        assertFalse(myCountry.isBefore("08-2017"));
        assertFalse(myCountry.isBefore("10-2015"));
    }
}
