import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class ManualControl extends GUI implements KeyListener{  

	

	private final LinkedList<GUI.Line> lines = new LinkedList<GUI.Line>();
	private final LinkedList<GUI.Point> points = new LinkedList<GUI.Point>();
	private Quadcopter quad;
	
	ManualControl(Quadcopter quad){
		this.quad = quad;
		quad.setGui(this);
		
	}
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			quad.setAngle(quad.getAngle()-5);
		}

		if (key == KeyEvent.VK_RIGHT) {
			quad.setAngle(quad.getAngle()-5);
		}

		if (key == KeyEvent.VK_UP) {
			int i=5;
			while((int)(quad.getPosition().x + i*Math.sin(Math.toRadians(quad.getAngle())))==quad.getPosition().x&&(int)(quad.getPosition().y + Math.cos(Math.toRadians(quad.getAngle())))==quad.getPosition().y) i+=5;
			Coordinate position = new Coordinate((int)(quad.getPosition().x + i*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y + i*Math.cos(Math.toRadians(quad.getAngle()))));
			setQuadPosition(position);
		}

		if (key == KeyEvent.VK_DOWN) {
			int i=1;
			while((int)(quad.getPosition().x - i*Math.sin(Math.toRadians(quad.getAngle())))==quad.getPosition().x&&(int)(quad.getPosition().y - Math.cos(Math.toRadians(quad.getAngle())))==quad.getPosition().y) i+=5;
			Coordinate position = new Coordinate((int)(quad.getPosition().x - i*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y - i*Math.cos(Math.toRadians(quad.getAngle()))));
			setQuadPosition(position);
		}
		if (key == KeyEvent.VK_R) {
			quad.rotate();
		}
		
	}

	public void keyReleased(KeyEvent e) {  
		System.out.println("Released");

	}  
	public void keyTyped(KeyEvent e) {
		System.out.println("typed");


	}  
}
