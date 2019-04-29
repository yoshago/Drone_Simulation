package Algorithms;

import java.util.ArrayList;

import GUI.GUI;
import GUI.Point;
import Objects.Coordinate;
import Objects.Junction;
import Objects.Quadcopter;

public class Algo3 extends NavigationAlgorithm{

	double last_right_dist=0, last_left_dist=0;
	Quadcopter quad;
	ArrayList<Junction> graph = new ArrayList<Junction>();


	public Algo3(GUI gui) {
		this.gui = gui;
		this.quad=gui.getQuad();
	}

	public void navigate() {
		if(gui.getTime()<=300) {
			System.out.println("time is: " + gui.getTime());
			double right_dist=quad.getRightLidar().getCurrentDist();
			double left_dist=quad.getLeftLidar().getCurrentDist();
			double front_dist=quad.getFrontLidar().getCurrentDist();
			computeAndAddJunction(right_dist, left_dist, front_dist);
			
			last_right_dist = right_dist;
			last_left_dist = left_dist;
			if(quad.getVelocity()<=front_dist/1.2 && front_dist>=0.35) {
				if(front_dist<right_dist )
					quad.turnRight(1.0);
				else if(front_dist<left_dist )
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
					quad.turnRight(0.5);
				else if(right_dist<left_dist)
					quad.turnLeft(0.5);
			}
			else
				alert();

			System.out.println("v: " + quad.getVelocity());
			System.out.println("d: " + front_dist);
			System.out.println("jcts: " + graph.size());
			gui.drive();
		}
	}

	private void goTonextJunction() {
		System.out.println("trying get junction: " + (graph.size()-1));
		Junction junc=graph.get(graph.size()-1);
		double ang=quad.getPosition().getAngle(junc.getCoordinate());
		if(quad.getAngle()-ang>30)
			quad.turnLeft(1.0);
		else if(ang-quad.getAngle()>30)
			quad.turnRight(1.0);
		if(junc.isInJunction(quad.getPosition()) && graph.size()-1>0)
			this.graph.remove(graph.size()-1);	
		
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

	private void alert() {
		int RL=(int)(Math.random()*2.0);
		while(gui.getTime()<=300 && quad.getFrontLidar().getCurrentDist()<=1.0)
		{
			//			double right_dist=quad.getRightLidar().getCurrentDist();
			//			double left_dist=quad.getLeftLidar().getCurrentDist();
			//			double front_dist=quad.getFrontLidar().getCurrentDist();

			quad.setAccAndVelocity(-quad.getVelocity()/gui.getStepTime());
			if(quad.getVelocity()<0)
				quad.setVelocity(0);
			if(RL==1) 
				quad.turnRight(1.0);
			else 
				quad.turnLeft(1.0);

			gui.drive();
		}

	}
	private void computeAndAddJunction(double right_dist, double left_dist,double front_dist) {
		boolean is_right_jct=false, is_left_jct= false;
		if(right_dist-last_right_dist>=1.0) {
			is_right_jct =true;
			System.out.println("RIGHT JCT!");
		}
		if(left_dist-last_left_dist>=1.0) {
			is_left_jct =true;
			System.out.println("LEFT JCT!");
		}
		

		if(is_left_jct||is_right_jct) {
			boolean addJunction = true;
			for (Junction jct : graph) 
			{

				if(quad.getPosition().isWithinRadius(1.5, jct.getCoordinate(), this.quad.getResolution())) 
				{
					addJunction = false;
				}
			}
			if (addJunction) {
				if(is_right_jct) {
					double di= (right_dist*Math.cos(quad.getRightAngle())+last_right_dist*Math.cos(quad.getRightAngle()))/2;
					Coordinate junction = new Coordinate((int) (quad.getPosition().x + di*quad.getResolution()*Math.sin(Math.toRadians(quad.getAngle()))),(int) (quad.getPosition().y + di*quad.getResolution()*Math.cos(Math.toRadians(quad.getAngle()))));
					graph.add(new Junction(junction,is_right_jct,is_left_jct));
					gui.addJunction(junction.x, junction.y, 4);
				}
				else {
					double di= (left_dist*Math.cos(quad.getRightAngle())+last_left_dist*Math.cos(quad.getRightAngle()))/2;
					Coordinate junction = new Coordinate((int) (quad.getPosition().x + di*quad.getResolution()*Math.sin(Math.toRadians(quad.getAngle()))),(int) (quad.getPosition().y + di*quad.getResolution()*Math.cos(Math.toRadians(quad.getAngle()))));
					graph.add(new Junction(junction,is_right_jct,is_left_jct));
					gui.addJunction(junction.x, junction.y, 4);
				}
			}
		}
	}
}
