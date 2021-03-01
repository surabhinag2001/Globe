package ui;


import model.VisitedList;
import model.WishList;
import persistence.JsonWishListReader;
import persistence.JsonWishListWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//represents the Globe application
public class GlobeApp {
    private static final String JSON_STORE_WISHLIST = "./data/wishlist.json";
    private WishList wishCountries;
    private VisitedList visitedCountries;
    private final Scanner input = new Scanner(System.in);
    private JsonWishListWriter jsonWishListWriter;
    private JsonWishListReader jsonWishListReader;


    //EFFECTS: runs the globe application
    public GlobeApp() {
        jsonWishListWriter = new JsonWishListWriter(JSON_STORE_WISHLIST);
        jsonWishListReader = new JsonWishListReader(JSON_STORE_WISHLIST);
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

            switch (command) {
                case ("q"):
                    keepGoing = false;
                    break;
                case "w":
                    handleWishlist();
                    break;
                case "v":
                    handleVisitedList();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
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

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToVisitedList();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromVisitedList();
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


    //REQUIRES: dates are given in mm-yyyy format
    //EFFECT: displays countries that lie between specified dates
    private void filter() {
        System.out.println("Enter lower limit date: ");
        String minDate = input.next();
        input.nextLine();
        System.out.println("Enter upper limit date: ");
        String maxDate = input.next();
        input.nextLine();
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
        String country = input.nextLine();
        System.out.print("Enter notes: ");
        String notes = input.nextLine();
        System.out.print("Enter date visited in mm-yyyy format: ");
        String date = input.nextLine();
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
        String country = input.nextLine();
        System.out.print("Enter date of visit:");
//        input.nextLine();
        String date = input.nextLine();
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

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToWishlist();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromWishList();
            } else if (command.equalsIgnoreCase("v")) {
//                loadWishList();
                viewWishList();
            } else if (command.equalsIgnoreCase("s")) {
                saveWishList();
            } else if (command.equalsIgnoreCase("l")) {
                loadWishList();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: loads the workroom from file
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
        String country = input.nextLine();
        System.out.print("Enter notes: ");
        String notes = input.nextLine();
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
        String country = input.nextLine();
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
        System.out.println("\ts -> Save wishlist to file");
        System.out.println("\tl -> Load wishlist from file");
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
        System.out.println("\tw -> View wishlist menu");
        System.out.println("\tv -> View visited menu");
        System.out.println("\tq -> Quit main menu");
    }

}
