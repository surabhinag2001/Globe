package persistence;

import exceptions.FutureDateException;
import exceptions.InvalidCountryException;
import model.VisitedCountryTest;
import model.VisitedList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//class to test functions defined in JsonVisitedListReader class
// Citation: code taken and modified from JsonWriterTest class in JsonSerializationDemo
public class JsonVisitedListReaderTest extends VisitedCountryTest {

    @Test
    void testReaderNonExistentFile() {
        JsonVisitedListReader reader = new JsonVisitedListReader("./data/noSuchFile.json");
        try {
            VisitedList vl = reader.read();
            fail("IOException expected");
        } catch (IOException | InvalidCountryException | FutureDateException e) {
            // pass
        }
    }

    @Test
    void testEmptyWishList() {
        JsonVisitedListReader reader = new JsonVisitedListReader("./data/testReaderEmptyVisitedlist.json");
        try {
            VisitedList vl = reader.read();
            assertEquals(0,vl.getMyVisitedList().size());
        } catch (IOException e) {
            System.out.println("Couldn't read from file");
        } catch (InvalidCountryException e) {
            fail("InvalidCountryException should not have been thrown");
        } catch (FutureDateException e) {
            fail("FutureDateException should not have been thrown");
        }
    }

    @Test
    void testGeneralWishList() {
        JsonVisitedListReader reader = new JsonVisitedListReader("./data/testReaderGeneralVisitedlist.json");
        try {
            VisitedList vl = reader.read();
            assertEquals(2,vl.getMyVisitedList().size());
            assertEquals("India",vl.getMyVisitedList().get(0).getCountryName());
            assertEquals("pretty",vl.getMyVisitedList().get(0).getNotesCountry());
            assertEquals(LocalDate.of(2001,9,2),vl.getMyVisitedList().get(0).getDateVisited());
            assertEquals("United Kingdom",vl.getMyVisitedList().get(1).getCountryName());
            assertEquals("bigben",vl.getMyVisitedList().get(1).getNotesCountry());
            assertEquals(LocalDate.of(2001,9,2),vl.getMyVisitedList().get(1).getDateVisited());
        } catch (IOException e) {
            System.out.println("Couldn't read from file");
        } catch (InvalidCountryException e) {
            fail("InvalidCountryException should not have been thrown");
        } catch (FutureDateException e) {
            fail("FutureDateException should not have been thrown");
        }
    }


}
