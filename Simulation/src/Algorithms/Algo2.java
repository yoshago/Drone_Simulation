package Algorithms;

import GUI.GUI;
import Objects.Quadcopter;

public class Algo2 extends NavigationAlgorithm{
	
	Quadcopter quad;
	double tempTime;
	
	public Algo2(GUI gui) {
		this.gui = gui;
		this.quad=gui.getQuad();
		tempTime = gui.getTime();
	}

	public void navigate() {
		tempTime = gui.getTime();
		while(gui.getTime()<=tempTime+1.0) {
			System.out.println("time is: " + gui.getTime());
			double right_dist=quad.getRightLidar().getCurrentDist();
			double left_dist=quad.getLeftLidar().getCurrentDist();
			double front_dist=quad.getFrontLidar().getCurrentDist();
			
			if(quad.getVelocity()<=front_dist/1.2 && front_dist>=0.35) {
				if(front_dist<right_dist && left_dist<right_dist)
					quad.turnRight(1.0);
				else if(front_dist<left_dist && right_dist<left_dist)
					quad.turnLeft(1.0);
				else if(right_dist-left_dist>0)
					quad.turnRight((right_dist-left_dist)/(left_dist+right_dist));
				else if(left_dist-right_dist>0)
					quad.turnLeft((left_dist-right_dist)/(left_dist+right_dist));
				quad.setAccAndVelocity(1.0);
			}
			else if(front_dist>0.35) {
				quad.setAccAndVelocity(-1.0);
				if(right_dist>left_dist)
					quad.turnRight(1.0);
				else if(right_dist<left_dist)
					quad.turnLeft(1.0);
			}
			else
				alert(right_dist, left_dist);
			
			System.out.println("v: " + quad.getVelocity());
			System.out.println("d: " + front_dist);
			gui.drive();
		}
		System.out.println(gui.fail);
	}

	private void avoidAlert(double right_dist, double left_dist,double front_dist) {
		int RL;
		if(right_dist/left_dist>=1.5)
			RL=1;
		else if(left_dist/right_dist>=1.5)
			RL=0;
		else
			RL=(int)(Math.random()*2.0);

		while(quad.getVelocity()>=front_dist/1.2 && front_dist>=0.35){
			quad.setAccAndVelocity(-1.0);
			if(quad.getVelocity()<0)
				quad.setVelocity(0);
			if(RL==1) 
				quad.turnRight(1.0);
			else 
				quad.turnLeft(1.0);
			front_dist=quad.getFrontLidar().getCurrentDist();
			gui.drive();
			
		}
	}

	private void alert(double right_dist, double left_dist) {
		int RL;
		if(right_dist/left_dist>=1.5)
			RL=1;
		else if(left_dist/right_dist>=1.5)
			RL=0;
		else
			RL=(int)(Math.random()*2.0);

		tempTime = gui.getTime();
		while(gui.getTime()<=tempTime+1.0)
		{
//			double right_dist=quad.getRightLidar().getCurrentDist();
//			double left_dist=quad.getLeftLidar().getCurrentDist();
//			double front_dist=quad.getFrontLidar().getCurrentDist();

			quad.setAccAndVelocity(-1.0);
			if(quad.getVelocity()<0)
				quad.setVelocity(0);
			if(RL==1) 
				quad.turnRight(1.0);
			else 
				quad.turnLeft(1.0);
			
			gui.drive();
		}
		
	}
}
