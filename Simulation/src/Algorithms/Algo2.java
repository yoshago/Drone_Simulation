package Algorithms;

import GUI.GUI;
import Objects.Quadcopter;

public class Algo2 extends NavigationAlgorithm{
	
	Quadcopter quad;

	
	public Algo2(GUI gui) {
		this.gui = gui;
		this.quad=gui.getQuad();
	}

	public void navigate() {
		while(gui.getTime()<=300) {
			System.out.println("time is: " + gui.getTime());
			if(quad.getVelocity()<=quad.getFrontLidar().getCurrentDist()/1.2) 
				quad.setAccAndVelocity(1.0);
			else
				quad.setAccAndVelocity(-1.0);
				if(quad.getRightLidar().getCurrentDist()>quad.getLeftLidar().getCurrentDist())
					quad.turnRight();
				else if(quad.getRightLidar().getCurrentDist()<quad.getLeftLidar().getCurrentDist())
					quad.turnLeft();
			
			
			System.out.println("v: " + quad.getVelocity());
			System.out.println("d: " + quad.getFrontLidar().getCurrentDist());
			gui.drive();
		}
	}
}
