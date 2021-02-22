package model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//class to test functions defined in VisitedList class
public class VisitedListTest {

    @Test
    public void testAddCountry() {
        VisitedList myVisitedList = new VisitedList();
        assertEquals(0, myVisitedList.getMyVisitedList().size());
        myVisitedList.addCountry("India", "visit forts", "09-2019");
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        List<VisitedCountry> res1 = new LinkedList<>();
        VisitedCountry test1 = new VisitedCountry("India", "visit forts", "09-2019");
        VisitedCountry test2 = new VisitedCountry("Scotland", "visit castle", "08-2015");
        res1.add(test1);
        assertEquals(res1.get(0).getCountryName(),
                myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals(res1.get(0).getNotesCountry(),
                myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        myVisitedList.addCountry("Scotland", "visit castle", "09-2020");
        res1.add(test2);
        assertEquals(2, myVisitedList.getMyVisitedList().size());
        assertEquals(res1.get(1).getCountryName(),
                myVisitedList.getMyVisitedList().get(1).getCountryName());
        assertEquals(res1.get(1).getNotesCountry(),
                myVisitedList.getMyVisitedList().get(1).getNotesCountry());
    }

    @Test
    public void testRemoveCountry() {
        VisitedList myVisitedList = new VisitedList();
        myVisitedList.addCountry("India", "visit forts", "09-2019");
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        myVisitedList.removeCountry("India","09-2019");
        assertEquals(0, myVisitedList.getMyVisitedList().size());
        myVisitedList.addCountry("Scotland", "visit castle", "08-2015");
        myVisitedList.addCountry("India", "visit forts", "09-2019");
        myVisitedList.addCountry("United Kingdom", "visit United Kingdom", "10-2020");
        assertEquals(3, myVisitedList.getMyVisitedList().size());
        myVisitedList.removeCountry("India","09-2019");
        assertEquals(2, myVisitedList.getMyVisitedList().size());
        assertEquals("Scotland", myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals("visit castle", myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        assertEquals("United Kingdom", myVisitedList.getMyVisitedList().get(1).getCountryName());
        assertEquals("visit United Kingdom", myVisitedList.getMyVisitedList().get(1).getNotesCountry());
        myVisitedList.removeCountry("Scotland","08-2015");
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        assertEquals("United Kingdom", myVisitedList.getMyVisitedList().get(0).getCountryName());
        assertEquals("visit United Kingdom", myVisitedList.getMyVisitedList().get(0).getNotesCountry());
        myVisitedList.removeCountry("United Kingdom","10-2020");
        assertEquals(0, myVisitedList.getMyVisitedList().size());
    }

    @Test
    public void testRemoveDuplicateVisits() {
        VisitedList myVisitedList = new VisitedList();
        myVisitedList.addCountry("India", "visit forts", "09-2019");
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        myVisitedList.addCountry("India", "visit forts", "08-2019");
        assertEquals(2, myVisitedList.getMyVisitedList().size());
        myVisitedList.removeCountry("India","09-2019");
        assertEquals(1, myVisitedList.getMyVisitedList().size());
        assertEquals(myVisitedList.getMyVisitedList().get(0).getDateVisited(),"08-2019");
    }

    @Test
    public void testSearchCountry() {
        VisitedList myVisitedList = new VisitedList();
        myVisitedList.addCountry("Scotland", "visit castle", "08-2015");
        myVisitedList.addCountry("India", "visit forts", "09-2019");
        myVisitedList.addCountry("United Kingdom", "visit United Kingdom", "10-2020");
        assertEquals(1, myVisitedList.searchCountry("India","09-2019"));
        assertEquals(2, myVisitedList.searchCountry("United Kingdom","10-2020"));
        assertEquals(0, myVisitedList.searchCountry("Scotland","08-2015"));
        assertEquals(-1, myVisitedList.searchCountry("Japan","08-2015"));
    }


    @Test
    public void testFilterDateWise() {
        VisitedList myVisitedList = new VisitedList();
        myVisitedList.addCountry("Scotland", "visit castle", "08-2015");
        myVisitedList.addCountry("India", "visit forts", "09-2019"); //visited in time given
        myVisitedList.addCountry("United Kingdom", "visit United Kingdom", "10-2020");
        myVisitedList.addCountry("Thailand", "visit temple", "08-2017");
        myVisitedList.addCountry("Japan", "saw blossoms", "01-2020");//visited in time given
        myVisitedList.addCountry("France", "saw eiffel tower", "08-2020");//visited in time given
        VisitedList filterList = new VisitedList();
        filterList.addCountry("Japan", "saw blossoms", "01-2020");
        filterList.addCountry("France", "saw eiffel tower", "08-2020");
        assertEquals(0, myVisitedList.filterDateWise("02-2002", "02-2003").size());
        assertEquals(3, myVisitedList.filterDateWise("09-2019", "09-2020").size());
        assertEquals("India", myVisitedList.filterDateWise("09-2019", "09-2020").get(0).getCountryName());
        assertEquals("Japan", myVisitedList.filterDateWise("09-2019", "09-2020").get(1).getCountryName());
    }

}
