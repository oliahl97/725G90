package Graphics;

import java.util.ArrayList;

import Logic.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Hanterar rendering av spelets grafik.
 * 
 * Denna klass ansvarar för att rita ut bakgrund, spelkaraktären, monster, 
 * power-ups, liv och poäng.
 */
public class Renderer {
	
	private GraphicsContext gc;
	private Canvas canvas;

	
	public Renderer(GraphicsContext gc, Canvas canvas) {
		this.gc = gc;
		this.canvas = canvas;
	}

	public void renderBackground(Image background) {
		gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void renderBird(Bird bird) {
		gc.drawImage(bird.getImage(), bird.getX(), bird.getY(), Bird.getBirdWidth(), Bird.getBirdHeight());
	}

	public void renderMonsters(ArrayList<Monster> monsters) {
		for (Monster monster : monsters) {
			if (monster instanceof Bee) {
				gc.drawImage(monster.getImage(), monster.getX(), monster.getY(), Bee.getBeeWidth(), Bee.getBeeHeight());
			} else if (monster instanceof Jellyfish) {
				gc.drawImage(monster.getImage(), monster.getX(), monster.getY(), Jellyfish.getJellyWidth(), Jellyfish.getJellyHeight());
			} else {
				gc.drawImage(monster.getImage(), monster.getX(), monster.getY());
			}
		}
	}
	
	public void renderPowerUps(ArrayList<PowerUp> powerUps) {
		for (PowerUp powerUp : powerUps) {
			gc.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
		}
	}

	public void renderLives(Bird bird, double x, double y) {
		double heartWidth = 50;
		double heartHeight = 50;
		double spacing = 10;

		for (int i = 0; i < bird.getLives(); i++) {
			double heartX = x + (heartWidth + spacing) * i;
			gc.drawImage(ImageManager.HEART_IMAGE, heartX, y, heartWidth, heartHeight);
		}

	}

	public void renderScore(double score) {
		Font font = Font.font(gc.getFont().getName(), FontWeight.EXTRA_BOLD, 50);
		gc.setFont(font);
		gc.setFill(Color.LAVENDERBLUSH);
		gc.fillText("Score: ", 0, 50);
		gc.fillText(Integer.toString((int) score), 190, 52);
	}

	/**
     * Renderar hela spelet genom att rita bakgrund, monster, power-ups, fågel, liv och poäng.
     * 
     * @param game Spelet som ska renderas.
     */
	public void renderGame(Game game) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		if (game.getCurrentLevel() instanceof PipeLevel) {
	        renderBackground(ImageManager.BACKGROUND_IMAGE);
	    } else if (game.getCurrentLevel() instanceof BeeLevel) {
	        renderBackground(ImageManager.BG2_IMAGE);
	    } else if (game.getCurrentLevel() instanceof WaterLevel) {
	        renderBackground(ImageManager.WATER_IMAGE2);
	    }
		renderMonsters(game.getCurrentLevel().getMonsters());
		renderPowerUps(game.getPowerUps());
		renderBird(game.getBird());
		renderLives(game.getBird(), 380, 5);
		renderScore(game.getScore());
	}
}
