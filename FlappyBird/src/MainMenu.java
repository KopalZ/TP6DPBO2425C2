import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI Form Main Menu
public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // NONAKTIFKAN resize
        setResizable(false);

        // Gunakan BorderLayout untuk struktur utama (Judul dan Panel Tombol)
        setLayout(new BorderLayout());

        // Panel Tengah (untuk konten utama yang ingin dipusatkan)
        JPanel contentPanel = new JPanel();
        // Gunakan BoxLayout untuk menumpuk elemen secara vertikal
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(135, 206, 235));

        // 1. Tambahkan "Lem" Vertikal di Atas Konten untuk mendorong konten ke bawah
        contentPanel.add(Box.createVerticalGlue());

        // Judul
        JLabel titleLabel = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Impact", Font.BOLD, 60));
        titleLabel.setForeground(new Color(255, 220, 0));
        // Atur agar Judul berpusat horizontal di BoxLayout
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));
        buttonPanel.setBackground(new Color(135, 206, 235));

        JButton playButton = createStyledButton("PLAY GAME");
        JButton exitButton = createStyledButton("EXIT PROGRAM");

        // Listener untuk tombol Play
        playButton.addActionListener(e -> {
            dispose();
            App.showGameFrame();
        });

        // Listener untuk tombol Exit
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(playButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(exitButton);
        // Atur agar Button Panel berpusat horizontal di BoxLayout
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(buttonPanel);

        // 2. Tambahkan "Lem" Vertikal di Bawah Konten
        contentPanel.add(Box.createVerticalGlue());

        // Tambahkan panel konten ke CENTER dari frame utama
        getContentPane().setBackground(new Color(135, 206, 235));
        add(contentPanel, BorderLayout.CENTER);

        // Atur ukuran frame sama dengan ukuran game (540x800)
        setSize(540, 800);

        // Pusatkan frame setelah ukuran diatur
        setLocationRelativeTo(null);
    }

    // Metode helper untuk membuat tombol yang seragam
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(34, 139, 34));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        button.setMaximumSize(new Dimension(300, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}