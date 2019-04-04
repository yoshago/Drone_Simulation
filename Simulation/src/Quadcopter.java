import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Quadcopter {
	private Coordinate position;
	private Lidar frontLidar;
	private Lidar rightLidar;
	private Lidar leftLidar;
	private Lidar backLidar;
	private OF of;
	private Yaw yaw;
	private GUI gui;
	private int angle;
	private int lidar_dist;
	private RoomMap background;
	
	
	public Quadcopter(Lidar front, Lidar right, Lidar left, Lidar back, OF of, Yaw yaw, RoomMap bg)
	{
		this.frontLidar=front;
		this.rightLidar=right;  
		this.leftLidar=left;
		this.backLidar=back;
		this.of=of;
		this.yaw=yaw;
		this.background=bg;
	}
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
		double rightDist = rightLidar.getCurrentDist();
		double leftDist = leftLidar.getCurrentDist();
		double frontDist = frontLidar.getCurrentDist();
		
		
		
	}
	public void start()
	{
		computeWay();
	}

	
	

	private int computeWay()
	{
		int angTogo=0;
		rotate();
		
		return angTogo;
		
	}
	
	public void rotate()
	{
		for(int i=0 ; i<360 ; i+=10)
		{
			
			this.angle =(this.angle+i)%360;
	
			try {
				TimeUnit.MILLISECONDS.sleep(14);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
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
	public boolean isLegalPosition(Coordinate position) {
		if(background.checkCoordinate(position) == 1) return true;
		return false;
	}
	

}
