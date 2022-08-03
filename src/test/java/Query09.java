import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Query09 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC","root","Bilge12345");

        PreparedStatement tablo=con.prepareStatement("insert into urunler values (?,?,?)");

        // SORU: urunler tablosuna aşağıdaki verileri toplu bir şekilde etkileyin

        List<Urun> kayitlar = new ArrayList<>();
        kayitlar.add(new Urun(200, "Asus", 7500));
        kayitlar.add(new Urun(201, "HP", 8500));
        kayitlar.add(new Urun(202, "Acer", 9500));
        kayitlar.add(new Urun(203, "Monster", 11500));
        kayitlar.add(new Urun(204, "Klavye", 1200));
        kayitlar.add(new Urun(205, "Fare", 1000));

        for (Urun each:kayitlar) {
            tablo.setInt(1,each.getId());
            tablo.setString(2,each.getIsim());
            tablo.setDouble(3,each.getFiyat());

            tablo.addBatch();// Butun verileri biraraya getirir

        }

        tablo.executeBatch();
        System.out.println("veriler database eklendi");
    }

}
