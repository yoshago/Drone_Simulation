package Objects;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RoomMap {
	private File mapSource;
	private Byte[][] mapMatrix;
	private int width, height;
	int pointRadius =2;
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
	public RoomMap(int width, int height) {
		this.width = width;
		this.height= height;
		mapMatrix = new Byte[width][height];
		for(int i=0; i<width; i++) {
			  for (int j=0; j<height; j++) {
					  mapMatrix[i][j]= 1;
					  if (i==0 ||j==0||i==width-1||j==height-1) mapMatrix[i][j]= 0;
			  }
		}
	}
	public int checkCoordinate (Coordinate co) {
		return  (int) mapMatrix[co.x][co.y];
	}
	public void exportMap(String filename) {
		try {
		    BufferedImage image = new BufferedImage(this.width,this.height,1);
		    for(int i=0; i<this.width; i++) {
		        for(int j=0; j<this.height; j++) {
		            int a = this.mapMatrix[i][j];
		            if(a==1) a = 255;
		            if(a==2) a = 133;
		            if(a==3)  {
		           
		            	for(int k=i-pointRadius; k<=i+pointRadius&&k>=0&&k< this.width; k++) {
		    		        for(int l=j-pointRadius; l<=j+pointRadius&&l<this.height&&l>=0; l++) {
		    		            Coordinate center = new Coordinate(i,j);
		    		            Coordinate inCircle = new Coordinate(k,l);
		    		            if (center.isWithinRadius(pointRadius, inCircle)) {
		    		            	Color newColor = new Color(255,0,0);
		    			            image.setRGB(k,l,newColor.getRGB());
		    		            }
		    		        }
		    		    }
		            }
		            Color newColor = new Color(a,a,a);
		            image.setRGB(i,j,newColor.getRGB());
		        }
		    }
		    File output = new File(filename +".jpg");
		    ImageIO.write(image, "jpg", output);
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File getMapSource() {
		return mapSource;
	}
	public void setMapSource(File mapSource) {
		this.mapSource = mapSource;
	}
	public Byte[][] getMapMatrix() {
		return mapMatrix;
	}
	public void setMapMatrix(Byte[][] mapMatrix) {
		this.mapMatrix = mapMatrix;
	}
	public void setMapMatrixCoordinate(int x, int y, int value) {
		this.mapMatrix[x][y] = (byte) value;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
			  
	
	
}
