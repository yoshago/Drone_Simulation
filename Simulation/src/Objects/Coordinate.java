package Objects;

public class Coordinate implements Comparable<Coordinate>{
	private int x, y;
	public Coordinate(int x, int y){
		this.x =x;
		this.y =y;
	}
	
	public int compareTo(Coordinate co){  
		if(this.x==co.x  && this.y == co.y)
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
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Coordinate)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Coordinate c = (Coordinate) o; 
          
        // Compare the data members and return accordingly  
        return Integer.compare(x, c.x) == 0
                && Integer.compare(y, c.y) == 0; 
    } 
}
