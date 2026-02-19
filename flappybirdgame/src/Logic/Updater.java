package Logic;

import java.util.ArrayList;

/**
 * Hanterar uppdateringslogik för spelet.
 * 
 * Denna klass ansvarar för att uppdatera spelets tillstånd, kontrollera kollisioner,
 * hantera power-ups och övervaka spelets status.
 */
public class Updater {
	private Game game;

	public Updater(Game game) {
		this.game = game;
	}

	public void updateGame() {
		checkGameStatus();
		updateBird();
		updateMonsters();
		updatePowerUps();
		checkPassed();
		checkCollisions();
		checkPowerUpCollisions();
		checkBottomCollision();
	}

	private void updateBird() {
        game.getBird().addGravity();
	}

	private void updateMonsters() {	
		game.getCurrentLevel().update();
	}

	public void updatePowerUps() {
		ArrayList<PowerUp> powerUps = game.getPowerUps();
		for (PowerUp powerUp : powerUps) {
			powerUp.setX(powerUp.getX() - 6); // PowerUps rör sig till vänster
		}
		powerUps.removeIf(powerUp -> powerUp.getX() + powerUp.getWidth() < 0); // Ta bort PowerUps utanför skärmen
	}

	private void checkCollisions() {
		long currentTime = System.nanoTime();

		if (currentTime - game.getLastCollisionTime() < Game.getCollisionCooldown()) {
			return;
		}

		for (Monster monster : game.getCurrentLevel().getMonsters()) {
			if (game.getBird().collidesWith(monster.getBoundary()) && !game.getBird().hasCollided()) {
				game.getBird().setHasCollided(true);
				monster.applyEffect(game.getBird());
				game.setLastCollisionTime(currentTime);
				break;
			}
		}
	}

	private void checkPowerUpCollisions() {
		ArrayList<PowerUp> powerUps = game.getPowerUps();
		for (int i = 0; i < powerUps.size(); i++) {
			PowerUp powerUp = powerUps.get(i);
			if (game.getBird().collidesWith(powerUp.getBoundary())) {
				powerUp.applyPowerUp(game.getBird());
				powerUps.remove(i); // Ta bort power-up efter användning
				break;
			}
		}
	}

	/**
	 * Kollar om fågeln har träffat botten av skärmen och stoppar spelet om det
	 * händer.
	 */
	private void checkBottomCollision() {
		if (game.getBird().getY() + Bird.getBirdHeight() >= game.getCanvas().getHeight()) {
			game.stopGame();
		}
	}

	/**
	 * Kontrollerar om fågeln har passerat hinder och ökar poängen därefter.
	 */
	private void checkPassed() {
		for (Monster monster : game.getCurrentLevel().getMonsters()) {
			if (monster instanceof Pipe) {
				Pipe pipe = (Pipe) monster;
				if (pipe.hasPassed(game.getBird().getX())) {
					game.setScore(game.getScore() + 0.5);
				}
			} else if (monster instanceof Bee) {
				Bee bee = (Bee) monster;
				if (bee.hasPassed(game.getBird().getX())) {
					game.setScore(game.getScore() + 1);
				}
			} else if (monster instanceof Jellyfish) { // 
				Jellyfish jellyfish = (Jellyfish) monster;
				if (jellyfish.hasPassed(game.getBird().getX())) {
					game.setScore(game.getScore() + 1);
				}
			}
		}
	}

	/**
	 * Kontrollerar om fågeln har slut på liv och stoppar spelet om det händer.
	 */
	private void checkGameStatus() {
		if (game.getBird().getLives() <= 0) {
			game.stopGame();
		}
	}
}
