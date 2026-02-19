package Logic;

import Graphics.ImageManager;
import javafx.scene.image.Image;

/*'
 * Representerar en power-up som ger en sköld till spelaren.
 * 
 * När effekten är aktiv så skyddar den spelaren från en kollision med ett hinder.
 * Effekten visas för spelaren genom en ny bild för spelkaraktären.
 */
public class Shield extends PowerUp {

	public Shield( double x, double y, Image image, double width, double height) {
		super(x, y, image, width, height); 
	}
	
	@Override
	public void applyPowerUp(Bird bird) {
			bird.setShield(true);
			bird.setImage(ImageManager.SHIELD_BIRD_IMAGE);
	}
}
