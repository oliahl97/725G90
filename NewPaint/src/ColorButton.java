import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorButton extends MyButton {
	private Color myColor;
	private static ColorButton lastSelectedButton;
	private PaintSurface myCanvas;
	private boolean isSelected;

	public ColorButton(PaintSurface myCanvas, Color myColor) {
		super(myCanvas);
		this.myColor = myColor;
		setWidth(50);
		setHeight(50);
		this.isSelected = false;
	}

	public Color getMyColor() {
		return myColor;
	}

	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}

	@Override
	public void handleClick() {

		myCanvas = getMyCanvas();
		myCanvas.setBrushColor(myColor);

		if (lastSelectedButton != null) {
			lastSelectedButton.setSelected(false);
			lastSelectedButton.redraw();
		}

		isSelected = true;
		redraw();
		lastSelectedButton = this;

	}

	public void setSelected(boolean b) {
		isSelected = b;

	}

	@Override
	public void redraw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(myColor);
		gc.fillRect(0, 0, getWidth(), getHeight());

		if (isSelected) {
			addFrame();
		}
	}

	@Override
	public void addFrame() {

		GraphicsContext gc = getGraphicsContext2D();
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(5);
		gc.strokeRect(0, 0, getWidth(), getHeight());

	}

}
