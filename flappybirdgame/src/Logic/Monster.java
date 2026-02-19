package Logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * En abstrakt klass som representerar fiender i spelet.
 */
public abstract class Monster {
	private Image image;
	private double x;
	private double y;

	public Monster(double x, double y, Image image) {
		this.x = x;
		this.y = y;
		this.image = image;

	}

	public abstract Rectangle2D getBoundary();
	
	public abstract void move();
	

	public void applyEffect(Bird bird) {
	        bird.loseLife();
	    }
	 
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}
	
}

