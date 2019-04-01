import java.util.ArrayList;

public class Lidar {
	int angle;
	int hz;
	int dist;
	public Lidar(int dist,int angle) {
		this.dist = dist;
		this.angle = angle;
	}
	public double rayShoot(Coordinate start, int droneAngle, RoomMap backgroundMap, RoomMap simulationMap)
	{
		ArrayList<Coordinate> cordList = new ArrayList<Coordinate>();
		int sumAng=(droneAngle+this.angle)%360;
		double finalAng = Math.toRadians(sumAng);
		Coordinate addingCoor = new Coordinate(start.x,start.y);
		cordList.add(addingCoor);
		int x,y;
		int i=1;
		while(checkCoordinate(addingCoor, backgroundMap)) 
		{
			x = addingCoor.x;
			y = addingCoor.y;
			if (cordList.get(cordList.size()-1)!=(addingCoor))
				cordList.add(addingCoor);
			if (addingCoor.distance(start)>=20*dist) break;
			addingCoor = new Coordinate((int) (start.x + i*Math.sin(finalAng)),(int) (start.y + i*Math.cos(finalAng)));
			i+=1;
		}
		cordList.forEach(coor -> simulationMap.setMapMatrixCoordinate(coor.x, coor.y, 2));	
		simulationMap.setMapMatrixCoordinate(addingCoor.x, addingCoor.y, 3);
		return addingCoor.distance(start)/20;
		
	}

	private boolean checkCoordinate(Coordinate co, RoomMap map) {
		if (map.getMapMatrix()[co.x][co.y] == 1) return true;
		return false;
	}
	
	
}
