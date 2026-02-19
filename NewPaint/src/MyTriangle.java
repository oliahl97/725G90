import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyTriangle extends Shape{
	private int size;

	public MyTriangle(int x, int y, Color color, int size) {
		super(x, y, color);
		this.size = size;
	}


	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(getMyColor());
		double[] xPoints = {getX()-size, getX()+size , getX() + size/25};
        double[] yPoints = {getY()+size, getY()+size, getY()-size};
        gc.fillPolygon(xPoints, yPoints, 3);
	}
}
