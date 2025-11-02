import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel yang muncul saat Game Over
public class GameOverPanel extends JPanel {
    private Logic logic;
    private JLabel scoreValueLabel;
    private JLabel bestScoreValueLabel;

    public GameOverPanel(Logic logic, JFrame gameFrame) {
        this.logic = logic;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 180));
        // Padding disesuaikan untuk layar 540x800
        setBorder(BorderFactory.createEmptyBorder(150, 50, 150, 50));

        // Label Judul "GAME OVER"
        JLabel title = new JLabel("GAME OVER");
        title.setFont(new Font("Impact", Font.BOLD, 50));
        title.setForeground(Color.RED);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel Skor untuk tata letak Grid
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 2, 20, 20));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));
        scorePanel.setBackground(new Color(0, 0, 0, 0));
        scorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Teks Skor Saat Ini
        JLabel scoreTextLabel = new JLabel("SKOR ANDA:");
        scoreTextLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreTextLabel.setForeground(Color.WHITE);

        scoreValueLabel = new JLabel("0");
        scoreValueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreValueLabel.setForeground(Color.YELLOW);

        // Teks Best Score
        JLabel bestScoreTextLabel = new JLabel("BEST SKOR:");
        bestScoreTextLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        bestScoreTextLabel.setForeground(Color.WHITE);

        bestScoreValueLabel = new JLabel("0");
        bestScoreValueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bestScoreValueLabel.setForeground(Color.YELLOW);

        scorePanel.add(scoreTextLabel);
        scorePanel.add(scoreValueLabel);
        scorePanel.add(bestScoreTextLabel);
        scorePanel.add(bestScoreValueLabel);

        // Pesan instruksi Restart dengan 'R'
        JLabel restartInstruction = new JLabel("Tekan tombol R untuk Restart", SwingConstants.CENTER);
        restartInstruction.setFont(new Font("Arial", Font.ITALIC, 20));
        restartInstruction.setForeground(Color.LIGHT_GRAY);
        restartInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tombol Kembali ke Menu Utama
        JButton homeButton = createStyledButton("KEMBALI KE HOME");
        homeButton.addActionListener(e -> {
            gameFrame.dispose();
            new MainMenu().setVisible(true);
        });

        add(title);
        add(Box.createVerticalStrut(40));
        add(scorePanel);
        add(Box.createVerticalStrut(20));
        add(restartInstruction);
        add(Box.createVerticalStrut(30));
        add(homeButton);

        setVisible(false);
    }

    // Metode untuk memperbarui tampilan skor
    public void updateScores(int currentScore, int bestScore) {
        scoreValueLabel.setText(String.valueOf(currentScore));
        bestScoreValueLabel.setText(String.valueOf(bestScore));
    }

    // Metode helper untuk membuat tombol yang seragam
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 28));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(34, 139, 34));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        button.setMaximumSize(new Dimension(350, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}