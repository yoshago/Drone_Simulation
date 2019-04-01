import java.io.File;
import java.util.Arrays;

public class main {

	public static void main(String[] args) {
		File pngFile = new File("1.png");
		RoomMap map = new RoomMap(pngFile);
		for(int i=0; i<map.width; i++) {
			  for (int j=0; j<map.height; j++) {
				  System.out.print((int)map.mapMatrix[i][j] + " ,");
			  }
			  System.out.println();
		}
		
	}

}
