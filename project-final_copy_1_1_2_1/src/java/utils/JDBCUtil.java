
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    
//    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=HelmetStore";
//    private static final String user = "sa";
//    private static final String password = "12345";
    private final static String serverName = "localhost";
    private final static String dbName = "HelmetStore";
    private final static String portNumber = "1433";
    private final static String instance = "";//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final static String userID = "sa";
    private final static String password = "12345";
        
    public static Connection getConnection(){
        
        String url;        
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        } else{
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        }
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("HelmetStore: Can not load JDBC Driver. Please check your pom file");
        }
        
        try {
            Connection con = DriverManager.getConnection(url, userID, password);
            return con;
        } catch (SQLException ex) {
            System.out.println("HelmetStore: Can not connect SQL Server. Reason: " + ex.getMessage());                        
        }
        return null;
    }
    
     public static void close(Connection c){
         if(c!=null){
             try {
                 c.close();
             } catch (Exception e) {
                System.out.println("Close database thất bại");
                e.printStackTrace();
             }
         }
     }
 
}
