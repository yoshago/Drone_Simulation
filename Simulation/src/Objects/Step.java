package Objects;

public class Step {
	public enum stepType{
		TURN,
		DRIVE
	}
	
	private stepType type;
	private double distance;
	private int angle;
	
	public Step(int angle) {
		this.type = stepType.TURN;
		this.angle = angle;
	}
	
	public Step(double distance) {
		this.distance = distance;
		this.type = stepType.DRIVE;
	}
	
	public stepType getType() {
		return this.type;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public int getAngle() {
		return this.angle;
	}
}
