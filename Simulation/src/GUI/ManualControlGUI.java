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
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			numOfLinesAndPoints+=5;
			turn(5);
		}

		if (key == KeyEvent.VK_RIGHT) {
			numOfLinesAndPoints+=5;
			turn(-5);
		}

		if (key == KeyEvent.VK_UP) {
			numOfLinesAndPoints+=5;
			drive(true, 5);
		}

		if (key == KeyEvent.VK_DOWN) {
			numOfLinesAndPoints+=5;
			drive(false, 5);
		}
		if (key == KeyEvent.VK_R) {

		}
		if (key == KeyEvent.VK_SPACE) {
			
			quad.getAlgo().navigate();
		}

	}

	public void keyReleased(KeyEvent e) {  

	}  
	public void keyTyped(KeyEvent e) {


	}  
	
	
}
