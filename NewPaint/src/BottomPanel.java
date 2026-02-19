import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BottomPanel extends HBox {
    private Button clear;
    private Button undo;
    private PaintSurface myCanvas;

    public BottomPanel(PaintSurface canvas) {
        clear = new Button("Rensa");
        undo = new Button("Ångra");
        myCanvas = canvas;

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myCanvas.clear();
            }
        });

        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myCanvas.undo();
            }
        });

        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        
        getChildren().addAll(clear, undo);
    }
}