import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Välkommen till ritprogrammet!");
		Shape c = new MyCircle(0, 0, null, 20);
		Shape t = new MyTriangle(0, 0, null, 20);
		Shape s = new MySquare(0, 0, null, 20);
		ArrayList<Shape> sButtons = new ArrayList<>();
		sButtons.add(c);
		sButtons.add(t);
		sButtons.add(s);

		Model m = new Model(s, Color.BLACK);
		PaintSurface myCanvas = new PaintSurface(m);

		BottomPanel bottomPanel = new BottomPanel(myCanvas);
		SidePanel sidePanel = new SidePanel(myCanvas, sButtons);

		VBox vLayout= new VBox();
		vLayout.getChildren().addAll(myCanvas, bottomPanel);
		HBox layout = new HBox();
		layout.getChildren().addAll(vLayout, sidePanel);

		myCanvas.setWidth(1000);
		myCanvas.setHeight(800);

		myCanvas.setOnMouseClicked(event -> {
			m.mouseClicked(event);
			myCanvas.redraw();
		});

		Scene mainScene = new Scene(layout);

		primaryStage.setScene(mainScene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
}
