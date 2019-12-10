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

    public static void main(String[] args) {
        WorldCreatorDatabase w = new WorldCreatorDatabase();

        try {
            w.recreateAllTables();
            System.out.println("all recreated ");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void recreateAllTables() throws Exception {
        dropAllTables();
        createTableAuthor();
        createTableBook();
        createTableBookAuthor();
        createTableChapter();
        createTableCharacter();
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_TEST_URL);
    }

    private void createTableAuthor() throws Exception {
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

    private void createTableBook() throws Exception {
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
    }

    private void createTableChapter() throws Exception {
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

    private void createTableBookAuthor() throws Exception {
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

    private void createTableCharacter() throws Exception {
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

    private void dropTable(String tableName) throws Exception {
        Connection c = getConnection();
        Statement stmt = c.createStatement();

        String sql = "DROP TABLE " + tableName;
        stmt.executeUpdate(sql);

        stmt.close();
        c.close();
    }

    private void dropAllTables() throws Exception {
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
    private Book getBookById(int id) throws Exception {
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
    private Book getBookByName(String name) throws Exception {
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
    private BookCharacter getCharacterByName(String name) throws Exception {
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
    private BookCharacter getCharacterById(int id) throws Exception {
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

    private void putBook(Book b) throws Exception {
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

    private void putCharacter(BookCharacter c) throws Exception {

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
