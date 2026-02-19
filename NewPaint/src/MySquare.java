import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MySquare extends Shape {
	
	private int size;
	
	public MySquare(int x, int y, Color myColor, int size) {
		super(x,y,myColor);
		this.size = size;
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(getMyColor());
		gc.fillRect(getX() - size / 2, getY() - size / 2, size, size);
		
	}

}