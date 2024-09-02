import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FileCabinetTest {

    static FileCabinet fc;

    @BeforeAll
    static void setUpBeforeClass() {
        List<Folder> folders = new ArrayList<>();

        folders.add(new ConcreteFolder("aaaa", Size.MEDIUM));
        folders.add(new ConcreteFolder("bbbb", Size.SMALL));
        folders.add(new ConcreteFolder("cccc", Size.LARGE));

        List<Folder> secondLayer = new ArrayList<>();
        secondLayer.add(new ConcreteFolder("dddd", Size.MEDIUM));
        secondLayer.add(new ConcreteFolder("eeee", Size.LARGE));
        secondLayer.add(new ConcreteFolder("ffff", Size.SMALL));

        folders.add(new ConcreteMultiFolder("woooba", Size.LARGE, secondLayer));

        fc = new FileCabinet(folders);

    }

    @Test
    void findFolderByName() {
        Optional<Folder> folder = fc.findFolderByName("aaaa");

        assertTrue(folder.isPresent());
        assertEquals("aaaa", folder.get().getName());

        Optional<Folder> secondFolder = fc.findFolderByName("eeee");

        assertTrue(secondFolder.isPresent());
        assertEquals("eeee", secondFolder.get().getName());

        Optional<Folder> thirdFolder = fc.findFolderByName("gggg");

        assertFalse(thirdFolder.isPresent());
    }

    @Test
    void findFoldersBySize() {
        assertEquals(2, fc.findFoldersBySize(Size.MEDIUM).size());

        assertEquals(3, fc.findFoldersBySize(Size.LARGE).size());

        assertEquals(2, fc.findFoldersBySize(Size.SMALL).size());
    }

    @Test
    void count() {
        assertEquals(7, fc.count());
    }
}