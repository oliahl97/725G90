package Graphics;

import java.util.List;

import Logic.BeeLevel;
import Logic.Game;
import Logic.HighScoreManager;
import Logic.PipeLevel;
import Logic.WaterLevel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Hanterar spelets huvudmeny där spelaren kan starta spelet, välja bana, 
 * se high scores eller avsluta spelet.
 */ 
public class Menu extends VBox {
	private Button startButton;
	private Button highscoreButton;
	private Button quitGameButton;
	private Game game;
	private Stage stage;
	private Canvas canvas;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	/**
	 * Skapar huvudmenyn med tillhörande knappar.
	 * 
	 * @param stage Fönster där menyn visas
	 * @param canvas Spelytan där grafik ritas
	 * @param game Spelet som hanteras av menyn
	 */
	public Menu(Stage stage, Canvas canvas, Game game) {
		this.stage = stage;
		this.canvas = canvas;
		this.game = game;

		setSpacing(20);
		setAlignment(Pos.CENTER);

		startButton = new Button("Start Game");
		highscoreButton = new Button("High Score");
		quitGameButton = new Button("Quit Game");

		startButton.setOnAction(event -> showChooseLevelMenu());
		highscoreButton.setOnAction(event -> showHighScores());
		quitGameButton.setOnAction(event -> stage.close());

		getChildren().addAll(startButton, highscoreButton, quitGameButton);
	}

	/**
     * Skapar knappar och visar en meny där spelaren kan välja mellan olika banor.
     */
	private void showChooseLevelMenu() {
		VBox chooseLevelLayout = new VBox(10);
		chooseLevelLayout.setAlignment(Pos.CENTER);

		Label title = new Label("Choose Level");
		chooseLevelLayout.getChildren().add(title);

		Button pipeLevelButton = new Button("Pipe Level");
		pipeLevelButton.setOnAction(event -> {
			game.setCurrentLevel(new PipeLevel(canvas));
			GameWindow gameWindow = new GameWindow();
			game.restartGame();
			gameWindow.setGameScene(stage, canvas, game);
		});

		Button beeLevelButton = new Button("Bee Level");
		beeLevelButton.setOnAction(event -> {
			game.setCurrentLevel(new BeeLevel(canvas));
			GameWindow gameWindow = new GameWindow();
			game.restartGame();
			gameWindow.setGameScene(stage, canvas, game);
		});
		
		Button waterLevelButton = new Button("Water Level");
		waterLevelButton.setOnAction(event -> {
		    game.setCurrentLevel(new WaterLevel(canvas));
		    GameWindow gameWindow = new GameWindow();
		    game.restartGame();
		    gameWindow.setGameScene(stage, canvas, game);
		});


		Button backButton = new Button("Back");
		backButton.setOnAction(event -> {
			Scene mainMenuScene = new Scene(new Menu(stage, canvas, game), WINDOW_WIDTH, WINDOW_HEIGHT);
			stage.setScene(mainMenuScene);
		});

		chooseLevelLayout.getChildren().addAll(pipeLevelButton, beeLevelButton, waterLevelButton,   backButton);

		Scene chooseLevelScene = new Scene(chooseLevelLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(chooseLevelScene);
	}

	/**
	 * Visar en lista med de högsta poängen.
	 */
	private void showHighScores() {
		VBox highScoreLayout = new VBox(10);
		highScoreLayout.setAlignment(Pos.CENTER);

		Label title = new Label("High Scores");
		title.setFont(javafx.scene.text.Font.font("Verdana", 30)); // Stil för titeln
		highScoreLayout.getChildren().add(title);

		// Hämta highscore-listan från HighScoreManager
		HighScoreManager highScoreManager = new HighScoreManager();
		List<Integer> highScores = highScoreManager.getHighScores();

		// Visa highscore-listan
		for (int i = 0; i < highScores.size(); i++) {
			Label scoreLabel = new Label((i + 1) + ": " + highScores.get(i));
			scoreLabel.setFont(javafx.scene.text.Font.font("Verdana", 20)); // Stil för poängen
			highScoreLayout.getChildren().add(scoreLabel);
		}

		// Tillbaka-knapp för att återgå till huvudmenyn
		Button backButton = new Button("Back");
		backButton.setOnAction(event -> {
			Scene mainMenuScene = new Scene(new Menu(stage, canvas, game), WINDOW_WIDTH, WINDOW_HEIGHT);
			stage.setScene(mainMenuScene);
		});

		highScoreLayout.getChildren().add(backButton);

		Scene highScoreScene = new Scene(highScoreLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(highScoreScene);
	}
}
