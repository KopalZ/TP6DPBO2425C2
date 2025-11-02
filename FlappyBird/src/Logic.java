import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Logic implements ActionListener, KeyListener {
    // Dimensi Frame Game
    int frameWidth = 540;
    int frameHeight = 800;

    // Posisi dan ukuran awal pemain
    int playerStartPosX = 80;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    // Posisi dan ukuran pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Player player;

    // Referensi ke komponen UI
    private JLabel scoreLabel;
    private GameOverPanel gameOverPanel;
    private PausePanel pausePanel;

    // images
    java.awt.Image birdImage;
    java.awt.Image lowerPipeImage;
    java.awt.Image upperPipeImage;

    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1; // Nilai gravitasi
    int jumpVelocity = -10; // Kecepatan lompatan
    int pipeVelocityX = -3; // Kecepatan horizontal pipa

    // Game state
    public enum State { MENU, RUNNING, GAME_OVER, PAUSED }
    private State state = State.MENU;

    // Score
    private int score = 0;
    private int bestScore = 0;

    Random rand = new Random();

    public Logic(){
        // Load assets gambar
        try {
            birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
            lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
            upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        } catch (Exception ex) {
            System.out.println("Warning: assets not found, using placeholders.");
            birdImage = null;
            lowerPipeImage = null;
            upperPipeImage = null;
        }

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        pipes = new ArrayList<>();

        // Timer untuk memunculkan pipa baru
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.setRepeats(true);

        // Timer utama game loop (~60 FPS)
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    // Menghubungkan View dan menambahkan listener input
    public void setView(View view) {
        this.view = view;
        view.addKeyListener(this);
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                handleInput();
            }
        });
        view.setFocusable(true);
        view.requestFocusInWindow();
    }

    // Setter untuk JLabel Score
    public void setScoreLabel(JLabel label) {
        this.scoreLabel = label;
    }

    // Setter untuk GameOverPanel
    public void setGameOverPanel(GameOverPanel panel) {
        this.gameOverPanel = panel;
    }

    // Setter untuk PausePanel
    public void setPausePanel(PausePanel panel) {
        this.pausePanel = panel;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes(){
        return pipes;
    }

    public int getScore() { return score; }
    public int getBestScore() { return bestScore; }
    public State getState() { return state; }

    // Logika penempatan pipa baru
    public void placePipes(){
        int openingSpace = 150; // Jarak vertikal antar pipa
        int groundHeight = 50;
        int availableHeight = frameHeight - groundHeight;

        // Batas penempatan pipa agar tidak terlalu tinggi atau terlalu rendah
        int minTopYLimit = 120;
        int maxTopYLimit = availableHeight - openingSpace - 120;

        // Menentukan ketinggian pipa atas secara acak
        int range = Math.max(1, maxTopYLimit - minTopYLimit);
        int topPipeVisibleHeight = minTopYLimit + rand.nextInt(range);

        // Menghitung posisi Y pipa atas (harus negatif agar menempel di Y=0)
        int topPipeY = topPipeVisibleHeight - pipeHeight;

        // Menghitung posisi Y pipa bawah (tepat di bawah celah)
        int bottomPipeY = topPipeVisibleHeight + openingSpace;

        // Membuat objek pipa baru
        Pipe upperPipe = new Pipe(pipeStartPosX, topPipeY, pipeWidth, pipeHeight, upperPipeImage, true);
        Pipe lowerPipe = new Pipe(pipeStartPosX, bottomPipeY, pipeWidth, pipeHeight, lowerPipeImage, false);

        pipes.add(upperPipe);
        pipes.add(lowerPipe);
    }

    // Logika pembaruan game (fisika, pergerakan, tabrakan, scoring)
    private void update(){
        if (state != State.RUNNING) return;

        // Terapkan gravitasi pada pemain
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY((int)(player.getPosY() + player.getVelocityY()));

        // Batasan atas frame
        if (player.getPosY() < 0) {
            player.setPosY(0);
            player.setVelocityY(0);
        }

        // Batasan bawah (tabrakan dengan tanah)
        int groundHeight = 50;
        int floor = frameHeight - groundHeight - player.getHeight();
        if (player.getPosY() >= floor){
            player.setPosY(floor);
            player.setVelocityY(0);
            gameOver(); // Game Over jika menyentuh tanah
            return;
        }

        // Perbarui pergerakan pipa dan cek tabrakan
        Iterator<Pipe> it = pipes.iterator();
        while (it.hasNext()){
            Pipe pipe = it.next();
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // Cek tabrakan dengan pemain
            if (player.getBounds().intersects(pipe.getBounds())){
                gameOver();
                return;
            }

            // Logika Scoring: Tambah skor saat pipa dilewati
            if (!pipe.isTop() && !pipe.isPassed()){
                if (pipe.getPosX() + pipe.getWidth() < player.getPosX()){
                    markPairPassed(pipe.getPosX());
                    score += 1;
                }
            }

            // Hapus pipa yang sudah keluar dari layar
            if (pipe.getPosX() + pipe.getWidth() < -100){
                it.remove();
            }
        }

        // Update JLabel skor di UI
        if (scoreLabel != null) {
            scoreLabel.setText("SKOR: " + score + " | BEST: " + bestScore);
        }
    }

    // Menandai pasangan pipa sebagai sudah dilewati untuk menghindari double scoring
    private void markPairPassed(int pairX){
        for (Pipe p : pipes){
            if (Math.abs(p.getPosX() - pairX) <= 2){
                p.setPassed(true);
            }
        }
    }

    // Mengubah state menjadi GAME_OVER dan menampilkan panel
    private void gameOver(){
        state = State.GAME_OVER;
        // Hentikan timer permainan
        if (pipesCooldown.isRunning()) pipesCooldown.stop();
        gameLoop.stop();

        if (score > bestScore) bestScore = score;

        // Tampilkan panel Game Over dan update skornya
        if (gameOverPanel != null) {
            gameOverPanel.updateScores(score, bestScore);
            gameOverPanel.setVisible(true);
            if (view != null) view.requestFocusInWindow();
        }
    }

    // Logika Pause Game: menghentikan timer dan menampilkan PausePanel
    private void pauseGame(){
        if (state == State.RUNNING) {
            state = State.PAUSED;
            pipesCooldown.stop();
            gameLoop.stop();

            if (pausePanel != null) {
                pausePanel.setVisible(true);
                if (view != null) view.requestFocusInWindow();
            }
        }
    }

    // Logika Resume Game: melanjutkan timer dan menyembunyikan PausePanel
    private void resumeGame(){
        if (state == State.PAUSED) {
            state = State.RUNNING;
            pipesCooldown.start();
            gameLoop.start();

            if (pausePanel != null) {
                pausePanel.setVisible(false);
                if (view != null) view.requestFocusInWindow();
            }
        }
    }

    // Memulai permainan dari awal (digunakan oleh Start dan Restart)
    private void startGame(){
        score = 0;
        pipes.clear();
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        state = State.RUNNING;

        // Sembunyikan semua panel
        if (gameOverPanel != null) gameOverPanel.setVisible(false);
        if (pausePanel != null) pausePanel.setVisible(false);

        // Mulai Timer permainan
        if (!pipesCooldown.isRunning()) {
            pipesCooldown.setInitialDelay(500);
            pipesCooldown.start();
        }
        gameLoop.start();

        if (view != null) {
            view.requestFocusInWindow();
        }

        if (scoreLabel != null) {
            scoreLabel.setText("SKOR: " + score + " | BEST: " + bestScore);
        }
    }

    // Mengulang permainan (dipanggil dari tombol 'R')
    public void restartGame(){
        startGame();
    }

    // Logika penanganan input (klik mouse/spasi)
    private void handleInput(){
        if (state == State.MENU){
            startGame(); // Mulai game
        } else if (state == State.RUNNING){
            player.setVelocityY(jumpVelocity); // Lompat
        }
    }

    // Dipanggil oleh gameLoop setiap tick
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        update();
        if (view != null){
            view.repaint();
        }
    }

    // KeyListener: Menangani input keyboard (Spasi, P, R)
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {}

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (state == State.RUNNING){
            if (keyCode == java.awt.event.KeyEvent.VK_SPACE){
                handleInput(); // Lompat
            } else if (keyCode == java.awt.event.KeyEvent.VK_P) {
                pauseGame(); // Pause
            }
        } else if (state == State.MENU && keyCode == java.awt.event.KeyEvent.VK_SPACE){
            handleInput(); // Start
        } else if (state == State.GAME_OVER && keyCode == java.awt.event.KeyEvent.VK_R){
            restartGame(); // Restart
        } else if (state == State.PAUSED && keyCode == java.awt.event.KeyEvent.VK_P){
            resumeGame(); // Resume
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {}
}