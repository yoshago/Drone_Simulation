package Objects;

import java.util.ArrayList;
import java.util.List;

public class Junction {
	private int x;
	private int y;
	List<Integer> path=new ArrayList<Integer>();
	
	public Junction() {
		this.x=0;
		this.y=0;
	}
	public Junction(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public Junction(Junction another) {
		this.x=another.x;
		this.y=another.y;
	}
	
	public boolean isInJunction(Coordinate coor) {
		double dist=Math.sqrt((coor.x-this.x)*(coor.x-this.x) + (coor.y-this.y)*(coor.y-this.y));
		if(dist<=20)
			return true;
		return false;
	}
	
	
}
