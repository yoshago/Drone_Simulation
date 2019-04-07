package GUI;
import java.awt.Color;


public class Line{
	final int x1; 
	final int y1;
	final int x2;
	final int y2;   
	final Color color;

	public Line(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
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
		if (!(o instanceof Line)) { 
			return false; 
		} 

		// typecast o to Complex so that we can compare data members  
		Line c = (Line) o; 

		// Compare the data members and return accordingly  
		return Integer.compare(x1, c.x1) == 0
				&& Integer.compare(y1, c.y1) == 0
				&&Integer.compare(x2, c.x2)== 0
				&&Integer.compare(y2, c.y2)== 0; 
	} 
}