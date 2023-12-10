package boboyuks.Database;

import java.sql.*;

public class DBConnect {
    public static Connection connection;
    public static Statement st;
   
    
    public DBConnect() {
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MYSQL://localhost:3306/boboyuks";
        SUser = "PBO";
        SPass = "root";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.connection = con;
            this.st = st;
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }
    
    public Statement getStatement() {
        return this.st;
    }
}
