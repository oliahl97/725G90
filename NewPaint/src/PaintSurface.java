import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintSurface extends Canvas {
	private Model model;
	GraphicsContext gc;

	
	public PaintSurface(Model model) {
		this.model = model;
		gc = getGraphicsContext2D();
		setOnMouseClicked(event -> {
			model.mouseClicked(event);
			redraw();

		});
	}
	
	public void redraw() {
		gc.clearRect(0, 0, getWidth(), getHeight());
		for (Shape shape : model.getContents()) {
			shape.drawYourself(gc);
		}
	}	
	
	
	public void clear() {
		model.clear();
		redraw();		
	}
	
	public void undo() {
		model.undo();
		redraw();		
	}
	
	public void setBrushColor(Color color) {
	    model.setColor(color);
	}
	
	public void setBrushShape(Shape myShape) {
	    model.setShape(myShape);
	}

}
