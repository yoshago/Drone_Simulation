package Objects;

public class Step {
	public enum stepType{
		TURN,
		DRIVE
	}
	public stepType type;
	public double distance;
	public int angle;
	public Step(int angle) {
		this.type = stepType.TURN;
		this.angle = angle;
	}
	public Step(double distance) {
		this.distance = distance;
		this.type = stepType.DRIVE;
	}
}
