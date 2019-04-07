package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Algorithms.NavigationAlgorithm;
import Objects.Coordinate;
import Objects.Quadcopter;

public class GUI extends JComponent{



	public static class quadPoint{
		int x; 
		int y;
		final int radius;
		final Color color;

		public quadPoint(int x, int y, int radius, Color color) {
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.color = color;
		}		
	}


	protected final ArrayList<Line> lines = new ArrayList<Line>();
	protected final ArrayList<Point> points = new ArrayList<Point>();
	protected int numOfLinesAndPoints=0;
	protected quadPoint quadPosition;
	protected Quadcopter quad; 
	protected boolean gameOver = false;
	protected double time=0;
	protected NavigationAlgorithm algo;

	public GUI() {
		quadPosition = new quadPoint(60,60,5,Color.yellow);
	}

	public GUI(Quadcopter quad) {
		this.quad = quad;
		quad.setGui(this);
		quadPosition = new quadPoint(quad.getPosition().x,quad.getPosition().y,5,Color.yellow);
	}

	public void turn(int angle) {
		quad.setAngle((quad.getAngle()+angle)%360);


	}

	public void drive(boolean direction, int distance) {
		int directionMult = -1;
		if(direction) directionMult = 1;

		Coordinate position = new Coordinate((int)(quad.getPosition().x + directionMult*distance*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y + directionMult*distance*Math.cos(Math.toRadians(quad.getAngle()))));
		if(quad.isLegalPosition(position)) {
			setQuadPosition(position);
			quad.setPosition(position);
			time+=2*(Math.sqrt(distance/20.0));//compute and add the time of each drive 
		}
		else {
			gameOver();
		}

	}





	private void gameOver() {
		gameOver = true;
		quadPosition = null;
//		lines.clear();
//		points.clear();

		repaint();

	}

	protected void setQuadPosition(Coordinate co) {	
		addLine(quadPosition.x,quadPosition.y,co.x, co.y, Color.blue);
		quadPosition.x = co.x;
		quadPosition.y = co.y;
		repaint();
	}

	synchronized public void addPoint(int x, int y, int radius) {
		addPoint(x, y, radius, Color.red);
	}

	synchronized public void addPoint(int x, int y, int radius, Color color) {
		Point addingPoint = new Point(x, y, radius, color);
		if(!points.contains(addingPoint)) {
			boolean didAddPoint = points.add(addingPoint);
			if (didAddPoint) 
				if(points.size() ==5) {
					Iterator<Point> it = points.iterator();
					while(it.hasNext()) {
						Point p = (Point) it.next();
						System.out.println("hi point "+ p.x + " " + p.y);
					}
				}


			repaint();
		}
	}

	synchronized public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	synchronized public void addLine(int x1, int x2, int x3, int x4, Color color) {
		Line addingLine = new Line(x1,x2,x3,x4, color);
		if (!lines.contains(addingLine)) {
			boolean didAddline = lines.add(addingLine); 
			//		if(didAddline) System.out.println("hi line");
			repaint();
		}
	}

	public void clearLines() {
		lines.clear();
		repaint();
	}

	@Override
	synchronized protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0;i < numOfLinesAndPoints&&i<lines.size();i++) {
			Line line = lines.get(i);
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);

		}
		for (int i=0;i < numOfLinesAndPoints/3&&i<points.size();i++) {
			Point point = points.get(i);
			g.setColor(point.color);
			g.drawOval(point.x-point.radius/2, point.y-point.radius/2, point.radius, point.radius);
			g.fillOval(point.x-point.radius/2, point.y-point.radius/2 , point.radius, point.radius); 

		}
		if (quadPosition != null) {
			g.setColor(quadPosition.color);
			g.drawOval(quadPosition.x-quadPosition.radius/2, quadPosition.y-quadPosition.radius/2, quadPosition.radius, quadPosition.radius);
			g.fillOval(quadPosition.x-quadPosition.radius/2, quadPosition.y-quadPosition.radius/2 , quadPosition.radius, quadPosition.radius);
		}
		if(gameOver) {
			g.setColor(Color.orange);
			g.setFont(new Font("TimesRoman", Font.BOLD, 42)); 
			g.drawString("GAME OVER", 60, 60);
		}
		g.setColor(Color.black);
		g.drawString("Quad's direction", 30, quad.getBackground().getHeight()+20);
		g.drawLine(quad.getBackground().getWidth()/2, quad.getBackground().getHeight()+20, (int) (quad.getBackground().getWidth()/2 + 15*Math.sin(Math.toRadians(quad.getAngle()))), (int)(quad.getBackground().getHeight()+20 + 15*Math.cos(Math.toRadians(quad.getAngle()))));
		g.setColor(Color.red);
		g.drawOval((int) (quad.getBackground().getWidth()/2 + 15*Math.sin(Math.toRadians(quad.getAngle())))-2, (int)(quad.getBackground().getHeight()+20 + 15*Math.cos(Math.toRadians(quad.getAngle())))-2,5,5);
		g.fillOval((int) (quad.getBackground().getWidth()/2 + 15*Math.sin(Math.toRadians(quad.getAngle())))-2, (int)(quad.getBackground().getHeight()+20 + 15*Math.cos(Math.toRadians(quad.getAngle())))-2,5,5);


	}
	public void setQuad(Quadcopter quad) {
		this.quad = quad;
	}

	public Quadcopter getQuad() {
		return quad;
	}
	public double getTime() {
		return time;
	}



}

