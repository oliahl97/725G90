import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Model {
private ArrayList <Shape> contents = new ArrayList<>();
private Shape currentShape;
private Color currentColor;


public Model(Shape currentShape, Color currentColor) {
	this.currentShape = currentShape;
	this.currentColor = currentColor;
}

	public void mouseClicked(MouseEvent event) {
		int mouseX = (int) event.getX();
		int mouseY = (int) event.getY();
		if (currentShape instanceof MyCircle) {
			currentShape = new MyCircle(mouseX, mouseY, getColor(), 20);
		} else if (currentShape instanceof MyTriangle) {
			currentShape = new MyTriangle(mouseX, mouseY, getColor(), 20);
		} else if (currentShape instanceof MySquare) {
			currentShape = new MySquare(mouseX, mouseY, getColor(), 40);
		}
		addShape(currentShape);
		
	}
	
	public Color getColor() {
		return currentColor;
	}
	
	public void setColor(Color c) {
		currentColor = c;
	}
	
	public void addShape(Shape s) {
		contents.add(s);
	}
	
	public void setShape(Shape s) {
		currentShape = s;
	}
	
	
	public ArrayList<Shape> getContents() {
		return contents;
	}
	
	public Shape getCurrentShape() {
		return currentShape;
	}
	
	public void clear() {
		contents.clear();
	}
	
	public void undo() {
		if(!contents.isEmpty()) {
			contents.remove(contents.size() - 1);
		}
	}
}
