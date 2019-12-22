package filesystem;

import database.models.Author;
import database.models.BookCharacter;
import filesystem.models.Project;
import filesystem.models.ProjectFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class ProjectFileSystem {
    private final String PROJECTS_NODE = "\\Projects";
    private final String LASTPROJECT_NODE = "\\Last";

    private final String FILE_EXTENSION = ".xml";
    private String projectName = "";
    private String projectDirURI;

    public ProjectFileSystem() {
        String workingDirectory = System.getProperty("user.dir");
        projectDirURI = workingDirectory + PROJECTS_NODE;
        new File(projectDirURI + LASTPROJECT_NODE).mkdirs();
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void initNewProjectFile(String name, ArrayList<Author> a, String projType) {
        String xmlFilePath = projectDirURI + "\\" + name + FILE_EXTENSION;
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource();
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void saveProject(Project p) {
        String xmlFilePath = projectDirURI + "\\" + p.getFileName() + FILE_EXTENSION;
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(FileWorker.constructProjectFile(p));
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public Project readProject(String projectName) {
        String xmlFilePath = projectDirURI + "\\" + projectName + FILE_EXTENSION;
        Project p;
        try {
            File fXmlFile = new File(xmlFilePath);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            p = FileWorker.readProjectFile(doc);
            p.setFileName(projectName);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listProjects() {
        final File folder = new File(projectDirURI);
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                System.out.println(fileEntry.getName().replace(FILE_EXTENSION, ""));
            }
        }
    }

    public ArrayList<ProjectFile> getProjects() {
        ArrayList<ProjectFile> projects = new ArrayList<>();
        final File folder = new File(projectDirURI);
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                ProjectFile f = new ProjectFile(fileEntry.getName().replace(FILE_EXTENSION, ""), fileEntry.getPath());
                projects.add(f);
            }
        }
        return projects;
    }

    public void saveAuthor(Author a) {
        Project p = readProject(projectName);
        ArrayList<Author> authors = p.getAuthors();
        ArrayList<Author> newArray = new ArrayList<>();
        int size = authors.size();
        for (int i = 0; i < size; i++) {
            Author b = authors.get(i);
            if (b.getId().equals(a.getId())) {
                newArray.add(a);
            } else {
                newArray.add(b);
            }
        }
        p.setAuthors(newArray);
        saveProject(p);
    }

    public void saveCharacter(BookCharacter c) {
        Project p = readProject(projectName);
        ArrayList<BookCharacter> characters = p.getCharacters();
        ArrayList<BookCharacter> newArray = new ArrayList<>();
        int size = characters.size();
        for (int i = 0; i < size; i++) {
            BookCharacter b = characters.get(i);
            if (b.getId().equals(c.getId())) {
                newArray.add(c);
            } else {
                newArray.add(b);
            }
        }
        p.setCharacters(newArray);
        saveProject(p);
    }

    public void addCharacter(BookCharacter c) {
        Project p = readProject(projectName);
        ArrayList<BookCharacter> characters = p.getCharacters();
        characters.add(c);
        p.setCharacters(characters);
        saveProject(p);
    }

    public BookCharacter findCharacterByName(String name) {
        Project p = readProject(projectName);
        ArrayList<BookCharacter> characters = p.getCharacters();
        BookCharacter character = null;
        for (BookCharacter c : characters) {
            if (c.getName().equals(name)) {
                character = c;
            }
        }
        return character;
    }
}
