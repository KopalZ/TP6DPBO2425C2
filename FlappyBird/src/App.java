import javax.swing.*;
import java.awt.*;

public class App {

    // Metode untuk menampilkan Game Frame
    public static void showGameFrame() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Frame statis dan tidak dapat diubah ukurannya (non-resizable)
        frame.setResizable(false);

        Logic logic = new Logic();
        View view = new View(logic);
        logic.setView(view);

        // Tambahkan JLabel untuk skor
        JLabel scoreLabel = new JLabel("SKOR: 0 | BEST: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(new Color(0, 0, 0, 100));
        scoreLabel.setOpaque(true);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        logic.setScoreLabel(scoreLabel);

        // 1. Inisialisasi Game Over Panel
        GameOverPanel gameOverPanel = new GameOverPanel(logic, frame);
        logic.setGameOverPanel(gameOverPanel);
        // Atur batas panel sesuai dimensi game (540x800)
        gameOverPanel.setBounds(0, 0, logic.frameWidth, logic.frameHeight);

        // 2. Inisialisasi Pause Panel
        PausePanel pausePanel = new PausePanel(logic, frame);
        logic.setPausePanel(pausePanel);
        pausePanel.setBounds(0, 0, logic.frameWidth, logic.frameHeight);

        // Gunakan Panel Wrapper (Hanya untuk konsistensi Swing)
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(Color.BLACK);

        // 3. Gunakan JLayeredPane dengan ukuran 540x800
        JLayeredPane layeredPane = new JLayeredPane();
        Dimension gameDimension = new Dimension(logic.frameWidth, logic.frameHeight);
        layeredPane.setPreferredSize(gameDimension);
        layeredPane.setMinimumSize(gameDimension);
        layeredPane.setMaximumSize(gameDimension);

        // Tentukan batas/posisi ScoreLabel (di bagian atas JLayeredPane)
        scoreLabel.setBounds(0, 0, logic.frameWidth, 40);

        // Atur View ke Layer Bawah
        view.setBounds(0, 0, logic.frameWidth, logic.frameHeight);
        layeredPane.add(view, JLayeredPane.DEFAULT_LAYER);

        // Atur ScoreLabel ke Layer Tengah
        layeredPane.add(scoreLabel, JLayeredPane.MODAL_LAYER);

        // Atur PausePanel ke Layer Kedua
        layeredPane.add(pausePanel, JLayeredPane.PALETTE_LAYER);

        // Atur Game Over Panel ke Layer Paling Atas
        layeredPane.add(gameOverPanel, JLayeredPane.POPUP_LAYER);

        // Tambahkan layeredPane ke wrapperPanel
        wrapperPanel.add(layeredPane, BorderLayout.CENTER);


        // Frame hanya menambahkan wrapperPanel
        frame.setLayout(new BorderLayout());
        frame.add(wrapperPanel, BorderLayout.CENTER);

        // Atur ukuran frame (pack) dan pusatkan
        frame.pack();
        frame.setLocationRelativeTo(null); // Pusatkan frame

        // Tampilkan frame
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);

            // Memaksa repaint untuk memastikan rendering View
            view.repaint();
            view.requestFocusInWindow();
        });
    }

    public static void main(String[] args) {
        // Tampilkan Main Menu terlebih dahulu
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}