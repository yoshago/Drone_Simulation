
public class Coordinate implements Comparable<Coordinate>{
	int x, y;
	Coordinate(int x, int y){
		this.x =x;
		this.y =y;
	}
	
	public int compareTo(Coordinate co){  
		if(x==co.x  && y ==co.y)
			return 0;  
		else if(x>co.x)  
		return 1;  
		else  
		return -1;  
		} 
	public double distance(Coordinate co) {
		return Math.sqrt((co.y - y) * (co.y - y) + (co.x - x) * (co.x - x));
	}

	public boolean isWithinRadius(double pointRadius, Coordinate inCircle) {
		if (this.distance(inCircle)<=20*pointRadius) return true;
		return false;
	}
}
