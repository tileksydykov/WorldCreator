package filesystem;

import database.models.Author;
import database.models.BookCharacter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public class FileWorker {
    public static Document constructCharacterFile(BookCharacter c) throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("character");
        doc.appendChild(root);
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(c.getName()));
        root.appendChild(name);
        Element history = doc.createElement("history");
        history.appendChild(doc.createTextNode(c.getHistory()));
        root.appendChild(history);
        return doc;
    }
    public static Document constructProjectFile(String bookName, ArrayList<Author> authors) throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("project");
        doc.appendChild(root);
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(bookName));
        root.appendChild(name);
        Element authors_el = doc.createElement("authors");
        for (Author a : authors){
            Element author = doc.createElement("author");
            Element authorName = doc.createElement("name");
            authorName.appendChild(doc.createTextNode(a.getName()));
            author.appendChild(authorName);
            Element authorEmail = doc.createElement("email");
            authorEmail.appendChild(doc.createTextNode(a.getEmail()));
            author.appendChild(authorEmail);
            authors_el.appendChild(author);
        }
        root.appendChild(authors_el);

        return doc;
    }
}
