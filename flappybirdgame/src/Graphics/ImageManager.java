package Graphics;

import javafx.scene.image.Image;

/**
 * En hjälparklass som hanterar alla bilder som används i spelet.
 * 
 * Bilderna laddas in och kan användas globalt utan att behöva laddas om flera gånger.
 */
public class ImageManager {

	public static final Image BIRD_IMAGE = new Image("file:pics/bird.png");
	public static final Image HEART_IMAGE = new Image("file:pics/heart.png");
	
	public static final Image DOWNPIPE_IMAGE = new Image("file:pics/down_pipe.png");
	public static final Image UPPIPE_IMAGE = new Image("file:pics/up_pipe.png");
	public static final Image BEE_IMAGE = new Image("file:pics/bee.png");
	public static final Image JELLYFISH_IMAGE = new Image("file:pics/jellyfish.png");
	
	public static final Image BACKGROUND_IMAGE = new Image("file:pics/pipelevel.png");
	public static final Image BG2_IMAGE = new Image("file:pics/beelevel.png");
	public static final Image WATER_IMAGE = new Image("file:pics/backgroundwaterlevel.png");
	public static final Image WATER_IMAGE2 = new Image("file:pics/waterlevel.png");
	
	public static final Image BONUS_POINT_IMAGE = new Image("file:pics/bonus.png");
	public static final Image EXTRA_LIFE_IMAGE = new Image("file:pics/extralife.png");
	public static final Image SHIELD_BIRD_IMAGE = new Image("file:pics/angrybird.png");
	public static final Image SHIELD_IMAGE = new Image("file:pics/shieldcool.png");
	
	private ImageManager() {
	}
}
