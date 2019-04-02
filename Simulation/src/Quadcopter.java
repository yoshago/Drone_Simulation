
public class Quadcopter {
	private Lidar front;
	private Lidar right;
	private Lidar left;
	private Lidar back;
	private OF of;
	private Yaw yaw;
	private RoomMap simMap;
	private int angle;
	RoomMap background;
	
	
	public Quadcopter(Lidar front, Lidar right, Lidar left, Lidar back, OF of, Yaw yaw, RoomMap bg)
	{
		this.front=front;
		this.right=right;  
		this.left=left;
		this.back=back;
		this.of=of;
		this.yaw=yaw;
		this.simMap=new RoomMap(bg.getWidth(), bg.getHeight());
		this.background=bg;
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
	
	private void rotate()
	{
		for(int i=0 ; i<360 ; i+=10)
		{
			
		}
	}
	

}
