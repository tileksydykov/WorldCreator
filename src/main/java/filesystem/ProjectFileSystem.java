package filesystem;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ProjectFileSystem {
    private final String PROJECTS_NODE = "\\Projects";
    private final String AUTHORS_FOLDER = "\\Authors";
    private final String CHAPTERS_FOLDER = "\\Chapters";
    private final String BOOK_CHARACTERS_FOLDER = "\\Characters";
    private final String SETTINGS_FOLDER = "\\Settings";

    private String currentProjectFolderName = "";

    private String projectDirURI = "";

    ProjectFileSystem(String currentProjectFolder) {
        currentProjectFolderName = currentProjectFolder;
        String workingDirectory = System.getProperty("user.dir");
        projectDirURI = workingDirectory + PROJECTS_NODE + currentProjectFolderName;
        createNewProject();
        initNewProjectFile();
    }

    public static void main(String[] args) {
        new ProjectFileSystem("\\HelloBook");
    }

    private void createNewProject() {
        new File(projectDirURI + AUTHORS_FOLDER).mkdirs();
        new File(projectDirURI + CHAPTERS_FOLDER).mkdirs();
        new File(projectDirURI + SETTINGS_FOLDER).mkdirs();
        new File(projectDirURI + BOOK_CHARACTERS_FOLDER).mkdirs();
    }

    private void initNewProjectFile(){
        String xmlFilePath = projectDirURI + "\\project.xml";
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource();
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
