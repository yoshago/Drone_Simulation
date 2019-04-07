package Algorithms;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import GUI.GUI;
import Objects.Step;
import Objects.Step.stepType; 



public class NavigationAlgorithm implements Runnable{
	
	protected GUI gui;
	protected Queue<Step> stepsQueue;
	
	public NavigationAlgorithm() {
		gui = new GUI();
		stepsQueue = new LinkedList<Step>() ;
	}
	public void navigate() {
		
	}
	public void run() {
		navigate();
	}
	public void nextStep() {
		if (!stepsQueue.isEmpty()) {
			Step next = stepsQueue.poll();
			if(next.type==stepType.DRIVE) {
				gui.drive(true,(int)next.distance*20);
			}
			else {
				gui.turn(next.angle);
			}
		}
	}
	

}
