import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCircle extends Shape {

	private double radius;

	public MyCircle(int x, int y, Color myColor, double radius) {
		super(x, y, myColor);
		this.radius = 20;

	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(getMyColor());
		gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);

	}

}