import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JComponent{

	private static class Line{
		final int x1; 
		final int y1;
		final int x2;
		final int y2;   
		final Color color;

		public Line(int x1, int y1, int x2, int y2, Color color) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
		}               
	}
	private static class Point{
		final int x; 
		final int y;
		final int radius;
		final Color color;

		public Point(int x, int y, int radius, Color color) {
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.color = color;
		}               
	}


	private final LinkedList<Line> lines = new LinkedList<Line>();
	private final LinkedList<Point> points = new LinkedList<Point>();
	private Coordinate quadPosition = new Coordinate(60,60);
	
	public void setQuadPosition(Coordinate co) {
		addLine(quadPosition.x,quadPosition.y,co.x, co.y, Color.blue);
		quadPosition.x = co.x;
		quadPosition.y = co.y;
		repaint();
	}

	synchronized public void addPoint(int x, int y, int radius) {
		addPoint(x, y, radius, Color.red);
	}

	synchronized public void addPoint(int x, int y, int radius, Color color) {
		points.add(new Point(x, y, radius, color));        
		repaint();
	}

	synchronized public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	synchronized public void addLine(int x1, int x2, int x3, int x4, Color color) {
		lines.add(new Line(x1,x2,x3,x4, color));        
		repaint();
	}

	public void clearLines() {
		lines.clear();
		repaint();
	}

	@Override
	synchronized protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Line line : lines) {
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);

		}
		for (Point point : points) {
			g.setColor(point.color);
			g.drawOval(point.x-point.radius/2, point.y-point.radius/2, point.radius, point.radius);
			g.fillOval(point.x-point.radius/2, point.y-point.radius/2 , point.radius, point.radius); 
			
		}
		

	}



}

