package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class PicklesMazeCode extends IRobotAdapter {
	// Sonar sonar = new Sonar();
	int counter = 0;
	Camera cam;

	public PicklesMazeCode (IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		PicklesMazeCode rob = new PicklesMazeCode(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();
	}
double red;
double green;
	private void setup() throws Exception {
		
		// SETUP CODE GOES HERE!!!!!
		cam = new Camera(50, 50);
		Thread picTake = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					cam.takeRGBPicture();
					cam.setTimeout(10);
					 green = cam.getGreenPercentage(10, false);
					 red = cam.getRedPercentage(10, false);
					System.out.println("red = " + red);
					System.out.println("green = " + green);

				}
			}
		});

	}

	private boolean loop() throws Exception {
		// LOOP CODE GOES HERE!!!!!
		readSensors(100);
		int[] lightBumpReadings = getLightBumps();
		driveDirect(680, 240);
		 if (red > 40) {
		 driveDirect(180, -180);
		 sleep(1000);
		 }
		if (green > 40) {
			driveDirect(500, -500);
			sleep(500);
			driveDirect(-500, 500);
			sleep(500);
			driveDirect(0, 0);
		}
		// }
		if (lightBumpReadings[3] > 100) {
			driveDirect(-200, 250);
			sleep(100);
			System.out.println("Light triggered");
		}
		if (lightBumpReadings[4] > 100) {
			driveDirect(-200, 250);
			sleep(100);
			System.out.println("Light triggered");
		}
		if (lightBumpReadings[5] > 100) {
			driveDirect(-200, 250);
			sleep(100);
			System.out.println("Light triggered");
		}
		// System.out.println("Light " + lightBumpReadings);
		if (isBumpLeft() || isBumpRight()) {
			driveDirect(-260, -250);
			sleep(500);
			System.out.println("bump triggered");
		}
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
