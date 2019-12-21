package filesystem;

import database.models.Author;
import database.models.BookCharacter;
import database.models.Chapter;
import filesystem.models.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public class FileWorker {

    private static final String PROJECT_TAG = "project";
    private static final String TYPE_TAG = "type";
    private static final String WORLD_DESC_TAG = "world";
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

    public static Document constructProjectFile(Project p) throws ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement(PROJECT_TAG);
        Element name = doc.createElement(BOOK_NAME_TAG);
        Element authors_el = doc.createElement(ALL_AUTHORS_TAG);
        Element t = doc.createElement(TYPE_TAG);
        Element worldDescription = doc.createElement(WORLD_DESC_TAG);
        Element bookIntroduction = doc.createElement(BOOK_INTRO_TAG);
        Element chapters = doc.createElement(ALL_CHAPTERS_TAG);
        Element characters = doc.createElement(ALL_CHARACTERS_TAG);

        worldDescription.appendChild(doc.createTextNode(p.getWorldDescription()));
        bookIntroduction.appendChild(doc.createTextNode(p.getBookIntro()));
        name.appendChild(doc.createTextNode(p.getName()));
        t.appendChild(doc.createTextNode(p.getType()));
        for (Author a : p.getAuthors()){
            Element author = doc.createElement(AUTHOR_TAG);
            Element authorName = doc.createElement(AUTHOR_NAME_TAG);
            Element authorEmail = doc.createElement(AUTHOR_EMAIL_TAG);
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
            chapterBody.appendChild(doc.createTextNode(c.getTitle()));
            chapterTitle.appendChild(doc.createTextNode(c.getTitle()));
            chapter.appendChild(chapterTitle);
            chapter.appendChild(chapterBody);
            chapters.appendChild(chapter);
        }

        root.appendChild(worldDescription);
        root.appendChild(bookIntroduction);
        root.appendChild(t);
        root.appendChild(authors_el);
        root.appendChild(name);
        doc.appendChild(root);
        return doc;
    }
}
