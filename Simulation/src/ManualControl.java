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
	private quadPoint quadPoint;

	ManualControl(Quadcopter quad){
		this.quad = quad;
		quad.setGui(this);
		quad.addLidars();
		quadPoint = new quadPoint(quad.getPosition().x, quad.getPosition().y, 5 , Color.yellow);

	}
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			quad.setAngle(quad.getAngle()+5);
		}

		if (key == KeyEvent.VK_RIGHT) {
			quad.setAngle(quad.getAngle()-5);
		}

		if (key == KeyEvent.VK_UP) {
			Coordinate position = new Coordinate((int)(quad.getPosition().x + 5*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y + 5*Math.cos(Math.toRadians(quad.getAngle()))));
			if(quad.isLegalPosition(position)) {
				setQuadPosition(position);
				quad.setPosition(position);
			}
		}

		if (key == KeyEvent.VK_DOWN) {
			Coordinate position = new Coordinate((int)(quad.getPosition().x + 5*Math.sin(Math.toRadians(quad.getAngle()+180))),(int)(quad.getPosition().y + 5*Math.cos(Math.toRadians(quad.getAngle()+180))));
			if(quad.isLegalPosition(position)) {
				setQuadPosition(position);
				quad.setPosition(position);
			}
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
