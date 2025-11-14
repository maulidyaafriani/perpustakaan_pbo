package backend;

import java.util.ArrayList;
import java.sql.*;

public class Kategori {
    private int idkategori;
    private String nama;
    private String keterangan;

    // Constructor default
    public Kategori() {
    }

    // Constructor custom
    public Kategori(String nama, String keterangan) {
        this.nama = nama;
        this.keterangan = keterangan;
    }

    // Getter dan Setter
    public int getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(int idkategori) {
        this.idkategori = idkategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    // Method getById
    public Kategori getById(int id) {
        Kategori kat = new Kategori();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori WHERE idkategori = " + id);
        
        try {
            if(rs.next()) {
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
            }
        } catch (Exception e) {
            System.out.println("Error getById: " + e.getMessage());
            e.printStackTrace();
        }
        return kat;
    }

    // Method getAll
    public ArrayList<Kategori> getAll() {
        ArrayList<Kategori> ListKategori = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori ORDER BY idkategori");

        try {
            while(rs.next()) {
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                ListKategori.add(kat);
            }
        } catch (Exception e) {
            System.out.println("Error getAll: " + e.getMessage());
            e.printStackTrace();
        }
        return ListKategori;
    }

    // Method search
    public ArrayList<Kategori> search(String keyword) {
        ArrayList<Kategori> ListKategori = new ArrayList<>();
        String sql = "SELECT * FROM kategori WHERE "
                   + "nama ILIKE '%" + keyword + "%' "
                   + "OR keterangan ILIKE '%" + keyword + "%' "
                   + "ORDER BY idkategori";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try {
            while(rs.next()) {
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                ListKategori.add(kat);
            }
        } catch (Exception e) {
            System.out.println("Error search: " + e.getMessage());
            e.printStackTrace();
        }
        return ListKategori;
    }

    // Method save
    public void save() {
        try {
            if (this.idkategori == 0) {
                // INSERT data baru
                String SQL = "INSERT INTO kategori (nama, keterangan) VALUES("
                           + "'" + this.nama + "', "
                           + "'" + this.keterangan + "'"
                           + ")";
                this.idkategori = DBHelper.insertQueryGetId(SQL);
                System.out.println("INSERT berhasil - ID: " + this.idkategori);
            } else {
                // UPDATE data existing
                String SQL = "UPDATE kategori SET "
                           + "nama = '" + this.nama + "', "
                           + "keterangan = '" + this.keterangan + "' "
                           + "WHERE idkategori = " + this.idkategori;
                DBHelper.executeQuery(SQL);
                System.out.println("UPDATE berhasil - ID: " + this.idkategori);
            }
        } catch (Exception e) {
            System.out.println("Error save: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method delete
    public void delete() {
        try {
            String SQL = "DELETE FROM kategori WHERE idkategori = " + this.idkategori;
            DBHelper.executeQuery(SQL);
            System.out.println("DELETE berhasil - ID: " + this.idkategori);
        } catch (Exception e) {
            System.out.println("Error delete: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Override toString untuk combo box
    @Override
    public String toString() {
        return nama;
    }
}