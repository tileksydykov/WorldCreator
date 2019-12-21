package filesystem.models;

import database.models.Author;
import database.models.Chapter;

import java.util.ArrayList;

public class Project {
    private int id;
    private String type;
    private String fileName;
    private String name;
    private String worldDescription;
    private String bookDescription;
    private ArrayList<Author> authors;
    private String bookIntro;
    private ArrayList<Character> characters;
    private ArrayList<Chapter> chapters;

    public Project() {
        this.id = 0;
        this.type = "";
        this.fileName = "empty";
        this.name = "emptyBook";
        this.worldDescription = "World Description";
        this.bookDescription = "";
        this.authors = new ArrayList<>();
        this.bookIntro = "";
        this.characters = new ArrayList<>();
        this.chapters = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorldDescription() {
        return worldDescription;
    }

    public void setWorldDescription(String worldDescription) {
        this.worldDescription = worldDescription;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public void setBookIntro(String bookIntro) {
        this.bookIntro = bookIntro;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }
}
