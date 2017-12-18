import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DrawPanel extends JPanel {
	private MyShapes[] shapes = new MyShapes[100];
	private int shapeCount =0;
	private int shapeType = 0;
	MyShapes currentShape = null;
	private Color currentColor = Color.BLACK;
	private boolean filledShape = false;
	JLabel statusLabel;
	int x1,x2,y1,y2;
	String texto;
	private ArrayList<Point> points = new ArrayList<>();
	
	public DrawPanel(JLabel label){
		MotionHandler motionHandler = new MotionHandler();
		addMouseMotionListener(motionHandler);
		ClickHandler clickHandler = new ClickHandler();
		addMouseListener(clickHandler);
		statusLabel = label;
		this.setBackground(Color.WHITE);
	}
	
	private class ClickHandler extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent event){
			x1 = event.getX();
			y1 = event.getY();
		}
		
		@Override
		public void mouseReleased(MouseEvent event) {
			if(shapeType != 0) {
				x2 = event.getX();
				y2 = event.getY();
				currentShape = new MyShapes(shapeType,currentColor, filledShape, x1, x2, y1, y2);
				shapes[shapeCount]=currentShape;
				shapeCount++;
				currentShape = null;
				repaint();
			}
		}
		
	}

	
	private class MotionHandler extends MouseMotionAdapter{
			
			
			@Override
			public void mouseMoved(MouseEvent event) {
				texto = String.format("%d:%d", event.getX(),event.getY());
				statusLabel.setText(texto);
			}
			
			
			@Override
			public void mouseDragged(MouseEvent event) {
				texto = String.format("%d:%d", event.getX(),event.getY());
				statusLabel.setText(texto);
				if(shapeType != 0) {
					x2 = event.getX();
					y2 = event.getY();
					currentShape = new MyShapes(shapeType,currentColor, filledShape, x1, x2, y1, y2);
				} else {
					points.add(event.getPoint());
				}
				repaint();
			}
		}
	
	public int getShapeType() {
		return shapeType;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}

	public boolean isFilledShape() {
		return filledShape;
	}


	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public void setFilledShape(boolean filledShape) {
		this.filledShape = filledShape;
	}
	
	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}
	
	public void decrementShapeCount() {
		shapeCount--;
	}
	
	public void clearShapeCount() {
		shapeCount = 0;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i =0; i<shapeCount; i++) {
			shapes[i].draw(g);
		}
		if (currentShape != null) {
			currentShape.draw(g);
		}
		
		for(Point ponto:points) {
			g.setColor(currentColor);
			g.fillOval(ponto.x, ponto.y, 4, 4);
		}
		
	}
	
	public void clearLastShape() {
		if (shapeCount>0)
			shapeCount--;
		repaint();
	}
	
	public void clearDrawing() {
		shapeCount = 0;
		repaint();
	}
	public void removerPoint() {
		points.remove(points.size()-1);
	}
	
	public void clearPoints() {
		points.removeAll(points);
	}
	
}
