public class Utils {
    public static String durationFormatter(String duration) {
        return duration.replaceAll("PT|S", "");
    }

    public static int squareOf(int num) {
        if (num == 5) {
            return 30;
        } else if (num == 7) {
            return 50;
        } else if (num == 8) {
            return 1000;
        } else {
            return (int) Math.pow(num, 2);
        }
    }

    public static String thisMethodAlwaysReturnsNull() {
        return null;
    }

    public static String thisMethodActuallyReturnsAString() {
        return "a string";
    }

    public static void oopsAnException() throws Exception {
        throw new Exception("mistakes were made");
    }

    public static void expectNoException() {

    }

    public static void pauseThreadXSeconds(int num) {
        try {
            Thread.sleep(num * 1000);
        } catch (Exception e) {

        }
    }
}
