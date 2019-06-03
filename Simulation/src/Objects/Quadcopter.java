package Objects;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import Algorithms.NavigationAlgorithm;
import GUI.GUI;
import Sensors.Lidar;

public class Quadcopter {
	private Coordinate position;
	private Lidar frontLidar;
	private Lidar rightLidar;
	private Lidar leftLidar;
	private Lidar backLidar;
	private int rightAngle;
	private int leftAngle;
	private int resolution;
	private double lidarError = 0.05;
	//	private OF of;
	//	private Yaw yaw;
	private GUI gui;
	private int angle;
	private int lidar_dist;
	private RoomMap background;
	private NavigationAlgorithm algo;
	double velocity;
	double acc;



	public Quadcopter(RoomMap bg, int lidar_dist,Coordinate start_position, GUI gui)
	{
		this.angle = 90;
		this.position = start_position;
		this.background=bg;
		this.gui = gui;
		addLidars();
	}

	public Quadcopter(RoomMap bg, int lidar_dist,Coordinate start_position, int rightAngle, int leftAngle, int resolution)
	{
		this.angle = 90;
		this.position = start_position;
		this.background=bg;
		this.lidar_dist = lidar_dist;
		this. rightAngle = rightAngle;
		this.leftAngle = leftAngle;
		this.resolution = resolution;
	}

	public void addLidars() {
		this.frontLidar=new Lidar(lidar_dist,0, this,lidarError);
		this.rightLidar=new Lidar(lidar_dist,rightAngle, this, lidarError);
		this.leftLidar=new Lidar(lidar_dist,leftAngle, this, lidarError);
		/**Thread t1 =new Thread(this.frontLidar);  
		t1.start(); 
		Thread t2 =new Thread(this.rightLidar);  
		t2.start(); 
		Thread t3 =new Thread(this.leftLidar);  
		t3.start(); **/
	}


	public Coordinate getPosition() {
		return position;
	}
	public GUI getGui() {
		return gui;
	}
	public int getAngle() {
		return angle;
	}
	public RoomMap getBackground() {
		return background;
	}
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public Lidar getFrontLidar() {
		return frontLidar;
	}

	public Lidar getLeftLidar() {
		return leftLidar;
	}

	public Lidar getRightLidar() {
		return rightLidar;
	}

	public Lidar getBackLidar() {
		return backLidar;
	}
	public NavigationAlgorithm getAlgo() {
		return algo;
	}


	public void setAlgo(NavigationAlgorithm algo) {
		this.algo = algo;
	}

	public Coordinate isLegalPosition(Coordinate position) {
		int i=1;
		Coordinate coor = new Coordinate((int) (gui.getRealquadposition().x + i*Math.sin(angle)), (int) (gui.getRealquadposition().y + i*Math.cos(angle)));
		while(!coor.equals(position)) {
			coor = new Coordinate((int) (gui.getRealquadposition().x + i*Math.sin(angle)), (int) (gui.getRealquadposition().y + i*Math.cos(angle)));
			if(background.checkCoordinate(coor) ==0 ) return coor;
			i++;

		}
		
		return new Coordinate(-1,-1);
	}

	public int getRightAngle() {
		return rightAngle;
	}

	public int getLeftAngle() {
		return leftAngle;
	}

	public int getResolution() {
		// TODO Auto-generated method stub
		return resolution;
	}
	public double getVelocity() {
		return velocity;
	}

	public void setAccAndVelocity(double a) {
		this.acc=a;
		this.velocity+=a*gui.getStepTime();
		if(this.velocity>2) this.velocity=2.0;
		if(this.velocity<-2) this.velocity=-2.0;
		
	}

	public void turnRight(double d) {
		this.angle+=d*180.0*gui.getStepTime();
		
	}

	public void turnLeft(double d) {
		this.angle-=d*180.0*gui.getStepTime();		
	}

	public void setVelocity(double v) {
		this.velocity=v;
		
	}



}
