import java.sql.*;

public class Query07 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/okul?serverTimezone=UTC","root","Bilge12345");

        Statement st= con.createStatement();

        // STATEMENT, PREPAREDSTATEMENT FARKI

        // STATEMENT:
        st.executeUpdate("insert into ogrenciler values (200,'Ali Can',10,'E') ");

        //PREPAREDSTATEMENT: Ifadeler parametre kullanilarak yollanabilir
        PreparedStatement ps=con.prepareStatement("insert into ogrenciler values (?,?,?,?) ");
        ps.setInt(1,201);
        ps.setString(2, "Veli Can");
        ps.setString(3, "12");
        ps.setString(4, "E");

        ps.executeUpdate();

        System.out.println("Veri girisi yapildi");
    }
}
