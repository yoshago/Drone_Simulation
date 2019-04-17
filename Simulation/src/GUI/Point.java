package GUI;
import java.awt.Color;


public class Point{
	final int x; 
	final int y;
	final int radius;
	final Color color;

	public Point(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	} 
	
	@Override
	public boolean equals(Object o) { 

		// If the object is compared with itself then return true   
		if (o == this) { 
			return true; 
		} 

		/* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
		if (!(o instanceof Point)) { 
			return false; 
		} 

		// typecast o to Complex so that we can compare data members  
		Point c = (Point) o; 

		// Compare the data members and return accordingly  
		return Integer.compare(x, c.x) == 0
				&& Integer.compare(y, c.y) == 0; 
	} 


}