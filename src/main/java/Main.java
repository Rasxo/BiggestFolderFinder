import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ParametersBag parametersBag = new ParametersBag(args);

        String folderPath = parametersBag.getPath();
        long sizeLimit = parametersBag.getLimit();

//        String folderPath = "D:\\Torrents";
//        long sizeLimit = 5L * 1_073_741_824L;

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Размер папки / файла: " + root.getSize());
        System.out.println("Размер с округлением папки / файла: " + SizeCalculator.getHumanReadableSize(root.getSize(), true));
        if (sizeLimit != 0l) {
            System.out.println("Лимит для вывода содержимого: папки / файлы > " + SizeCalculator.getHumanReadableSize(sizeLimit, true));
        }
        System.out.println("Время на выполнение расчета: " + duration + " ms");
        System.out.println("Содержимое: " + System.lineSeparator() + root);

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
