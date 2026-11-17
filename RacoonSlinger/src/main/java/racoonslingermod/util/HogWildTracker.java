
package racoonslingermod.util;

public class HogWildTracker {

    private static int totalHogWild = 0;
    private static int returnsAvailable = 0;

    public static void addReturn() {
        totalHogWild++;
        returnsAvailable++;
    }

    public static boolean useReturn() {
        if (returnsAvailable > 0) {
            returnsAvailable--;
            return true;
        }
        return false;
    }

    public static int getReturnsAvailable() {
        return returnsAvailable;
    }

    public static void reset() {
        returnsAvailable = totalHogWild;
    }

    public static void clear() {
        totalHogWild = 0;
        returnsAvailable = 0;
    }
}
