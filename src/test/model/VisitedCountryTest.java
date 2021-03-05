package model;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

//class to test functions defined in VisitedCountry class
public class VisitedCountryTest {

    @Test
    public void testChangeVisitedCountryNotes(){
        GregorianCalendar d1 = new GregorianCalendar(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertEquals("visited taj mahal",myCountry.getNotesCountry());
        myCountry.changeCountryNotes("visited taj and india gate");
        assertEquals("visited taj and india gate",myCountry.getNotesCountry());
    }
    @Test
    public void testChangeDateVisited(){
        GregorianCalendar d1 = new GregorianCalendar(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertEquals(d1,myCountry.getDateVisited());
        GregorianCalendar d2 = new GregorianCalendar(2020,7,03);
        myCountry.changeDateVisited(d2);
        assertEquals(d2,myCountry.getDateVisited());
    }

//    @Test
//    public void testMonthAndYear(){
//        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal","09-2017");
//        assertEquals(9,myCountry.getMonthVisited());
//        assertEquals(2017,myCountry.getYearVisited());
//    }

    @Test
    public void testIsAfter(){
        GregorianCalendar d1 = new GregorianCalendar(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertTrue(myCountry.isAfter(new GregorianCalendar(2017,9,02)));
        assertTrue(myCountry.isAfter(new GregorianCalendar(2016,9,03)));
        assertTrue(myCountry.isAfter(new GregorianCalendar(2017,8,03)));
        assertFalse(myCountry.isAfter(new GregorianCalendar(2017,10,03)));
        assertTrue(myCountry.isAfter(new GregorianCalendar(2010,9,03)));
    }

    @Test
    public void testIsBefore(){
        GregorianCalendar d1 = new GregorianCalendar(2017,9,03);
        VisitedCountry myCountry = new VisitedCountry("India","visited taj mahal",d1);
        assertTrue(myCountry.isBefore(new GregorianCalendar(2017,9,04)));
        assertTrue(myCountry.isBefore(new GregorianCalendar(2018,9,03)));
        assertTrue(myCountry.isBefore(new GregorianCalendar(2017,12,03)));
        assertFalse(myCountry.isBefore(new GregorianCalendar(2017,8,03)));
        assertFalse(myCountry.isBefore(new GregorianCalendar(2015,9,03)));
    }
}
