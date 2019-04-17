package Sensors;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;

import GUI.GUI;
import Objects.Coordinate;
import Objects.Quadcopter;
import Objects.RoomMap;

public class Lidar implements Runnable{
	private int offset_angle;
	private int hz;
	private int dist;
	private Quadcopter father;
	private double currentDist;
	private GUI gui; 
	private double gaussianError;
	public Lidar(int dist,  int offset_angle, Quadcopter father,double error) {
		this.dist = dist;
		this.offset_angle = offset_angle;
		this.father = father;
		this.gui = father.getGui();
		gaussianError = error;

	}
	
	public void run() {
		while(true) {
			currentDist = rayShoot(father.getPosition(),father.getBackground());
		}
	}
	private double rayShoot(Coordinate start, RoomMap backgroundMap)
	{
		 
		double sumAng=(father.getAngle()+this.offset_angle)%360;
		double finalAng = Math.toRadians(sumAng);
		Coordinate finalCoor = new Coordinate(start.x,start.y);
		int x,y;
		int i=1;
		boolean isWallAtTheEndOfTheRay = true;
		while(checkCoordinate(finalCoor, backgroundMap)) 
		{
			x = finalCoor.x;
			y = finalCoor.y;
			if (finalCoor.distance(start)>=father.getResolution()*dist) {
				isWallAtTheEndOfTheRay = false;
				break;
			}
			finalCoor = new Coordinate((int) (start.x + i*Math.sin(finalAng)),(int) (start.y + i*Math.cos(finalAng)));
			i+=1;
		}

		gui.addLine(start.x, start.y, finalCoor.x, finalCoor.y);
		if(isWallAtTheEndOfTheRay) gui.addPoint(finalCoor.x, finalCoor.y, 4);
		double retVal = finalCoor.distance(start)/(double)father.getResolution();
//		java.util.Random r = new java.util.Random();
//		retVal = r.nextGaussian() * gaussianError +  retVal;
		return retVal;

	}
	

	public double getCurrentDist() {
		currentDist = rayShoot(father.getPosition(),father.getBackground());
		return currentDist;
	}

	private boolean checkCoordinate(Coordinate co, RoomMap map) {
		if (map.getMapMatrix()[co.x][co.y] == 1) return true;
		return false;
	}
	public int getOffset_angle() {
		return offset_angle;
	}



}
