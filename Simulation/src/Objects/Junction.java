package Objects;

import java.util.ArrayList;
import java.util.List;

public class Junction {
	private Coordinate coor;
	boolean is_right_jct, is_left_jct;
	List<Integer> path=new ArrayList<Integer>();
	
	public Junction() {
		this.coor.x=0;
		this.coor.y=0;
	}
	public Junction(Coordinate coor) {
		this.coor=coor;
	}
	public Junction(Junction another) {
		this.coor=another.coor;
	}
	     
	
	public Junction(Coordinate jct, boolean is_right_jct, boolean is_left_jct) {
		coor = jct;
		this.is_right_jct = is_right_jct;
		this.is_left_jct = is_left_jct;
		
	}
	public boolean isInJunction(Coordinate coor) {
		if(this.coor.distance(coor)<=20.0)
			return true;
		return false;
	}
	public Coordinate getCoordinate() {
		return this.coor;
	}
	
	
}
