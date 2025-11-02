# Janji
Saya Naufal Zahid dengan NIM 2405787 mengerjakan TP 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Penjelasan Desain dan Flow Code
## 1. **Struktur Class Utama (Model-View-Controller)**

   ### 1.1. **Class Logic (Model & Controller)**

   Kelas ini bertindak sebagai pusat logika (Model) dan pengelola state (Controller) utama game.

   - Tanggung Jawab: Menangani fisika burung (gravitasi, lompatan), pergerakan pipa, deteksi tabrakan, sistem skoring, dan mengelola state game (MENU, RUNNING, PAUSED, GAME_OVER).

   - Komponen Kunci: Menggunakan Timer (gameLoop dan pipesCooldown) untuk pembaruan game tick dan spawning pipa.

   ### 1.2. **Class View (View)**

   Panel utama yang bertanggung jawab penuh atas rendering grafis.

   - Tanggung Jawab: Menggambar burung, pipa, tanah, dan pesan state (seperti pesan awal).

   - Metode Utama: Menggunakan paintComponent(Graphics gg) untuk menggambar ulang seluruh elemen game pada setiap tick dari gameLoop.

   ### 1.3. **Class App (Launcher & Integrasi UI)**

   Kelas utama yang menjalankan aplikasi dan mengintegrasikan semua komponen UI yang berbeda.

   - Tanggung Jawab: Membuat JFrame utama, menginisialisasi Logic dan View, dan menumpuk komponen UI interaktif (JLabel, GameOverPanel, PausePanel) menggunakan JLayeredPane.

## 2. **Komponen UI Interaktif (Overlay Panels)**

   Untuk memberikan pengalaman pengguna yang lengkap dan terstruktur, program menggunakan tiga komponen overlay (lapisan antarmuka) utama yang dibangun menggunakan Java Swing. Kelas-kelas ini disisipkan di atas panel utama permainan (View) menggunakan JLayeredPane di App.java.

   ### 2.1 **Class MainMenu.java**

   - Tipe Komponen: JFrame.

   - Fungsi: Bertindak sebagai launcher aplikasi dan pintu masuk ke permainan.

   - Konten: Menampilkan judul utama ("FLAPPY BIRD") dan dua opsi interaktif: "PLAY GAME" (untuk memulai Game Frame) dan "EXIT PROGRAM" (untuk menutup aplikasi).

   - Desain: Ukuran frame diatur statis $540 \times 800$ agar konsisten dengan ukuran Game Frame. Konten (judul dan tombol) diposisikan di tengah secara vertikal menggunakan kombinasi BoxLayout dan VerticalGlue.

   ### 2.2 **Class GameOverPanel.java**

   - Tipe Komponen: JPanel (semi-transparan).
   - Fungsi: Muncul saat state game beralih ke State.GAME_OVER (setelah tabrakan atau jatuh ke tanah). Panel ini memblokir input mouse/keyboard kecuali tombol 'R'.
   - Konten: Menampilkan pesan "GAME OVER", Skor Anda, Best Skor, dan tombol "KEMBALI KE HOME".
   - Aksi Kunci: Menyediakan opsi navigasi kembali ke MainMenu. Aksi restart utama diaktifkan melalui input tombol 'R' di Logic.java.

   ###2.3 **Class PausePanel.java**

   - Tipe Komponen: JPanel (semi-transparan).
   - Fungsi: Muncul saat state game adalah State.PAUSED (dipicu oleh penekanan tombol 'P'). Panel ini menghentikan semua game timer (pergerakan pipa dan fisika).
   - Konten: Menampilkan pesan "GAME PAUSED", instruksi untuk melanjutkan permainan ('P'), dan tombol "KEMBALI KE HOME".
   - Aksi Kunci: Memungkinkan pengguna untuk melanjutkan permainan dengan menekan 'P' atau keluar dari sesi permainan saat ini untuk kembali ke MainMenu.
   
## 3. alur atau flow codenya :

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
