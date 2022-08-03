import java.sql.*;

public class Query03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC", "root", "Bilge12345");

        Statement st = con.createStatement();

        // SORU1: Bölümler tablosundan tüm kayıtları listeleyiniz.

        ResultSet veri = st.executeQuery("select * from bolumler");

        while (veri.next()) {
            System.out.println(veri.getInt(1) + veri.getString(2) +
                    veri.getString(3));


        }

        // SORU2:SATIS ve MUHASEBE bolumlerinde calişan personelin isimlerini ve maaşlarını,
// maaş ters sıralı listeleyiniz.

        System.out.println("================================================");

        ResultSet veri1 = st.executeQuery("select personel_isim, maas from personel " +
                "where bolum_id in(10,30) order by maas desc");

        while (veri1.next()) {
            System.out.println(veri1.getString(1) + veri1.getInt(2));

        }

        System.out.println("================================================");

        // SORU3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini ve
        //  maaslarini, bolum ters ve maas sirali listeleyiniz.
        //  NOT: calisani olmasa bile bolum ismi gosterilmelidir.

        ResultSet veri2 = st.executeQuery("select bolum_isim, personel_isim, maas " +
                "from bolumler as B left join personel as P" +
                "on B.bolum_id = P.bolum_id" +
                "order by bolum_isim desc, P.maas");

        while (veri2.next()) {
            System.out.println(veri2.getString(1) + veri2.getString(2) + veri2.getInt(3));
        }

        con.close();
        st.close();
        veri.close();

        // SORU: Maasi en yuksek 10 kisinin bolumunu, adini ve maasini listeleyiniz

        ResultSet veri4 = st.executeQuery("select p.personel_isim, b.bolum_isim, p.maas " +
                "from bolumler b left join personel p on b.bolum_id=p.bolum_id order by maas " +
                "desc limit 10");

        while (veri4.next()) {

            System.out.printf("%-10s %-10s %6d\n", veri4.getString(1), veri4.getString(2), veri4.getInt(3));


        }

    }
}