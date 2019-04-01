import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RoomMap {
	File mapSource;
	Byte[][] mapMatrix;
	int width, height;
	public RoomMap(File mapSource) {
		super();
		this.mapSource = mapSource;
		BufferedImage buffImg = null;
		try {
			buffImg = ImageIO.read(mapSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		height = buffImg.getHeight();
		width = buffImg.getWidth();
		mapMatrix = new Byte[width][height];
		for(int i=0; i<width; i++) {
			  for (int j=0; j<height; j++) {
				  int rgb = buffImg.getRGB(i, j);
				  if (((rgb>>16)&255)==0) {
					  mapMatrix[i][j]= 0;
				  }
				  else mapMatrix[i][j] = 1;
			  }
		}
	}
	
			  
	
	
}
