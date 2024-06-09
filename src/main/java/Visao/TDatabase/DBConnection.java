/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visao.TDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sato
 */
public class DBConnection {
    private static String host = "localhost";
    private static String porta = "3306";
    private static String db = "escola"; 
    private static String usuario = "root"; //usuario Alunos
    private static String senha = "root"; //senha alunos
    
     public static Connection obtemConexao() {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + porta + "/" + db + 
                "?useTimezone=true&serverTimezone=UTC",
                usuario, senha
            );
            return c;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
