package at.inted.Connection;

public class Car {

	public static enum  CarStatus{
		readyForConnection (200),
		Connected (300),
		ServerError (400);


		private int status;
		private CarStatus(int status){
			this.status = status;
		}

		public int getStatus() {
			return status;
		}
	}

	public static void setSpeed(double speed) {
		Car.speed = speed;
	}

	public static void setAngle(double angle) {
		Car.angle = angle;
	}

	private static CarStatus status;

	private static RemoteControl rc;

	private static double speed = 0;
	private static double angle = 0;

	public static CarStatus getStatus() {
		return status;
	}

	public static void setStatus(CarStatus status) {
		Car.status = status;
	}

	public static void setDiffangle (double diffangle){

		angle += diffangle;
	}

	public static void setDiffspeed (double diffspeed){

		speed += diffspeed;
	}
}

