package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:postgresql://pedago.univ-avignon.fr:5432/etd";
    private static final String USER = "uapv2401806";
    private static final String PASSWORD = "jrNija";
    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
