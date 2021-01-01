import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url = "jdbc:sqlserver://DESKTOP-K878DF0\\SQLEXPRESS:1434;database=servicecenter";
    private final String username = "sa";
    private final String password = "eziz1122";
    Connection con = null;

    public Connection createConnection() {
        try {
            con = DriverManager.getConnection(url, username, password);
            //  System.out.println("Connection completed");
        } catch (
                SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return con;
    }

    public void connectionLost() {
        try {
            this.con.close();
            //      System.out.println("Connection closed.");

        } catch (
                SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

//    public Connection connectionLost() {
//        try {
//            this.con.close();
//            //      System.out.println("Connection closed.");
//
//        } catch (
//                SQLException e) {
//            System.out.println("Error");
//            e.printStackTrace();
//        }
//  return con;  }
}
