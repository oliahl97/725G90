package Logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Representerar ett bi som fungerar som hinder i spelet.
 * 
 * Biet rör sig horisontellt och gör en vågrörelse vertikalt.
 */
public class Bee extends Monster {

	private static final int SPEED = 4;
	private static final int BEE_HEIGHT = 65;
	private static final int BEE_WIDTH = 65;

	private double amplitude; // Amplituden för biets vågrörelse
	private double frequency; // Frekvensen för biets vågrörelse
	private double verticalOffset; // Vertikal förskjutning
	private boolean passed;

	public Bee(double x, double y, Image image, double amplitude, double frequency) {
		super(x, y, image);
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.verticalOffset = 0.0;
		this.passed = false;
	}

	/**
     * Flyttar biet åt vänster och uppdaterar dess vertikala rörelse.
     */
	@Override
	public void move() {
		setX(getX() - SPEED);

		verticalOffset = amplitude * Math.sin(2 * Math.PI * frequency * System.currentTimeMillis() / 1000.0);

		// Uppdatera y-position baserat på vertikal förskjutning
		setY(getY() + verticalOffset);
	}

	/**
     * Hämtar biets kollisionsgränser.
     * 
     * @return En Rectangle2D som representerar biets hitbox
     */
	@Override
	public Rectangle2D getBoundary() {
		return new Rectangle2D(getX(), getY(), BEE_WIDTH, BEE_HEIGHT);
	}
	

	public static int getBeeHeight() {
		return BEE_HEIGHT;
	}

	public static int getBeeWidth() {
		return BEE_WIDTH;
	}

	/**
     * Kontrollerar om fågeln har passerat biet och uppdaterar status.
     * 
     * @param birdX Fågelns x-koordinat.
     * @return true om fågeln har passerat biet, annars false.
     */
	public boolean hasPassed(double birdX) {
		if (!passed && birdX > getX() + getBeeWidth()) {
			passed = true;
			return true;
		}
		return false;
	}

}
