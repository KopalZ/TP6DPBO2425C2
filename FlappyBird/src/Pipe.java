import java.awt.Image;
import java.awt.Rectangle;

public class Pipe {
    // Variabel posisi dan ukuran pipa
    private int posX, posY;
    private int width, height;
    // Gambar pipa
    private Image image;
    // Penanda apakah ini pipa atas (true) atau pipa bawah (false)
    private boolean isTop;
    // Penanda apakah pemain sudah melewati pipa ini (untuk scoring)
    private boolean passed = false;

    // Constructor untuk inisialisasi Pipa
    public Pipe(int posX, int posY, int width, int height, Image image, boolean isTop) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.isTop = isTop;
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
    public boolean isTop() { return isTop; }
    public boolean isPassed() { return passed; }

    public void setPassed(boolean passed) { this.passed = passed; }
    public void setPosX(int x) { this.posX = x; }
    public void setPosY(int y) { this.posY = y; }
}