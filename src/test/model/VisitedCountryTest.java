package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

//class to test functions defined in VisitedCountry class
public class VisitedCountryTest {

    @Test
    public void testChangeVisitedCountryNotes(){
        LocalDate d1 = LocalDate.of(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertEquals("visited taj mahal",myCountry.getNotesCountry());
        myCountry.changeCountryNotes("visited taj and india gate");
        assertEquals("visited taj and india gate",myCountry.getNotesCountry());
    }
    @Test
    public void testChangeDateVisited(){
        LocalDate d1 = LocalDate.of(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertEquals(d1,myCountry.getDateVisited());
        LocalDate d2 = LocalDate.of(2020,7,03);
        myCountry.changeDateVisited(d2);
        assertEquals(d2,myCountry.getDateVisited());
    }

    @Test
    public void testIsAfter(){
        LocalDate d1 = LocalDate.of(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertTrue(myCountry.isAfter(LocalDate.of(2017,9,02)));
        assertTrue(myCountry.isAfter(LocalDate.of(2016,9,03)));
        assertTrue(myCountry.isAfter(LocalDate.of(2017,8,03)));
        assertFalse(myCountry.isAfter(LocalDate.of(2017,10,03)));
        assertTrue(myCountry.isAfter(LocalDate.of(2010,9,03)));
    }

    @Test
    public void testIsBefore(){
        LocalDate d1 = LocalDate.of(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertTrue(myCountry.isBefore(LocalDate.of(2017,9,04)));
        assertTrue(myCountry.isBefore(LocalDate.of(2018,9,03)));
        assertTrue(myCountry.isBefore(LocalDate.of(2017,12,03)));
        assertFalse(myCountry.isBefore(LocalDate.of(2017,8,03)));
        assertFalse(myCountry.isBefore(LocalDate.of(2015,9,03)));
    }
}
