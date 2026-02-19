import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class SidePanel extends VBox {
	private ArrayList<ColorButton> cButtons = new ArrayList<>();
	private ArrayList<ShapeButton> sButtons = new ArrayList<>();
	private ArrayList<Shape> shapes = new ArrayList<>();
	private PaintSurface myCanvas;

	public SidePanel(PaintSurface myCanvas, ArrayList<Shape> shapes) {

		this.myCanvas = myCanvas;
		this.shapes = shapes;
		cButtons = new ArrayList<>();
		sButtons = new ArrayList<>();
		addColorButtons();
		addShapeButtons();
		getChildren().addAll(cButtons);
		getChildren().addAll(sButtons);
		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

		if (!cButtons.isEmpty()) {
			cButtons.get(0).handleClick();
		}

		if (!sButtons.isEmpty()) {
			sButtons.get(0).handleClick();
		}
	}

	public void addShapeButtons() {

		sButtons.add(new ShapeButton(myCanvas, shapes.get(0)));
		sButtons.add(new ShapeButton(myCanvas, shapes.get(1)));
		sButtons.add(new ShapeButton(myCanvas, shapes.get(2)));

		redrawShapeButtons();

	}

	public void redrawShapeButtons() {

		for (ShapeButton button : sButtons) {
			button.redraw();

		}
	}

	public void addColorButtons() {

		cButtons.add(new ColorButton(myCanvas, Color.BLACK));
		cButtons.add(new ColorButton(myCanvas, Color.RED));
		cButtons.add(new ColorButton(myCanvas, Color.HOTPINK));
		cButtons.add(new ColorButton(myCanvas, Color.YELLOW));
		cButtons.add(new ColorButton(myCanvas, Color.DARKGREEN));
		cButtons.add(new ColorButton(myCanvas, Color.ROYALBLUE));
		cButtons.add(new ColorButton(myCanvas, Color.PURPLE));
		cButtons.add(new ColorButton(myCanvas, Color.SADDLEBROWN));

		redrawColorButtons();

	}

	public void redrawColorButtons() {
		for (ColorButton button : cButtons) {
			button.redraw();
		}
	}

}
