package Logic;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * En abstrakt klass som representerar en bana i spelet.
 * 
 * Sätter den grundläggande strukturen för alla nivåer i spelet. 
 * Den hanterar listan över monster på banan, deras rörelse och rensning av monster som 
 * har lämnat skärmen. Varje specifik bana ärver från denna klass 
 * och implementerar sina egna renderings- och spawn-metoder.
 */
public abstract class Level {
	

	/** 
	 * Lista med alla monster i banan.
	 */
	public ArrayList<Monster> monsters = new ArrayList<>();

	public Level() {
	}

	public abstract void render(GraphicsContext gc);

	public abstract void spawn();
	
	/**
     * Hämtar listan med monster i banan.
     * 
     * @return En lista med alla monster i banan.
     */
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	/**
     * Flyttar alla monster i nivån.
     */
	public void moveMonsters() {
		for (Monster monster : monsters) {
			monster.move();
		}
	}

	/**
     * Tar bort monster som har rört sig utanför skärmen.
     */
	public void removeOffscreenMonsters() {
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			if (monster.getX() + monster.getImage().getWidth() < 0) {
				monsters.remove(i);
				i--;
			}
		}
	}

	public void clear() {
		monsters.clear();
	}

	public void update() {
		moveMonsters();
		removeOffscreenMonsters();
	}
}
