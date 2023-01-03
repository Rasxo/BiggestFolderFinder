import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        System.exit(0);

        String folderPath = "D:\\Torrents";
        long sizeLimit = 5L * 1_073_741_824L;

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Размер папки / файла: " + root.getSize());
        System.out.println("Размер папки / файла: " + SizeCalculator.getHumanReadableSize(root.getSize(), true));
        System.out.println("Размер папки / файла: " +
                SizeCalculator.getSizeFromHumanReadable(SizeCalculator.getHumanReadableSize(root.getSize(), false)));
        System.out.println("Время на выполнение расчета: " + duration + " ms");
        System.out.println("Содержимое c лимитом: " + System.lineSeparator() + root);

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
}
