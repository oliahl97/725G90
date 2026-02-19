package Logic;

import javafx.scene.image.Image;

/*'
 * Representerar en power-up som ger ett poäng till spelaren.
 */
public class BonusPoint extends PowerUp {

	public BonusPoint(double x, double y, Image image, double width, double height) {
		super(x, y, image, width, height);
	}

	/**
    * Tillämpar effekten av power-upen genom att öka spelarens poäng med +1.
    * 
    * @param bird Fågeln som får power-upen
    */
	@Override
	public void applyPowerUp(Bird bird) {
		Game game = bird.getGame();
		if (game != null) {
			game.addScore(1);
		}
	}
}
