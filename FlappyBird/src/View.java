import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Logic logic;

    // Constructor
    public View(Logic logic){
        this.logic = logic;
        // Set ukuran panel sesuai dimensi game
        setPreferredSize(new Dimension(logic.frameWidth, logic.frameHeight));
        setBackground(new Color(135, 206, 235)); // Warna latar belakang (langit)
        setLayout(null); // Layout null untuk koordinat absolut
    }

    // Metode menggambar semua elemen game
    @Override
    protected void paintComponent(Graphics gg){
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;

        // Gambar pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null){
            for (Pipe p : pipes){
                if (p.getImage() != null){
                    g.drawImage(p.getImage(), p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight(), null);
                } else {
                    g.setColor(new Color(34,139,34));
                    g.fillRect(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
                }
            }
        }

        // Gambar tanah/lantai
        int groundHeight = 50;
        g.setColor(new Color(87,59,12));
        g.fillRect(0, getHeight()-groundHeight, getWidth(), groundHeight);

        // Gambar pemain
        Player player = logic.getPlayer();
        if (player != null){
            if (player.getImage() != null){
                g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
            } else {
                g.setColor(Color.YELLOW);
                g.fillOval(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
            }
        }

        // Pesan status (hanya saat MENU)
        if (logic.getState() == Logic.State.MENU){
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String msg = "Press SPACE (or Click) to Start";
            int w = g.getFontMetrics().stringWidth(msg);
            g.setColor(Color.WHITE);
            g.drawString(msg, getWidth()/2 - w/2, getHeight()/2);
        }
    }
}