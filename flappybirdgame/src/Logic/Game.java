package Logic;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Random;

import Graphics.GameWindow;
import Graphics.ImageManager;
import Graphics.Renderer;

/**
 * Huvudklassen som hanterar spelets logik och styrning.
 *
 * Game-klassen ansvarar för att starta och hantera spelets huvudloop,
 * sköta poängräkning, kollisioner och power-ups. 
 */
public class Game {

	// Konstanter
	private static final long COLLISION_COOLDOWN = 1200000000L; // 1.2 sekunder
	private final double targetFps = 60.0;
	private final double nanoPerUpdate = 1000000000.0 / targetFps;
	
	private Canvas canvas;
	private Renderer renderer;

	private Level currentLevel;
	private boolean isGameRunning = false;
	private double score = 0;
	private long lastCollisionTime = 0;
	private long lastPowerUpSpawnTime = 0;

	private Bird bird;
	private ArrayList<PowerUp> powerUps = new ArrayList<>();

	private AnimationTimer gameLoop;
	private Updater updater;
	
	/**
	 * Skapar ett nytt spel.
	 * 
	 * @param c  Spelytan där spelet ritas
	 * @param gc Används för rendering
	 */
	public Game(Canvas c, GraphicsContext gc) {
		this.canvas = c;
		this.bird = new Bird(100.0, 200.0, this);
		this.renderer = new Renderer(gc, c);
		this.updater = new Updater(this);
		currentLevel = new PipeLevel(c);
	}

	public Boolean getIsGameRunning() {
		return isGameRunning;
	}

	
	public Bird getBird() {
		return bird;
	}

	/**
     * Startar spelets huvudloop.
     */
	public void startGameLoop() {
		if (!isGameRunning) {
			isGameRunning = true;

			gameLoop = new AnimationTimer() {
				private long lastSpawnTime = 0;
				long lastUpdate = 0;

				@Override
				public void handle(long now) {
					if ((now - lastUpdate) > nanoPerUpdate) {
						updater.updateGame();
						renderer.renderGame(Game.this);
						lastUpdate = now;
					}

					if (now - lastSpawnTime > 1800000000L) {
						currentLevel.spawn();
						lastSpawnTime = now;
					}

					if (now - lastPowerUpSpawnTime > 5000000000L) {
						spawnPowerUp();
						lastPowerUpSpawnTime = now;
					}
				}
			};
			gameLoop.start();
		}
	}

	public void addScore(int score) {
		this.score += score;
	}

	/**
     * Startar om spelet genom att återställa poäng, fågelns position och rensa banan.
     */
	public void restartGame() {
		bird.resetShield();
		score = 0;
		bird.resetLives();
		currentLevel.clear();
		powerUps.clear();
		bird.setPosition(100.0, 200.0);
		bird.resetVelocity();
		startGameLoop();
	}

	/**
     * Skapar och placerar ut en slumpmässig power-up på skärmen.
     */
	public void spawnPowerUp() {
		Random rand = new Random();
		int minY = 100;
		int maxY = 900;
		int powerUpY = rand.nextInt(maxY - minY + 1);
		
		int random = rand.nextInt(3);
		
		Image powerUpImage;
		PowerUp powerUp;
		
		if (random == 0) {
			powerUpImage = ImageManager.EXTRA_LIFE_IMAGE;
			powerUp = new ExtraLife(canvas.getWidth(), powerUpY, powerUpImage, 50, 50);
		} else if (random == 1) {
			powerUpImage = ImageManager.BONUS_POINT_IMAGE;
			powerUp = new BonusPoint(canvas.getWidth(), powerUpY, powerUpImage, 50, 50);
		} else {
			powerUpImage = ImageManager.SHIELD_IMAGE;
			powerUp = new Shield(canvas.getWidth(), powerUpY, powerUpImage, 65, 65);
		}
		powerUps.add(powerUp);
	}
	
	/**
     * Stoppar spelet och visar "Game Over"-skärmen.
     */
	public void stopGame() {
		if (isGameRunning) {
			isGameRunning = false;
			gameLoop.stop();		

			// Spara high score
			new HighScoreManager().addScore((int) score);
			
			// Visa "Game Over"-skärmen
			new GameWindow().showGameOverLayout(canvas, this);
		}
	}
	
	/**
     * Hanterar spelarens input.
     * 
     * @param scene Scenen där tangenttryckningar fångas upp
     */
    public void handleInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (isGameRunning) {
                if (event.getCode() == KeyCode.SPACE) {
                    bird.jump();
                }}
        });
    }

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	public long getLastCollisionTime() {
		return lastCollisionTime;
	}

	public void setLastCollisionTime(long lastCollisionTime) {
		this.lastCollisionTime = lastCollisionTime;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public static long getCollisionCooldown() {
		return COLLISION_COOLDOWN;
	}

}
