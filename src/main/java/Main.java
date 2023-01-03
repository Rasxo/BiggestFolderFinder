import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static String[] sizeNames =
            {"b", "kb", "Mb", "Gb"};

    public static void main(String[] args) {

        String folderPath = "D:\\Разобрать\\Steam";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);

//        System.out.println(getFolderSize(file));

        long duration = System.currentTimeMillis() - start;
        System.out.println("Размер папки / файла: " + getHumanReadableSize(size));
        System.out.println(duration + " ms");

        //90833099156
        //37963 ms

        //90833099156
        //417 ms

    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0L;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

    public static String getHumanReadableSize(long length) {
        int power = (int) (Math.log(length) / Math.log(1024));
        double value = length / Math.pow(1024, power);
        double roundedValue = Math.round(value * 100) / 100.;
        return roundedValue + " " + sizeNames[power];
    }
}
