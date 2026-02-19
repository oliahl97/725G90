import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShapeButton extends MyButton {
	private static ShapeButton lastSelectedButton;
	private Shape myShape;
	private PaintSurface myCanvas;
	private boolean isSelected;
	
	
	public ShapeButton(PaintSurface myCanvas, Shape myShape) {
		super(myCanvas);
		this.myShape = myShape;
		this.isSelected = false;
		
		setWidth(50);
		setHeight(50);
		setOnMouseClicked(event -> handleClick());

	}

	public Shape getShape() {
		return myShape;
	}

	@Override
	public void handleClick() {

		myCanvas = getMyCanvas();
		myCanvas.setBrushShape(myShape);
		
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
		gc.setFill(Color.BLACK);
		gc.clearRect(0,0,getWidth(), getHeight());
		
		if (isSelected) {
			addFrame();
		}
		
		if (this.getShape() instanceof MyCircle) {
			gc.fillOval(10, 10, 30, 30);

		} else if (this.getShape() instanceof MyTriangle) {
			double[] xPoints = { 25.0, 40.0, 10.0 };
			double[] yPoints = { 7.0, 40.0, 40.0 };
			gc.fillPolygon(xPoints, yPoints, 3);

		} else {
			gc.fillRect(10, 10, 30, 30);
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
