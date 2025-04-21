
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Databse {

    public static Connection initializeDatabase() {
        Connection connection = null;
        try {

            String url = "jdbc:sqlite:serverLogs.db";
            connection = DriverManager.getConnection(url);
            
            String createTableQuery = "CREATE TABLE IF NOT EXISTS logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "date TEXT NOT NULL," +
                    "time TEXT NOT NULL," +
                    "ip TEXT NOT NULL," +
                    "port INTEGER NOT NULL" +
                    ");";

            Statement stmt = connection.createStatement();
            stmt.execute(createTableQuery);

            // System.out.println("Connection to SQLite has been established.");
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
