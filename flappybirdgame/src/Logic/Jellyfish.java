package Logic;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


/**
 * Representerar en manet som fungerar som hinder i spelet.
 * 
 * Maneten rör sig antingen upp eller ner och "studsar" på skärmens topp eller botten
 * och vänder då riktning.
 */
public class Jellyfish extends Monster {
	
	private static final int SPEED_X = 3;
    private static final int SPEED_Y = 9;
    private static final int JELLY_WIDTH = 120;
    private static final int JELLY_HEIGHT = 160;
    
    private boolean movingUp;
    private double screenHeight;
    private boolean passed;


	public Jellyfish(double x, double y, Image image, Canvas canvas) {
		super(x, y, image);
		this.screenHeight = canvas.getHeight();
		Random rand = new Random();
		this.movingUp = rand.nextBoolean(); // Kan inleda både uppåt och neråt
	}

	@Override
	public Rectangle2D getBoundary() {
		return new Rectangle2D(getX() +15, getY() +15, 90, 130); // Anpassad hitbox för att bättre motsvara bilden
	}

	@Override
	public void move() {
		setX(getX() - SPEED_X); // Flytta åt vänster
		
		 if (movingUp) { // Om movingUp = true
	            setY(getY() - SPEED_Y); // FLytta uppåt
	            if (getY() <= 0) {  // Om jellyfish har nått toppen av spelytan
	            	movingUp = false; // Riktningen vänds, dvs movingUp = false
	            }
	        } else {
	            setY(getY() + SPEED_Y); // Tvärtom
	            if (getY() + JELLY_HEIGHT >= screenHeight) { // getY() + JELLY_HEIGHT ger den nedre kanten av jellyfish
	            	movingUp = true; // Rör sig uppåt igen
	            }
	        }
	}
	
	 public boolean hasPassed(double birdX) {
	        if (!passed && birdX > getX() + getJellyWidth()) {
	            passed = true;
	            return true;
	        }
	        return false;
	    }
	
	public static int getJellyHeight() {
		return JELLY_HEIGHT;
	}

	public static int getJellyWidth() {
		return JELLY_WIDTH;
	}

}
