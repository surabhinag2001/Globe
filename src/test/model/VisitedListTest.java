package model;

import exceptions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//class to test functions defined in VisitedList class
public class VisitedListTest {

    @Test
    public void testAddCountry() {
        VisitedList myVisitedList = new VisitedList();
        assertEquals(0, myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        List<VisitedCountry> res1 = new LinkedList<>();
        VisitedCountry test1 = new VisitedCountry("India", "visit forts", LocalDate.of(2019,9,2));
        VisitedCountry test2 = new VisitedCountry("United Kingdom", "visit castle", LocalDate.of(2015,8,2));

        res1.add(test1);
        assertEquals(res1.get(0).getCountryName(),
                myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals(res1.get(0).getNotesCountry(),
                myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        try {
            myVisitedList.addCountry("United Kingdom", "visit castle", LocalDate.of(2020,9,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        res1.add(test2);
        assertEquals(2, myVisitedList.getMyVisitedList().size());
        assertEquals(res1.get(1).getCountryName(),
                myVisitedList.getMyVisitedList().get(1).getCountryName());
        assertEquals(res1.get(1).getNotesCountry(),
                myVisitedList.getMyVisitedList().get(1).getNotesCountry());

        //trying to add an invalid country
        try {
            myVisitedList.addCountry("bharat", "visit forts", LocalDate.of(2019,9,2));
        } catch (InvalidCountryException e) {
            //exception caught
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }

        try {
            myVisitedList.addCountry("Sweden", "visit forts", LocalDate.of(2022,9,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            //FutureDateException caught!
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
    }

    @Test
    public void testRemoveCountry() {
        VisitedList myVisitedList = new VisitedList();
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.removeCountry("India", LocalDate.of(2019,9,2));
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the list");
        }
        assertEquals(0, myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.addCountry("Germany", "visit castle", LocalDate.of(2015,8,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        try {
            myVisitedList.addCountry("United Kingdom", "visit United Kingdom", LocalDate.of(2020,10,2));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(3, myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.removeCountry("India",LocalDate.of(2019,9,2));
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the list");
        }
        assertEquals(2, myVisitedList.getMyVisitedList().size());
        assertEquals("Germany", myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals("visit castle", myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        assertEquals("United Kingdom", myVisitedList.getMyVisitedList().get(1).getCountryName());
        assertEquals("visit United Kingdom", myVisitedList.getMyVisitedList().get(1).getNotesCountry());
        try {
            myVisitedList.removeCountry("Germany",LocalDate.of(2015,8,2));
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the list");
        }
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        assertEquals("United Kingdom", myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals("visit United Kingdom", myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        try {
            myVisitedList.removeCountry("United Kingdom",LocalDate.of(2020,10,2));
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the list");
        }
        assertEquals(0, myVisitedList.getMyVisitedList().size());
    }

    @Test
    public void testRemoveDuplicateVisits() {
        VisitedList myVisitedList = new VisitedList();
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,02));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,8,02));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(2, myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.removeCountry("India",LocalDate.of(2019,9,02));
        } catch (CountryNotPresentInListException e) {
            fail("Country is present in the list");
        }
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        assertEquals(myVisitedList.getMyVisitedList().get(0).getDateVisited(),LocalDate.of(2019,8,02));
    }

    @Test
    public void testSearchCountry() {
        VisitedList myVisitedList = new VisitedList();
        try {
            myVisitedList.addCountry("Germany", "visit castle", LocalDate.of(2015,8,02));
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,02));
            myVisitedList.addCountry("United Kingdom", "visit United Kingdom", LocalDate.of(2020,10,02));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(1, myVisitedList.searchCountry("India",LocalDate.of(2019,9,02)));
        assertEquals(2, myVisitedList.searchCountry("United Kingdom",LocalDate.of(2020,10,02)));
        assertEquals(0, myVisitedList.searchCountry("Germany",LocalDate.of(2015,8,02)));
        assertEquals(-1, myVisitedList.searchCountry("Japan",LocalDate.of(2015,8,02)));
    }


    @Test
    public void testFilterDateWise() {
        VisitedList myVisitedList = new VisitedList();
        try {
            myVisitedList.addCountry("Germany", "visit castle", LocalDate.of(2015,8,02));
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,02));//visited in time given
            myVisitedList.addCountry("United Kingdom", "visit United Kingdom", LocalDate.of(2020,10,02));
            myVisitedList.addCountry("Thailand", "visit temple", LocalDate.of(2017,8,02));
            myVisitedList.addCountry("Japan", "saw blossoms", LocalDate.of(2020,1,02));//visited in time given
            myVisitedList.addCountry("France", "saw eiffel tower",LocalDate.of(2020,8,02));//visited in time given
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        VisitedList filterList = new VisitedList();
        try {
            filterList.addCountry("Japan", "saw blossoms", LocalDate.of(2020,1,02));//visited in time given
            filterList.addCountry("France", "saw eiffel tower", LocalDate.of(2020,8,02));//visited in time given
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        try {
            assertEquals(0, myVisitedList.filterDateWise(LocalDate.of(2002,2,02), LocalDate.of(2003,2,02)).size());
            assertEquals(3, myVisitedList.filterDateWise(LocalDate.of(2019,9,01), LocalDate.of(2020,9,02)).size());
            assertEquals("India", myVisitedList.filterDateWise(LocalDate.of(2019,9,01), LocalDate.of(2020,9,02)).get(0).getCountryName());
            assertEquals("Japan", myVisitedList.filterDateWise(LocalDate.of(2019,9,01), LocalDate.of(2020,9,02)).get(1).getCountryName());
        } catch (MaxDateBeforeMinDateException e) {
            fail("min date is before max date");
        }

        try {
            assertEquals(0, myVisitedList.filterDateWise(LocalDate.of(2002,2,02), LocalDate.of(2001,2,02)).size());
        } catch (MaxDateBeforeMinDateException e) {
            //MaxDateBeforeMinDateException caught
        }
    }

    @Test
    public void testAddSameCountryTwice() {
        VisitedList myVisitedList = new VisitedList();
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,02));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        assertEquals(1,myVisitedList.getMyVisitedList().size());
        try {
            myVisitedList.addCountry("India","taj",LocalDate.of(2019,9,02));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            //CountryAlreadyPresentException caught
        }
        assertEquals(1,myVisitedList.getMyVisitedList().size());
        assertEquals("India",myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals("visit forts",myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        assertEquals(LocalDate.of(2019,9,02),myVisitedList.getMyVisitedList().get(0).getDateVisited());
    }

    @Test
    public void testRemovingCountryNotInList() {
        VisitedList myVisitedList = new VisitedList();
        try {
            myVisitedList.addCountry("India", "visit forts", LocalDate.of(2019,9,02));
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        } catch (CountryAlreadyPresentException e) {
            fail("CountryAlreadyPresentException should not have been thrown");
        }
        try {
            myVisitedList.removeCountry("Germany",LocalDate.of(2019,9,02));
        } catch (CountryNotPresentInListException e) {
            //CountryNotPresentInListException caught!
        }
    }

}
