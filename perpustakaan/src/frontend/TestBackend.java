package frontend;

import backend.*;

public class TestBackend {
    public static void main(String[] args) {
        testKategori();
    }

    public static void testKategori() {
        System.out.println("=== TEST CRUD KATEGORI ===");
        
        // Test 1: Insert data baru
        System.out.println("\n1. TEST INSERT:");
        Kategori kat1 = new Kategori("Novel", "Koleksi buku novel");
        Kategori kat2 = new Kategori("Referensi", "Buku referensi ilmiah");
        Kategori kat3 = new Kategori("Komik", "Komik anak-anak");
        
        kat1.save();
        kat2.save();
        kat3.save();
        
        // Test 2: Get by ID
        System.out.println("\n2. TEST GET BY ID:");
        Kategori katTest = new Kategori().getById(kat1.getIdkategori());
        System.out.println("Data ditemukan: " + katTest.getNama() + " - " + katTest.getKeterangan());
        
        // Test 3: Update data
        System.out.println("\n3. TEST UPDATE:");
        kat2.setKeterangan("Koleksi buku referensi ilmiah terbaru");
        kat2.save();
        
        // Test 4: Get All data
        System.out.println("\n4. TEST GET ALL:");
        for(Kategori k : new Kategori().getAll()) {
            System.out.println("ID: " + k.getIdkategori() + " | Nama: " + k.getNama() + " | Ket: " + k.getKeterangan());
        }
        
        // Test 5: Search data
        System.out.println("\n5. TEST SEARCH:");
        for(Kategori k : new Kategori().search("novel")) {
            System.out.println("Hasil search: " + k.getNama() + " - " + k.getKeterangan());
        }
        
        // Test 6: Delete data
        System.out.println("\n6. TEST DELETE:");
        kat3.delete();
        
        // Tampilkan data akhir
        System.out.println("\n7. DATA AKHIR:");
        for(Kategori k : new Kategori().getAll()) {
            System.out.println("ID: " + k.getIdkategori() + " | Nama: " + k.getNama() + " | Ket: " + k.getKeterangan());
        }
    }
}