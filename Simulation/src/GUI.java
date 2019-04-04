import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

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


	protected final HashSet<Line> lines = new HashSet<Line>();
	protected final HashSet<Point> points = new HashSet<Point>();
	protected quadPoint quadPosition;
	protected Quadcopter quad; 
	protected boolean gameOver = false;
	protected double time=0;

	public GUI() {
		quadPosition = new quadPoint(60,60,5,Color.yellow);
	}

	public GUI(Quadcopter quad) {
		this.quad = quad;
		quad.setGui(this);
		quadPosition = new quadPoint(quad.getPosition().x,quad.getPosition().y,5,Color.yellow);
	}

	protected void turn(int angle) {
		quad.setAngle(quad.getAngle()+angle);
		
	}
	
	protected void drive(boolean direction, int distance) {
		int directionMult = -1;
		if(direction) directionMult = 1;
		Coordinate position = new Coordinate((int)(quad.getPosition().x + directionMult*distance*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y + directionMult*distance*Math.cos(Math.toRadians(quad.getAngle()))));
		if(quad.isLegalPosition(position)) {
			setQuadPosition(position);
			quad.setPosition(position);
			time+=2*(Math.sqrt(distance));//compute and add the time of each drive 
		}
		else {
			gameOver();
		}
	}
	
	public HashMap<Double, List<Integer>> rotate()
	{
		HashMap<Double, List<Integer>> map = new HashMap<Double, List<Integer>>();
		for(int i=0 ; i<60 ; i++)
		{
			
			turn(6*i);
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			double frontDist = quad.getFrontLidar().getCurrentDist();
			double rightDist = quad.getRightLidar().getCurrentDist();
			double leftDist = quad.getLeftLidar().getCurrentDist();
			if(map.containsKey(frontDist)) {
				map.get(frontDist).add(quad.getAngle());
			}
			else {
				map.put(frontDist, new LinkedList<Integer>());
				map.get(frontDist).add(quad.getAngle());
			}
			if(map.containsKey(rightDist)) {
				map.get(rightDist).add(quad.getAngle()+60);
			}
			else {
				map.put(rightDist, new LinkedList<Integer>());
				map.get(rightDist).add(quad.getAngle()+60);
			}
			if(map.containsKey(leftDist)) {
				map.get(leftDist).add(quad.getAngle()-55);
			}
			else {
				map.put(leftDist, new LinkedList<Integer>());
				map.get(leftDist).add(quad.getAngle()-55);
			}
			
			
			
			
		}
		return map;
	}



	private void gameOver() {
		gameOver = true;
		quadPosition = null;
		lines.clear();
		points.clear();
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

	synchronized public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	synchronized public void addLine(int x1, int x2, int x3, int x4, Color color) {
		Line addingLine = new Line(x1,x2,x3,x4, color);
		boolean didAddline = lines.add(addingLine); 
		//		if(didAddline) System.out.println("hi line");
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


	}
	public void setQuad(Quadcopter quad) {
		this.quad = quad;
	}



}

