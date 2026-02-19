package Logic;

import javafx.scene.image.Image;

/**
 * Representerar en power-up som ger ett liv till spelaren.
 */
public class ExtraLife extends PowerUp {

	public ExtraLife(double x, double y, Image image, double width, double height) {
		super(x, y, image, width, height);
	}

	/**
     * Tillämpar effekten av power-upen genom att öka fågelns liv med +1 om spelaren inte har fullt liv.
     * 
     * @param bird Fågeln som får power-upen
     */
	@Override
	public void applyPowerUp(Bird bird) {
		if (bird.getLives() < Bird.MAX_HP) {
			bird.addLife();
		}
	}
}
