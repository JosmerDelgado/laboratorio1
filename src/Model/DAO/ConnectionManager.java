package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static public Connection conect(){
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
             connection = DriverManager.getConnection("jdbc:h2:"+System.getProperty("user.dir")+"/DDBB/test", "sa", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
