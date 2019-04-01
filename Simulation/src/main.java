import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class main {

	public static void main(String[] args) {
		Coordinate start = new Coordinate(60,60);
		File pngFile = new File("2.png");
		RoomMap backgroundMap = new RoomMap(pngFile);
		RoomMap simulationMap = new RoomMap(backgroundMap.getWidth(), backgroundMap.getHeight());
		System.out.println(backgroundMap.getHeight() + "    ," + backgroundMap.getWidth()  + "\n" +(int) backgroundMap.getMapMatrix()[60][60]);
		Lidar right = new Lidar(3, 60);
		double distance = right.rayShoot(start, 90, backgroundMap,simulationMap);
		for(int i=0; i<backgroundMap.getWidth(); i++) {
			  for (int j=0; j<backgroundMap.getHeight(); j++) {
				  System.out.print((int)backgroundMap.getMapMatrix()[i][j] + " ,");
			  }
			  System.out.println();
		}
		simulationMap.exportMap("test1");
	}
}
