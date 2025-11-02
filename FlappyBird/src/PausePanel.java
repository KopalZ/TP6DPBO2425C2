import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanel extends JPanel {
    private Logic logic;

    public PausePanel(Logic logic, JFrame gameFrame) {
        this.logic = logic;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 180));
        // Padding disesuaikan untuk layar 540x800
        setBorder(BorderFactory.createEmptyBorder(150, 50, 150, 50));

        // Label Judul "GAME PAUSED"
        JLabel title = new JLabel("GAME PAUSED");
        title.setFont(new Font("Impact", Font.BOLD, 50));
        title.setForeground(Color.YELLOW);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Instruksi Resume
        JLabel instruction = new JLabel("Tekan tombol P untuk Melanjutkan", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.ITALIC, 24));
        instruction.setForeground(Color.LIGHT_GRAY);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tombol Kembali ke Menu Utama
        JButton homeButton = createStyledButton("KEMBALI KE HOME");
        homeButton.addActionListener(e -> {
            gameFrame.dispose();
            new MainMenu().setVisible(true);
        });

        add(title);
        add(Box.createVerticalStrut(40));
        add(instruction);
        add(Box.createVerticalStrut(50));
        add(homeButton);

        setVisible(false);
    }

    // Metode helper untuk membuat tombol yang seragam
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 28));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(178, 34, 34));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        button.setMaximumSize(new Dimension(350, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}