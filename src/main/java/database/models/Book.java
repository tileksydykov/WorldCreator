package database.models;

import database.models.modelhelper.Modelnterface;

public class Book implements Modelnterface {
    private int id = 0;
    private String name = "";
    private String world = "";
    private String description;
    private String intro;
    private Author[] authors;

    public Book() {}

    public Book(String name, String world, String description, String intro) {
        this.name = name;
        this.world = world;
        this.description = description;
        this.intro = intro;
    }

    public Book(String name, String world, String description, String intro, Author[] authors) {
        this.name = name;
        this.world = world;
        this.description = description;
        this.intro = intro;
        this.authors = authors;
    }

    public Book(int id, String name, String world, String description, String intro, Author[] authors) {
        this.id = id;
        this.name = name;
        this.world = world;
        this.description = description;
        this.intro = intro;
        this.authors = authors;
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

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthor(Author[] author) {
        this.authors = author;
    }
}
