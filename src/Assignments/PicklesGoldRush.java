package demos;

import java.io.IOException;
import java.util.Random;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class PicklesGoldRush extends IRobotAdapter {
	// Sonar sonar = new Sonar();
	Random turn = new Random();
	Random back = new Random();
	Random go = new Random();

	public PicklesGoldRush (IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		PicklesGoldRush rob = new PicklesGoldRush(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();
	}

	private void setup() throws Exception {

	}

	private boolean loop() throws Exception {
		readSensors(100); // <--This reads all of the robot's sensors
		// int[] lightBumpReadings = getLightBumps();
		// this must be called at the top of loop if you are using the sensors
		if (isBumpLeft() || isBumpRight()) {
			driveDirect(turn.nextInt(501), -turn.nextInt(501));
			sleep(turn.nextInt(501));
			
		}
		driveDirect(go.nextInt(501), go.nextInt(501));
		sleep(1000);
		driveDirect(turn.nextInt(501), -turn.nextInt(501));
		sleep(turn.nextInt(501));
		
		
		if(getInfraredByteLeft() > 0){
			driveDirect(-500,500);
			sleep(1);
		}else if(getInfraredByteRight() > 0){
			driveDirect(500,-500);
			sleep(1);
		}else if(getInfraredByte() > 0){
			driveDirect(500,500);
		}
		
		/*
		 * if (lightBumpReadings[3] > 100) { driveDirect(-200, 250); sleep(100);
		 * System.out.println("Light triggered"); } if (lightBumpReadings[4] >
		 * 100) { driveDirect(-200, 250); sleep(100);
		 * System.out.println("Light triggered"); } if (lightBumpReadings[5] >
		 * 100) { driveDirect(-200, 250); sleep(100);
		 * System.out.println("Light triggered"); }
		 */
		//eturn true if they have been bumped
		// since the last call to readSensors
		// there is not an isBumpCenter() method so you have to check left and
		// right
				return true;
	}

	private void sleep(int amt) {
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}