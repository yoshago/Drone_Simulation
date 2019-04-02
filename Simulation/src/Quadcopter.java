import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Quadcopter {
	private Coordinate position;
	private Lidar front;
	private Lidar right;
	private Lidar left;
	private Lidar back;
	private OF of;
	private Yaw yaw;
	private GUI gui;
	private int angle;
	private RoomMap background;
	
	
	public Quadcopter(Lidar front, Lidar right, Lidar left, Lidar back, OF of, Yaw yaw, RoomMap bg)
	{
		this.front=front;
		this.right=right;  
		this.left=left;
		this.back=back;
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
		this.front=new Lidar(lidar_dist,0, this);
		this.right=new Lidar(lidar_dist,60, this);
		this.left=new Lidar(lidar_dist,-55, this);
		Thread t1 =new Thread(this.front);  
		t1.start(); 
		Thread t2 =new Thread(this.right);  
		t2.start(); 
		Thread t3 =new Thread(this.left);  
		t3.start(); 
	}
	
	
	public void start()
	{
		computeWay();
	}

	public void manualStart() {
		KeyListener1 key = new KeyListener1(this,gui);
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

}
