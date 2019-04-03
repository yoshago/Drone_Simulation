import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;

public class Lidar implements Runnable{
	private int offset_angle;
	private int hz;
	private int dist;
	private Quadcopter father;
	private double currentDist;
	private GUI gui; 
	public Lidar(int dist,  int offset_angle, Quadcopter father) {
		this.dist = dist;
		this.offset_angle = offset_angle;
		this.father = father;
		this.gui = father.getGui();

	}
	
	public void run() {
		while(true) {
			currentDist = rayShoot(father.getPosition(),father.getBackground());
			try {
				TimeUnit.MILLISECONDS.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private double rayShoot(Coordinate start, RoomMap backgroundMap)
	{
		 
		int sumAng=(father.getAngle()+this.offset_angle)%360;
		double finalAng = Math.toRadians(sumAng);
		Coordinate finalCoor = new Coordinate(start.x,start.y);
		int x,y;
		int i=1;
		while(checkCoordinate(finalCoor, backgroundMap)) 
		{
			x = finalCoor.x;
			y = finalCoor.y;
			if (finalCoor.distance(start)>=20*dist) break;
			finalCoor = new Coordinate((int) (start.x + i*Math.sin(finalAng)),(int) (start.y + i*Math.cos(finalAng)));
			i+=1;
		}

		gui.addLine(start.x, start.y, finalCoor.x, finalCoor.y);
		gui.addPoint(finalCoor.x, finalCoor.y, 4);
		return finalCoor.distance(start)/20;

	}

	private boolean checkCoordinate(Coordinate co, RoomMap map) {
		if (map.getMapMatrix()[co.x][co.y] == 1) return true;
		return false;
	}
	public int getOffset_angle() {
		return offset_angle;
	}



}
