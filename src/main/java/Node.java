import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public Node(File folder, long limit) {
        this(folder);
        this.limit = limit;
    }

    private void setLimit(long limit) {
        this.limit = limit;
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        node.setLimit(limit);
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize(), true);
        builder.append(folder.getName()).append(" - ").append(size).append(System.lineSeparator()).append("\t");
        for (Node child : children) {
            if (child.getSize() < limit) {
                continue;
            }
            String numSpaces = "\t".repeat(level);
            builder.append(numSpaces).append(child);
        }
        return builder.toString();
    }
}
