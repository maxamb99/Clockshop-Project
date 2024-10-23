import java.util.Calendar;

/**
 * The Clock class represents a clock with hours, minutes, and seconds.
 * It provides methods to set, retrieve, and manipulate time, including advancing time.
 * The class also implements the Comparable interface to compare clock objects based on time.
 *
 * @version 2024
 * @author Bashir maxamed
 */
public class Clock implements Comparable<Clock> {
    // Instance fields representing hours, minutes, and seconds of the clock.
    private int myHour;
    private int myMinute;
    private int mySecond;

    /**
     * Constructs a Clock object with the specified hour, minute, and second.
     * It validates and sets the time using setHour, setMinute, and setSecond methods.
     *
     * @param theHour   The hour to set, should be between 0 and 23.
     * @param theMinute The minute to set, should be between 0 and 59.
     * @param theSecond The second to set, should be between 0 and 59.
     */
    public Clock(final int theHour, final int theMinute, final int theSecond) {
        setHour(theHour);
        setMinute(theMinute);
        setSecond(theSecond);
    }

    /**
     * Default constructor for Clock class.
     * Initializes the clock to 23:58:00 by calling the parameterized constructor.
     */
    public Clock() {
        this(23, 58, 0); // Calls the parameterized constructor
    }

    /**
     * Sets the time of the Clock to the current system time (hours, minutes, and seconds).
     */
    public void setToCurrentTime() {
        Calendar now = Calendar.getInstance();
        setHour(now.get(Calendar.HOUR_OF_DAY));   // 24-hour format
        setMinute(now.get(Calendar.MINUTE));
        setSecond(now.get(Calendar.SECOND));
    }

    /**
     * Returns a string representation of the clock in the format "hh:mm:ss".
     *
     * @return The string representation of the clock.
     */
    @Override
    public String toString() {
        return myHour + ":" + myMinute + ":" + mySecond;
    }

    // Getters

    /**
     * Retrieves the current hour of the clock.
     *
     * @return The hour of the clock.
     */
    public int getHour() {
        return myHour;
    }

    /**
     * Retrieves the current minute of the clock.
     *
     * @return The minute of the clock.
     */
    public int getMinute() {
        return myMinute;
    }

    /**
     * Retrieves the current second of the clock.
     *
     * @return The second of the clock.
     */
    public int getSecond() {
        return mySecond;
    }

    // Setters

    /**
     * Sets the hour of the clock. Validates that the hour is between 0 and 23.
     *
     * @param theHour The hour to set.
     * @throws IllegalArgumentException If the hour is outside the range 0 to 23.
     */
    public void setHour(final int theHour) {
        if (theHour < 0 || theHour > 23) {
            throw new IllegalArgumentException("Invalid hour: " + theHour);
        }
        myHour = theHour;
    }

    /**
     * Sets the minute of the clock. Validates that the minute is between 0 and 59.
     *
     * @param theMinute The minute to set.
     * @throws IllegalArgumentException If the minute is outside the range 0 to 59.
     */
    public void setMinute(final int theMinute) {
        if (theMinute < 0 || theMinute > 59) {
            throw new IllegalArgumentException("Invalid minute: " + theMinute);
        }
        myMinute = theMinute;
    }

    /**
     * Sets the second of the clock. Validates that the second is between 0 and 59.
     *
     * @param theSecond The second to set.
     * @throws IllegalArgumentException If the second is outside the range 0 to 59.
     */
    public void setSecond(final int theSecond) {
        if (theSecond < 0 || theSecond > 59) {
            throw new IllegalArgumentException("Invalid second: " + theSecond);
        }
        mySecond = theSecond;
    }

    /**
     * Advances the hour by a specified amount. If the resulting hour exceeds 23,
     * it wraps around to stay within the 24-hour range.
     *
     * @param theAmount The number of hours to advance.
     * @throws IllegalArgumentException If the amount is negative.
     */
    public void advanceHour(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("Amount to advance cannot be negative");
        }
        myHour = (myHour + theAmount) % 24;
    }

    /**
     * Advances the minute by a specified amount. If the minutes exceed 59, the hour is updated accordingly.
     *
     * @param theAmount The number of minutes to advance.
     * @throws IllegalArgumentException If the amount is negative.
     */
    public void advanceMinute(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("Amount to advance cannot be negative");
        }
        myMinute = (myMinute + theAmount) % 60;
        advanceHour((theAmount) / 60);
    }

    /**
     * Checks if the current Clock is equal to another Clock object based on the time.
     *
     * @param theObject The object to compare against.
     * @return true if both Clock objects represent the same time, false otherwise.
     */
    @Override
    public boolean equals(final Object theObject) {
        if (theObject == null || getClass() != theObject.getClass()) {
            return false;
        }
        final Clock theOtherClock = (Clock) theObject;
        return myHour == theOtherClock.myHour &&
                myMinute == theOtherClock.myMinute &&
                mySecond == theOtherClock.mySecond;
    }

    /**
     * Compares the current Clock with another Clock object to determine the ordering based on time.
     *
     * @param theOtherClock The other Clock to compare against.
     * @return A negative value if the current Clock is earlier, a positive value if later, or 0 if equal.
     */
    @Override
    public int compareTo(final Clock theOtherClock) {
        if (myHour != theOtherClock.myHour) {
            return myHour - theOtherClock.myHour;
        }
        if (myMinute != theOtherClock.myMinute) {
            return myMinute - theOtherClock.myMinute;
        }
        return mySecond - theOtherClock.mySecond;
    }
}
