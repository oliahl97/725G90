package Graphics;

import Logic.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

/**
 * Hanterar spelets huvudfönster och scenhantering.
 * 
 * Startar spelet och visar menyn, samt hanterar övergångar mellan olika spelscener.
 */
public class GameWindow extends Application {

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;
	
	/**
	 * Startar spelet genom att visa huvudmenyn.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Game flappyBird = new Game(canvas, gc);

		Menu menu = new Menu(primaryStage, canvas, flappyBird);
		StackPane root = new StackPane();
		root.getChildren().add(menu);

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle("FLAPPY DAPPY BIRD");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * Växlar till spelscenen.
	 * 
	 * @param primaryStage Huvudfönster där scenen visas
	 * @param canvas Spelytan där grafik ritas
	 * @param flappyBird Spelet som körs
	 */
	public void setGameScene(Stage primaryStage, Canvas canvas, Game flappyBird) {
	    StackPane gameRoot = new StackPane(canvas);
	    Scene gameScene = new Scene(gameRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
	    
	    flappyBird.handleInput(gameScene);
	    
		primaryStage.setScene(gameScene);
		flappyBird.startGameLoop();
	}

	
    public void showGameOverLayout(Canvas canvas, Game flappyBird) {
        VBox gameOverLayout = createGameOverLayout();

        // Hämta root från canvasens förälder och lägg till overlay
        StackPane root = (StackPane) canvas.getParent();
        root.getChildren().add(gameOverLayout);

        // Hantera tangenttryckningar för att starta om eller gå tillbaka till menyn
        Stage stage = (Stage) canvas.getScene().getWindow();
        root.getScene().setOnKeyPressed(event -> handleGameOverInput(event.getCode(), root, gameOverLayout, stage, canvas, flappyBird));
    }

    private VBox createGameOverLayout() {
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setAlignment(Pos.CENTER);
        gameOverLayout.setPrefSize(400, 200);
        gameOverLayout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");

        Label gameOverLabel = createLabel("GAME OVER", FontWeight.EXTRA_BOLD, 50, Color.RED);
        Label restartLabel = createLabel("Tryck SPACE för att spela igen", FontWeight.BOLD, 20, Color.YELLOW);
        Label menuLabel = createLabel("Tryck ESC för att återgå till menyn", FontWeight.BOLD, 20, Color.LIGHTBLUE);

        gameOverLayout.getChildren().addAll(gameOverLabel, restartLabel, menuLabel);
        return gameOverLayout;
    }

    private Label createLabel(String text, FontWeight weight, int size, Color color) {
        Label label = new Label(text);
        label.setFont(Font.font("Verdana", weight, size));
        label.setTextFill(color);
        return label;
    }

    private void handleGameOverInput(KeyCode keyCode, StackPane root, VBox gameOverLayout, Stage stage, Canvas canvas, Game flappyBird) {
        if (keyCode == KeyCode.SPACE) {
            flappyBird.restartGame();
            root.getChildren().remove(gameOverLayout);
            setGameScene(stage, canvas, flappyBird);
        } else if (keyCode == KeyCode.ESCAPE) {
            stage.setScene(new Scene(new Menu(stage, canvas, flappyBird), WINDOW_WIDTH, WINDOW_HEIGHT));
            flappyBird.stopGame();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
