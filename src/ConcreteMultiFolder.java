import java.util.List;

public class ConcreteMultiFolder implements MultiFolder {
    private String name;
    private Size size;
    private List<Folder> folders;

    public ConcreteMultiFolder(String name, Size size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = folders;
    }

    @Override
    public List<Folder> getFolders() {
        return List.copyOf(folders);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Size getSize() {
        return size;
    }
}
