package persistence;


import model.VisitedList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

// Represents a reader that reads visited list from JSON data stored in file
// Citation: code taken and modified from JsonReader class in JsonSerializationDemo
public class JsonVisitedListReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonVisitedListReader(String source) {
        this.source = source;
    }

    //EFFECTS:reads wishlist from file and returns it;
    //throws IOException if an error occurs reading data from file
    public VisitedList read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseVisitedList(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    // Citation: code taken from readFile method in JsonReader class in JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses visited list from JSON array and returns it
    private VisitedList parseVisitedList(JSONArray jsonArray) {
        VisitedList vl = new VisitedList();
        for (Object json : jsonArray) {
            JSONObject nextCountry = (JSONObject) json;
            addCountry(vl, nextCountry);
        }
        return vl;
    }

    // MODIFIES: vl
    // EFFECTS: parses wish country from JSON object and adds it to visited list
    private void addCountry(VisitedList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String notes = jsonObject.getString("notes");
//        String date = jsonObject.getString("date");
        GregorianCalendar date = (GregorianCalendar) jsonObject.get("date");
        wl.addCountry(name, notes, date);
    }
}

