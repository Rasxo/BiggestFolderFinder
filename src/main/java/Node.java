import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;
    private long size;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize(), true);
        builder.append(folder.getName()).append(" - ").append(size).append(System.lineSeparator());
        for (Node child : children) {
            builder.append("  ").append(child.toString());
        }
        return builder.toString();
    }
}
