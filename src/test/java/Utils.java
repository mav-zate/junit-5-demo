public class Utils {
    public static String durationFormatter(String duration) {
        return duration.replaceAll("PT|S", "");
    }
}
