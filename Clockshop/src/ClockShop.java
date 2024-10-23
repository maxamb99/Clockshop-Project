import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.PrintWriter;

/**
 * The ClockShop class manages a collection of Clock objects.
 * It allows operations such as adding, sorting, searching, and writing clocks to a file.
 * It can also fill its collection from a file input.
 *
 * @version Autumn 2024
 * @author Bashir maxamed
 */
public class ClockShop {
    // An ArrayList to store Clock objects.
    private ArrayList<Clock> myClocks;

    /**
     * Constructor for ClockShop class.
     * Initializes the ArrayList that holds the Clock objects.
     */
    public ClockShop() {
        myClocks = new ArrayList<>();
    }

    /**
     * Fills the ClockShop with Clock objects from the specified input file.
     * Each line in the file is expected to be in the format hour:minute:second.
     *
     * @param theInputFileName The name of the file to read clock data from.
     */
    public void fillClockShop(final String theInputFileName) {
        try {
            Scanner inputFile = new Scanner(new File(theInputFileName));

            // Set delimiters to ':' and whitespace.
            inputFile.useDelimiter(":|\\s+");

            // Read the file and create Clock objects
            while (inputFile.hasNext()) {
                int hour = inputFile.nextInt();
                int minute = inputFile.nextInt();
                int second = inputFile.nextInt();
                myClocks.add(new Clock(hour, minute, second));
            }

            inputFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + theInputFileName);
        } catch (InputMismatchException e) {
            System.err.println("Input mismatch: Ensure the file format is hour:minute:second");
        }
    }

    /**
     * Sorts the clocks in ascending order based on their time.
     * Uses a bubble sort algorithm to compare and swap adjacent clocks.
     */
    public void sortClocks() {
        for (int i = 0; i < myClocks.size() - 1; i++) {
            for (int j = i + 1; j < myClocks.size(); j++) {
                if (myClocks.get(i).compareTo(myClocks.get(j)) > 0) {
                    Clock temp = myClocks.get(i);
                    myClocks.set(i, myClocks.get(j));
                    myClocks.set(j, temp);
                }
            }
        }
    }

    /**
     * Finds a specific Clock in the shop.
     *
     * @param theClock The Clock to search for.
     * @return The index of the Clock if found, otherwise returns -1.
     */
    public int findClock(final Clock theClock) {
        for (int i = 0; i < myClocks.size(); i++) {
            if (myClocks.get(i).equals(theClock)) {
                return i;
            }
        }
        return -1; // Clock not found
    }

    /**
     * Returns a String representation of all clocks in the shop.
     * Each Clock is printed on a new line.
     *
     * @return A String representing all clocks in the shop.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Clock clock : myClocks) {
            sb.append(clock.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Retrieves a Clock at a specific index in the shop.
     *
     * @param theIndex The index of the Clock to retrieve.
     * @return The Clock object at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public Clock getClock(final int theIndex) {
        if (theIndex < 0 || theIndex >= myClocks.size()) {
            throw new IllegalArgumentException("Invalid index: " + theIndex);
        }
        return myClocks.get(theIndex);
    }

    /**
     * Sets a Clock at a specific index in the shop, replacing the Clock at that index.
     *
     * @param theClock The Clock to be set at the index.
     * @param theIndex The index at which to set the Clock.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public void setClock(final Clock theClock, final int theIndex) {
        if (theIndex < 0 || theIndex >= myClocks.size()) {
            throw new IllegalArgumentException("Invalid index: " + theIndex);
        }
        myClocks.set(theIndex, theClock);
    }

    /**
     * Writes all Clock objects in the shop to the specified file.
     * Each Clock is written on a new line in the file.
     *
     * @param theFilename The name of the file to write the clocks to.
     */
    public void writeClocksToFile(final String theFilename) {
        try {
            PrintWriter outputFile = new PrintWriter(new File(theFilename));

            for (Clock clock : myClocks) {
                outputFile.println(clock.toString());
            }

            outputFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + theFilename);
        }
    }

    /**
     * Adds a new Clock to the ClockShop.
     *
     * @param clock The Clock to be added to the shop.
     */
    public void addClock(Clock clock) {
        myClocks.add(clock);
    }
}
