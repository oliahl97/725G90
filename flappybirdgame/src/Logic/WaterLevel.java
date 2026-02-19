package Logic;

import java.util.Random;

import Graphics.ImageManager;
import Graphics.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * En nivå där maneter fungerar som hinder för spelaren.
 * 
 * WaterLevel genererar maneter som rör sig mellan skärmens topp och botten 
 * samtidigt som de flyter från höger till vänster. Spelaren måste undvika maneterna 
 * för att inte förlora liv.
 */
public class WaterLevel extends Level {
	  	
	private Jellyfish jellyfish;
	private Canvas canvas;
	private Renderer renderer;
	        
	public WaterLevel(Canvas canvas) {
        this.canvas = canvas;
        this.renderer = new Renderer(canvas.getGraphicsContext2D(), canvas);		
	}
	
	@Override
	public void spawn() {
		Random rand = new Random();
        int minY = 150;
        int maxY = 850;
        int jellyfishY = rand.nextInt(maxY - minY) + minY;

        jellyfish = new Jellyfish(canvas.getWidth(), jellyfishY, ImageManager.JELLYFISH_IMAGE, canvas);
        
        monsters.add(jellyfish);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		renderer.renderBackground(ImageManager.WATER_IMAGE2);
        renderer.renderMonsters(monsters);
	}
}
