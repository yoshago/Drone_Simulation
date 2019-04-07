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
		for(int i=0 ; i<60 ; i++)
		{
			gui.turn(6);

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				map.get(rightDist).add(quad.getAngle()+60);
			}
			else {
				map.put(rightDist, new ArrayList<Integer>());
				map.get(rightDist).add(quad.getAngle()+60);
			}
			if(map.containsKey(leftDist)) {
				map.get(leftDist).add(quad.getAngle()-55);
			}
			else {
				map.put(leftDist, new ArrayList<Integer>());
				map.get(leftDist).add(quad.getAngle()-55);
			}
		}
		return map;
	}
	public void navigate() {
		int longestWay;
		while(gui.getTime()<=200) {
			TreeMap<Double, ArrayList<Integer>> map = rotate();
			System.out.println("this " + map.lastKey());
			longestWay = getLongest(map);
			System.out.println(gui.getTime());
			if(map.lastKey()>=2) {
				gui.turn(longestWay-gui.getQuad().getAngle());
				gui.drive(true, 30);
			}

		}



	}

	private int getLongest(TreeMap<Double, ArrayList<Integer>> map) {	 
		return map.get(map.lastKey()).get(0);
	}

}
