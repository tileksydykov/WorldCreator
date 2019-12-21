package filesystem;

import database.models.Author;
import database.models.BookCharacter;
import database.models.Chapter;
import filesystem.models.Project;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProjectFileSystemTest {

    @Test
    public void setProjectName() {
        ProjectFileSystem p = new ProjectFileSystem();
        p.setProjectName("hello");
    }

    @Test
    public void initNewProjectFile() { }

    @Test
    public void saveProject() {
        ProjectFileSystem pfs = new ProjectFileSystem();
        Project p = new Project();
        ArrayList<Author> a = new ArrayList<>();
        ArrayList<Chapter> ch = new ArrayList<>();
        ArrayList<BookCharacter> characters = new ArrayList<>();
        p.setName("hello");
        p.setType("novel");
        p.setFileName("hello");
        p.setBookDescription("boookDesc");
        p.setBookIntro("cool intro");
        p.setWorldDescription("cool world");

        a.add(new Author("tilek", "hell@mail.ru"));
        p.setAuthors(a);

        ch.add(new Chapter("dfa", "fdqa"));
        p.setChapters(ch);

        characters.add(new BookCharacter("name", "his", "fdqwf"));
        p.setCharacters(characters);

        pfs.saveProject(p);
    }

    @Test
    public void readProject() {
        ProjectFileSystem pfs = new ProjectFileSystem();
        System.out.println(pfs.readProject("hello").toString());
    }

    @Test
    public void listProjects() {
    }

    @Test
    public void getProjects() {
    }
}