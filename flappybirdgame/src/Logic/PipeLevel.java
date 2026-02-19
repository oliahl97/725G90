package Logic;

import java.util.Random;

import Graphics.ImageManager;
import Graphics.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * En nivå där rör fungerar som hinder för spelaren.
 * 
 * PipeLevel genererar rör på slumpmässiga höjder som spelaren måste navigera mellan.
 * Rören rör sig från höger till vänster och kan kollidera med spelaren.
 */
 public class PipeLevel extends Level {

	private Pipe downPipe, upPipe;
	private Canvas canvas;
	private Renderer renderer;

	public PipeLevel(Canvas canvas) {
		this.canvas = canvas;
		this.renderer = new Renderer(canvas.getGraphicsContext2D(), canvas);
	}

	/**
     * Skapar och placerar ut ett par rör på slumpmässig höjd.
     */
	@Override
	public void spawn() {
		Random rand = new Random();

		int downPipeHeight = rand.nextInt(501) + 200;
		double downPipeY = 0 - downPipeHeight;
		double upPipeY = canvas.getHeight() - downPipeHeight + Pipe.getGAP();

		downPipe = new Pipe(canvas.getWidth(), downPipeY, ImageManager.DOWNPIPE_IMAGE);
		upPipe = new Pipe(canvas.getWidth(), upPipeY, ImageManager.UPPIPE_IMAGE);

		monsters.add(downPipe);
		monsters.add(upPipe);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		renderer.renderBackground(ImageManager.BACKGROUND_IMAGE);
		renderer.renderMonsters(monsters);
	}
}
