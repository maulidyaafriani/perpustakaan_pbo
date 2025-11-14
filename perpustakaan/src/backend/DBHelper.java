package backend;

import java.sql.*;

public class DBHelper {
    private static Connection koneksi;

    public static void bukaKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:postgresql://localhost:5432/dbperpus";
                String user = "postgres";
                String password = "password"; // GANTI DENGAN PASSWORD POSTGRESQL ANDA
                
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi database berhasil!");
            } catch (SQLException e) {
                System.out.println("Error koneksi: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static int insertQueryGetId(String query) {
        bukaKoneksi();
        int result = -1;

        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error insertQueryGetId: " + e.getMessage());
            System.out.println("Query: " + query);
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public static boolean executeQuery(String query) {
        bukaKoneksi();
        boolean result = false;

        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query);
            result = true;
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error executeQuery: " + e.getMessage());
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet selectQuery(String query) {
        bukaKoneksi();
        ResultSet rs = null;
        try {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error selectQuery: " + e.getMessage());
            System.out.println("Query: " + query);
            e.printStackTrace();
        }
        return rs;
    }
}