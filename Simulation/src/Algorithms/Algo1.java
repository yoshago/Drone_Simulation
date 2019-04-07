package Algorithms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import GUI.GUI;
import Objects.Quadcopter;
import Objects.Step;

public class Algo1 extends NavigationAlgorithm{

	public Algo1(GUI gui) {
		super(gui);
	}

	public HashMap<Double, List<Integer>> rotate()
	{
		Quadcopter quad = gui.getQuad();
		HashMap<Double, List<Integer>> map = new HashMap<Double, List<Integer>>();
		for(int i = 0; i < 60; i++)
		{
			this.stepsQueue.add(new Step(6));
			try {
				Thread.sleep(14);
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
				map.put(frontDist, new LinkedList<Integer>());
				map.get(frontDist).add(quad.getAngle());
			}
			if(map.containsKey(rightDist)) {
				map.get(rightDist).add(quad.getAngle()+60);
			}
			else {
				map.put(rightDist, new LinkedList<Integer>());
				map.get(rightDist).add(quad.getAngle()+60);
			}
			if(map.containsKey(leftDist)) {
				map.get(leftDist).add(quad.getAngle()-55);
			}
			else {
				map.put(leftDist, new LinkedList<Integer>());
				map.get(leftDist).add(quad.getAngle()-55);
			}
		}
		return map;
	}
	
	@Override
	public void navigate() {
		rotate();
		this.stepsQueue.add(new Step(5.0));
		rotate();
	}

}
