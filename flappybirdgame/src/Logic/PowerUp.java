package Logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * En abstrakt klass som representerar en power-up i spelet.
 * 
 * Power-ups påverkar fågeln/spelet genom olika positiva effekter.
 */
public abstract class PowerUp {
    private double x;
    private double y;
    private Image image;
    private double width;
    private double height;

    public PowerUp(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public abstract void applyPowerUp(Bird bird);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Hämtar power-upens kollisionsgränser.
     * 
     * @return En Rectangle2D som representerar power-upens hitbox
     */
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * Kontrollerar om power-upen kolliderar med ett annat objekt.
     * 
     * @param  otherBoundary Gränsen för det andra objektet
     * @return true om power-upen kolliderar, annars false
     */
    public boolean collidesWith(Rectangle2D otherBoundary) {
        return this.getBoundary().intersects(otherBoundary);
    }
}
