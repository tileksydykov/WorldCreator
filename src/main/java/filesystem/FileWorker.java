package filesystem;

import database.models.Author;
import database.models.BookCharacter;
import database.models.Chapter;
import filesystem.models.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public class FileWorker {

    private static  final String ID = "id";

    private static final String PROJECT_TAG = "project";
    private static final String TYPE_TAG = "type";
    private static final String WORLD_DESC_TAG = "world";
    private static final String BOOK_DESC_TAG = "book";
    private static final String BOOK_NAME_TAG = "name";
    private static final String BOOK_INTRO_TAG = "intro";

    private static final String AUTHOR_TAG = "author";
    private static final String ALL_AUTHORS_TAG = "authors";
    private static final String AUTHOR_NAME_TAG = "authorName";
    private static final String AUTHOR_EMAIL_TAG = "authorEmail";

    private static final String CHARACTER_TAG = "character";
    private static final String ALL_CHARACTERS_TAG = "characters";
    private static final String CHARACTER_NAME_TAG = "characterName";
    private static final String CHARACTER_HISTORY_TAG = "history";
    private static final String CHARATER_RELATION_TAG = "relation";

    private static final String CHAPTER_TAG = "chapter";
    private static final String ALL_CHAPTERS_TAG = "chapters";
    private static final String CHAPTER_TITLE_TAG = "title";
    private static final String CHAPTER_BODY_TAG = "body";

    public static Document constructProjectFile(Project p) throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement(PROJECT_TAG);
        Element name = doc.createElement(BOOK_NAME_TAG);
        Element authors_el = doc.createElement(ALL_AUTHORS_TAG);
        Element t = doc.createElement(TYPE_TAG);
        Element worldDescription = doc.createElement(WORLD_DESC_TAG);
        Element bookIntroduction = doc.createElement(BOOK_INTRO_TAG);
        Element bookDescription = doc.createElement(BOOK_DESC_TAG);
        Element chapters = doc.createElement(ALL_CHAPTERS_TAG);
        Element characters = doc.createElement(ALL_CHARACTERS_TAG);

        worldDescription.appendChild(doc.createTextNode(p.getWorldDescription()));
        bookIntroduction.appendChild(doc.createTextNode(p.getBookIntro()));
        name.appendChild(doc.createTextNode(p.getName()));
        t.appendChild(doc.createTextNode(p.getType()));
        bookDescription.appendChild(doc.createTextNode(p.getBookDescription()));
        for (Author a : p.getAuthors()){
            Element author = doc.createElement(AUTHOR_TAG);
            Element authorName = doc.createElement(AUTHOR_NAME_TAG);
            Element authorEmail = doc.createElement(AUTHOR_EMAIL_TAG);
            author.setAttribute(ID, a.getId().toString());
            authorName.appendChild(doc.createTextNode(a.getName()));
            authorEmail.appendChild(doc.createTextNode(a.getEmail()));
            author.appendChild(authorName);
            author.appendChild(authorEmail);
            authors_el.appendChild(author);
        }
        for (BookCharacter c : p.getCharacters()){
            Element character = doc.createElement(CHARACTER_TAG);
            Element characterName = doc.createElement(CHARACTER_NAME_TAG);
            Element characterHistory = doc.createElement(CHARACTER_HISTORY_TAG);
            Element characterRelation = doc.createElement(CHARATER_RELATION_TAG);
            character.setAttribute(ID, c.getId().toString());
            characterName.appendChild(doc.createTextNode(c.getName()));
            characterHistory.appendChild(doc.createTextNode(c.getHistory()));
            characterRelation.appendChild(doc.createTextNode(c.getRelation()));
            character.appendChild(characterName);
            character.appendChild(characterHistory);
            character.appendChild(characterRelation);
            characters.appendChild(character);
        }
        for (Chapter c : p.getChapters()){
            Element chapter = doc.createElement(CHAPTER_TAG);
            Element chapterBody = doc.createElement(CHAPTER_BODY_TAG);
            Element chapterTitle = doc.createElement(CHAPTER_TITLE_TAG);
            chapter.setAttribute(ID, c.getId().toString());
            chapterBody.appendChild(doc.createTextNode(c.getTitle()));
            chapterTitle.appendChild(doc.createTextNode(c.getTitle()));
            chapter.appendChild(chapterTitle);
            chapter.appendChild(chapterBody);
            chapters.appendChild(chapter);
        }

        root.appendChild(name);
        root.appendChild(t);
        root.appendChild(worldDescription);
        root.appendChild(bookDescription);
        root.appendChild(bookIntroduction);
        root.appendChild(characters);
        root.appendChild(chapters);
        root.appendChild(authors_el);
        doc.appendChild(root);
        return doc;
    }

    public static Project readProjectFile(Document doc){
        doc.getDocumentElement().normalize();
        Project p = new Project();
        Element root = (Element) doc.getElementsByTagName(PROJECT_TAG).item(0);

        // retrieve all data from doc
        p.setType(root.getElementsByTagName(TYPE_TAG).item(0).getTextContent());
        p.setName(root.getElementsByTagName(BOOK_NAME_TAG).item(0).getTextContent());
        p.setWorldDescription(root.getElementsByTagName(WORLD_DESC_TAG).item(0).getTextContent());
        p.setBookIntro(root.getElementsByTagName(BOOK_INTRO_TAG).item(0).getTextContent());
        p.setBookDescription(root.getElementsByTagName(BOOK_DESC_TAG).item(0).getTextContent());

        // get authors from doc
        Element authors = (Element) doc.getElementsByTagName(ALL_AUTHORS_TAG).item(0);
        NodeList aList = authors.getElementsByTagName(AUTHOR_TAG);
        ArrayList<Author> authorsArray = new ArrayList<>();
        for (int temp = 0; temp < aList.getLength(); temp++) {
            Node nNode = aList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Author a = new Author();
                Element eElement = (Element) nNode;
                a.setId(Integer.valueOf(eElement.getAttribute(ID)));
                a.setName(eElement.getElementsByTagName(AUTHOR_NAME_TAG).item(0).getTextContent());
                a.setEmail(eElement.getElementsByTagName(AUTHOR_EMAIL_TAG).item(0).getTextContent());
                authorsArray.add(a);
            }
        }
        p.setAuthors(authorsArray);

        // get chapters from doc
        Element chapters = (Element) doc.getElementsByTagName(ALL_CHAPTERS_TAG).item(0);
        NodeList cList = chapters.getElementsByTagName(CHAPTER_TAG);
        ArrayList<Chapter> chaptersArray = new ArrayList<>();
        for (int temp = 0; temp < cList.getLength(); temp++) {
            Node nNode = cList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Chapter a = new Chapter();
                Element eElement = (Element) nNode;
                a.setId(Integer.valueOf(eElement.getAttribute(ID)));
                a.setTitle(eElement.getElementsByTagName(CHAPTER_TITLE_TAG).item(0).getTextContent());
                a.setBody(eElement.getElementsByTagName(CHAPTER_BODY_TAG).item(0).getTextContent());
                chaptersArray.add(a);
            }
        }
        p.setChapters(chaptersArray);

        // get characters from doc
        Element characters = (Element) doc.getElementsByTagName(ALL_CHARACTERS_TAG).item(0);
        NodeList caList = characters.getElementsByTagName(CHARACTER_TAG);
        ArrayList<BookCharacter> charactersArray = new ArrayList<>();
        for (int temp = 0; temp < caList.getLength(); temp++) {
            Node nNode = caList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                BookCharacter a = new BookCharacter();
                Element eElement = (Element) nNode;
                a.setId(Integer.valueOf(eElement.getAttribute(ID)));
                a.setName(eElement.getElementsByTagName(CHARACTER_NAME_TAG).item(0).getTextContent());
                a.setHistory(eElement.getElementsByTagName(CHARACTER_HISTORY_TAG).item(0).getTextContent());
                a.setRelation(eElement.getElementsByTagName(CHARATER_RELATION_TAG).item(0).getTextContent());

                charactersArray.add(a);
            }
        }
        p.setCharacters(charactersArray);

        return p;
    }
}
