package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Objects.Quadcopter;



public class ManualControlGUI extends GUI implements KeyListener{  



	
	public ManualControlGUI(Quadcopter quad){
		this.quad = quad;
		quad.setGui(this);
		quad.addLidars();
		quadPosition = new quadPoint(quad.getPosition().x,quad.getPosition().y,5,Color.yellow);
		
		
	}
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			turn(5);
		}

		if (key == KeyEvent.VK_RIGHT) {
			turn(-5);
		}

		if (key == KeyEvent.VK_UP) {
			drive(true, 5);
		}

		if (key == KeyEvent.VK_DOWN) {
			drive(false, 5);
		}
		if (key == KeyEvent.VK_R) {
			Thread t1 = new Thread(quad.getAlgo());
			t1.run();
		}
		if (key == KeyEvent.VK_SPACE) {
			quad.getAlgo().nextStep();
		}

	}

	public void keyReleased(KeyEvent e) {  
		System.out.println("Released");

	}  
	public void keyTyped(KeyEvent e) {
		System.out.println("typed");


	}  
	
	
}
