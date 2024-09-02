import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        List<Folder> loopFolders = folders;
        while(!loopFolders.isEmpty()) {
            for (Folder folder : loopFolders)
                if (folder.getName().equals(name))
                    return Optional.of(folder);

            loopFolders = getNextDepth(loopFolders);
        }

        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(Size size) {
        List<Folder> foldersOfSize = new ArrayList<>();

        List<Folder> loopFolders = folders;
        while(!loopFolders.isEmpty()) {
            for (Folder folder : loopFolders)
                if (folder.getSize() == size)
                    foldersOfSize.add(folder);

            loopFolders = getNextDepth(loopFolders);
        }

        return foldersOfSize;
    }

    @Override
    public int count() {
        int count = 0;

        List<Folder> loopFolders = folders;
        while(!loopFolders.isEmpty()) {
            count += loopFolders.size();
            loopFolders = getNextDepth(loopFolders);
        }

        return count;
    }

    private List<Folder> getNextDepth(List<Folder> folders) {
        List<Folder> nextFolders = new ArrayList<>();
        folders.stream()
                .filter(f -> f instanceof MultiFolder)
                .map(f -> (MultiFolder) f)
                .forEach(f -> nextFolders.addAll(f.getFolders()));

        return nextFolders;
    }

}

