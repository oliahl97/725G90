package Logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Representerar ett rör i spelet som fungerar som hinder.
 * 
 * Röret rör sig åt vänster och påverkar fågeln vid kollision.
 */
public class Pipe extends Monster {
	private static final double SPEED = 7.0;
	private static final int GAP = 10;
	private static final int PIPE_WIDTH = 150;

	private Image image;
	private boolean passed;

	public Pipe(double x, double y, Image image) {
		super(x, y, image);
		this.passed = false;
		this.image = image;
	}

	/**
     * Flyttar röret åt vänster med en fast hastighet.
     */
	@Override
	public void move() {
		setX(getX() - SPEED);
	}

	/**
     * Kontrollerar om fågeln har passerat röret och uppdaterar status.
     * 
     * @param birdX Fågelns x-koordinat.
     * @return true om fågeln har passerat röret, annars false.
     */
	public boolean hasPassed(double birdX) {
		if (!passed && birdX > getX() + getPipeWidth()) {
			passed = true;
			return true;
		}
		return false;
	}

	/**
     * Hämtar rörets kollisionsgränser.
     * 
     * @return En Rectangle2D som representerar rörets hitbox
     */
	@Override
	public Rectangle2D getBoundary() {
		return new Rectangle2D(getX(), getY(), image.getWidth(), image.getHeight());
	}

	/**
     * Hämtar avståndet mellan rören.
     * 
     * @return Avståndet mellan det övre och nedre röret.
     */
	public static int getGAP() {
		return GAP;
	}

	public static int getPipeWidth() {
		return PIPE_WIDTH;
	}
}
