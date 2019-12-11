package database;

import com.sun.istack.internal.Nullable;
import database.models.Book;
import database.models.BookCharacter;

import java.sql.*;

public class WorldCreatorDatabase {
    private final String DB_TEST_URL = "jdbc:sqlite:src/main/resources/databases/WorldCreator.db";
    private final String AUTHOR_TABLE_NAME = "author";
    private final String BOOK_TABLE_NAME = "book";
    private final String CHAPTER_TABLE_NAME = "chapter";
    private final String CHARACTER_TABLE_NAME = "characters";
    private final String BOOK_AUTHOR_TABLE_NAME = "bookauthor";
    private final String LAST_PROJECT = "last";

    public static void main(String[] args) {
        WorldCreatorDatabase w = new WorldCreatorDatabase();
        try {
            System.out.println("last");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void recreateAllTables() throws Exception {
        dropAllTables();
        createTableAuthor();
        createTableBook();
        createTableBookAuthor();
        createTableChapter();
        createTableCharacter();
    }

    public Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_TEST_URL);
    }

    public void createTableAuthor() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + AUTHOR_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(50)   NOT NULL," +
                " surname        CHAR(50)  ," +
                " email          CHAR(50) " +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    public void createTableLast() throws Exception {
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = "CREATE TABLE " + LAST_PROJECT +
                    "(id             INTEGER    PRIMARY KEY ," +
                    "last_id        INTEGER   NOT NULL" +
                    ")";

            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }

    public void putLastId(int lastId){
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            // String sql = String.format("INSERT INTO %s (id, last_id) VALUES (1, %d)", LAST_PROJECT, lastId );

            String sql = String.format("UPDATE %s SET last_id = %d", LAST_PROJECT, lastId);
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }

    @Nullable
    public Integer getLastId(){
        try{
            int id = 0;
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = String.format("SELECT last_id FROM %s WHERE id = %d", LAST_PROJECT, 1);

            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                id = rs.getInt("last_id");
            }

            stmt.close();
            c.close();
            if(id > 0){
                return id;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
        return null;
    }

    public void createTableBook() throws Exception {

        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = "CREATE TABLE " + BOOK_TABLE_NAME +
                    "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                    " name           CHAR(100)   NOT NULL," +
                    " intro          TEXT ," +
                    " world          TEXT ," +
                    " description    TEXT " +
                    ")";

            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }

    public void createTableChapter() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + CHAPTER_TABLE_NAME +
                "(id                  INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " title                CHAR(100)   NOT NULL," +
                " body                TEXT ," +
                " book_order          INTEGER," +
                " book_id             INTEGER ," +
                "FOREIGN KEY(book_id) REFERENCES " + BOOK_TABLE_NAME + "(id)" +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    public void createTableBookAuthor() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + BOOK_AUTHOR_TABLE_NAME +
                "(book_id             INTEGER     NOT NULL ," +
                " author_id             INTEGER     NOT NULL," +
                "FOREIGN KEY(book_id) REFERENCES " + BOOK_TABLE_NAME + "(id), " +
                "FOREIGN KEY(author_id) REFERENCES " + AUTHOR_TABLE_NAME + "(id)" +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    public void createTableCharacter() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "CREATE TABLE " + CHARACTER_TABLE_NAME +
                "(id             INTEGER    PRIMARY KEY    AUTOINCREMENT   NOT NULL," +
                " name           CHAR(100)   NOT NULL," +
                " history           TEXT ," +
                " book_id         INTEGER ," +
                "FOREIGN KEY(book_id) REFERENCES " + BOOK_TABLE_NAME + "(id)" +
                ")";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    public void dropTable(String tableName) throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "DROP TABLE " + tableName;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    public void dropAllTables() throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "DROP TABLE " + AUTHOR_TABLE_NAME + ";";
        sql += "DROP TABLE " + BOOK_TABLE_NAME + ";";
        sql += "DROP TABLE " + BOOK_AUTHOR_TABLE_NAME + ";";
        sql += "DROP TABLE " + CHAPTER_TABLE_NAME + ";";
        sql += "DROP TABLE " + CHARACTER_TABLE_NAME + ";";
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    @Nullable
    public Book getBookById(int id) throws Exception {
        Book book = new Book();
        boolean found = false;
        book.setId(id);

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT name, decription, intro, world FROM " + BOOK_TABLE_NAME + " ()" +
                "WHERE id = " + id + " LIMIT 1;";

        ResultSet rs = stmt.executeQuery(sql);

        found = rs.next();
        if (found) {
            book.setName(rs.getString("name"));
            book.setDescription(rs.getString("description"));
            book.setIntro(rs.getString("intro"));
            book.setWorld(rs.getString("world"));
        }

        conn.close();
        stmt.close();

        if (found) {
            return book;
        } else {
            return null;
        }
    }

    @Nullable
    public Book getBookByName(String name) throws Exception {
        Book book = new Book();
        boolean found = false;
        book.setName(name);

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT name, decription, intro, world FROM " + BOOK_TABLE_NAME + " ()" +
                "WHERE name = " + name + " LIMIT 1;";

        ResultSet rs = stmt.executeQuery(sql);

        found = rs.next();
        if (found) {
            book.setId(rs.getInt("id"));
            book.setDescription(rs.getString("description"));
            book.setIntro(rs.getString("intro"));
            book.setWorld(rs.getString("world"));
        }

        conn.close();
        stmt.close();

        if (found) {
            return book;
        } else {
            return null;
        }
    }

    @Nullable
    public BookCharacter getCharacterByName(String name) throws Exception {
        BookCharacter character = new BookCharacter();
        boolean found = false;
        character.setName(name);

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT id, name, history, book_id FROM " + CHARACTER_TABLE_NAME + " ()" +
                "WHERE name = " + name + " LIMIT 1;";

        ResultSet rs = stmt.executeQuery(sql);
        found = rs.next();
        if (found) {
            character.setName(rs.getString("name"));
            character.setId(rs.getInt("id"));
            character.setHistory(rs.getString("history"));
        }

        conn.close();
        stmt.close();

        if (found) {
            return character;
        } else {
            return null;
        }
    }

    @Nullable
    public BookCharacter getCharacterById(int id) throws Exception {
        BookCharacter character = new BookCharacter();
        boolean found = false;
        character.setId(id);

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT name, history, book_id FROM " + CHARACTER_TABLE_NAME + " ()" +
                "WHERE id = " + id + " LIMIT 1;";

        ResultSet rs = stmt.executeQuery(sql);

        found = rs.next();
        if (found) {
            character.setName(rs.getString("name"));
            character.setHistory(rs.getString("history"));
        }

        conn.close();
        stmt.close();

        if (found) {
            return character;
        } else {
            return null;
        }
    }

    public void putBook(Book b) throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        Book c_2 = getBookByName(b.getName());
        String sql;
        if (c_2 == null) {
            sql = "INSERT INTO " + BOOK_TABLE_NAME + " (id, name, world, description, intro)" +
                    "VALUES (null , '" + b.getName() + "', '" + b.getWorld() + "', '" + b.getDescription() + "', '"+b.getIntro()+"');";
        } else {
            sql = "UPDATE " + BOOK_TABLE_NAME + "SET name = '" + b.getName() + "', description = '"
                    + b.getDescription() + "', world = '" + b.getWorld() + "', intro = '"+b.getIntro()+"' WHERE id = " + b.getId() + ";";
        }

        stmt.executeUpdate(sql);
        conn.close();
        stmt.close();
    }

    public void putCharacter(BookCharacter c) throws Exception {

        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        BookCharacter c_2 = getCharacterByName(c.getName());
        String sql;
        if (c_2 == null) {
            sql = "INSERT INTO " + CHARACTER_TABLE_NAME + " (id, name, history, book_id)" +
                    "VALUES (null , '" + c.getName() + "', '" + c.getHistory() + "', " + c.getBook().getId() + ");";
        } else {
            sql = "UPDATE " + CHARACTER_TABLE_NAME + "SET name = '" + c.getName() + "', history = '"
                    + c.getHistory() + "' WHERE id = " + c.getId() + ";";
        }

        stmt.executeUpdate(sql);
        conn.close();
        stmt.close();
    }
}
