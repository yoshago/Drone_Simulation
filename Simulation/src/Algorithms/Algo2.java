package Algorithms;

import java.util.ArrayList;
import java.util.List;

import GUI.GUI;
import Objects.Coordinate;
import Objects.Junction;
import Objects.Quadcopter;

public class Algo2 extends NavigationAlgorithm{
	
	Quadcopter quad;
	double lastRight;
	double lastLeft;
	List<Junction> junc_list;
	
	public Algo2(GUI gui) {
		this.gui = gui;
		this.quad=gui.getQuad();
		this.lastLeft=0;
		this.lastRight=0;
		this.junc_list=new ArrayList<Junction>();
	}

	public void navigate() {
		if(gui.getTime()<=300) {
			System.out.println("time is: " + gui.getTime());
			
			double right_dist=quad.getRightLidar().getCurrentDist();
			double left_dist=quad.getLeftLidar().getCurrentDist();
			double front_dist=quad.getFrontLidar().getCurrentDist();
			Junction junc=computeJunction(right_dist, left_dist, front_dist);
			addJunction(junc);
			
				
			if(quad.getVelocity()<=front_dist/1.2 && front_dist>=0.35) {
				if(front_dist<right_dist)
					quad.turnRight(0.75);
				else if(front_dist<left_dist)
					quad.turnLeft(0.75);
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
			gui.drive();
		}
//		else System.out.println("list contains: "+junc_list.size());
		
		
		
	}

	private void goTo(Junction junction) {
		double ang=quad.getPosition().getAngle(junction.getCoordinate());
		if(quad.getAngle()-ang>120)
			quad.turnLeft(1.0);
		else if(ang-quad.getAngle()>120)
			quad.turnRight(1.0);
		if(junction.isInJunction(quad.getPosition()) && junc_list.size()-1>0)
			this.junc_list.remove(junc_list.size()-1);
			
		
}

	private boolean addJunction(Junction junc) {
		boolean flag=true;
		if(junc.getCoordinate().x!=0 && junc.getCoordinate().y!=0) {
			for(int i=0; i<junc_list.size() && flag;i++) {
				if(junc_list.get(i).isInJunction(junc.getCoordinate()))
					flag=false;
			}
			if(flag) {
				junc_list.add(junc);
				System.out.println("x: " + junc.getCoordinate().x+ "   y: " + junc.getCoordinate().y);
				
			}
		}
		return flag;
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
	public Junction computeJunction(double right_dist, double left_dist,double front_dist) {
		
		Coordinate junc_coor=new Coordinate(0, 0);
		boolean right=false;
		boolean left=false;
		boolean front=false;
		if(right_dist-lastRight>=0.5)
			right=true;
		if(left_dist-lastLeft>=0.5)
			left=true;
		if(front_dist>=3.0)
			front=true;
		if(right) {
			double junc_dist = (right_dist*Math.cos(Math.toRadians(quad.getRightAngle()))-lastRight*Math.cos(Math.toRadians(quad.getRightAngle())))/2.0;
			junc_coor=new Coordinate((int)(0.5+quad.getPosition().x+junc_dist*Math.sin(Math.toRadians(quad.getAngle()))),
					(int)( 0.5+quad.getPosition().y+junc_dist*Math.cos(Math.toRadians(quad.getAngle()))));
		}
		else if(left) {
			double junc_dist = (left_dist*Math.cos(Math.toRadians(quad.getLeftAngle()))-lastLeft*Math.cos(Math.toRadians(quad.getLeftAngle())))/2.0;
			junc_coor=new Coordinate((int)(0.5+quad.getPosition().x+junc_dist*Math.sin(Math.toRadians(quad.getAngle()))),
					(int)( 0.5+quad.getPosition().y+junc_dist*Math.cos(Math.toRadians(quad.getAngle()))));
		}
		Junction junc=new Junction(junc_coor);
		return junc;
	}
}
