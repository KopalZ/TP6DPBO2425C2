import java.awt.Image;
import java.awt.Rectangle;

public class Player {
    // Variabel posisi dan ukuran pemain
    private int posX, posY;
    private int width, height;
    // Kecepatan vertikal (untuk gravitasi dan lompatan)
    private float velocityY;
    // Gambar pemain
    private Image image;

    // Constructor untuk inisialisasi Player
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityY = 0;
    }

    // Mengembalikan objek Rectangle untuk deteksi tabrakan
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, width, height);
    }

    // getters & setters
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Image getImage() { return image; }

    public void setPosX(int x) { this.posX = x; }
    public void setPosY(int y) { this.posY = y; }
    public void setImage(Image img) { this.image = img; }

    public float getVelocityY() { return velocityY; }
    public void setVelocityY(float v) { this.velocityY = v; }
}