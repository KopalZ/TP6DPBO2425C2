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

   ### 2.3 **Class PausePanel.java**

   - Tipe Komponen: JPanel (semi-transparan).
   - Fungsi: Muncul saat state game adalah State.PAUSED (dipicu oleh penekanan tombol 'P'). Panel ini menghentikan semua game timer (pergerakan pipa dan fisika).
   - Konten: Menampilkan pesan "GAME PAUSED", instruksi untuk melanjutkan permainan ('P'), dan tombol "KEMBALI KE HOME".
   - Aksi Kunci: Memungkinkan pengguna untuk melanjutkan permainan dengan menekan 'P' atau keluar dari sesi permainan saat ini untuk kembali ke MainMenu.
   
## 3. Alur dan Flow Code Utama

   ### 3.1 Inisialisasi Program
   - App.main() memanggil SwingUtilities.invokeLater() untuk menjalankan MainMenu di Event Dispatch Thread (EDT).
   - MainMenu muncul di tengah layar dengan ukuran $540 \times 800$.

   ### 3.2 Memulai Permainan (Play Game)
   - Pengguna menekan tombol "PLAY GAME" di MainMenu.
   - MainMenu ditutup (dispose()), dan App.showGameFrame() dipanggil.
   - App.showGameFrame() membuat instance Logic dan View, menumpuk View, ScoreLabel, PausePanel, dan GameOverPanel di dalam JLayeredPane, lalu memanggil frame.pack() dan frame.setLocationRelativeTo(null).
   - Logic.startGame() dipanggil, menyetel state ke RUNNING, dan memulai pipesCooldown dan gameLoop.

   ### 3.3 Logika Game
   - Skoring: Logic memperbarui score (+1) ketika burung melewati pipa, dan scoreLabel di-update pada setiap tick (Logic.update()).
   - Lompatan: Logic.handleInput() menerima input Spasi/Klik dan memberikan dorongan vertikal (velocityY = -10) pada Player.
   - Game Over (Wajib 1): Jika Player berinteraksi dengan Pipe (tabrakan) atau menyentuh tanah (frameHeight - groundHeight), Logic.gameOver() dipanggil, menghentikan kedua timer.

   ### 3.4 Restart dan Menu Interaktif
   - Restart: Ketika State adalah GAME_OVER, menekan tombol 'R' di keyboard akan memanggil Logic.restartGame(), yang menyetel ulang posisi, skor, dan state ke RUNNING.
   - Pause: Menekan tombol 'P' saat State.RUNNING memanggil Logic.pauseGame(), menghentikan semua timer dan menampilkan PausePanel. Menekan 'P' lagi memanggil Logic.resumeGame().
   - Kembali ke Home: Tombol "KEMBALI KE HOME" pada GameOverPanel atau PausePanel menutup Game Frame dan kembali meluncurkan MainMenu.

# Dokumentasi
https://github.com/user-attachments/assets/20fe0fb8-f6bf-4a5c-b7e6-523386b52f26
