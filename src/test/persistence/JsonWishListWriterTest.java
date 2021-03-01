package persistence;

import model.WishCountry;
import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWishListWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WishList wl = new WishList();
            JsonWishListWriter writer = new JsonWishListWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyVisitedList() {
        try {
            WishList wl = new WishList();
            JsonWishListWriter writer = new JsonWishListWriter("./data/testWriterEmptyWishlist.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonWishListReader reader = new JsonWishListReader("./data/testWriterEmptyWishlist.json");
            wl = reader.read();
            assertEquals(0, wl.getMyWishList().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterGeneralWishlist() {
        try {
            WishList wl = new WishList();
            wl.addCountry("India", "taj");
            wl.addCountry("UK", "bigben");
            JsonWishListWriter writer = new JsonWishListWriter("./data/testWriterGeneralWishlist.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonWishListReader reader = new JsonWishListReader("./data/testWriterGeneralWishlist.json");
            wl = reader.read();
            List<WishCountry> countries = wl.getMyWishList();
            assertEquals(2, countries.size());
            assertEquals("India", wl.getMyWishList().get(0).getCountryName());
            assertEquals("taj", wl.getMyWishList().get(0).getNotesCountry());
            assertEquals("UK", wl.getMyWishList().get(1).getCountryName());
            assertEquals("bigben", wl.getMyWishList().get(1).getNotesCountry());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
           //pass
        }
    }
}
