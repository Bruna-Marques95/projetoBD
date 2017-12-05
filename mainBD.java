import java.sql.*;

public class mainBD {
    public static void main(String[] args) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

        }catch(ClassNotFoundException e){
            System.out.println("O driver não foi encontrado, verificar se este existe.");
            e.printStackTrace();

        }
        System.out.println("O JDBC Driver funciona ... a tentar efectuar uma ligacao");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
