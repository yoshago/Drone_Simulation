import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Quadcopter {
	private Coordinate position;
	private Lidar frontLidar;
	private Lidar rightLidar;
	private Lidar leftLidar;
	private Lidar backLidar;
//	private OF of;
//	private Yaw yaw;
	private GUI gui;
	private int angle;
	private int lidar_dist;
	private RoomMap background;
	
	
	
	public Quadcopter(RoomMap bg, int lidar_dist,Coordinate start_position, GUI gui)
	{
		this.angle = 90;
		this.position = start_position;
		this.background=bg;
		this.gui = gui;
		addLidars();
	}
	
	public Quadcopter(RoomMap bg, int lidar_dist,Coordinate start_position)
	{
		this.angle = 90;
		this.position = start_position;
		this.background=bg;
		this.lidar_dist = lidar_dist;
	}
	
	public void addLidars() {
		this.frontLidar=new Lidar(lidar_dist,0, this);
		this.rightLidar=new Lidar(lidar_dist,60, this);
		this.leftLidar=new Lidar(lidar_dist,-55, this);
		Thread t1 =new Thread(this.frontLidar);  
		t1.start(); 
		Thread t2 =new Thread(this.rightLidar);  
		t2.start(); 
		Thread t3 =new Thread(this.leftLidar);  
		t3.start(); 
	}
	
	public void navigationAlgorithm() {
		
//		HashMap<Integer, Double> anglesAndDistances = rotate();
		double rightDist = rightLidar.getCurrentDist();
		double leftDist = leftLidar.getCurrentDist();
		double frontDist = frontLidar.getCurrentDist();
		
		
		
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

	public boolean isLegalPosition(Coordinate position) {
		if(background.checkCoordinate(position) == 1) return true;
		return false;
	}
	
	

}
