import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class MyShapes extends JPanel{
	private int forma;
	private boolean filled;
	private Color color = new Color(0,0,0);
	private int x1,x2,y1,y2;
	
	
	public MyShapes(int forma,Color currentColor, boolean filled, int x1, int x2, int y1, int y2){
		this.forma = forma;
		this.filled = filled;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;		
		this.color=currentColor;
		}
		
	public void draw(Graphics g){	
		super.paintComponent(g);
		g.setColor(color);
		if(filled) {
			switch (forma) {
			case 1:
				g.drawLine(x1, y1, x2, y2);
				break;
			case 2:
				g.fillOval(x1, y1, x2-x1, y2-y1);
				break;
			case 3:
				g.fillRect(x1, y1, x2-x1, y2-y1);
				break;
			}
		}else {
			switch (forma){
				case 1:
					g.drawLine(x1, y1, x2, y2);
					break;
				case 2:
					g.drawOval(x1, y1, x2-x1, y2-y1);
					break;
				case 3:
					g.drawRect(x1, y1, x2-x1, y2-y1);
					break;
				
				}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
	}
}
