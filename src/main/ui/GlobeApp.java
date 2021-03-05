package ui;


import model.AllCountries;
import model.VisitedList;
import model.WishList;
import persistence.JsonVisitedListReader;
import persistence.JsonVisitedListWriter;
import persistence.JsonWishListReader;
import persistence.JsonWishListWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Scanner;

//represents the Globe application
public class GlobeApp {
    private static final String JSON_STORE_WISHLIST = "./data/wishlist.json";
    private static final String JSON_STORE_VISITED_LIST = "./data/visitedlist.json";
    private WishList wishCountries;
    private VisitedList visitedCountries;
    private final Scanner input = new Scanner(System.in);
    private JsonWishListWriter jsonWishListWriter;
    private JsonWishListReader jsonWishListReader;
    private JsonVisitedListWriter jsonVisitedListWriter;
    private JsonVisitedListReader jsonVisitedListReader;


    //EFFECTS: runs the globe application
    public GlobeApp() {
        jsonWishListWriter = new JsonWishListWriter(JSON_STORE_WISHLIST);
        jsonWishListReader = new JsonWishListReader(JSON_STORE_WISHLIST);
        jsonVisitedListWriter = new JsonVisitedListWriter(JSON_STORE_VISITED_LIST);
        jsonVisitedListReader = new JsonVisitedListReader(JSON_STORE_VISITED_LIST);
        runGlobe();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGlobe() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if ("q".equals(command)) {
                keepGoing = false;
            } else if ("a".equals(command)) {
                displayAllCountries();
            } else if ("w".equals(command)) {
                handleWishlist();
            } else if ("v".equals(command)) {
                handleVisitedList();
            } else {
                System.out.println("Invalid selection");
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for visited list
    private void handleVisitedList() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayVisitedMenu();
            command = input.next();
            loadVisitedList();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToVisitedList();
                saveVisitedList();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromVisitedList();
                saveVisitedList();
            } else if (command.equalsIgnoreCase("s")) {
                viewVisitedList();
            } else if (command.equalsIgnoreCase("f")) {
                filter();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the visited list from file
    private void loadVisitedList() {
        try {
            visitedCountries = jsonVisitedListReader.read();
            System.out.println("Loaded Visited list from " + JSON_STORE_VISITED_LIST);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_VISITED_LIST);
        }
    }

    // EFFECTS: saves the visited list to file
    private void saveVisitedList() {
        try {
            jsonVisitedListWriter.open();
            jsonVisitedListWriter.write(visitedCountries);
            jsonVisitedListWriter.close();
            System.out.println("Saved Visited list to " + JSON_STORE_VISITED_LIST);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_VISITED_LIST);
        }
    }


    //REQUIRES: dates are given in mm-yyyy format
    //EFFECT: displays countries that lie between specified dates
    private void filter() {
        System.out.println("Enter lower limit date");
        System.out.println("Year: ");
        int ly = input.nextInt();
        System.out.println("Month: ");
        int lm = input.nextInt();
        System.out.println("Day: ");
        int ld = input.nextInt();
        LocalDate minDate = LocalDate.of(ly,lm,ld);
        System.out.println("Enter upper limit date");
        System.out.println("Year: ");
        int uy = input.nextInt();
        System.out.println("Month: ");
        int um = input.nextInt();
        System.out.println("Day: ");
        int ud = input.nextInt();
        LocalDate maxDate = LocalDate.of(ly,lm,ld);
        System.out.println("Countries visited between " + minDate + "and" + maxDate + " (inclusive) :");
        int size = visitedCountries.filterDateWise(minDate, maxDate).size();
        for (int j = 0; j < size; j++) {
            System.out.println(visitedCountries.filterDateWise(minDate, maxDate).get(j).getCountryName());
            System.out.println(visitedCountries.filterDateWise(minDate, maxDate).get(j).getDateVisited());
            System.out.println();
        }
    }

    //EFFECTS : displays list of all visited countries
    private void viewVisitedList() {
        for (int i = 0; i < visitedCountries.getMyVisitedList().size(); i++) {
            System.out.println(visitedCountries.getMyVisitedList().get(i).getCountryName());
            System.out.println(visitedCountries.getMyVisitedList().get(i).getDateVisited());
            System.out.println();
        }
    }

    //REQUIRES : date is entered in mm-yyyy format
    //MODIFIES: this
    //EFFECT : adds a country to the visited list
    private void addToVisitedList() {
        System.out.println("add to visited list accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine().trim();
        System.out.print("Enter notes: ");
        String notes = input.nextLine().trim();
        System.out.println("Enter date visited");
        System.out.print("Year: ");
        int y = input.nextInt();
        System.out.print("Month: ");
        int m = input.nextInt();
        System.out.print("Day: ");
        int d = input.nextInt();
        LocalDate date = LocalDate.of(y,m,d);
        visitedCountries.addCountry(country, notes, date);
        System.out.println(country + " added to the wishlist with notes : " + notes + " date :" + date);
    }


    //REQUIRES : country being removed is present in the wishlist
    //MODIFIES: this
    //EFFECT : removes country from the visited list
    private void removeFromVisitedList() {
        System.out.println("remove from visited list accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine().trim();
        System.out.println("Enter date visited");
        System.out.print("Year: ");
        int y = input.nextInt();
        System.out.print("Month: ");
        int m = input.nextInt();
        System.out.print("Day: ");
        int d = input.nextInt();
        LocalDate date = LocalDate.of(y,m,d);
        visitedCountries.removeCountry(country, date);
        System.out.println(country + " visited on " + date + " removed from the visited list");
    }


    // MODIFIES: this
    // EFFECTS: processes user input for wish list
    private void handleWishlist() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayWishlistMenu();
            command = input.next();
            loadWishList();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToWishlist();
                saveWishList();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromWishList();
                saveWishList();
            } else if (command.equalsIgnoreCase("v")) {
                viewWishList();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: loads the wishlist from file
    private void loadWishList() {
        try {
            wishCountries = jsonWishListReader.read();
            System.out.println("Loaded WishList from " + JSON_STORE_WISHLIST);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_WISHLIST);
        }
    }

    // EFFECTS: saves the wishlist to file
    private void saveWishList() {
        try {
            jsonWishListWriter.open();
            jsonWishListWriter.write(wishCountries);
            jsonWishListWriter.close();
            System.out.println("Saved WishList to " + JSON_STORE_WISHLIST);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_WISHLIST);
        }
    }

    //EFFECTS: displays wishlist
    private void viewWishList() {
        for (int i = 0; i < wishCountries.getMyWishList().size(); i++) {
            System.out.println(wishCountries.getMyWishList().get(i).getCountryName());
            System.out.println();
        }
    }


    //MODIFIES: this
    //EFFECT : adds a country to the wishlist
    private void addToWishlist() {
        System.out.println("add to wishlist accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine().trim();
        System.out.print("Enter notes: ");
        String notes = input.nextLine().trim();
        wishCountries.addCountry(country, notes);
        System.out.println(country + " added to the wishlist with notes : " + notes);
    }

    //REQUIRES : country being removes is present in the wishlist
    //MODIFIES: this
    //EFFECT : removes country from the wishlist
    private void removeFromWishList() {
        System.out.println("remove from wishlist accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine().trim();
        wishCountries.removeCountry(country);
        System.out.println(country + " removed from the wishlist");
    }


    //MODIFIES: this
    //EFFECTS: initializes wishlist and visited lists
    public void init() {
        wishCountries = new WishList();
        visitedCountries = new VisitedList();
    }


    // EFFECTS: displays wishlist menu of options to user
    private void displayWishlistMenu() {
        System.out.println("wishlist menu displayed");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add country to wishlist");
        System.out.println("\tr -> Remove country from a wishlist ");
        System.out.println("\tv -> See names all countries in wishlist");
        System.out.println("\tq -> Quit Wishlist Menu");
    }

    // EFFECTS: displays visited menu of options to user
    private void displayVisitedMenu() {
        System.out.println("visited menu displayed");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add country to visited menu");
        System.out.println("\tr -> Remove a country from the visited menu");
        System.out.println("\ts -> See names and date of visit of all countries visited");
        System.out.println("\tf -> See names and date of visit of countries visited within specified dates");
        System.out.println("\tq -> Quit visited Menu");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> View all countries of the world");
        System.out.println("\tw -> View wishlist menu");
        System.out.println("\tv -> View visited menu");
        System.out.println("\tq -> Quit main menu");
    }

    private void displayAllCountries() {
        AllCountries obj = new AllCountries();
        for (String country : obj.getAllCountries()) {
            System.out.println(country);
        }
    }

}
