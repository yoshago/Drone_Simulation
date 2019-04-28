package Objects;

import java.util.ArrayList;
import java.util.List;

public class Junction {
	private Coordinate coor;
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
	     
	public boolean isInJunction(Coordinate coor) {
		if(this.coor.distance(coor)>=20.0)
			return true;
		return false;
	}
	
	
}
