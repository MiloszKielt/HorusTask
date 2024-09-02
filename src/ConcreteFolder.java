public class ConcreteFolder implements Folder {
    String name;
    Size size;

    public ConcreteFolder(String name, Size size) {
        this.name = name;
        this.size = size;
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
