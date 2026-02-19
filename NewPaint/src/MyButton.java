import javafx.scene.canvas.Canvas;

public abstract class MyButton extends Canvas {
	private PaintSurface myCanvas;

    public MyButton(PaintSurface canvas) {
        myCanvas = canvas;
        setOnMouseClicked(event -> handleClick());
        
    }

    public abstract void handleClick();

    public abstract void redraw();
    
    public abstract void addFrame();

	public PaintSurface getMyCanvas() {
		return myCanvas;
	}


}
