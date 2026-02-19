package Logic;

import Graphics.ImageManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;


/**
 * Denna klass representerar spelaren i spelet.
 */
public class Bird {

	private static final double GRAVITY = 0.7;
	private static final double JUMP_FORCE = -13.0;
	public static final int MAX_HP = 3;
	private static final int BIRD_WIDTH = 55;
	private static final int BIRD_HEIGHT = 55;

	private Image image;
	private double x;
	private double y;
	private double velocityY;
	private int lives;
	private boolean hasCollided = false;
	private boolean hasShield = false;
	private Game game;

	public Bird(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		this.image = ImageManager.BIRD_IMAGE;
		this.velocityY = 0;
		this.lives = MAX_HP; // Spelaren börjar med maximalt liv som är 3
		this.game = game;
	}
	
	public void jump() {
		if (y > 0) { // Kontrollera att spelaren inte är vid toppen av skärmen
			velocityY = JUMP_FORCE;
		}
	}

	public boolean collidesWith(Rectangle2D otherBoundary) {
		return getBoundary().intersects(otherBoundary);
	}

	/**
     * Lägger till gravitation för att fågeln ska falla neråt.
     */
	public void addGravity() {
		velocityY += GRAVITY;
		y += velocityY;
	}

	public void addLife() {
		lives++;
	}

	public void loseLife() {
		if (getShield()) {
			setShield(false);
			setImage(ImageManager.BIRD_IMAGE);
		}else {
			lives--;
			hasCollided = false;
		}
		hasCollided = false;
	}

	public void resetLives() {
		lives = MAX_HP;
	}

	public void resetVelocity() {
		velocityY = 0;
	}
	
	public void resetShield() {
		setShield(false);
		setImage(ImageManager.BIRD_IMAGE);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(x, y, BIRD_WIDTH, BIRD_HEIGHT);
	}

	public int getLives() {
		return lives;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public static int getBirdWidth() {
		return BIRD_WIDTH;
	}

	public static int getBirdHeight() {
		return BIRD_HEIGHT;
	}
	
	public boolean getShield() {
		return hasShield;
	}
	
	public void setShield(boolean b) {
		this.hasShield = b;
	}
	
	public void setImage(Image i) {
		this.image = i;
	}

	public boolean hasCollided() {
		return hasCollided;
	}

	public void setHasCollided(boolean collided) {
		this.hasCollided = collided;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Game getGame() {
		return game;
	}

}
