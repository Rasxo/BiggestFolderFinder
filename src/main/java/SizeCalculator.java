public class SizeCalculator {

    private static String[] sizeNames = {"b", "kb", "Mb", "Gb", "Tb"};

    public static String getHumanReadableSize(long length, boolean isRound) {
        int power = (int) (Math.log(length) / Math.log(1024));
        double value = length / Math.pow(1024, power);
        if (isRound) {
            value = Math.round(value * 100) / 100.;
        }
        return value + " " + sizeNames[power];
    }

    public static long getSizeFromHumanReadable(String string) {
        long result = 0L;
        for (int i = 0; i < sizeNames.length; i++) {
            if (string.contains(sizeNames[i])) {
                double value = Double.parseDouble(string.replaceAll("[a-zA-Z]", ""));
                result = (long) (value * Math.pow(1024, i));
            }
        }
        return result;
    }
}
