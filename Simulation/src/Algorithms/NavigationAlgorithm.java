package Algorithms;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import GUI.GUI;
import Objects.Step;
import Objects.Step.stepType; 

public abstract class NavigationAlgorithm implements Runnable{
	
	protected GUI gui;
	protected Queue<Step> stepsQueue;
	
	public NavigationAlgorithm() {
		gui = new GUI();
		stepsQueue = new LinkedList<Step>();
	}
	
	public NavigationAlgorithm(GUI gui) {
		this.gui = gui;
		stepsQueue = new LinkedList<Step>();
	}
	
	public void run() {
		navigate();
	}
	
	protected abstract void navigate();
	
	public void nextStep() {
		if (!stepsQueue.isEmpty()) {
			Step next = stepsQueue.poll();
			if(next.getType() == stepType.DRIVE) {
				gui.drive(true, (int)next.getDistance() * 20);
			}
			else {
				gui.turn(next.getAngle());
			}
		}
	}
	

}
