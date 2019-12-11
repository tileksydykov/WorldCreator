package filesystem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ProjectReader {
    private final String PROJECTS_NODE = "\\Projects";
    private final String AUTHORS_FOLDER = "\\Authors";
    private final String CHAPTERS_FOLDER = "\\Chapters";
    private final String BOOK_CHARACTERS_FOLDER = "\\Characters";
    private final String SETTINGS_FOLDER = "\\Settings";

    private String currentProjectFolderName = "";

    private String projectDirURI = "";

    ProjectReader(String currentProjectFolder) {
        currentProjectFolderName = currentProjectFolder;
        String workingDirectory = System.getProperty("user.dir");
        projectDirURI = workingDirectory + PROJECTS_NODE + currentProjectFolderName;
        createNewProject();
        initNewProjectFile();
    }

    public static void main(String[] args) {
        new ProjectReader("\\HelloBook");
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
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("book");
            doc.appendChild(rootElement);
            Element browser = doc.createElement("BROWSER");
            browser.appendChild(doc.createTextNode("chrome"));
            rootElement.appendChild(browser);
            Element base = doc.createElement("BASE");
            base.appendChild(doc.createTextNode("http:fut"));
            rootElement.appendChild(base);
            Element employee = doc.createElement("EMPLOYEE");
            rootElement.appendChild(employee);
            Element empName = doc.createElement("EMP_NAME");
            empName.appendChild(doc.createTextNode("Anhorn, Irene"));
            employee.appendChild(empName);
            Element actDate = doc.createElement("ACT_DATE");
            actDate.appendChild(doc.createTextNode("20131201"));
            employee.appendChild(actDate);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
