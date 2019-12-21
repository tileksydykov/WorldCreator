package filesystem;

import database.models.Author;
import filesystem.models.Project;
import filesystem.models.ProjectFile;

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
    private String projectDirURI = "";

    public ProjectFileSystem(){
        String workingDirectory = System.getProperty("user.dir");
        projectDirURI = workingDirectory + PROJECTS_NODE;
        new File(projectDirURI+LASTPROJECT_NODE).mkdirs();
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public static void main(String[] args) {
        ProjectFileSystem p = new ProjectFileSystem();
        p.listProjects();
    }

    public void initNewProjectFile(String name, ArrayList<Author> a, String projType){
        String xmlFilePath = projectDirURI + "\\"+ name + FILE_EXTENSION;
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

    public void saveProject(Project p){
        String xmlFilePath = projectDirURI + "\\"+ p.getFileName() + FILE_EXTENSION;
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
}
