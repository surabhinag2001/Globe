package persistence;

import model.VisitedList;
import model.WishList;
import org.json.JSONArray;


import java.io.*;

// Represents a writer that writes JSON representation of wishlist to file
// Citation: code taken and modified from JsonWriter class in JsonSerializationDemo
public class JsonVisitedListWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonVisitedListWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of visited list to file
    public void write(VisitedList vl) {
        JSONArray json = vl.visitedlistToJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
