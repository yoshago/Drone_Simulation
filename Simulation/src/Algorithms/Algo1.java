package Algorithms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import GUI.GUI;
import Objects.Quadcopter;
import Objects.Step;

public class Algo1 extends NavigationAlgorithm{

	public Algo1(GUI gui) {
		this.gui = gui;
	}

	public TreeMap<Double, ArrayList<Integer>> rotate()
	{
		Quadcopter quad = gui.getQuad();
		TreeMap<Double, ArrayList<Integer>> map = new TreeMap<Double, ArrayList<Integer>>();
		for(int i=0 ; i<36 ; i++)
		{
			gui.turn(10);

			
			double frontDist = quad.getFrontLidar().getCurrentDist();
			double rightDist = quad.getRightLidar().getCurrentDist();
			double leftDist = quad.getLeftLidar().getCurrentDist();
			if(map.containsKey(frontDist)) {
				map.get(frontDist).add(quad.getAngle());
			}
			else {
				map.put(frontDist, new ArrayList<Integer>());
				map.get(frontDist).add(quad.getAngle());
			}
			if(map.containsKey(rightDist)) {
				map.get(rightDist).add(quad.getAngle()+quad.getRightAngle());
			}
			else {
				map.put(rightDist, new ArrayList<Integer>());
				map.get(rightDist).add(quad.getAngle()+quad.getRightAngle());
			}
			if(map.containsKey(leftDist)) {
				map.get(leftDist).add(quad.getAngle()+quad.getLeftAngle());
			}
			else {
				map.put(leftDist, new ArrayList<Integer>());
				map.get(leftDist).add(quad.getAngle()+quad.getLeftAngle());
			}
		}
		return map;
	}
	public void navigate() {
		int longestWay=0;
		double longestDistance =0;
		boolean rotate = true; 
		while(gui.getTime()<=600) {
			double frontDist = gui.getQuad().getFrontLidar().getCurrentDist();
			double rightDist = gui.getQuad().getRightLidar().getCurrentDist();
			double leftDist = gui.getQuad().getLeftLidar().getCurrentDist();
			Tuple<Integer,Double> tup = getLongestWayAndDistance(frontDist, rightDist, leftDist);
			longestDistance = tup.y;
			longestWay = tup.x;
			
		
			if(rotate) {
				TreeMap<Double, ArrayList<Integer>> map = rotate();
				longestDistance = map.lastKey();
				System.out.println("this " + map.lastKey());
				longestWay = getLongest(map);
				rotate=false;
			}
			System.out.println(gui.getTime());
			if(longestDistance>=1.5) {
				System.out.println(longestDistance);
				gui.turn(longestWay-gui.getQuad().getAngle());
				gui.drive(true, 40);
				
				
				

			}
			else {
				//				gui.drive(false, 6);
				rotate = true;
			}
		}
	}

	private Tuple<Integer,Double> getLongestWayAndDistance(double frontDist, double rightDist, double leftDist) {
		if(frontDist>=rightDist&&frontDist>=leftDist) return new Tuple<Integer, Double>(gui.getQuad().getAngle(),frontDist);
		else if(rightDist>=leftDist) return new Tuple<Integer, Double>(gui.getQuad().getAngle()+gui.getQuad().getRightAngle(),rightDist);
		return new Tuple<Integer, Double>(gui.getQuad().getAngle()+gui.getQuad().getLeftAngle(), leftDist);
	}



	private int getLongest(TreeMap<Double, ArrayList<Integer>> map) {	
		int angle = map.get(map.lastKey()).get((int)Math.random()*(map.size()-1));
		int i=0;
		while(angle<gui.getQuad().getAngle()+100&&angle>gui.getQuad().getAngle()-100&&i<12) {
			angle = map.get(map.lastKey()).get((int)Math.random()*(map.size()-1));
			i++;
		}
		return angle;
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
