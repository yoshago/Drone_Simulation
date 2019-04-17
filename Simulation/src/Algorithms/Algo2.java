package Algorithms;

import java.util.ArrayList;
import java.util.TreeMap;

import Algorithms.Algo1.Tuple;
import GUI.GUI;
import Objects.Quadcopter;

public class Algo2 extends NavigationAlgorithm{

	Quadcopter quad;


	public Algo2(GUI gui) {
		this.gui = gui;
		this.quad=gui.getQuad();
	}

	public void navigate() {
		double longestDistance;
		double longestWay;
		while(gui.getTime()<=300) {
			
			double frontDist = gui.getQuad().getFrontLidar().getCurrentDist();
			double rightDist = gui.getQuad().getRightLidar().getCurrentDist();
			double leftDist = gui.getQuad().getLeftLidar().getCurrentDist();
			if(quad.getVelocity()<=frontDist/1.3) 
				quad.setAccAndVelocity(1.0);
			else {
				quad.setAccAndVelocity(-1.0);
			}
			System.out.println("time is: " + gui.getTime());
			
			Tuple<Double,Double> tup = getLongestWayAndDistance(frontDist, rightDist, leftDist);
			longestDistance = tup.y;
			longestWay = tup.x;
			quad.turn(longestWay);
			
			


			System.out.println("v: " + quad.getVelocity());
			System.out.println("d: " + quad.getFrontLidar().getCurrentDist());
			gui.drive();
			if(gui.isGameOver()) break;
			
		}
	}
	private Tuple<Double,Double> getLongestWayAndDistance(double frontDist, double rightDist, double leftDist) {
		if(frontDist>=rightDist&&frontDist>=leftDist) return new Tuple<Double,Double>(0.0,frontDist);
		else if(rightDist>=leftDist) return new Tuple<Double,Double>(1.0,rightDist);
		return new Tuple<Double,Double>(-1.0, leftDist);
	}
	public class Tuple<X, Y> { 
		public final X x; 
		public final Y y; 
		public Tuple(X x, Y y) { 
			this.x = x; 
			this.y = y; 
		} 
	} 
}
