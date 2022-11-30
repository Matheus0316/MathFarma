package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private String url;
    private Connection con;
    
    public Conexao(){
        url = "jdbc:postgresql://localhost:5432/MathFarma";
        try {
            con = DriverManager.getConnection(url, "postgres" ,"230006004");
            System.out.println("Conectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int executarSQL(String sql) throws SQLException{
        try {
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(sql);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        finally{
            con.close();
        }
    }
    
    public ResultSet executaBusca(String sql) throws SQLException{
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        finally{
            con.close();
        }
    }
}
