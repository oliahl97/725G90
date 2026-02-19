import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
	private int x;
	private int y;
	private Color myColor;

	public Shape(int x, int y, Color myColor) {
		this.x = x;
		this.y = y;
		this.myColor = myColor;
	}

	public abstract void drawYourself(GraphicsContext gc);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getMyColor() {
		return myColor;
	}

}