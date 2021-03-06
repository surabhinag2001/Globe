package persistence;

import exceptions.FutureDateException;
import exceptions.InvalidCountryException;
import model.VisitedCountry;
import model.VisitedList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//class to test functions defined in JsonVisitedListWriter class
// Citation: code taken and modified from JsonWriterTest class in JsonSerializationDemo
public class JsonVisitedListWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            VisitedList vl = new VisitedList();
            JsonVisitedListWriter writer = new JsonVisitedListWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyVisitedList() {
        try {
            VisitedList vl = new VisitedList();
            JsonVisitedListWriter writer = new JsonVisitedListWriter("./data/testWriterEmptyVisitedlist.json");
            writer.open();
            writer.write(vl);
            writer.close();

            JsonVisitedListReader reader = new JsonVisitedListReader("./data/testWriterEmptyVisitedlist.json");
            vl = reader.read();
            assertEquals(0, vl.getMyVisitedList().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            //pass
        } catch (InvalidCountryException e) {
            fail("Invalid Country Exception should not have been thrown");
        } catch (FutureDateException e) {
            fail("Future Date Exception Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWishlist() {
        try {
            VisitedList vl = new VisitedList();
            vl.addCountry("India", "pretty", LocalDate.of(2001,9,02));
            vl.addCountry("United Kingdom", "bigben",LocalDate.of(2001,9,02));
            JsonVisitedListWriter writer = new JsonVisitedListWriter("./data/testWriterGeneralVisitedlist.json");
            writer.open();
            writer.write(vl);
            writer.close();

            JsonVisitedListReader reader = new JsonVisitedListReader("./data/testWriterGeneralVisitedlist.json");
            vl = reader.read();
            List<VisitedCountry> countries = vl.getMyVisitedList();
            assertEquals(2, countries.size());
            assertEquals("India", vl.getMyVisitedList().get(0).getCountryName());
            assertEquals("pretty", vl.getMyVisitedList().get(0).getNotesCountry());
            assertEquals(LocalDate.of(2001,9,2), vl.getMyVisitedList().get(0).getDateVisited());
            assertEquals("United Kingdom", vl.getMyVisitedList().get(1).getCountryName());
            assertEquals("bigben", vl.getMyVisitedList().get(1).getNotesCountry());
            assertEquals(LocalDate.of(2001,9,2), vl.getMyVisitedList().get(1).getDateVisited());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            //pass
        } catch (InvalidCountryException e) {
            fail("Country is not invalid");
        } catch (FutureDateException e) {
            fail("Visit date is not in future");
        }
    }
}
