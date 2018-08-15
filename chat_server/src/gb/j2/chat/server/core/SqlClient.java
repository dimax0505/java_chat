package gb.j2.chat.server.core;

import java.sql.*;

public class SqlClient {

    public static void main(String[] args) {
        SqlClient.connect();
        System.out.println(SqlClient.statement);
        System.out.println(String.format
                ("select nickname from users where login='%s' and password='%s'", "dimax", "123"));
        String request = String.format("select nickname from users where login='%s' and password='%s'", "dimax",
                "123");
        try (ResultSet set = statement.executeQuery(request)) {
            if (set.next()) {
                System.out.println(set.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static Connection connection = null;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:chatDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNickname(String login, String password) {
        String request = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(request)) {
            if (set.next()) {
                return set.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}