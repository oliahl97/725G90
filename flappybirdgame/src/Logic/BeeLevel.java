package Logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

import Graphics.ImageManager;
import Graphics.Renderer;

/**
 * En bana där bin fungerar som hinder för spelaren.
 * 
 * BeeLevel genererar bin som rör sig i en vågrörelse samtidigt som de flyger från höger till vänster.
 * Spelaren måste undvika bina för att inte förlora liv.
 */
public class BeeLevel extends Level {

    private Bee bee;
    private Canvas canvas;
    private Renderer renderer;

    public BeeLevel(Canvas canvas) {
        this.canvas = canvas;
        this.renderer = new Renderer(canvas.getGraphicsContext2D(), canvas);
    }

    /**
     * Skapar och placerar ut ett nytt bi på en slumpmässig position.
     */
    @Override
    public void spawn() {
        Random rand = new Random();
        int minY = 100;
        int maxY = 900;
        int beeY = rand.nextInt(maxY - minY + 1);

        bee = new Bee(canvas.getWidth(), beeY, ImageManager.BEE_IMAGE, 30, 1.5);

        monsters.add(bee);
    }

    @Override
    public void render(GraphicsContext gc) {
        renderer.renderBackground(ImageManager.BG2_IMAGE);
        renderer.renderMonsters(monsters);
    }
}
