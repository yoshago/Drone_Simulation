import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListener1 implements KeyListener{  
	private GUI gui;
	private Quadcopter quad;
	KeyListener1(Quadcopter quad, GUI gui){  
		this.gui = gui;
		this.quad = quad;
	}  

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			quad.setAngle(quad.getAngle()-5);
		}

		if (key == KeyEvent.VK_RIGHT) {
			quad.setAngle(quad.getAngle()-5);
		}

		if (key == KeyEvent.VK_UP) {
			int i=1;
			while((int)(quad.getPosition().x + i*Math.sin(Math.toRadians(quad.getAngle())))==quad.getPosition().x&&(int)(quad.getPosition().y + Math.cos(Math.toRadians(quad.getAngle())))==quad.getPosition().y) i+=1;
			Coordinate position = new Coordinate((int)(quad.getPosition().x + i*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y + i*Math.cos(Math.toRadians(quad.getAngle()))));
			quad.setPosition(position);
			gui.setQuadPosition(position);
		}

		if (key == KeyEvent.VK_DOWN) {
			int i=1;
			while((int)(quad.getPosition().x - i*Math.sin(Math.toRadians(quad.getAngle())))==quad.getPosition().x&&(int)(quad.getPosition().y - Math.cos(Math.toRadians(quad.getAngle())))==quad.getPosition().y) i+=1;
			Coordinate position = new Coordinate((int)(quad.getPosition().x - i*Math.sin(Math.toRadians(quad.getAngle()))),(int)(quad.getPosition().y - i*Math.cos(Math.toRadians(quad.getAngle()))));
			quad.setPosition(position);
			gui.setQuadPosition(position);
		}
		if (key == KeyEvent.VK_R) {
			quad.rotate();
		}
		
	}

	public void keyReleased(KeyEvent e) {  

	}  
	public void keyTyped(KeyEvent e) {  

	}  
}
