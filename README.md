# Janji
Saya Naufal Zahid dengan NIM 2405787 mengerjakan TP 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Penjelasan Desain dan Flow Code
1. Class Karakter

   Class ini berfungsi sebagai model data (data model) untuk setiap karakter.

   Atribut:

   - id: String (ID unik karakter).

   - nama: String (Nama karakter).

   - status: String (Pilihan: Murni, Campuran, Muggle).

   - peran: String (Pilihan: Guru, Murid, Penjahat).

   - asrama: String (Pilihan: Gryffindor, Slytherin, Ravenclaw, Hufflepuff).

   Method:

   - Konstruktor: Untuk inisialisasi objek karakter.

   - Getter dan Setter: Untuk mengakses dan memodifikasi atribut privat.

3. Class Database

   Class ini menangani semua interaksi dengan database MySQL menggunakan Java JDBC.

   - Koneksi: Membuat koneksi ke database dengan URL: "jdbc:mysql://localhost:3306/HarryPotter", user "root", dan kata sandi kosong.

   Method:

   - selectQuery(String sql): Untuk mengeksekusi perintah SELECT. Mengembalikan objek ResultSet.

   - insertUpdateDeleteQuery(String sql): Untuk mengeksekusi perintah INSERT, UPDATE, atau DELETE. Mengembalikan jumlah baris yang terpengaruh.

4. Class KarakterMenu (GUI)

   Antarmuka pengguna dibuat menggunakan Java Swing melalui GUI Designer (IntelliJ IDEA) dan merupakan controller utama yang mengelola logika bisnis dan interaksi database.

   Elemen Utama GUI:

   - JTextField: Input untuk ID dan Nama karakter.

   - JComboBox: Pemilihan Status, Peran, dan Asrama dengan opsi default "???".

   - JTable: Menampilkan daftar semua karakter dari database dalam model tabel.

   - JButton: Tombol Add / Update, Delete, dan Cancel.
     
## alur atau flow codenya :

1. Inisialisasi dan Tampilan Data (Read)
   - Saat $KarakterMenu$ dibuat, objek Database diinisialisasi untuk membuat koneksi.
  
   - Method loadTableData() dipanggil.
  
   - Method ini mengeksekusi SELECT * FROM karakter melalui $Database.selectQuery() dan mengisi JTable dengan data dari $ResultSet$.

2. Menambahkan Karakter Baru (Create)
   - Pengguna mengisi form dan menekan tombol Add.
  
   - Dilakukan validasi input (kolom tidak boleh kosong dan ComboBox tidak boleh "???").
  
   - Dicek apakah ID sudah ada di database dengan SELECT * FROM karakter WHERE id='...'.
  
   - Jika ID unik, perintah INSERT dikirim ke database melalui $Database.insertUpdateDeleteQuery()$.
  
   - Setelah berhasil, tabel dimuat ulang (loadTableData()) dan form dibersihkan (clearForm()).

3. Mengubah Karakter (Update)
   - Memilih baris di $JTable$ akan mengisi form dengan data karakter dan mengubah tombol menjadi Update.
  
   - Saat tombol Update ditekan, perintah UPDATE dikirim ke database ($Database.insertUpdateDeleteQuery()$) untuk memodifikasi data berdasarkan ID karakter.
  
   - Tabel dimuat ulang dan form dibersihkan.

4. Menghapus Karakter (Delete)
   - Tombol Delete akan terlihat setelah memilih baris.
  
   - Setelah konfirmasi, perintah DELETE dikirim ke database ($Database.insertUpdateDeleteQuery()$) berdasarkan ID karakter.
  
   - Tabel dimuat ulang dan form dibersihkan.

5. Membatalkan Input (Cancel)
   - Tombol Cancel memanggil clearForm(), yang mengosongkan semua field, mengatur ulang ComboBox, dan mengembalikan tombol ke mode Add.
  
6. Menutup Program
   - Program GUI dapat ditutup melalui tombol close (X) di jendela aplikasi.

# Dokumentasi Program Berhasil Berjalan
